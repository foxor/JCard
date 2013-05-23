package com.foxor.jcard.geml.expressions;

public class Equals extends Expression{
    protected Expression left;
    protected Expression right;
    public Expression getLeft() {
        return left;
    }
    public void setLeft(Expression left) {
        this.left = left;
    }
    public Expression getRight() {
        return right;
    }
    public void setRight(Expression right) {
        this.right = right;
    }
}