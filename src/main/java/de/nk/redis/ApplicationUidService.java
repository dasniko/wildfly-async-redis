package de.nk.redis;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.Suspend;
import org.jboss.resteasy.spi.AsynchronousResponse;

import com.lambdaworks.redis.RedisAsyncConnection;

@Path("/")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ApplicationUidService {

    private static final int TTL = 300000;

    @Inject
    private MyRedisConnectionManager redis;

    private RedisAsyncConnection<Object, Object> connection;

    @PostConstruct
    public void init() {
        connection = redis.connect();
    }

    @PreDestroy
    public void destroy() {
        connection.close();
        redis.shutdown();
    }

    @PUT
    @Path("/")
    public void aquireGuarantee(@Suspend(1000) final AsynchronousResponse response, final ApplicationUid app) {
        Future<String> result = connection.setexnx(app, "1", TTL);
        result.addListener(new GenericFutureListener<Future<? super String>>() {
            @Override
            public void operationComplete(Future<? super String> future) throws Exception {
                response.setResponse(Response.status(null == future.get() ? Status.OK : Status.CREATED).build());
            }
        });
    }

}
