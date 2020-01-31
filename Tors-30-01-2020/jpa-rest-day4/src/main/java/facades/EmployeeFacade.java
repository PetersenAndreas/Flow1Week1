package facades;

import dto.EmployeeDTO;
import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    //Private Constructor to ensure Singleton
       public EmployeeFacade() {
    }
    
    
    

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EmployeeDTO getEmployeeById(int id) {
        EntityManager em = getEntityManager();
        try {
            Employee ep = em.find(Employee.class, id);
            return new EmployeeDTO(ep);
        } finally {
            em.close();
        }

    }

    public List<EmployeeDTO> getEmployeesByName(String Name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<EmployeeDTO> q = em.createQuery("SELECT e name from Employee e where e.name = :Name", EmployeeDTO.class);
            q.setParameter("Name", Name);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<EmployeeDTO> getAllEmployees() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery q = em.createQuery("SELECT e FROM Employee e", Employee.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<EmployeeDTO> getEmployeesWithHighestSalary() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery q = em.createQuery("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e.salary) FROM Employee e)", Employee.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EmployeeDTO createEmployee(String name, String address, int salary) {
        
        Employee ep = new Employee(name, address, salary);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(ep);
            em.getTransaction().commit();
            return new EmployeeDTO(ep);
        } finally {
            em.close();
        }
    }
    
    public static void main (String[] args) {
        EmployeeFacade ef = new EmployeeFacade();
        ef.createEmployee("Nina", "Dogmevej 4", 10000);
        ef.createEmployee("Ole Bent", "Kummevej 4", 100);
        ef.createEmployee("Kagemand Hallo", "Yesyes 25", 550);
        ef.createEmployee("Lone Holland", "Motorvej 96", 968);
        ef.createEmployee("Kent Borgesen", "Lyngbyvej 27", 100);
        System.out.println(ef.getEmployeeById(1));
        System.out.println(ef.getEmployeesByName("Ole Bent"));
        System.out.println(ef.getAllEmployees());
        System.out.println(ef.getEmployeesWithHighestSalary());
    }

}
