package com.foxor.jcard.geml.expressions;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * Property gets a property from the context GObject
 * 
 * Server only
 * 
 * @author ijames1
 *
 */
public class Property extends Expression {
    
    /**
     * The name of the property to retrieve.
     */
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        Method method = m.getContext().getClass().getMethod("get" + StringUtils.capitalize(name), new Class<?>[0]);
        return (Expression)method.invoke(m.getContext(), new Object[0]);
    }
}