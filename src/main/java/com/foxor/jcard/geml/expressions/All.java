package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.GObject;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * All is a utility expression for map + filter on GObjects known to the machine
 * 
 * Server Only
 * 
 * @author ijames1
 *
 */
public class All extends Expression {
    /**
     * Filters GObjects to those that match !!ofClass in GEML
     */
    protected String ofClass;
    
    /**
     * A function to map across all matched expressions
     */
    protected Expression callback;

    public Expression getCallback() {
        return callback;
    }

    public void setCallback(Expression callback) {
        this.callback = callback;
    }

    public String getOfClass() {
        return ofClass;
    }

    public void setOfClass(String ofClass) {
        this.ofClass = ofClass;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        GObject oldContext = m.getContext();
        for (GObject obj : m.getObjects()) {
            if (ofClass != null && !obj.getClass().getName().endsWith(ofClass)) {
                continue;
            }
            m.setContext(obj);
            callback.execute(m);
        }
        m.setContext(oldContext);
        return this;
    }
}