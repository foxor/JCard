package com.foxor.jcard.geml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import org.yaml.snakeyaml.Yaml;

import com.foxor.jcard.geml.expressions.Ply;
import com.foxor.jcard.geml.expressions.Server;

public class Machine {
    
    private class Frame {
        public GObject context;
    
        public Map<String, GObject> locals;

        public Frame(GObject context, Map<String, GObject> locals) {
            super();
            this.context = context;
            this.locals = locals;
        }
    }
    
    public Map<String, GObject> globals;
        
    protected Stack<Frame> frames;
    
    protected List<Expression> outgoingMessages;
    
    /**
     * We only want to send messages that are new to the client.
     * The client is stateful, and remembers the last turns, but the server is not.
     * Therefore, we replay the whole game, but we don't echo any of the messages until the last ply
     */
    protected boolean sendMessages;
    
    protected Yaml yaml;
    
    protected Random rng;
    
    public Machine() {
        outgoingMessages = new ArrayList<Expression>();
        frames = new Stack<Machine.Frame>();
        frames.push(new Frame(null, new HashMap<String, GObject>()));
        globals = new HashMap<String, GObject>();
        yaml = new Yaml();
        rng = new Random(0);
    }
    
    private static String GemlToYaml(String geml) {
        return geml.replaceAll("!!", "!!com.foxor.jcard.geml.expressions.");
    }
    
    private static String YamlToGeml(String yaml) {
        return yaml.replaceAll("!!com.foxor.jcard.geml.expressions.", "!!");
    }
    
    public void addMessage(Expression message) {
        if (sendMessages) {
            outgoingMessages.add(message);
        }
    }
    
    public void addGlobal(GObject object) {
        globals.put(object.getId(), object);
    }
    
    public Collection<GObject> getGlobals() {
        return globals.values();
    }
    
    public GObject getGlobal(String id) {
        return globals.get(id);
    }
    
    public GObject getContext() {
        return frames.peek().context;
    }
    
    public void setContext(GObject context) {
        frames.peek().context = context;
    }
    
    public void pushFrame() {
        frames.push(new Frame(getContext(), new HashMap<String, GObject>(frames.peek().locals)));
    }
    
    public void popFrame() {
        frames.pop();
    }
    
    public void addLocal(String name, GObject value) {
        frames.peek().locals.put(name, value);
    }
    
    public GObject getLocal(String name) {
        GObject local = frames.peek().locals.get(name);
        if (local == null) {
            return globals.get(name);
        }
        return local;
    }
    
    public void broadcastEvent(String event) throws Exception {
        for (GObject obj : getGlobals()) {
            obj.triggerEvent(event, this);
        }
    }
    
    public void addRandomness(long val) {
        rng.setSeed(rng.nextLong() ^ val);
    }
    
    public int randomInt(int max) {
        return rng.nextInt(max);
    }
    
    public GObject copy(GObject source) {
        return (GObject)yaml.load(yaml.dump(source));
    }
    
    @SuppressWarnings("unchecked")
    public String process(String geml) throws Exception {
        Map<String, Object> gemlLoaded = ((Map<String, Object>)yaml.load(GemlToYaml(geml)));
        List<Expression> rules = (List<Expression>)gemlLoaded.get("rules");
        List<Expression> turns = (List<Expression>)gemlLoaded.get("turns");
        List<Expression> messages = processGeml(rules, turns);
        for (int i = 0; i < messages.size(); i++) {
            Server message = new Server();
            // Currently, this includes much more information about the object than we need, 
            // and it is information that is accurate at the end of the processing,
            // rather than when the message was sent.
            // I'm not sure if that is a problem or not
            message.setAction(messages.get(i));
            messages.set(i, message);
        }
        messages.add(0, ((Ply)turns.get(turns.size() - 1)).getMessages().get(0));
        Ply response = new Ply();
        response.setMessages(messages);
        String yamlResponse = yaml.dump(response);
        return YamlToGeml(yamlResponse);
    }
    
    private List<Expression> processGeml(List<Expression> rules, List<Expression> turns) throws Exception {
        sendMessages = turns.size() == 0;
        for (Expression rule : rules) {
            rule.execute(this);
        }
        for (int i = 0; i < turns.size(); i++) {
            sendMessages = (i == turns.size() - 1);
            turns.get(i).execute(this);
        }
        return outgoingMessages;
    }
}