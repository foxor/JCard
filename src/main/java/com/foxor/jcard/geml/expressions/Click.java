package com.foxor.jcard.geml.expressions;

public class Click extends Expression {
    protected Expression target;

    public Expression getTarget() {
        return target;
    }

    public void setTarget(Expression target) {
        this.target = target;
    }
}