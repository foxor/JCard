package com.foxor.jcard.geml.expressions;

public class MoveTo extends Expression {
    protected Expression card;
    protected Zone zone;
    public Expression getCard() {
        return card;
    }
    public void setCard(Expression card) {
        this.card = card;
    }
    public Zone getZone() {
        return zone;
    }
    public void setZone(Zone zone) {
        this.zone = zone;
    }
}