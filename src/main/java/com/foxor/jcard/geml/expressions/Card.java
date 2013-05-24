package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.GObject;

/**
 * 
 * GObject representing a card.
 * 
 * @author ijames1
 *
 */
public class Card extends GObject {
    
    /**
     * The zone in which the card currently resides
     */
    protected Expression zone;

    public Expression getZone() {
        return zone;
    }

    public void setZone(Expression zone) {
        this.zone = zone;
    }
}