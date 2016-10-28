package ua.rd.pizzaservice.repository;

import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 * @author Anton_Mishkurov
 */
public class JpaOrderRepository implements PizzaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Pizza findPizzaByID(Integer id) {
        return entityManager.find(Pizza.class, id);
    }

    @Override
    @Transactional
    public Pizza save(Pizza pizza) {
        return entityManager.merge(pizza);
    }

}
