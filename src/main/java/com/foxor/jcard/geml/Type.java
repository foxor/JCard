package com.foxor.jcard.geml;

public class Type<T> extends Expression {
    protected T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        return this;
    }
}