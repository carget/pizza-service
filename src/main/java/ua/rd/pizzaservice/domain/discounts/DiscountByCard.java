package ua.rd.pizzaservice.domain.discounts;

import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * //TODO add comments
 *
 * @author Anton Mishkyroff
 */
public class DiscountByCard implements Discount {
    @Override
    public BigDecimal calc(Order order , BigDecimal totalOrderSumWithDiscounts) {
        Customer customer = order.getCustomer();

        if (customer.getDiscountCard() == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal maxDiscount = totalOrderSumWithDiscounts.multiply(new BigDecimal(0.3));
        BigDecimal cardAmountDiscount = customer.getDiscountCard().getAmount().multiply(new BigDecimal(0.1));
        return maxDiscount.min(cardAmountDiscount).setScale(2, RoundingMode.HALF_DOWN);
    }
}
