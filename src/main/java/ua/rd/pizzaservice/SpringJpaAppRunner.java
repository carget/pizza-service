package ua.rd.pizzaservice;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.services.OrderService;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @author Anton_Mishkurov
 */
public class SpringJpaAppRunner {
    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext =
                new ClassPathXmlApplicationContext("repoContext.xml");
        ConfigurableApplicationContext appContext =
                new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, repoContext);

        PizzaRepository pizzaRepository = (PizzaRepository) appContext.getBean("pizzaRepository");
        Pizza pizza = new Pizza(null, BigDecimal.TEN, "Bavarian" , Pizza.Type.SEA);

        System.out.println(pizzaRepository.save(pizza));

        repoContext.close();
        appContext.close();
    }
}