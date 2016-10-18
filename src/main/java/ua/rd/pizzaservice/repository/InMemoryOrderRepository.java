package ua.rd.pizzaservice.repository;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.infrastructure.Benchmark;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anton_Mishkurov
 */
@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private Map<Long, Order> orderList;

    public InMemoryOrderRepository() {
        this.orderList = new HashMap<>();
    }

    @Override
    @Benchmark
    public void saveOrder(Order newOrder) {
        newOrder.setNextId();
        orderList.put(newOrder.getId(), newOrder);
    }

    @Override
    public Order getOrderById(Long orderId) {
        if (orderId == null) {
            throw new IllegalStateException("Order id cannot be null!");
        }
        return orderList.get(orderId);
    }
}
