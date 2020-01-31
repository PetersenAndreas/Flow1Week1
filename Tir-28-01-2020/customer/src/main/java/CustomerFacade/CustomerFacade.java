/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CustomerFacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author andre
 */
public class CustomerFacade {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");

    public EntityManager getManager() {
        return emf.createEntityManager();
    }


    public Customer findByID(int id) {
        EntityManager em = getManager();
        try {
            Customer cs = em.find(Customer.class, id);
            return cs;
        } finally {
            em.close();
        }
        
    }

    public List<Customer> findByLastName(String lastName) {
        EntityManager em = getManager();
        try {
            TypedQuery<Customer> q = em.createQuery("select c lastName from Customer c where c.lastName = :lastName", Customer.class);
            q.setParameter("lastName", lastName);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public int getNumberOfCustomers() {
        EntityManager em = getManager();
        try {
            TypedQuery<Customer> q = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return q.getResultList().size();
        } finally {
            em.close();
        }
    }

    public List<Customer> allCustomers() {
        EntityManager em = getManager();
        try{
        TypedQuery q = em.createQuery("SELECT c FROM Customer c", Customer.class);
        return q.getResultList();
        } finally {
        em.close();
        }
    }
    
    public Customer addCustomer(String firstName, String lastName){
        Customer cs = new Customer (firstName, lastName);
        EntityManager em = emf.createEntityManager();
           try{
               em.getTransaction().begin();
               em.persist(cs);
               em.getTransaction().commit();
               return cs;
           } finally {
               em.close();
           }
    }

    public static void main(String[] args) {
        CustomerFacade cf = new CustomerFacade();
        //System.out.println(cf.findByLastName("Petersen")); //Find a customer on lastName
        //System.out.println(cf.allCustomers()); //Find all customers
        //System.out.println(cf.getNumberOfCustomers()); //Get the total amount of customers
        //cf.addCustomer("Lotte", "Hansen"); //Create a customer
        //System.out.println(cf.findByID(1)); //Find an exsiting customer on their id
        
    }
}
