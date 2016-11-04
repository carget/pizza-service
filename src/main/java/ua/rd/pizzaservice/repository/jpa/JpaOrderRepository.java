package ua.rd.pizzaservice.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.infrastructure.Benchmark;
import ua.rd.pizzaservice.repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Anton_Mishkurov
 */
@Repository("orderRepository")
public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    @Benchmark
    public void saveOrder(Order newOrder) {
//        entityManager.persist(newOrder);
        entityManager.merge(newOrder);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return entityManager.find(Order.class, orderId);
    }
}
