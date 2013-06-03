package com.foxor.jcard.geml.expressions;

import java.util.List;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.GObject;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * All is a utility expression for map + filter on GObjects known to the machine
 * 
 * Server Only
 * 
 * @author ijames1
 *
 */
public class All extends Expression {
    /**
     * Filters GObjects to those that match !!ofClass in GEML
     */
    protected String ofClass;
    
    /**
     * A function to map across all matched expressions
     */
    protected List<Expression> callback;

    public List<Expression> getCallback() {
        return callback;
    }

    public void setCallback(List<Expression> callback) {
        this.callback = callback;
    }

    public String getOfClass() {
        return ofClass;
    }

    public void setOfClass(String ofClass) {
        this.ofClass = ofClass;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        Expression last = null;
        for (GObject obj : m.getGlobals()) {
            if (ofClass != null && !obj.getClass().getName().endsWith(ofClass)) {
                continue;
            }
            m.pushFrame();
            m.setContext(obj);
            for (Expression e : callback) {
                last = e.execute(m);
            }
            m.popFrame();
        }
        return last;
    }
}