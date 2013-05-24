package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

public class ShowMessage extends Expression {
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