package ua.rd.pizzaservice.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * @author Anton_Mishkurov
 */
@Component
@Scope(SCOPE_PROTOTYPE)
public class Order {
    private Long id;
    private List<Pizza> pizzaList;
    private Customer customer;
    private static Long nextId = 0L;
    private Status status;

    public Order(Customer customer, List<Pizza> pizzaList) {
        this.customer = customer;
        this.pizzaList = pizzaList;
        this.status = Status.NEW;
    }

    public Order() {

    }

    public void setNextId() {
        id = nextId++;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", pizzaList=" + pizzaList +
                ", customer=" + customer +
                '}';
    }

    public BigDecimal getTotal() {
        BigDecimal total = pizzaList.stream()
                .map(Pizza::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, BigDecimal.ROUND_CEILING);
        total = total.subtract(getDiscountByQuantity());
        return total;
    }

    public BigDecimal getDiscountByQuantity() {
        if (pizzaList.size() < 4) {
            return new BigDecimal(0);
        }

        BigDecimal discount = new BigDecimal("0.3").setScale(2, BigDecimal.ROUND_CEILING);

        BigDecimal total = pizzaList.stream()
                .map(Pizza::getPrice)
                .max(BigDecimal::compareTo)
                .get().multiply(discount);
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
}
