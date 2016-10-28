package ua.rd.pizzaservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Anton_Mishkurov
 */
//@Component
//@Scope(SCOPE_PROTOTYPE)
@Entity
public class Order implements Serializable{

    public static final int PIZZA_QTY_FOR_DISCOUNT = 4;
    public static final double PIZZA_QTY_DISCOUNT_PERCENT = 0.3;

    //TODO replace with map
    private List<Pizza> pizzaList;
    @TableGenerator(name = "orderGen", allocationSize = 10, initialValue = 1000,
            pkColumnName = "GEN_NAME", pkColumnValue = "NEXT_ORDER_ID" ,valueColumnName = "NEXT_VAL")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "orderGen")
    private Long id;
    private Customer customer;
    private static Long nextId = 0L;
    @Enumerated(EnumType.STRING)
    private Status status;

    {
        this.status = Status.NEW;
    }

    public Order() {

    }

    public Order(Customer customer, List<Pizza> pizzaList) {
        this.customer = customer;
        this.pizzaList = pizzaList;
    }

    public void setNextId() {
        id = nextId++;
    }


    public BigDecimal getTotal() {
        BigDecimal total = pizzaList.stream()
                .map(Pizza::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, BigDecimal.ROUND_CEILING);
        total = total.subtract(getDiscountByQuantity());
        total = total.subtract(getDiscountByCard(total));
        return total;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (!this.status.canChangeTo(status)) {
            throw new IllegalArgumentException(String.format("Cannot change order status from %s to %s", this.status, status));
        }
        this.status = status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzaList = pizzas;
    }

    public Long getId() {
        return id;
    }

    public enum Status {
        NEW, IN_PROGRESS, CANCELED, DONE;

        private enum Transition {

            FROM_NEW(NEW, IN_PROGRESS, CANCELED),
            FROM_IN_PROGRESS(IN_PROGRESS, DONE),
            FROM_CANCELED(CANCELED),
            FROM_DONE(DONE);

            Status from;
            EnumSet<Status> to;

            Transition(Status from, Status... to) {
                this.from = from;
                this.to = EnumSet.noneOf(Status.class);
                this.to.addAll(Arrays.asList(to));
            }

            static final Map<Status, Set<Status>> transitions = new EnumMap<>(Status.class);

            static {
                for (Transition transition : Transition.values()) {
                    transitions.put(transition.from, EnumSet.copyOf(transition.to));
                }
            }
        }

        public boolean canChangeTo(Status status) {
            return Transition.transitions.get(this).contains(status);
        }
    }

    public BigDecimal getDiscountByQuantity() {
        if (pizzaList.size() < PIZZA_QTY_FOR_DISCOUNT) {
            return BigDecimal.ZERO;
        }

        BigDecimal discount = new BigDecimal(PIZZA_QTY_DISCOUNT_PERCENT).setScale(2, BigDecimal.ROUND_CEILING);

        BigDecimal total = pizzaList.stream()
                .map(Pizza::getPrice)
                .max(BigDecimal::compareTo)
                .get().multiply(discount);
        return total;
    }

    private BigDecimal getDiscountByCard(BigDecimal sumOrder) {
        if (customer.getDiscountCard() == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal maxDiscount = sumOrder.multiply(new BigDecimal(0.3));
        BigDecimal cardAmountDiscount = customer.getDiscountCard().getAmount().multiply(new BigDecimal(0.1));
        return maxDiscount.min(cardAmountDiscount);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", pizzaList=" + pizzaList +
                ", customer=" + customer +
                '}';
    }
}
