package ua.rd.pizzaservice;

import ua.rd.pizzaservice.domain.Address;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.DiscountCard;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.infrastructure.ApplicationContext;
import ua.rd.pizzaservice.infrastructure.Context;
import ua.rd.pizzaservice.infrastructure.JavaConfig;
import ua.rd.pizzaservice.services.SimpleOrderService;

import java.math.BigDecimal;

/**
 * @author Anton_Mishkurov
 */
public class SimplePizzaAppRunner {

    public static void main(String[] args) {
        Context context = new ApplicationContext(new JavaConfig());
        Address address = new Address(null, "Kudryashova", 18, null);
        Customer customer = new Customer("John Black", address);
        DiscountCard card = new DiscountCard(BigDecimal.ZERO);
        customer.setDiscountCard(card);
        Order order;

        SimpleOrderService orderService = context.getBean("orderService");
        order = orderService.placeNewOrder(customer, 1L, 2L, 3L);

        System.out.println(order);

//        PizzaRepository pizzaRepository = context.getBean("pizzaRepository");
//        System.out.println(pizzaRepository.findPizzaByID(1));

    }

}
