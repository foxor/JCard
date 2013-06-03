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
            // We have to evaluate the source, since we didn't get it from the machine
            m.setContext((GObject)source.execute(m));
        }
        Expression val = m.getLocal(name).execute(m);
        if (source != null) {
            m.popFrame();
        }
        return val;
    }
}