package com.foxor.jcard.geml.expressions;

import java.util.List;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

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
    protected Expression in;
    
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

    public Expression getIn() {
        return in;
    }

    public void setIn(Expression in) {
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
        for(Object pojo : (List<?>)((Type<?>)in.execute(m)).getValue()) {
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