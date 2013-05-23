package com.foxor.jcard.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import com.foxor.jcard.controllers.BaseController;
import com.foxor.jcard.geml.expressions.Card;
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
                "    !!If {condition: !!Equals {left: !!Property {name: \"zone\"}, right: *left}, then: " +
                "    !!MoveTo {card: !!Property {name: \"this\"}, zone: *right}}}," +
                "]";
        Map<String, Object> rules = (Map<String, Object>)yaml.load(BaseController.GemlToYaml(testGeml));
        Assert.assertEquals(((List<Object>)rules.get("rules")).get(0).getClass(), Zone.class);
        Assert.assertEquals(((List<Object>)rules.get("rules")).get(1).getClass(), Zone.class);
        Assert.assertEquals(((List<Object>)rules.get("rules")).get(2).getClass(), Card.class);
    }
}