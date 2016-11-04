package ua.rd.pizzaservice;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.rd.pizzaservice.config.AppConfig;
import ua.rd.pizzaservice.domain.*;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.services.OrderService;
import ua.rd.pizzaservice.services.SimpleOrderService;

import java.math.BigDecimal;

/**
 * @author Anton_Mishkurov
 */
public class SpringJavaConfigHibernateRunner {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        PizzaRepository pizzaRepository = (PizzaRepository) context.getBean("pizzaRepository");

        Address address = new Address(null, "Kudryashova", 18, null);
        Customer customer = new Customer("John Black", address);
        DiscountCard card = new DiscountCard();
        card.addAmount(BigDecimal.TEN);
        customer.setDiscountCard(card);

//        Pizza pizza = new Pizza(null, BigDecimal.TEN, "Meattt", Pizza.Type.MEAT);

        OrderService orderService = (OrderService) context.getBean("orderService");
        Order order = orderService.placeNewOrder(customer, 1001L, 1002L, 1001L);
        orderService.saveOrder(order);
//
        System.out.println(order);

//        pizzaRepository.save(pizza);

//        System.out.println(pizzaRepository.save(pizza));

        context.close();
    }
}
