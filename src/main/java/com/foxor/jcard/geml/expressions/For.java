package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;
import com.foxor.jcard.geml.Type;

/**
 * 
 * For is a looping structure for evaluating an expression many times, possibly with different context
 * 
 * For loops always iterate over a list
 * 
 * @author ijames1
 *
 */
public class For extends Expression {
    
    /**
     * The name of the iteration variable that changes.  This variable will appear in the locals
     */
    protected String name;
    
    /**
     * The list of values for the iteration variable to assume
     */
    protected Object[] in;
    
    /**
     * The list of expressions to process for each element
     */
    protected Expression[] loop;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object[] getIn() {
        return in;
    }

    public void setIn(Object[] in) {
        this.in = in;
    }

    public Expression[] getLoop() {
        return loop;
    }

    public void setLoop(Expression[] loop) {
        this.loop = loop;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        for(Object pojo : in) {
            m.pushFrame();
            m.addLocal(name, Type.TypeBox(pojo));
            for (Expression e : loop) {
                e.execute(m);
            }
            m.popFrame();
        }
        return this;
    }
}