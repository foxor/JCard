package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * A test for a condition when one input is larger than another
 * 
 * @author ijames1
 *
 */
public class GreaterThan extends Expression {
    
    /**
     * The value to measure against the value
     */
    protected Expression test;
    
    /**
     * A constant integer value to test against
     */
    protected int value;

    public Expression getTest() {
        return test;
    }

    public void setTest(Expression test) {
        this.test = test;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        @SuppressWarnings("unchecked")
        Type<Integer> result = (Type<Integer>)test.execute(m);
        return Type.TypeBox(result.getValue() > value);
    }
}