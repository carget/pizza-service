package ua.rd.pizzaservice.repository;

import org.springframework.stereotype.Repository;
import ua.rd.pizzaservice.domain.Order;

/**
 * @author Anton_Mishkurov
 */

public interface OrderRepository {

    Order saveOrder(Order newOrder) ;

    Order getOrderById(Long orderId);

}
