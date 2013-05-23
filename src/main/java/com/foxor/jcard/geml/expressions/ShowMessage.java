package com.foxor.jcard.geml.expressions;

public class ShowMessage extends Expression {
    protected String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}