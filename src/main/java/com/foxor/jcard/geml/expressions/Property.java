package com.foxor.jcard.geml.expressions;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.GObject;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * Property gets a property from the context GObject, or a specified local object
 * 
 * Server only
 * 
 * @author ijames1
 *
 */
public class Property extends Expression {
    
    /**
     * The object to query
     */
    protected GObject source;
    
    /**
     * The name of the property to retrieve.
     */
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
        GObject sourceBackup = source;
        if (source == null) {
            source = m.getContext();
        }
        GObject self = (GObject)source.execute(m);
        Object val;
        if (self.getData() != null && self.getData().containsKey(getName())) {
            val = self.getData().get(getName());
        }
        else {
            Method method = self.getClass().getMethod("get" + StringUtils.capitalize(name), new Class<?>[0]);
            val = method.invoke(self, new Object[0]);
        }
        source = sourceBackup;
        return Type.TypeBox(val);
    }
}