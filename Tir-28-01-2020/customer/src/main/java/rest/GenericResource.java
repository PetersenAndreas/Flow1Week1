/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import CustomerFacade.CustomerFacade;
import com.google.gson.Gson;
import entity.Customer;
import java.util.List;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author andre
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of rest.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    //@Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Hello from my first web service";
    }
    
    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRandom() {
        CustomerFacade cf = new CustomerFacade();
        List<Customer> customerList = cf.allCustomers();
        return new Gson().toJson(getRandomCustomer(customerList));
    }
    
    public Customer getRandomCustomer(List<Customer> customerList) {
        Random r = new Random();
        int number = r.nextInt(customerList.size());
        return customerList.get(number);
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        CustomerFacade cf = new CustomerFacade();
        List<Customer> customerList = cf.allCustomers();
        return new Gson().toJson(customerList);
    }
    
@GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCustomerOnId(@PathParam("id") int id) {
        CustomerFacade cf = new CustomerFacade();
        Customer c = cf.findByID(id);
        return new Gson().toJson(c);
    }
    
    

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
