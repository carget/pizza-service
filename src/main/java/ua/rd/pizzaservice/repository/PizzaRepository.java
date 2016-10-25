package ua.rd.pizzaservice.repository;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.Benchmark;

/**
 * @author Anton_Mishkurov
 */
@Repository("pizzaRepository")
public interface PizzaRepository {

    @Benchmark
    Pizza findPizzaByID(Integer id);

    Pizza save(Pizza pizza);
}
