package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.GObject;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * Local retrieves values stored within the current stack frame
 * 
 * @author ijames1
 *
 */
public class Local extends GObject {
    
    /**
     * The name of the variable to retrieve
     */
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        return m.getLocal(name).execute(m);
    }
}