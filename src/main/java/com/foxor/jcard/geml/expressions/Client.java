package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * An expression that contains messages sent by the client
 * 
 * The server will process these each time it gets an update, to bring the machine up to date with the client
 * The client shouldn't ever need to process these
 * 
 * @author ijames1
 *
 */
public class Client extends Expression {
    
    /**
     * The action submitted by the client
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