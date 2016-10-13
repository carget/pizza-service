package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.Benchmark;

/**
 * @author Anton_Mishkurov
 */
public interface PizzaRepository {

    @Benchmark
    Pizza findPizzaByID(Integer id);
}
