package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/metrics")
public class WebVitalsServer {
    Map<String, List<Object>> mapObj = new HashMap<>();

    @GET
    public Response sayHi() {
        Object[] keySetMap = mapObj.keySet().toArray();
        return Response.ok(keySetMap).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWVWithId(String id) {
        return Response.ok(mapObj.get(id)).build();
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response receiveWVWithId(@PathParam("id") String id, final Object dataReceive) {
//        System.out.println(dataReceive.toString());
        List<Object> webVitalsResultList = mapObj.get(id);
        if (webVitalsResultList == null) {
            webVitalsResultList = new ArrayList<>();
        }
        webVitalsResultList.add(dataReceive);
        mapObj.put(id, webVitalsResultList);
        return Response.ok("{\"status\": \"success\"}").build();
    }
    @DELETE
    @Path("/{id}")
    public Response deleteId(@PathParam("id") String id) {
        mapObj.remove(id);
        return Response.ok("{\"status\": \"success delete\"}").build();
    }
}
