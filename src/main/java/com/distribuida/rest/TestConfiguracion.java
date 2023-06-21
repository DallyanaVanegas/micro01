package com.distribuida.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/config")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestConfiguracion {

    @Inject
    @ConfigProperty(name= "prop.mensaje")
    String mensaje;

    @GET
    public String test(){

        var config = ConfigProvider.getConfig();

        var source = config.getConfigSources();
        for(var it:source){
            System.out.printf("Source [%s]-->ordinal %d\n",
            it.getName(), it.getOrdinal());
        }
        //prop.mensaje se llena desde el application.properties
        String mensaje = config.getValue("prop.mensaje", String.class);
        return mensaje;
    }

}
