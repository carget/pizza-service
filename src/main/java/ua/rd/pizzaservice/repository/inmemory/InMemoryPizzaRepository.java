package ua.rd.pizzaservice.repository.inmemory;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.Benchmark;
import ua.rd.pizzaservice.infrastructure.PostCreate;
import ua.rd.pizzaservice.repository.PizzaRepository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anton_Mishkurov
 */
public class InMemoryPizzaRepository implements PizzaRepository {

    private List<Pizza> pizzaList;

    public InMemoryPizzaRepository(){}


    @PostConstruct
    @Benchmark
    public void init(){
        pizzaList = new ArrayList<>();
        pizzaList.add(new Pizza(0L, new BigDecimal(10), "Pizza 1", Pizza.Type.MEAT));
        pizzaList.add(new Pizza(1L, new BigDecimal(20), "Pizza 2", Pizza.Type.SEA));
        pizzaList.add(new Pizza(2L, new BigDecimal(30), "Pizza 3", Pizza.Type.VEGETARIAN));
        pizzaList.add(new Pizza(3L, new BigDecimal(40), "Pizza 4", Pizza.Type.SEA));
        pizzaList.add(new Pizza(4L, new BigDecimal(50), "Pizza 5", Pizza.Type.MEAT));
    }

    @Override
    public Pizza findPizzaByID(Long id) {
        return pizzaList.get(Math.toIntExact(id));
    }

    @Override
    public Pizza save(Pizza pizza) {
        throw new UnsupportedOperationException("Not supported yet!");
    }

    @PostCreate
    public void testAnntotation(){
        System.out.println("Annotation rules!");
    }
}
