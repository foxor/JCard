package com.foxor.jcard.geml;

public class Type<T> extends GObject{
    
    protected T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null || !this.getClass().isAssignableFrom(other.getClass())) {
            return false;
        }
        return value.equals(((Type<?>)other).getValue());
    }
    
    public static <B> Type<B> TypeBox(B value) {
        Type<B> box = new Type<B>();
        box.setValue(value);
        return box;
    }
}