package com.distribuida.repo;

import com.distribuida.db.Book;
import com.distribuida.db.Persona;
import com.google.gson.Gson;
import io.lettuce.core.RedisClient;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

    /*@Inject
    RedisClient redisClient; //<- llamo al metodo para que imprima el url
    public Optional<Persona> findByIdOptionalCache(Long id){
        /**
         * Buscar en el cache
         * Esta en el cache?
         *      retorna la instancia
         * Si no esta?
         *      busca en la BD
         *      poner en el cache
         *      returna la instancia
         * */

        /*
        StatefulRedisConnection<String,String> conn;
        try{
            conn = redisClient.connect();
        }catch (Exception ex){
           ex.printStackTrace();
        }finally{
        }
        //aqui cerrar

        try(var conn = redisClient.connect()){
            var commands = conn.sync();
            String persona = commands.get(Long.toString(id));

            if(persona!= null){
                Gson gson = new Gson();
                Persona personaObj = gson.fromJson(persona,Persona.class);
                return  Optional.of(personaObj);
            }else{
                Optional<Persona> personaBase = findByIdOptional(id);
                if(personaBase.isPresent()){
                    Persona personaObj = personaBase.orElse(null);
                    String personaJson = new Gson().toJson(personaObj);
                    commands.set(String.valueOf(id),personaJson);
                    return personaBase;
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  Optional.empty();
    }*/
}
