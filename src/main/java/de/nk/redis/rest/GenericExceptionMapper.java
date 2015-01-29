package de.nk.redis.rest;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable t) {
        Map<String, String> err = new HashMap<String, String>();
        err.put("errorMessage", t.getMessage());
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(err).build();
    }

}
