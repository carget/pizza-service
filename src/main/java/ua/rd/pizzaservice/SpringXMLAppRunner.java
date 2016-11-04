package ua.rd.pizzaservice;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.Address;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.services.OrderService;

import java.util.Arrays;

/**
 * @author Anton_Mishkurov
 */
public class SpringXMLAppRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext =
                new ClassPathXmlApplicationContext("repoContext.xml");
        ConfigurableApplicationContext serviceContext =
                new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, repoContext);

        Address address = new Address(null, "Kudryashova", 18, null);
        Customer customer = new Customer("John Black", address);

//        Object objOrderService = serviceContext.getBean("orderService");

//        System.out.println(objOrderService.getClass());

        OrderService orderService = (OrderService) serviceContext.getBean("orderService");

        Order order = orderService.placeNewOrder(customer, 1L, 2L, 3L);
        System.out.println(order);

        System.out.println("Service context: " + Arrays.toString(serviceContext.getBeanDefinitionNames()));
        System.out.println("Repo context: " + Arrays.toString(repoContext.getBeanDefinitionNames()));

        repoContext.close();
        serviceContext.close();
    }
}
