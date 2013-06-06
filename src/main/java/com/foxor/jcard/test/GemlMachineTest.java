package com.foxor.jcard.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import com.foxor.jcard.geml.Expression;
import com.foxor.jcard.geml.Machine;
import com.foxor.jcard.geml.expressions.MoveTo;
import com.foxor.jcard.geml.expressions.Ply;
import com.foxor.jcard.geml.expressions.Server;
import com.foxor.jcard.geml.expressions.ShowMessage;
import com.foxor.jcard.geml.expressions.Zone;

public class GemlMachineTest {

    @Test
    public void basicGameTest() throws Exception {
        Yaml yaml = new Yaml();
        String testGeml = "rules: [\n"
                + "  &left  !!Zone {x: 0, y: 0,   width: 0.2, height: 1, id: left},\n"
                + "  &right !!Zone {x: 0, y: 0.8, width: 0.2, height: 1, id: right},\n"
                + "  &card !!Card {id: card},\n"
                + "  !!MoveTo {card: *card, zone: *left},\n"
                + "  !!On {target: *card, event: Click, callback: [\n"
                + "    !!If {condition: !!Equals {test: [!!Property {name: Zone}, *left]}, then: [\n"
                + "    !!MoveTo {zone: *right}]}\n"
                + "  ]},\n"
                + "  !!All {ofClass: Card, callback: [!!On {event: MoveTo, callback: [\n"
                + "    !!If {condition: !!Equals {test: [!!Property {name: Zone}, *right]}, then: [\n"
                + "    !!ShowMessage {text: You Win!}]},\n" + "  ]}]}\n"
                + "]\n" + "turns: [\n" + "  !!Ply {messages: [\n"
                + "    !!Client {action: !!Click {target: *card}},\n"
                + "  ]}\n" + "]\n";
        Machine machine = new Machine();
        String response = machine.process(testGeml).replaceAll("!!",
                "!!com.foxor.jcard.geml.expressions.");
        List<Expression> producedExpressions = ((Ply) yaml.load(response))
                .getMessages();
        Assert.assertEquals(((Zone) ((MoveTo) ((Server) producedExpressions
                .get(1)).getAction()).getZone()).getId(), "right");
        Assert.assertEquals(
                ((ShowMessage) ((Server) producedExpressions.get(2))
                        .getAction()).getText(), "You Win!");
    }
}