package dev.dj.resource;

import dev.dj.model.VendorDto;
import dev.dj.service.VendorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;

@Path("/vendors")
@AllArgsConstructor
public class VendorResource {

    private VendorService vendorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVendors() {
        return Response.ok(vendorService.getVendors()).build();
    }
    @GET
    @Path("/{address}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVendorsByAddress(@PathParam("address") String address) {
        return Response.ok(vendorService.getVendorsByAddress(address)).build();
    }
    @POST
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVendorsByAddress(VendorDto vendorDto) {
        vendorService.createVendor(vendorDto);
        return Response.ok("{\"status\":\"OK\"}").build();
    }
}
