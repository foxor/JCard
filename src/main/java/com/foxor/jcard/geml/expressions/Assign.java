package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * Assign places an entry in the locals in the current machine.
 * 
 * These entries can then be retrieved with !!Local
 * 
 * @author ijames1
 *
 */
public class Assign extends Expression {
    
    /**
     * The name of the local variable
     */
    protected String name;
    
    /**
     * The data that the variable holds
     */
    protected Expression value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Expression getValue() {
        return value;
    }

    public void setValue(Expression value) {
        this.value = value;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
