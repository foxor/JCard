package com.foxor.jcard.geml.expressions;

public class Server extends Expression {
    protected Expression action;

    public Expression getAction() {
        return action;
    }

    public void setAction(Expression action) {
        this.action = action;
    }
}