package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.GObject;
import com.foxor.jcard.geml.Machine;
/**
 * 
 * On creates event listeners on GObjects
 * 
 * Server only
 * 
 * @author ijames1
 *
 */
public class On extends Expression {
    
    /**
     * An expression that evaluates to the GObject on which to place the event listener
     */
    protected Expression target;
    
    /**
     * The literal string to listen to.  The !!X name of the message expression
     */
    protected String event;
    
    /**
     * The expression to call, with target as context
     */
    protected Expression callback;
    public Expression getTarget() {
        return target;
    }
    public void setTarget(Expression target) {
        this.target = target;
    }
    public String getEvent() {
        return event;
    }
    public void setEvent(String event) {
        this.event = event;
    }
    public Expression getCallback() {
        return callback;
    }
    public void setCallback(Expression callback) {
        this.callback = callback;
    }
    
    @Override
    public Expression execute(Machine m) throws Exception {
        Expression oldTarget = target;
        if (target == null) {
            target = m.getContext();
        }
        if (target != null && GObject.class.isAssignableFrom(target.getClass())) {
            ((GObject)target.execute(m)).addEventListener(event, callback);
        }
        target = oldTarget;
        return this;
    }
}