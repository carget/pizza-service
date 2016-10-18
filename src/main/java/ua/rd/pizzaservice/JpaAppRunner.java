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
        Pizza pizza = new Pizza(2, BigDecimal.TEN, "Bavarian" , Pizza.Type.SEA);

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(pizza);
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
