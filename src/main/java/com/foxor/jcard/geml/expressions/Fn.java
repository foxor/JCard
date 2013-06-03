package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.GObject;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * Fn allows you to specify functions which can be called at any time.
 * 
 * Places its callback in the locals to be retrieved later by !!Local
 * 
 * Server only
 * 
 * @author ijames1
 *
 */
public class Fn extends GObject {

    /**
     * The name of the function.  Place the callbacks in this location
     */
    protected String name;
    
    /**
     * The expressions to execute.
     */
    protected Expression[] callback;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Expression[] getCallback() {
        return callback;
    }

    public void setCallback(Expression[] callback) {
        this.callback = callback;
    }
    
    @Override
    public Expression execute(Machine m) throws Exception {
        if (m.getLocal(name) == null) {
            m.addLocal(name, this);
            return this;
        }
        else {
            Expression last = null;
            for (int i = 0; i < callback.length; i++) {
                last = callback[i].execute(m);
            }
            return last;
        }
    }
}