package ua.rd.pizzaservice.infrastructure;

import ua.rd.pizzaservice.repository.inmemory.InMemoryOrderRepository;
import ua.rd.pizzaservice.repository.inmemory.InMemoryPizzaRepository;
import ua.rd.pizzaservice.services.SimpleOrderService;
import ua.rd.pizzaservice.services.SimplePizzaService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anton_Mishkurov
 */
public class JavaConfig implements Config {

    private Map<String, Class<?>> classes = new HashMap<>();
    {
        classes.put("pizzaRepository", InMemoryPizzaRepository.class);
        classes.put("orderRepository", InMemoryOrderRepository.class);
        classes.put("orderService", SimpleOrderService.class);
        classes.put("pizzaService", SimplePizzaService.class);
    }

    @Override
    public Class<?> getImpl(String name) {
        return classes.get(name);
    }
}
