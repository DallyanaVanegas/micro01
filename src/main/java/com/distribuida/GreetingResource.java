package com.distribuida;

import com.distribuida.db.Persona;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/Persona")
public class GreetingResource {

    class Message {
        private String msg = "hola mundo";

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    /*public Message hello() {
        return new Message();
    }*/
    public List<Persona> listarPersonas() {
        return Persona.listAll();
    }

    @POST
    @Path("/crear")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response crearPersona(Persona persona) {
        persona.persist();

        return Response.status(Response.Status.CREATED)
                .entity(persona)
                .build();
    }

    @PUT
    @Path("/actualizar/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
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
    @Path("/borrar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
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