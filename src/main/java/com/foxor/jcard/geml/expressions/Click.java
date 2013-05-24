package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.GObject;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * Click is a message sent by the client and listened to by objects on the server
 * 
 * @author ijames1
 *
 */
public class Click extends Expression {
    public static final String EVENT_CODE = "Click";
    
    /**
     * The GObject that was clicked
     */
    protected GObject target;

    public GObject getTarget() {
        return target;
    }

    public void setTarget(GObject target) {
        this.target = target;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        target.triggerEvent(EVENT_CODE, m);
        return this;
    }
}