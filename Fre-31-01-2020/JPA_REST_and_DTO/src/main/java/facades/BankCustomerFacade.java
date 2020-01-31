package facades;

import dto.BankCustomerDTO;
import entities.BankCustomer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class BankCustomerFacade {

    private static BankCustomer instance;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    //Private Constructor to ensure Singleton   
    public BankCustomerFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankCustomer getBankCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankCustomer();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public BankCustomerDTO getCustomerByID(int id) {
        EntityManager em = getEntityManager();
        try {
            BankCustomer bc = em.find(BankCustomer.class, id);
            return new BankCustomerDTO(bc);
        } finally {
            em.close();
        }
    }

    public List<BankCustomerDTO> getCustomerByNameDTO(String fullName) {
        String[] sArray = fullName.split(" ");
        String fName = sArray[0].trim();
        String lName = sArray[1].trim();
        EntityManager em = getEntityManager();
        try {
            TypedQuery<BankCustomerDTO> q = em.createQuery("SELECT c FROM BankCustomer c where c.firstName = :firstName AND c.lastName = :lastName", BankCustomerDTO.class);
            q.setParameter("firstName", fName);
            q.setParameter("lastName", lName);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery q = em.createQuery("SELECT c FROM BankCustomer c", BankCustomer.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public BankCustomer addCustomer(BankCustomer cust) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cust);
            em.getTransaction().commit();
            return cust;
        } finally {
            em.close();
        }
    }
    
    public static void main(String[] args){
        BankCustomerFacade bf = new BankCustomerFacade();
        BankCustomer bc = new BankCustomer("Andreas", "Knud", "LO5666", 10, 1, "jk");
        BankCustomer bc2 = new BankCustomer("Nina", "Ninasen", "LO5236", 1000, 55, "jk");
        bf.addCustomer(bc);
        bf.addCustomer(bc2);
        
        System.out.println(bf.getCustomerByNameDTO("Andreas Knud"));
        System.out.println("");
        System.out.println(bf.getAllBankCustomers());
        System.out.println("");
        System.out.println(bf.getCustomerByID(2));
    }

}
