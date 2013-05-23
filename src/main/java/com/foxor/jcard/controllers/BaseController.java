
package com.foxor.jcard.controllers;

import java.util.Map;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.yaml.snakeyaml.Yaml;

@Path("/api/1")
public class BaseController {
    
    protected Yaml yaml = new Yaml();
    
    public static String GemlToYaml(String geml) {
        return geml.replaceAll("!!", "!!com.foxor.jcard.geml.expressions.");
    }
    
    @PUT
    @Path("startgame")
    @Produces("text/yaml")
    public String startGame(String yamlRules) {
        @SuppressWarnings("unchecked")
        Map<String, Object> rules = (Map<String, Object>)yaml.load(yamlRules);
        return yaml.dump(rules);
    }
}