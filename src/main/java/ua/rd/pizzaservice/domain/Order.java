package ua.rd.pizzaservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Anton_Mishkurov
 */
@Entity(name = "_Order")
public class Order implements Serializable {

    @ElementCollection
    @CollectionTable(name = "cart")
    @MapKeyJoinColumn(name = "pizza_id")
    @Column(name = "pizza_count")
    private Map<Pizza, Integer> cart;
    @TableGenerator(name = "orderGen", allocationSize = 10, initialValue = 1000,
            pkColumnName = "GEN_NAME", pkColumnValue = "NEXT_ORDER_ID", valueColumnName = "NEXT_VAL")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "orderGen")
    private Long id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "order_id")
    private Customer customer;
    private static Long nextId = 0L;
    @Enumerated(EnumType.STRING)
    private Status status;

    {
        this.status = Status.NEW;
    }

    public Order() {
    }

    public Order(Customer customer, Map<Pizza, Integer> pizzaList) {
        this.customer = customer;
        this.cart = pizzaList;
    }

    public BigDecimal getTotal() {
        BigDecimal total = cart.entrySet().stream()
                .map(e -> (e.getKey().getPrice().multiply(new BigDecimal(e.getValue()))))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, BigDecimal.ROUND_CEILING);
        return total.setScale(2, BigDecimal.ROUND_CEILING);
    }

    public void submitOrder(){
        setStatus(Status.IN_PROGRESS);
    }

    public void cancelOrder(){
        setStatus(Status.CANCELED);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if (!this.status.canChangeTo(status)) {
            throw new IllegalArgumentException(
                    String.format("Cannot change order status from %s to %s", this.status, status));
        }
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Map<Pizza, Integer> getCart() {
        return new HashMap<>(cart);  //clone existing map
    }

    public Customer getCustomer() {
        return customer;
    }

    public void doneOrder() {
        setStatus(Status.DONE);
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", cart=" + cart +
                ", customer=" + customer +
                '}';
    }
}
