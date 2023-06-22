package com.distribuida.repo;

import com.distribuida.db.Persona;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class PersonasRepository implements PanacheRepository<Persona> {

    @Inject
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
        */

        try(var conn = redisClient.connect()){
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return this.findByIdOptional(id);
    }
}
