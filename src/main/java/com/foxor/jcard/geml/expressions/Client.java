package com.foxor.jcard.geml.expressions;

public class Client extends Expression {
    protected Expression action;

    public Expression getAction() {
        return action;
    }

    public void setAction(Expression action) {
        this.action = action;
    }
}