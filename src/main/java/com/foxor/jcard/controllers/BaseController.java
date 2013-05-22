
package com.foxor.jcard.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.yaml.snakeyaml.Yaml;

import com.google.inject.Inject;

@Path("/api/1")
public class BaseController {
    
    @Inject
    protected Yaml yaml;
    
    @PUT
    @Path("startgame")
    @Produces("text/yaml")
    public String startGame() {
        return "Hi there!";
    }
    
    @GET
    @Produces("text/plain")
    public String test() {
        return "Hello World";
    }
}
