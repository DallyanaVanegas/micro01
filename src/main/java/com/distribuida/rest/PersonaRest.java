package com.distribuida.rest;

import com.distribuida.db.Persona;
import com.distribuida.repo.PersonasRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
@Path("/personas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class PersonaRest {

    Logger logger = LoggerFactory.getLogger(PersonaRest.class);

    @Inject
    PersonasRepository repo;
    @GET
    /*public Message hello() {
        return new Message();
    }*/
    public List<Persona> listarPersonas() {
        return Persona.listAll();
    }

    @GET
    @Path(value= "/{id}")
    public Response personaId(@PathParam("id") Long id) {
        logger.debug("Consultando personsa con id={}",id);

        var per = repo.findByIdOptionalCache(id);

        if(per.isEmpty()){
            return  Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(per.get()).build();

        //Persona persona = Persona.findById(id);
        //return persona;
    }
    @POST
    public Response crearPersona(Persona persona) {
        persona.persist();

        return Response.status(Response.Status.CREATED)
                .entity(persona)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizarPersona(@PathParam("id") Long id, Persona updatedPersona) {
        Persona persona = Persona.findById(id);

        if (persona != null) {
            persona.setName(updatedPersona.getName());
            persona.persist();

            return Response.ok(persona).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response borrarPersona(@PathParam("id") Long id) {
        Persona persona = Persona.findById(id);
        if (persona != null) {
            persona.delete();

            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
