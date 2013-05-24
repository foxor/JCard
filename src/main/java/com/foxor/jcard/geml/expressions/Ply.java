package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

public class Ply extends Expression {
    protected Expression[] messages;

    public Expression[] getMessages() {
        return messages;
    }

    public void setMessages(Expression[] messages) {
        this.messages = messages;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        for (Expression expression : messages) {
            expression.execute(m);
        }
        return this;
    }
}