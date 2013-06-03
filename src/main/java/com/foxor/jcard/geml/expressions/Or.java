package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * Or is a logical operator which returns the first true text expression
 * 
 * @author ijames1
 *
 */
public class Or extends Expression {
    
    /**
     * These are the expressions to test
     */
    protected Expression[] test;

    public Expression[] getTest() {
        return test;
    }

    public void setTest(Expression[] test) {
        this.test = test;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        if (test.length == 0) {
            return Type.TypeBox(true);
        }
        for (Expression e : test) {
            Expression val = e.execute(m);
            if (!Type.class.isAssignableFrom(val.getClass()) || !((Type<?>)val).getValue().equals(false)) {
                return val;
            }
        }
        return Type.TypeBox(false);
    }
}