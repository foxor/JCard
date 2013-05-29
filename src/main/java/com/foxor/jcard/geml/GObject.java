package com.foxor.jcard.geml;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class GObject extends Expression {
    
    /**
     * The event listeners attached to this object.  These are not serializable, just created by the machine at runtime
     */
    protected Map<String, Expression[]> eventListeners;
    
    /**
     * The id of this object.
     * 
     * Sometimes it's not possible to generate YAML with the correct anchors since, it is part of a larger GEML document.
     * 
     * Ids are used on the client and server to retrieve specific objects.
     */
    protected String id;
    
    /**
     * A place to put game-specific metadata.
     * 
     * This data should be retrieveable from !!Property on client and server
     */
    protected Map<String, Expression> data;
    
    public Map<String, Expression> getData() {
        return data;
    }

    public void setData(Map<String, Expression> data) {
        this.data = data;
    }

    public String getId() {
        if (id == "") {
            setId(UUID.randomUUID().toString());
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public GObject() {
        eventListeners = new HashMap<String, Expression[]>();
    }
    
    public void addEventListener(String event, Machine m, Expression[] callback) {
        GObject target = m.getGlobal(getId());
        target.eventListeners.put(event, callback);
    }
    
    public void triggerEvent(String event, Machine m) throws Exception {
        // Since GObjects copy themselves into the machine environment, target may or may not be this, depending on how the object was dispatched.
        // If this message is dispatched directly from the client, it will most likely not be this
        GObject target = m.getGlobal(getId());
        if (target.eventListeners.containsKey(event)) {
            m.pushFrame();
            m.setContext(target);
            for (Expression e : target.eventListeners.get(event)) {
                e.execute(m);
            }
            m.popFrame();
        }
    }
    
    @Override
    public Expression execute(Machine m) throws Exception {
        // We might have already created ourselves, in which case we don't want another one
        GObject existing = m.getGlobal(getId());
        if (existing != null) {
            return existing;
        }
        // We never want to modify the GEML AST loaded from the source file
        // We copy this here, so the copy ends up as the GObject in memory, which will have a snapshot of our data
        GObject copy = m.copy(this);
        if (data != null) {
            for (String key : this.data.keySet()) {
                copy.data.put(key, this.data.get(key).execute(m));
            }
        }
        m.addGlobal(copy);
        return copy;
    }
}