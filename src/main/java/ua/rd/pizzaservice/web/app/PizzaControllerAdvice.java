package ua.rd.pizzaservice.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.services.PizzaService;

/**
 * @author Anton_Mishkurov
 */
@ControllerAdvice
public class PizzaControllerAdvice {

    @Autowired
    private PizzaService pizzaService;

    @ModelAttribute
    public Pizza status(@RequestParam(name = "id", required = false) Pizza pizza) {
        System.out.println("Controller advice");
        return pizza;
    }
}