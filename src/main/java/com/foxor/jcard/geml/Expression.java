package com.foxor.jcard.geml;

public abstract class Expression {
    public abstract Expression execute(Machine m) throws Exception;
}