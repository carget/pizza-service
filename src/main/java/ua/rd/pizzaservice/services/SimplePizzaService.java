package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.InitialContext;
import ua.rd.pizzaservice.repository.InMemoryPizzaRepository;
import ua.rd.pizzaservice.repository.PizzaRepository;

/**
 * @author Anton_Mishkurov
 */
public class SimplePizzaService implements PizzaService {

    private PizzaRepository pizzaRepository;

    public SimplePizzaService(PizzaRepository pizzaRepository) {
//        InitialContext context = new InitialContext();
//        pizzaRepository = (PizzaRepository) context.getInstance("pizzaRepository");
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Pizza getPizzaByID(Integer id) {
        return pizzaRepository.getPizzaByID(id);
    }
}
