package com.foxor.jcard.geml.expressions;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * Moves a card to a zone.  The card and the zone are determined from the return values of their respective expressions.
 * 
 * This is a message which is sent from the server to the client, and also an event which causes triggers on the server.
 * 
 * @author ijames1
 *
 */
public class MoveTo extends Expression {
    
    public static final String EVENT_CODE = "MoveTo";
    
    /**
     * An expression that returns a card.  This could be a card itself, or a query
     */
    protected Expression card;
    
    /**
     * An expression that returns a zone.  This could be the zone itself, or a query
     */
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
        Card.LinkCardZone(card.execute(m), zone.execute(m));
        m.addMessage(this);
        m.broadcastEvent(EVENT_CODE);
        card = oldCard;
        return this;
    }
}