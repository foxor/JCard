package com.foxor.jcard.geml.expressions;

public class Ply extends Expression {
    protected Expression[] messages;

    public Expression[] getMessages() {
        return messages;
    }

    public void setMessages(Expression[] messages) {
        this.messages = messages;
    }
}