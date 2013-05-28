package com.foxor.jcard.geml;

import java.util.HashMap;
import java.util.Map;

public abstract class GObject extends Expression {
    
    /**
     * The event listeners attached to this object.  These are not serializable, just created by the machine at runtime
     */
    protected Map<String, Expression> eventListeners;
    
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
            m.pushFrame();
            m.setContext(this);
            eventListeners.get(event).execute(m);
            m.popFrame();
        }
    }
    
    @Override
    public Expression execute(Machine m) throws Exception {
        if (data != null) {
            Map<String, Expression> localData = new HashMap<String, Expression>();
            for (String key : this.data.keySet()) {
                localData.put(key, this.data.get(key).execute(m));
            }
            this.data = localData;
        }
        m.addGlobal(this);
        return this;
    }
}