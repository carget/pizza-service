package ua.rd.pizzaservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.Benchmark;
import ua.rd.pizzaservice.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anton_Mishkurov
 */
public class SimpleOrderService implements OrderService {

    public static final int MAX_PIZZA_COUNT = 10;

    private final OrderRepository orderRepository; // = new InMemoryOrderRepository();
    private final PizzaService pizzaService; //new SimplePizzaService();

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
    }

    @Benchmark
    @Override
    public Order placeNewOrder(Customer customer, Integer... pizzasID) {
        verifyPizzaCount(pizzasID);
        List<Pizza> pizzas = new ArrayList<>();

        for (Integer id : pizzasID) {
            pizzas.add(findPizzaById(id));  // get Pizza from predefined in-memory list
        }

//        Order newOrder = new Order(customer, pizzas);
        Order newOrder = createNewOrder();
        newOrder.setCustomer(customer);
        newOrder.setPizzas(pizzas);

        saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

    protected Order createNewOrder() {
        throw new IllegalStateException("Container cannot create order!");
    }

    private void verifyPizzaCount(Integer[] pizzasID) {
        int pizzaCount = pizzasID.length;
        if (pizzaCount > MAX_PIZZA_COUNT) {
            throw new IllegalArgumentException("Max pizza count is " + MAX_PIZZA_COUNT +
                    "\nYou want add " + pizzaCount + " pizzas");
        }
    }

    public Pizza findPizzaById(Integer id) {
        return pizzaService.getPizzaByID(id);
    }

    @Override
    public void saveOrder(Order newOrder) {
        newOrder.setNextId();
        orderRepository.saveOrder(newOrder);
    }

}
