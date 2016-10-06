package ua.rd.pizzaservice;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.services.SimpleOrderService;

import java.util.Arrays;

/**
 * @author Anton_Mishkurov
 */
public class SpringAppRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml");

        Customer customer = null;
        Order order;
        SimpleOrderService orderService = (SimpleOrderService) context.getBean("orderService");
        order = orderService.placeNewOrder(customer, 1, 2, 3);

        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        System.out.println(order);
        context.close();
    }
}
