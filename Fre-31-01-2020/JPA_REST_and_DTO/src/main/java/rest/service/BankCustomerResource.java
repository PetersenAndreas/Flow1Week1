package rest.service;

import com.google.gson.Gson;
import dto.BankCustomerDTO;
import entities.BankCustomer;
import entities.MakeTestData;
import static entities.MakeTestData.getMakeTestDataFacade;
import facades.BankCustomerFacade;
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


@Path("bankcustomer")
public class BankCustomerResource {
    
    //NOTE: Change Persistence unit name according to your setup
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    BankCustomer facade =  BankCustomerFacade.getBankCustomerFacade(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(BankCustomer entity) {
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        BankCustomerFacade bf = new BankCustomerFacade();
        List<BankCustomer> customerList = bf.getAllBankCustomers();
        return new Gson().toJson(customerList);
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEmployeeOnId(@PathParam("id") int id) {
        BankCustomerFacade bf = new BankCustomerFacade();
        BankCustomerDTO e = bf.getCustomerByID(id);
        return new Gson().toJson(e);
    }
    
    @GET
    @Path("/populate")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPopulate() {
        MakeTestData md = getMakeTestDataFacade(Persistence.createEntityManagerFactory("pu"));
        md.populate();
        String msg = "Updated DB";
        return new Gson().toJson(msg);
    }
    
}
