package ua.rd.pizzaservice;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.services.OrderService;

import java.util.Arrays;

/**
 * @author Anton_Mishkurov
 */
public class SpringAppRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext =
                new ClassPathXmlApplicationContext("repoContext.xml");
        ConfigurableApplicationContext serviceContext =
                new ClassPathXmlApplicationContext(new String[]{"serviceContext.xml"}, repoContext);

        Customer customer = new Customer();
        Order order;
        Object objOrderService = serviceContext.getBean("orderService");

        System.out.println(objOrderService.getClass());

        OrderService orderService = (OrderService) serviceContext.getBean("orderService");

        order = orderService.placeNewOrder(customer, 1, 2, 3);
        System.out.println(order);
        System.out.println(orderService.getClass());

        System.out.println(Arrays.toString(serviceContext.getBeanDefinitionNames()));

        PizzaRepository pizzaRepository = (PizzaRepository) repoContext.getBean("pizzaRepository");

        System.out.println(pizzaRepository.getPizzaByID(1));

//        System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));
        repoContext.close();

//        System.out.println(serviceContext.getBean("T1", SomeService.class).getString());

//        serviceContext.getBean("AAA");
        serviceContext.close();
    }
}
