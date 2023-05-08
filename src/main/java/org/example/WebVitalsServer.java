package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/metrics")
public class WebVitalsServer {
    private List<Object> webVitalsResult = new ArrayList<>();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWV() {
        return Response.ok(webVitalsResult).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response receiveWV(final Object dataReceive) {
//        System.out.println(request.headers().toString());
//        System.out.println(dataReceive.toString());
        webVitalsResult.add(dataReceive);
        return Response.ok("{\"status\": \"success\"}").build();
    }
}
