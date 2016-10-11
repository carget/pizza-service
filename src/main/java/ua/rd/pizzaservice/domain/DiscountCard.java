package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;

/**
 * @author Anton_Mishkurov
 */
public class DiscountCard {
    private BigDecimal amount;

    public DiscountCard() {
        this.amount = new BigDecimal(0);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void addAmount(BigDecimal amount) {
        this.amount.add(amount);
    }
}
