package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.GObject;
import com.foxor.jcard.geml.Machine;

public class On extends Expression {
    protected Expression target;
    protected String event;
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
            ((GObject)target).addEventListener(event, callback);
        }
        target = oldTarget;
        return this;
    }
}