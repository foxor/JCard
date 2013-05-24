package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

public class If extends Expression {
    protected Expression condition;
    protected Expression then;
    protected Expression otherwise;
    public Expression getOtherwise() {
        return otherwise;
    }
    public void setOtherwise(Expression otherwise) {
        this.otherwise = otherwise;
    }
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
    
    @Override
    public Expression execute(Machine m) throws Exception {
        Expression conditionValue = condition.execute(m);
        if (conditionValue == null || (Boolean.class.isAssignableFrom(conditionValue.getClass()) && !((Boolean)conditionValue).getValue())) {
            return otherwise.execute(m);
        }
        else {
            return then.execute(m);
        }
    }
}