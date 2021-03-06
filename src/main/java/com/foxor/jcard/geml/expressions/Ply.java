package com.foxor.jcard.geml.expressions;

import java.util.List;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * Ply is a wrapper for an event which was sent from the client and the server responses to that event.
 * 
 * Server executes ply while catching up to speed with the client
 * 
 * @author ijames1
 *
 */
public class Ply extends Expression {
    
    /**
     * The expressions from client and server in this ply
     */
    protected List<Expression> messages;

    public List<Expression> getMessages() {
        return messages;
    }

    public void setMessages(List<Expression> messages) {
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