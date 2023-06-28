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

    @Inject PersonasRepository repo;
    @GET
    public List<Persona> listarPersonas() {
        return repo.listAll();
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
        repo.persist(persona);

        return Response.status(Response.Status.CREATED)
                .entity(persona)
                .build();
    }

    @PUT
    @Path(value="/{id}")
    public Response actualizarPersona(@PathParam("id") Long id, Persona updatedPersona) {
        Persona persona = repo.findById(id);

        persona.setName(updatedPersona.getName());
        repo.persistAndFlush(persona);
        return Response.ok().build();
    }

    @DELETE
    @Path(value="/{id}")
    public void borrarPersona(@PathParam("id") Long id) {
        repo.findById(id);
    }
}
