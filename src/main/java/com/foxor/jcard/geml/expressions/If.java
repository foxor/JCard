package com.foxor.jcard.geml.expressions;

import java.util.List;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * An Expression that calls either its then or otherwise expressions based on the value of condition when evaluated.
 * Any condition which is neither null nor a GEML Boolean with the value false will be considered to be true
 * 
 * Server only.
 * 
 * @author ijames1
 *
 */
public class If extends Expression {
    
    /**
     * The expression to evaluate to figure which callback to execute
     */
    protected Expression condition;
    
    /**
     * The expression to evaluate if condition is not false
     */
    protected List<Expression> then;
    
    /**
     * The expression to evaluate if condition is either null or a Boolean with getValue() == false
     */
    protected List<Expression> otherwise;
    
    public List<Expression> getOtherwise() {
        return otherwise;
    }
    public void setOtherwise(List<Expression> otherwise) {
        this.otherwise = otherwise;
    }
    public Expression getCondition() {
        return condition;
    }
    public void setCondition(Expression condition) {
        this.condition = condition;
    }
    public List<Expression> getThen() {
        return then;
    }
    public void setThen(List<Expression> then) {
        this.then = then;
    }
    
    @Override
    public Expression execute(Machine m) throws Exception {
        Expression conditionValue = condition.execute(m);
        @SuppressWarnings("unchecked")
        Type<Boolean> falsehood = (Type<Boolean>)Type.TypeBox(false);
        Expression last = null;
        if (conditionValue == null || falsehood.equals(conditionValue)) {
            if (otherwise != null) {
                for (Expression e : otherwise) {
                    last = e.execute(m);
                }
            }
        }
        else {
            if (then != null) {
                for (Expression e : then) {
                    last = e.execute(m);
                }
            }
        }
        return last;
    }
}