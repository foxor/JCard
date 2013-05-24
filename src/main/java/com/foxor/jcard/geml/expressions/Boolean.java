package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Type;

/**
 * 
 * Type-boxed Boolean Expression for evaluating conditions
 * 
 * Server Only
 * 
 * @author ijames1
 *
 */
public class Boolean extends Type<java.lang.Boolean> {

    public static Boolean booleanFactory(boolean value) {
        Boolean b = new Boolean();
        b.setValue(value);
        return b;
    }
}