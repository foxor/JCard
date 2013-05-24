package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Type;

public class Boolean extends Type<java.lang.Boolean> {

    public static Boolean booleanFactory(boolean value) {
        Boolean b = new Boolean();
        b.setValue(value);
        return b;
    }
}