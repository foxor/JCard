package com.foxor.jcard.geml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.yaml.snakeyaml.Yaml;

public class Machine {
    private GObject context;
    
    private List<String> outgoingMessages;
    
    /**
     * We only want to send messages that are new to the client.
     * The client is stateful, and remembers the last turns, but the server is not.
     * Therefore, we replay the whole game, but we don't echo any of the messages until the last ply
     */
    private boolean sendMessages;
    
    private Set<GObject> objects;
    
    private Yaml yaml;
    
    public Machine() {
        outgoingMessages = new ArrayList<String>();
        objects = new HashSet<GObject>();
        yaml = new Yaml();
    }
    
    public void addMessage(Expression message) {
        if (sendMessages) {
            outgoingMessages.add(yaml.dump(message));
        }
    }
    
    public void addObject(GObject object) {
        objects.add(object);
    }
    
    public Set<GObject> getObjects() {
        return objects;
    }
    
    public GObject getContext() {
        return this.context;
    }
    
    public void setContext(GObject context) {
        this.context = context;
    }
    
    public void broadcastEvent(String event) throws Exception {
        for (GObject obj : getObjects()) {
            obj.triggerEvent(event, this);
        }
    }
    
    public List<String> process(List<Expression> rules, List<Expression> turns) throws Exception {
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