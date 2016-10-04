package ua.rd.pizzaservice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anton_Mishkurov
 */
public class SimpleOrderService {

    private List<Order> orderList;
    private static List<Pizza> pizzaList;

    static
    {
        pizzaList = new ArrayList<>();
        pizzaList.add(new Pizza(1, new BigDecimal(10), "Pizza 1", PizzaType.MEAT));
        pizzaList.add(new Pizza(2, new BigDecimal(20), "Pizza 2", PizzaType.SEA));
        pizzaList.add(new Pizza(3, new BigDecimal(30), "Pizza 3", PizzaType.VEGETARIAN));
        pizzaList.add(new Pizza(4, new BigDecimal(40), "Pizza 4", PizzaType.SEA));
        pizzaList.add(new Pizza(5, new BigDecimal(50), "Pizza 5", PizzaType.MEAT));
    }

    public SimpleOrderService() {
        this.orderList = new ArrayList<>();
    }

    public Order placeNewOrder(Customer customer, Integer... pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();

        for (Integer id : pizzasID) {
            pizzas.add(getPizzaByID(id));  // get Pizza from predefined in-memory list
        }
        Order newOrder = new Order(customer, pizzas);

        saveOrder(newOrder);  // set Order Id and save Order to in-memory list
        return newOrder;
    }

    private Pizza getPizzaByID(Integer id) {
        return pizzaList.get(id);
    }

    private void saveOrder(Order newOrder) {
        newOrder.setNextId();
        orderList.add(newOrder);
    }


}
