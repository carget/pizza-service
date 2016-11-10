package ua.rd.pizzaservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.Benchmark;
import ua.rd.pizzaservice.repository.OrderRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anton_Mishkurov
 */
@Service("orderService")
public class SimpleOrderService implements OrderService {

    public static final int MAX_PIZZA_COUNT = 10;

    private final OrderRepository orderRepository;
    private final PizzaService pizzaService;
    private final DiscountService discountService;

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService, DiscountService discountService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
        this.discountService = discountService;
    }

    @Benchmark
    @Override
    public Order placeNewOrder(Customer customer, Long... pizzasId) {
        verifyPizzaCount(pizzasId);
        Map<Pizza, Integer> pizzas = new HashMap<>();

        for (Long id : pizzasId) {
            Pizza pizzaById = findPizzaById(id);
            if (pizzaById == null) {
                throw new IllegalArgumentException(String.format("Pizza with ID=%d does not exist", id));
            }
            Integer currPizzaCount = pizzas.get(pizzaById);
            currPizzaCount = currPizzaCount == null ? 0 : currPizzaCount;
            pizzas.put(pizzaById, currPizzaCount + 1);
        }
        return new Order(customer, pizzas);
    }

    private void verifyPizzaCount(Long[] pizzasID) {
        int pizzaCount = pizzasID.length;
        if (pizzaCount > MAX_PIZZA_COUNT) {
            throw new IllegalStateException("Max pizza count is " + MAX_PIZZA_COUNT +
                    "\nYou want add " + pizzaCount + " pizzas");
        }
    }

    public Pizza findPizzaById(Long id) {
        return pizzaService.getPizzaByID(id);
    }

    @Override
    public void submitOrder(Order order) {
        orderRepository.saveOrder(order);
        order.submitOrder();
        order.getCustomer().getDiscountCard().subtractAmount(discountService.getDiscount(order));
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.getOrderById(orderId);
        order.cancelOrder();
        order.getCustomer().getDiscountCard().addAmount(discountService.getDiscount(order));
    }

    @Override
    public void completeOrder(Long orderId) {
        Order order = orderRepository.getOrderById(orderId);
        order.doneOrder();
    }

}
