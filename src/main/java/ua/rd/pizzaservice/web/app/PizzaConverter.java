package ua.rd.pizzaservice.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.services.PizzaService;

/**
 * @author Anton_Mishkurov
 */
public class PizzaConverter implements Converter<String, Pizza> {

    @Autowired
    private PizzaService pizzaService;

    @Override
    public Pizza convert(String pizzaId) {
        System.out.println("Convert : " + pizzaId);
        if (pizzaId == null || pizzaId.isEmpty()) {
            return new Pizza();
        }
        if (pizzaId != null) {
            return pizzaService.getPizzaByID(new Long(pizzaId));
        }
        return null;
    }
}
