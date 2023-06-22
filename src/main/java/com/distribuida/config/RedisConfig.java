package com.distribuida.config;

import io.lettuce.core.RedisClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

//componente tradicional <- se accede haciendo @Inject RedisConfig <
@ApplicationScoped
public class RedisConfig {
    @Inject
    @ConfigProperty(name="redis.host")
    private String host;
    @Inject
    @ConfigProperty(name="redis.port")
    private Integer port;
    @Inject
    @ConfigProperty(name="redis.password")
    private String password;

    //metodo productor <- se puede acceder a este haciendo @Inject RedisCliente cliente;
    //<- se usa si no tengo las fuentes.
    @ApplicationScoped
    public RedisClient cliente(){

        String url = String.format("redis://%s@%s:%d/0", password,host,port);
        System.out.println("URL redis:" +url);

        return RedisClient.create(url);
    }
}
