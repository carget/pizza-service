package ua.rd.pizzaservice.repository;

import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.infrastructure.PostCreate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anton_Mishkurov
 */
public class InMemoryPizzaRepository implements PizzaRepository {

    private List<Pizza> pizzaList;

    public InMemoryPizzaRepository(){}

    public void init(){
        pizzaList = new ArrayList<>();
        pizzaList.add(new Pizza(0, new BigDecimal(10), "Pizza 1", Pizza.Type.MEAT));
        pizzaList.add(new Pizza(1, new BigDecimal(20), "Pizza 2", Pizza.Type.SEA));
        pizzaList.add(new Pizza(2, new BigDecimal(30), "Pizza 3", Pizza.Type.VEGETARIAN));
        pizzaList.add(new Pizza(3, new BigDecimal(40), "Pizza 4", Pizza.Type.SEA));
        pizzaList.add(new Pizza(4, new BigDecimal(50), "Pizza 5", Pizza.Type.MEAT));
    }

    @Override
    public Pizza getPizzaByID(Integer id) {
        return pizzaList.get(id);
    }

    @PostCreate
    public void testAnntotation(){
        System.out.println("Annotation rules!");
    }
}
