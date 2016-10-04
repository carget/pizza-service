package ua.rd.pizzaservice;

import java.util.List;

/**
 * @author Anton_Mishkurov
 */
public class Order {
    private Long id;
    private List<Pizza> pizzaList;
    private Customer customer;
    private static Long nextId = 0L;

    public Order(Customer customer, List<Pizza> pizzaList) {
        this.customer = customer;
        this.pizzaList = pizzaList;
    }

    public void setNextId() {
        id = nextId++;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", pizzaList=" + pizzaList +
                ", customer=" + customer +
                '}';
    }
}
