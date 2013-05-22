package com.foxor.jcard.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import com.foxor.jcard.models.Card;
import com.foxor.jcard.models.Zone;

public class SerializationTest {
    @SuppressWarnings("unchecked")
    @Test
    public void testBasicGame() {
        Yaml yaml = new Yaml();
        Map<String, Object> rules = (Map<String, Object>)yaml.load(
                "rules: [" +
                "  !!com.foxor.jcard.models.Zone {x: 0, y: 0,   width: 0.2, height: 1, name: \"left\" }," +
                "  !!com.foxor.jcard.models.Zone {x: 0, y: 0.8, width: 0.2, height: 1, name: \"right\"}," +
                "  !!com.foxor.jcard.models.Card {id: \"test\"}," +
                "  !!com.foxor.jcard.models.Rule {code: \"moveTo #test #left\"}," +
                "  !!com.foxor.jcard.models.Rule {code: \"on .Card click (if this.zone #left (moveTo this #right))\"}," +
                "]"
        );
        Assert.assertEquals(((Zone)((List<Object>)rules.get("rules")).get(0)).getName(), "left");
        Assert.assertEquals(((Zone)((List<Object>)rules.get("rules")).get(1)).getName(), "right");
        Assert.assertEquals(((Card)((List<Object>)rules.get("rules")).get(2)).getId(), "test");
    }
}