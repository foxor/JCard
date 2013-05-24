package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

public class MoveTo extends Expression {
    
    public static final String EVENT_CODE = "MoveTo";
    
    protected Expression card;
    protected Expression zone;
    public Expression getCard() {
        return card;
    }
    public void setCard(Expression card) {
        this.card = card;
    }
    public Expression getZone() {
        return zone;
    }
    public void setZone(Expression zone) {
        this.zone = zone;
    }
    
    @Override
    public Expression execute(Machine m) throws Exception {
        Expression oldCard = card;
        if (card == null) {
            card = m.getContext();
        }
        ((Card)card.execute(m)).setZone(((Zone)zone.execute(m)));
        m.addMessage(this);
        m.broadcastEvent(EVENT_CODE);
        card = oldCard;
        return this;
    }
}