package io.twinterf;

import io.quarkus.runtime.annotations.RegisterForReflection;
import io.twinterf.entity.Employee;
import io.twinterf.repository.EmployeeRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employees")
@RegisterForReflection
public class EmployeeResource {

    @Inject
    private EmployeeRepository employeeRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllEmployees() {
        System.out.println("Find all employees");
        return Response.ok(employeeRepository.findAllEmployees()).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(employeeRepository.findEmployeeById(id)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee e) {
        employeeRepository.addEmployee(e);
        return Response.noContent().build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteEmployee(@PathParam("id") Long id) {
        employeeRepository.deleteEmployee(id);
        return Response.noContent().build();
    }
}
