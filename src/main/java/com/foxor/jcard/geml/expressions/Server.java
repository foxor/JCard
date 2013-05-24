package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * Encapsulates a message sent by the server in response to a client action
 * 
 * These are processed by the client and presented to the user
 * 
 * @author ijames1
 *
 */
public class Server extends Expression {
    
    /**
     * The message being sent
     */
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