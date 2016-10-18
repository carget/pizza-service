package ua.rd.pizzaservice;

import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.infrastructure.ApplicationContext;
import ua.rd.pizzaservice.infrastructure.Context;
import ua.rd.pizzaservice.infrastructure.JavaConfig;
import ua.rd.pizzaservice.services.SimpleOrderService;

/**
 * @author Anton_Mishkurov
 */
public class PizzaApp {

    public static void main(String[] args) {
        Context context = new ApplicationContext(new JavaConfig());
        Customer customer = new Customer("John", "Kudryashova str. 18");
        Order order;

        SimpleOrderService orderService = context.getBean("orderService");
        order = orderService.placeNewOrder(customer, 1, 2, 3);

        System.out.println(order);

//        PizzaRepository pizzaRepository = context.getBean("pizzaRepository");
//        System.out.println(pizzaRepository.findPizzaByID(1));

    }

}
