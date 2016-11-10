package ua.rd.pizzaservice;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.rd.pizzaservice.config.AppConfig;
import ua.rd.pizzaservice.domain.*;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.services.DiscountService;
import ua.rd.pizzaservice.services.OrderService;
import ua.rd.pizzaservice.services.PizzaService;

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
        DiscountCard card = new DiscountCard(BigDecimal.TEN);
        customer.setDiscountCard(card);

//        saveSomePizzas(pizzaRepository);

        OrderService orderService = (OrderService) context.getBean("orderService");
        DiscountService discountService = (DiscountService) context.getBean("discountService");
        Order order = orderService.placeNewOrder(customer, 5001L, 5002L, 5003L);
        orderService.submitOrder(order);

        System.out.println(order);
        System.out.println("Discount "  + discountService.getDiscount(order));

        context.close();
    }

    private static void saveSomePizzas(PizzaRepository pizzaRepository) {
        Pizza pizza = new Pizza(null, BigDecimal.TEN, "Meattt", Pizza.Type.MEAT);
        pizzaRepository.save(pizza);
        pizza = new Pizza(null, BigDecimal.TEN, "Seaaa", Pizza.Type.SEA);
        pizzaRepository.save(pizza);
        pizza = new Pizza(null, BigDecimal.TEN, "Veg", Pizza.Type.VEGETARIAN);
        pizzaRepository.save(pizza);
    }
}
