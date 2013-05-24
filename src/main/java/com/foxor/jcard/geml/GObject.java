package com.foxor.jcard.geml;

import java.util.HashMap;
import java.util.Map;

public abstract class GObject extends Expression {
    protected Map<String, Expression> eventListeners;
    
    protected String id;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GObject() {
        eventListeners = new HashMap<String, Expression>();
    }
    
    public void addEventListener(String event, Expression callback) {
        eventListeners.put(event, callback);
    }
    
    public void triggerEvent(String event, Machine m) throws Exception {
        if (eventListeners.containsKey(event)) {
            GObject context = m.getContext();
            m.setContext(this);
            eventListeners.get(event).execute(m);
            m.setContext(context);
        }
    }
    
    @Override
    public Expression execute(Machine m) throws Exception {
        m.addObject(this);
        return this;
    }
}