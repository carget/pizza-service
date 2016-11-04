package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Pizza;

/**
 * @author Anton_Mishkurov
 */
public interface PizzaService {
    Pizza getPizzaByID(Long id);
    Pizza save(Pizza pizza);
}
