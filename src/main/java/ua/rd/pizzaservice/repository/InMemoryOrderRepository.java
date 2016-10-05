package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.infrastructure.Benchmark;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anton_Mishkurov
 */
public class InMemoryOrderRepository implements OrderRepository {

    private List<Order> orderList;

    public InMemoryOrderRepository() {
        this.orderList = new ArrayList<>();
    }

    @Benchmark(value = false)
    @Override
    public void saveOrder(Order newOrder) {
        newOrder.setNextId();
        orderList.add(newOrder);
    }
}
