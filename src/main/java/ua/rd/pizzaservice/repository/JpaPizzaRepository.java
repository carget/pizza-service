package ua.rd.pizzaservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.Benchmark;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Anton_Mishkurov
 */
@Repository("pizzaRepository")
public class JpaPizzaRepository implements PizzaRepository {

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
