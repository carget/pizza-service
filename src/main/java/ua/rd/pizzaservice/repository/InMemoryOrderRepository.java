package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anton_Mishkurov
 */
public class InMemoryOrderRepository implements OrderRepository {

    private List<Order> orderList;

    public InMemoryOrderRepository(List<Order> orderList) {
        this.orderList = new ArrayList<>();
    }

    @Override
    public void saveOrder(Order newOrder) {
        newOrder.setNextId();
        orderList.add(newOrder);
    }
}