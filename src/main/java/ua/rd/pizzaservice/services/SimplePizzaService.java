package ua.rd.pizzaservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.Benchmark;
import ua.rd.pizzaservice.repository.PizzaRepository;

/**
 * @author Anton_Mishkurov
 */
@Service
public class SimplePizzaService implements PizzaService {

    private PizzaRepository pizzaRepository;

    @Autowired
    public SimplePizzaService(PizzaRepository pizzaRepository) {
//        InitialContext context = new InitialContext();
//        pizzaRepository = (PizzaRepository) context.getInstance("pizzaRepository");
        this.pizzaRepository = pizzaRepository;
    }

    @Benchmark
    @Override
    public Pizza getPizzaByID(Integer id) {
        return pizzaRepository.findPizzaByID(id);
    }
}
