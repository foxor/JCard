package com.foxor.jcard.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import com.foxor.jcard.controllers.BaseController;
import com.foxor.jcard.geml.expressions.Card;
import com.foxor.jcard.geml.expressions.Equals;
import com.foxor.jcard.geml.expressions.If;
import com.foxor.jcard.geml.expressions.On;
import com.foxor.jcard.geml.expressions.Zone;

public class SerializationTest {
    @SuppressWarnings("unchecked")
    @Test
    public void testBasicGame() {
        Yaml yaml = new Yaml();
        String testGeml = 
                "rules: [" +
                "  &left  !!Zone {x: 0, y: 0,   width: 0.2, height: 1}," +
                "  &right !!Zone {x: 0, y: 0.8, width: 0.2, height: 1}," +
                "  &card !!Card {}," +
                "  !!MoveTo {card: *card, zone: *left}," +
                "  !!On {target: \"$(.Card)\", event: \"click\", callback: " +
                "    !!If {condition: !!Equals {test: [!!Property {name: \"zone\"}, *left]}, then: " +
                "    !!MoveTo {card: !!Property {name: \"this\"}, zone: *right}}" +
                "  }," +
                "]";
        List<Object> rules = (List<Object>)((Map<String, Object>)yaml.load(BaseController.GemlToYaml(testGeml))).get("rules"); 
        Assert.assertEquals(rules.get(0).getClass(), Zone.class);
        Assert.assertEquals(rules.get(1).getClass(), Zone.class);
        Assert.assertEquals(rules.get(2).getClass(), Card.class);
        Assert.assertEquals(rules.get(0), ((Equals)((If)((On)rules.get(4)).getCallback()).getCondition()).getTest()[1]);
    }
}