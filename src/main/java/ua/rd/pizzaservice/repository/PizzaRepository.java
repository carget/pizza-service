package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Pizza;

/**
 * @author Anton_Mishkurov
 */
public interface PizzaRepository {

    Pizza getPizzaByID(Integer id);
}
