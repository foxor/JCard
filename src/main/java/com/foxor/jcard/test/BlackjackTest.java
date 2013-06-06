package com.foxor.jcard.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;
import com.foxor.jcard.geml.expressions.Ply;
import com.foxor.jcard.geml.expressions.Server;
import com.foxor.jcard.geml.expressions.ShowMessage;
import com.foxor.jcard.geml.expressions.Zone;

public class BlackjackTest {

    private final String blackjackGeml = "rules: [\n"
+ "  !!InitializationVector {ms: 431},\n"
+ "  &hit !!Zone {label: Hit, id: hit},\n"
+ "  &stand !!Zone {label: Stand, id: stand},\n"
+ "  &deck !!Zone {hidden: true, shuffled: true, id: deck},\n"
+ "  &restart !!Zone {label: Restart, id: restart},\n"
+ "  &playerHand !!Zone {id: playerHand},\n"
+ "  &dealerHand !!Zone {id: dealerHand},\n"
+ "  !!For {name: suit, in: !!Type {value: [hearts, spades, diamonds, clubs]}, loop: [\n"
+ "    !!For {name: value, in: !!Type {value: [2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen, king, ace]}, loop:[\n"
+ "      !!Assign {name: card, value: !!Card {data: {suit: !!Local {name: suit}, name: !!Local {name: value}}}},\n"
+ "      !!MoveTo {card: !!Local {name: card}, zone: *deck}\n"
+ "    ]}\n"
+ "  ]},\n"
+ "  !!Fn {name: computeCardValue, callback: [\n"
+ "    !!If {condition: !!Or {test: [\n"
+ "      !!Equals {test: [!!Type {value: king}, !!Property {name: name}]},\n"
+ "      !!Equals {test: [!!Type {value: queen}, !!Property {name: name}]},\n"
+ "      !!Equals {test: [!!Type {value: jack}, !!Property {name: name}]}\n"
+ "    ]}, then: [\n"
+ "      !!Type {value: 10}\n"
+ "    ], otherwise: [\n"
+ "    !!If {condition: !!Equals {test: [!!Type {value: ace}, !!Property {name: name}]}, then: [\n"
+ "      !!Type {value: 11}\n"
+ "    ], otherwise: [\n"
+ "      !!Property {name: name}\n"
+ "    ]}]}\n"
+ "  ]},\n"
+ "  !!Fn {name: computeHandValue, callback: [\n"
+ "    !!Assign {name: handValue, value: !!Type {value: 0}},\n"
+ "    !!For {name: card, in: !!Property {name: cards}, loop: [\n"
+ "      !!Assign {name: handValue, value: !!Add {values: [\n"
+ "        !!Local {name: handValue}, \n"
+ "        !!Call {source: !!Local {name: card}, name: computeCardValue}\n"
+ "      ]}}\n"
+ "    ]},\n"
+ "    !!Local {name: handValue}\n"
+ "  ]},\n"
+ "  !!On {target: *hit, event: Click, callback: [\n"
+ "    !!Draw {from: *deck, to: *playerHand}\n"
+ "  ]},\n"
+ "  !!On {target: *restart, event: Click, callback: [\n"
+ "    !!For {name: card, in: !!Property {name: cards, source: *playerHand}, loop: [\n"
+ "      !!MoveTo {card: !!Local {name: card}, zone: *deck}\n"
+ "    ]},\n"
+ "    !!For {name: card, in: !!Property {name: cards, source: *dealerHand}, loop: [\n"
+ "      !!MoveTo {card: !!Local {name: card}, zone: *deck}\n"
+ "    ]}\n"
+ "  ]},\n"
+ "  !!Draw {from: *deck, to: *playerHand},\n"
+ "  !!Draw {from: *deck, to: *dealerHand},\n"
+ "  !!Draw {from: *deck, to: *playerHand},\n"
+ "  !!Draw {from: *deck, to: *dealerHand},\n"
+ "  !!On {target: *playerHand, event: MoveTo, callback: [\n"
+ "    !!If {condition: !!GreaterThan {test: !!Local {name: computeHandValue}, value: 21}, then: [\n"
+ "      !!ShowMessage {text: Bust!}\n" + "    ]}\n" + "  ]},\n"
+ "]\n";


    @Test
    public void blackJackBustTest() throws Exception {
        Yaml yaml = new Yaml();
        String testGeml = blackjackGeml
                + "turns: [\n"
                + "  !!Ply {messages: [\n"
                + "    !!Client {action: !!Click {target: *hit}, receiveMs: 944},\n"
                + "  ]}\n" + "]\n";
        Machine machine = new Machine();
        String response = machine.process(testGeml).replaceAll("!!",
                "!!com.foxor.jcard.geml.expressions.");
        List<Expression> producedExpressions = ((Ply) yaml.load(response))
                .getMessages();
        Assert.assertEquals("Bust!", ((ShowMessage) ((Server) producedExpressions.get(2))
                        .getAction()).getText());
    }

    @Test
    public void blackJackRestartTest() throws Exception {
        String testGeml = blackjackGeml
                + "turns: [\n"
                + "  !!Ply {messages: [\n"
                + "    !!Client {action: !!Click {target: *hit}, receiveMs: 944},\n"
                + "  ]},\n"
                + "  !!Ply {messages: [\n"
                + "    !!Client {action: !!Click {target: *restart}, receiveMs: 324},\n"
                + "  ]},\n" 
                + "]\n";
        Machine machine = new Machine();
        machine.process(testGeml);
        Assert.assertEquals(52, ((Zone)machine.getGlobal("deck")).getCards().size());
    }

}
