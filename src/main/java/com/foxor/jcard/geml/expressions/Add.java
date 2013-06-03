package com.foxor.jcard.geml.expressions;

import java.util.List;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * Does the mathematical expression of addition, like you would expect
 * 
 * Server only
 * 
 * @author ijames1
 *
 */
public class Add extends Type<Integer> {

    protected List<Expression> values;
    
    public List<Expression> getValues() {
        return values;
    }

    public void setValues(List<Expression> values) {
        this.values = values;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Expression execute(Machine m) throws Exception {
        int acc = 0;
        for (Expression e : values) {
            acc += ((Type<Integer>)e.execute(m)).getValue();
        }
        return Type.TypeBox(acc);
    }
}