package com.foxor.jcard.geml.expressions;

public class On extends Expression {
    protected String target;
    protected String event;
    protected Expression callback;
    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
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
}