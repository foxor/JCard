package com.foxor.jcard.geml.expressions;

public class If extends Expression {
    protected Expression condition;
    protected Expression then;
    public Expression getCondition() {
        return condition;
    }
    public void setCondition(Expression condition) {
        this.condition = condition;
    }
    public Expression getThen() {
        return then;
    }
    public void setThen(Expression then) {
        this.then = then;
    }
}