package com.foxor.jcard.geml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import org.yaml.snakeyaml.Yaml;

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
    
    protected List<String> outgoingMessages;
    
    /**
     * We only want to send messages that are new to the client.
     * The client is stateful, and remembers the last turns, but the server is not.
     * Therefore, we replay the whole game, but we don't echo any of the messages until the last ply
     */
    protected boolean sendMessages;
    
    protected Yaml yaml;
    
    protected Random rng;
    
    public Machine() {
        outgoingMessages = new ArrayList<String>();
        frames = new Stack<Machine.Frame>();
        frames.push(new Frame(null, new HashMap<String, GObject>()));
        globals = new HashMap<String, GObject>();
        yaml = new Yaml();
        rng = new Random(0);
    }
    
    public void addMessage(Expression message) {
        if (sendMessages) {
            outgoingMessages.add(yaml.dump(message));
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
    
    public List<String> process(List<Expression> rules, List<Expression> turns) throws Exception {
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