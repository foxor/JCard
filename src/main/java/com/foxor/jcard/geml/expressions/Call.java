package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.GObject;
import com.foxor.jcard.geml.Machine;

public class Call extends GObject {
    
    protected GObject source;
    
    protected String name;

    public GObject getSource() {
        return source;
    }
    public void setSource(GObject source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        if (source != null) {
            m.pushFrame();
            m.setContext(source);
        }
        Expression val = m.getLocal(name).execute(m);
        if (source != null) {
            m.popFrame();
        }
        return val;
    }
}