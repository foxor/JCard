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
                "  !!com.foxor.jcard.models.Card {id: \"test\"}," +
                "  !!com.foxor.jcard.models.Rule {code: \"#test begins in the left zone\"}," +
                "  !!com.foxor.jcard.models.Rule {code: \"When a player clicks a card in the left zone: move that card to the right\"}," +
                "  !!com.foxor.jcard.models.Zone {x: 0, y: 0,   width: 0.2, height: 1, name: \"left\" }," +
                "  !!com.foxor.jcard.models.Zone {x: 0, y: 0.8, width: 0.2, height: 1, name: \"right\"}" +
                "]"
        );
        Assert.assertEquals(((Card)((List<Object>)rules.get("rules")).get(0)).getId(), "test");
        Assert.assertEquals(((Zone)((List<Object>)rules.get("rules")).get(4)).getName(), "right");
    }
}