package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.GObject;

/**
 * 
 * A zone is a place where a card can be.
 * 
 * The zone and the cards within it may or may not be displayed to the user
 * 
 * @author ijames1
 *
 */
public class Zone extends GObject {
    
    /**
     * The x coordinate of the left edge, expressed as a portion of the width of the game area
     */
    protected float x;
    
    /**
     * The y coordinate of the top edge, expressed as a portion of the height of the game area
     */
    protected float y;
    
    /**
     * The width of the zone, expressed as a portion of the width of the game area
     */
    protected float width;
    
    /**
     * The height of the zone, expressed as a portion of the height of the game area
     */
    protected float height;
    
    /**
     * A label to display to the user
     */
    protected String label;
    
    /**
     * Whether the contents of this zone, and the zone itself should be displayed
     */
    protected boolean hidden;
    
    /**
     * Whether this zone contains cards that should be dealt randomly
     */
    protected boolean shuffled;
    
    public boolean isHidden() {
        return hidden;
    }
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    public boolean isShuffled() {
        return shuffled;
    }
    public void setShuffled(boolean shuffled) {
        this.shuffled = shuffled;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
    public float getWidth() {
        return width;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public float getHeight() {
        return height;
    }
    public void setHeight(float height) {
        this.height = height;
    }
}