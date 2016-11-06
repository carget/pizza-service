package ua.rd.pizzaservice.domain.discounts;

import ua.rd.pizzaservice.domain.Order;

import java.math.BigDecimal;

/**
 * //TODO add comments
 *
 * @author Anton Mishkyroff
 */
public interface Discount {
    BigDecimal calc(Order order, BigDecimal totalOrderSumWithDiscounts);
}
