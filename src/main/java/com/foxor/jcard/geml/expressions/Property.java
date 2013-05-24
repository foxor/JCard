package com.foxor.jcard.geml.expressions;

import java.lang.reflect.Method;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

public class Property extends Expression {
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        Method method = m.getContext().getClass().getMethod("get" + name, new Class<?>[0]);
        return (Expression)method.invoke(m.getContext(), new Object[0]);
    }
}