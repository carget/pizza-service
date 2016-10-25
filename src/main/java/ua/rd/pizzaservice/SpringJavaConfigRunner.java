package ua.rd.pizzaservice;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.rd.pizzaservice.config.AppConfig;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;

import java.math.BigDecimal;

/**
 * @author Anton_Mishkurov
 */
public class SpringJavaConfigRunner {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        PizzaRepository pizzaRepository = (PizzaRepository) context.getBean("pizzaRepository");
        Pizza pizza = new Pizza(null, BigDecimal.TEN, "Bavarian" , Pizza.Type.SEA);

        System.out.println(pizzaRepository.save(pizza));

        context.close();
    }
}
