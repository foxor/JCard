package com.foxor.jcard.geml.expressions;

public class Equals extends Expression{
    protected Expression[] test;
    public Expression[] getTest() {
        return test;
    }
    public void setTest(Expression[] test) {
        this.test = test;
    }
}