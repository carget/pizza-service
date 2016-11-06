package ua.rd.pizzaservice.domain.discounts;

import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;

import java.math.BigDecimal;
import java.util.Map;

/**
 * //TODO add comments
 *
 * @author Anton Mishkyroff
 */
public class DiscountByQty implements Discount {

    private static final int PIZZA_QTY_FOR_DISCOUNT = 4;
    private static final double PIZZA_QTY_DISCOUNT_PERCENT = 0.3;

    @Override
    public BigDecimal calc(Order order, BigDecimal totalOrderSumWithDiscounts) {
        Map<Pizza, Integer> cart = order.getCart();
        if (cart.values().stream().mapToInt(Number::intValue).sum() < PIZZA_QTY_FOR_DISCOUNT) {
            return BigDecimal.ZERO;
        }

        BigDecimal discount = new BigDecimal(PIZZA_QTY_DISCOUNT_PERCENT).setScale(2, BigDecimal.ROUND_CEILING);

        BigDecimal total = cart.keySet().stream()
                .map(Pizza::getPrice)
                .max(BigDecimal::compareTo)
                .get().multiply(discount);
        return total;
    }
}
