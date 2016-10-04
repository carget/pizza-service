package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Order;

/**
 * @author Anton_Mishkurov
 */
public interface OrderService {
    void saveOrder(Order newOrder);
}
