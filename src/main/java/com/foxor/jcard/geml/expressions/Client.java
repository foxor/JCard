package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

public class Client extends Expression {
    protected Expression action;

    public Expression getAction() {
        return action;
    }

    public void setAction(Expression action) {
        this.action = action;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        return action.execute(m);
    }
}