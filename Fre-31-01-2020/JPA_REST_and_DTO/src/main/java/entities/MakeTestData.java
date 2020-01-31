/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author andre
 */
public class MakeTestData {
    
    private static MakeTestData instance;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager em = getEntityManager();
    
    //Private Constructor to ensure Singleton   
    public MakeTestData() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MakeTestData getMakeTestDataFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MakeTestData();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public static void main(String[] args) {
        populate();

    }

    public static void populate() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        BankCustomer bc1 = new BankCustomer("Holger", "Nielsen", "AC1254", 20.0, 875, "Hej");
        BankCustomer bc2 = new BankCustomer("Andreas", "Petersen", "LO5236", 35.0, 962, "HEjEhj");
        BankCustomer bc3 = new BankCustomer("Kevin", "Magnussen", "PL6369", 6335.2, 52, "VufVuf");
        BankCustomer bc4 = new BankCustomer("Nina", "Ninasen", "NJ6969", 500, 52, "Kurwa");
        

        try {
            em.getTransaction().begin();
            em.persist(bc1);
            em.persist(bc2);
            em.persist(bc3);
            em.persist(bc4);
            em.getTransaction().commit();
            System.out.println(bc1.getId());
            System.out.println(bc2.getId());
            System.out.println(bc3.getId());
            System.out.println(bc4.getId());

        } finally {
            em.close();
        }
    }
    
    
    
    
    
}
