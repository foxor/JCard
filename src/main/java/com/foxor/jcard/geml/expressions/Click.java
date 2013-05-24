package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

public class Click extends Expression {
    public static final String EVENT_CODE = "Click";
    
    protected Expression target;

    public Expression getTarget() {
        return target;
    }

    public void setTarget(Expression target) {
        this.target = target;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        m.broadcastEvent(EVENT_CODE);
        return this;
    }
}