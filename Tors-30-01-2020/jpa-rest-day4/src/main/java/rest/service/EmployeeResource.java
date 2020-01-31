package rest.service;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("employee")
public class EmployeeResource {
    
    //NOTE: Change Persistence unit name according to your setup
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    EmployeeFacade facade =  EmployeeFacade.getFacadeExample(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Employee entity) {
        throw new UnsupportedOperationException();
    }
    
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void update(Employee entity, @PathParam("id") int id) {
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        EmployeeFacade ef = new EmployeeFacade();
        List<EmployeeDTO> customerList = ef.getAllEmployees();
        return new Gson().toJson(customerList);
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeOnId(@PathParam("id") int id) {
        EmployeeFacade ef = new EmployeeFacade();
        EmployeeDTO e = ef.getEmployeeById(id);
        return new Gson().toJson(e);
    }
    
    @GET
    @Path("/highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighestPaid() {
        EmployeeFacade ef = new EmployeeFacade();
        List<EmployeeDTO> customerList = ef.getEmployeesWithHighestSalary();
        return new Gson().toJson(customerList);
    }
    
    @GET
    @Path("name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeOnName(@PathParam("name") String name) {
        EmployeeFacade ef = new EmployeeFacade();
        List<EmployeeDTO> e = ef.getEmployeesByName(name);
        return new Gson().toJson(e);
    }
    
}
