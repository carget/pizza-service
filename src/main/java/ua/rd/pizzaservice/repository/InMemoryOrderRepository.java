package ua.rd.pizzaservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.infrastructure.Benchmark;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anton_Mishkurov
 */
@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private List<Order> orderList;

    public InMemoryOrderRepository() {
        this.orderList = new ArrayList<>();
    }


    @Override
    @Benchmark
    public void saveOrder(Order newOrder) {
        newOrder.setNextId();
        orderList.add(newOrder);
    }
}
