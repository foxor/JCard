package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;
import com.foxor.jcard.geml.Type;

/**
 * 
 * A boolean expression for branching conditionals.  Tests if all test expressions evaluate to equal expressions
 * 
 * Server Only
 * 
 * @author ijames1
 *
 */
public class Equals extends Expression {
    /**
     * A list of expressions to test
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
        if (test == null || test.length == 0) {
            return Type.TypeBox(true);
        }
        Expression result = test[0].execute(m);
        if (result == null) {
            return Type.TypeBox(false);
        }
        for (int i = 1; i < test.length; i++) {
            Expression intermediate = test[1].execute(m);
            if (!intermediate.equals(result)) {
                return Type.TypeBox(false);
            }
        }
        return result;
    }
}