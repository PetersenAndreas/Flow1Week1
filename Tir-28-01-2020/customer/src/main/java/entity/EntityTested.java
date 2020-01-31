/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author andre
 */
public class EntityTested {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Customer cs1 = new Customer("Holger", "Nielsen");
        Customer cs2 = new Customer("Andreas", "Petersen");
        Customer cs3 = new Customer("Kevin", "Magnussen");
        Customer cs4 = new Customer("Brian", "Hansen");
        Customer cs5 = new Customer("Lise", "Svendsen");
        Customer cs6 = new Customer("Arne", "Jensen");

        try {
            em.getTransaction().begin();
            em.persist(cs1);
            em.persist(cs2);
            em.persist(cs3);
            em.persist(cs4);
            em.persist(cs5);
            em.persist(cs6);
            em.getTransaction().commit();
            System.out.println(cs1.getId());
            System.out.println(cs2.getId());
            System.out.println(cs3.getId());
            System.out.println(cs4.getId());
            System.out.println(cs5.getId());
            System.out.println(cs6.getId());

        } finally {
            em.close();
        }

    }

}
