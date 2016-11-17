package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Pizza;

import java.util.List;

/**
 * @author Anton_Mishkurov
 */
public interface PizzaService {
    Pizza getPizzaByID(Long id);
    Pizza save(Pizza pizza);
    List<Pizza> findAll();
}
