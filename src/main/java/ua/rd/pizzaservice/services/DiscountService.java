package ua.rd.pizzaservice.services;

import ua.rd.pizzaservice.domain.Order;

import java.math.BigDecimal;

/**
 * //TODO add comments
 *
 * @author Anton Mishkyroff
 */
public interface DiscountService {
    BigDecimal getDiscount(Order order);
}
