package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;

/**
 * @author Anton_Mishkurov
 */
public class DiscountCard {
    private BigDecimal amount;

    public DiscountCard() {
        this.amount = BigDecimal.ZERO;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void addAmount(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }
}
