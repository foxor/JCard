package com.foxor.jcard.geml.expressions;

import java.util.List;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;

/**
 * 
 * Draws a card from a certain zone to another.
 * 
 * The server will figure out what card to draw, then generate a MoveTo message corresponding to the move
 * 
 * @author ijames1
 *
 */
public class Draw extends Expression {
    
    /**
     * The zone from which to draw the card
     */
    protected Expression from;

    /**
     * The zone the card is drawn from
     */
    protected Expression to;

    public Expression getFrom() {
        return from;
    }

    public void setFrom(Expression from) {
        this.from = from;
    }

    public Expression getTo() {
        return to;
    }

    public void setTo(Expression to) {
        this.to = to;
    }

    @Override
    public Expression execute(Machine m) throws Exception {
        List<Expression> cards = ((Zone)from.execute(m)).getCards();
        Expression card = cards.get(m.randomInt(cards.size()));
        MoveTo generated = new MoveTo();
        generated.setCard(card);
        generated.setZone(to);
        return generated.execute(m);
    }
}