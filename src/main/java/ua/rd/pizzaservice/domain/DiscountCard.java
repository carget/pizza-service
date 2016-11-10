package ua.rd.pizzaservice.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Anton_Mishkurov
 */
@Entity
public class DiscountCard implements Cloneable {
    @TableGenerator(name = "discountGen", allocationSize = 10, initialValue = 3000,
            pkColumnName = "GEN_NAME", pkColumnValue = "NEXT_DISCOUNT_ID", valueColumnName = "NEXT_VAL")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "discountGen")
    private Long id;
    private BigDecimal amount;

    {
        this.amount = BigDecimal.ZERO;
    }

    public DiscountCard(BigDecimal amount) {
        this.amount = amount;
    }

    public DiscountCard() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void addAmount(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    public void subtractAmount(BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
