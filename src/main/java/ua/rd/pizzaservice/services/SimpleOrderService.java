package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.InMemoryOrderRepository;
import ua.rd.pizzaservice.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anton_Mishkurov
 */
public class SimpleOrderService implements OrderService {

    private final OrderRepository orderRepository; // = new InMemoryOrderRepository();
    private final PizzaService pizzaService; //new SimplePizzaService();

    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
    }

    public Order placeNewOrder(Customer customer, Integer... pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();

        for (Integer id : pizzasID) {
            pizzas.add(findPizzaById(id));  // get Pizza from predefined in-memory list
        }
        Order newOrder = new Order(customer, pizzas);

        saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

    public Pizza findPizzaById(Integer id){
        return pizzaService.getPizzaByID(id);
    }

    @Override
    public void saveOrder(Order newOrder) {
        newOrder.setNextId();
        orderRepository.saveOrder(newOrder);
    }


}
