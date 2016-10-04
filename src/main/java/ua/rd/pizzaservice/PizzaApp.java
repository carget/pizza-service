package ua.rd.pizzaservice;

/**
 * @author Anton_Mishkurov
 */
public class PizzaApp {
    public static void main(String[] args) {
        Customer customer = null;
        Order order;

        SimpleOrderService orderService = new SimpleOrderService();
        order = orderService.placeNewOrder(customer, 1, 2, 3);

        System.out.println(order);
    }

}
