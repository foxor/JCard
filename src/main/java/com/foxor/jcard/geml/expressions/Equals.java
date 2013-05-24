package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

public class Equals extends Expression{
    protected Expression[] test;
    public Expression[] getTest() {
        return test;
    }
    public void setTest(Expression[] test) {
        this.test = test;
    }
    
    @Override
    public Expression execute(Machine m) throws Exception {
        if (test == null || test.length == 0) {
            return Boolean.booleanFactory(true);
        }
        Expression result = test[0].execute(m);
        if (result == null) {
            return Boolean.booleanFactory(false);
        }
        for (int i = 1; i < test.length; i++) {
            Expression intermediate = test[1].execute(m);
            if (!intermediate.equals(result)) {
                return Boolean.booleanFactory(false);
            }
        }
        return result;
    }
}