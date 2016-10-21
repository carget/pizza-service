package ua.rd.pizzaservice;

import ua.rd.pizzaservice.domain.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;
import java.math.BigDecimal;

/**
 * @author Anton_Mishkurov
 */
public class JpaAppRunner {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Pizza pizza = new Pizza(null, BigDecimal.TEN, "Bavarian" , Pizza.Type.SEA);
        Pizza pizza1 = new Pizza(null, BigDecimal.TEN, "Bavarian111" , Pizza.Type.SEA);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        System.out.println("Before persist: ");
//        entityManager.merge(pizza);
        entityManager.persist(pizza);
//        entityManager.persist(pizza1);
        entityManager.flush();
        pizza1.setName("Hawai");
        System.out.println("After persist: " + pizza.getId());
//        transaction.rollback();
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
