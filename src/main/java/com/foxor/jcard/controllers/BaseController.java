
package com.foxor.jcard.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.yaml.snakeyaml.Yaml;

@Path("/api/1")
public class BaseController {
    
    protected Yaml yaml = new Yaml();
    
    @PUT
    @Path("startgame")
    @Produces("text/yaml")
    public String startGame(String yamlRules) {
        Object rules = yaml.load(yamlRules);
        return yaml.dump(rules);
    }
    
    @GET
    @Produces("text/plain")
    public String test() {
        return "Hello World";
    }
}
