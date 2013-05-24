package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.GObject;

public class Card extends GObject {
    protected Expression zone;

    public Expression getZone() {
        return zone;
    }

    public void setZone(Expression zone) {
        this.zone = zone;
    }
}