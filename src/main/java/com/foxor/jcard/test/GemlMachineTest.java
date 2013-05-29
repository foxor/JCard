package com.foxor.jcard.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import com.foxor.jcard.controllers.BaseController;
import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;
import com.foxor.jcard.geml.expressions.MoveTo;
import com.foxor.jcard.geml.expressions.ShowMessage;
import com.foxor.jcard.geml.expressions.Zone;

public class GemlMachineTest {
    
    @SuppressWarnings("unchecked")
    @Test
    public void basicGameTest() throws Exception {
        Yaml yaml = new Yaml();
        String testGeml = 
                "rules: [\n" +
                "  &left  !!Zone {x: 0, y: 0,   width: 0.2, height: 1, id: \"left\"},\n" +
                "  &right !!Zone {x: 0, y: 0.8, width: 0.2, height: 1, id: \"right\"},\n" +
                "  &card !!Card {id: \"card\"},\n" +
                "  !!MoveTo {card: *card, zone: *left},\n" +
                "  !!On {target: *card, event: \"Click\", callback: [\n" +
                "    !!If {condition: !!Equals {test: [!!Property {name: \"Zone\"}, *left]}, then: \n" +
                "    !!MoveTo {zone: *right}}\n" +
                "  ]},\n" +
                "  !!All {ofClass: \"Card\", callback: !!On {event: \"MoveTo\", callback: [\n" +
                "    !!If {condition: !!Equals {test: [!!Property {name: \"Zone\"}, *right]}, then: \n" +
                "    !!ShowMessage {text: \"You Win!\"}},\n" +
                "  ]}}\n" +
                "]\n" +
                "turns: [\n" +
                "  !!Ply {messages: [\n" +
                "    !!Client {action: !!Click {target: *card}},\n" +
                "  ]}\n" +
                "]\n";
        Map<String, Object> testGemlLoaded = ((Map<String, Object>)yaml.load(BaseController.GemlToYaml(testGeml)));
        List<Expression> rules = (List<Expression>)testGemlLoaded.get("rules");
        List<Expression> turns = (List<Expression>)testGemlLoaded.get("turns");
        Machine machine = new Machine();
        List<String> producedExpressions = machine.process(rules, turns);
        Assert.assertEquals(((Zone)((MoveTo)yaml.load(producedExpressions.get(0))).getZone()).getId(), "right");
        Assert.assertEquals(((ShowMessage)yaml.load(producedExpressions.get(1))).getText(), "You Win!");
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void blackJackTest() throws Exception {
        Yaml yaml = new Yaml();
        String testGeml = 
                "rules: [\n" +
                "  !!InitializationVector {ms: 431},\n" +
                "  &hit !!Zone {label: \"Hit\", id: \"hit\"},\n" +
                "  &stand !!Zone {label: \"Stand\", id: \"stand\"},\n" +
                "  &deck !!Zone {hidden: true, shuffled: true, id: \"deck\"},\n" +
                "  &playerHand !!Zone {id: \"playerHand\"},\n" +
                "  &dealerHand !!Zone {id: \"dealerHand\"},\n" +
                "  !!For {name: suit, in: [\"hearts\", \"spades\", \"diamonds\", \"clubs\"], loop: [\n" +
                "    !!For {name: value, in: [2, 3, 4, 5, 6, 7, 8, 9, 10, \"jack\", \"queen\", \"king\", \"ace\"], loop:[\n" +
                "      !!Assign {name: card, value: !!Card {data: {suit: !!Local {name: \"suit\"}, name: !!Local {name: \"value\"}}}},\n" +
                "      !!MoveTo {card: !!Local {name: \"card\"}, zone: *deck}\n" +
                "    ]}\n" +
                "  ]},\n" +
                "  !!On {target: *hit, event: \"Click\", callback: [\n" +
                "    !!Draw {from: *deck, to: *playerHand}\n" +
                "  ]},\n" +
                "  !!Draw {from: *deck, to: *playerHand},\n" +
                "  !!Draw {from: *deck, to: *dealerHand},\n" +
                "  !!Draw {from: *deck, to: *playerHand},\n" +
                "  !!Draw {from: *deck, to: *dealerHand},\n" +
                "]\n" +
                "turns: [\n" +
                "  !!Ply {messages: [\n" +
                "    !!Client {action: !!Click {target: *hit}, receiveMs: 944},\n" +
                "  ]}\n" +
                "]\n";
        Map<String, Object> testGemlLoaded = ((Map<String, Object>)yaml.load(BaseController.GemlToYaml(testGeml)));
        List<Expression> rules = (List<Expression>)testGemlLoaded.get("rules");
        List<Expression> turns = (List<Expression>)testGemlLoaded.get("turns");
        Machine machine = new Machine();
        List<String> serverMessages = machine.process(rules, new ArrayList<Expression>());
        Assert.assertTrue(serverMessages.size() > 0);
    }
}