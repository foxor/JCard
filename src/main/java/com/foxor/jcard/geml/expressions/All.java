package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.GObject;
import com.foxor.jcard.geml.Machine;

public class All extends Expression {
    protected String ofClass;
    protected Expression callback;

    public Expression getCallback() {
        return callback;
    }

    public void setCallback(Expression callback) {
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
        GObject oldContext = m.getContext();
        for (GObject obj : m.getObjects()) {
            if (ofClass != null && !obj.getClass().getName().endsWith(ofClass)) {
                continue;
            }
            m.setContext(obj);
            callback.execute(m);
        }
        m.setContext(oldContext);
        return this;
    }
}