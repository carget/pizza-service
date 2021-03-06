package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;

/**
 * @author Anton_Mishkurov
 */
public interface OrderService {
    Order placeNewOrder(Customer customer, Long... pizzasID);

    void submitOrder(Order newOrder);

    void cancelOrder(Long orderId);

    void completeOrder(Long orderId);

}
