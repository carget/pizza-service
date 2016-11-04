package ua.rd.pizzaservice.domain;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Anton_Mishkurov
 */
@Entity
public class DiscountCard {
    @TableGenerator(name = "discountGen", allocationSize = 10, initialValue = 1000,
            pkColumnName = "GEN_NAME", pkColumnValue = "NEXT_DISCOUNT_ID", valueColumnName = "NEXT_VAL")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "discountGen")
    private Long id;
    private BigDecimal amount;
    @OneToOne(mappedBy = "discountCard")
    private Customer customer;

    public DiscountCard(/*Customer customer*/) {
//        this.customer = customer;
        this.amount = BigDecimal.ZERO;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void addAmount(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }
}
