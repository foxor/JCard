package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * An instruction to present a message to the user
 * 
 * Sent by the server to the client
 * 
 * @author ijames1
 *
 */
public class ShowMessage extends Expression {
    
    /**
     * The text of the message to send
     */
    protected String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        m.addMessage(this);
        return this;
    }
}