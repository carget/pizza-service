package ua.rd.pizzaservice.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author Anton_Mishkurov
 */
public class OrderTest {

    private Customer customer;
    private Address address;
    private Map<Pizza, Integer> cart;
    private Order order;

    @Before
    public void setUp() throws Exception {
        address = new Address(null, "Kudryashova", 18, null);
        customer = new Customer("John Black", address);
        cart = new HashMap<>();
        cart.put(new Pizza(0L, new BigDecimal(10), "Pizza 1", Pizza.Type.MEAT), 1);
        cart.put(new Pizza(1L, new BigDecimal(20), "Pizza 2", Pizza.Type.SEA), 1);
        cart.put(new Pizza(2L, new BigDecimal(30), "Pizza 3", Pizza.Type.VEGETARIAN), 1);
        cart.put(new Pizza(3L, new BigDecimal(40), "Pizza 4", Pizza.Type.SEA), 1);
        cart.put(new Pizza(4L, new BigDecimal(50), "Pizza 5", Pizza.Type.MEAT), 1);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getTotalWithoutDiscountByQty() throws Exception {
        cart = new HashMap<>();
        cart.put(new Pizza(0L, new BigDecimal(10), "Pizza 1", Pizza.Type.MEAT), 1);
        cart.put(new Pizza(1L, new BigDecimal(20), "Pizza 2", Pizza.Type.SEA), 1);
        cart.put(new Pizza(2L, new BigDecimal(30), "Pizza 3", Pizza.Type.VEGETARIAN), 1);
        order = new Order(customer, cart);
        assertThat(order.getTotal().compareTo(new BigDecimal(60)), equalTo(0));
    }

    @Test
    public void canChangeStatusFromNewToCancel() {
        order = new Order(customer, cart);
        order.setStatus(Order.Status.CANCELED);
        assertThat(order.getStatus(), is(Order.Status.CANCELED));
    }

    @Test
    public void canChangeStatusFromNewToInProgress() {
        order = new Order(customer, cart);
        order.setStatus(Order.Status.IN_PROGRESS);
        assertThat(order.getStatus(), is(Order.Status.IN_PROGRESS));
    }

    @Test
    public void canChangeStatusFromInProgressToDone() {
        order = new Order(customer, cart);
        order.setStatus(Order.Status.IN_PROGRESS);
        order.setStatus(Order.Status.DONE);
        assertThat(order.getStatus(), is(Order.Status.DONE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotChangeStatusFromNewToDone() {
        order = new Order(customer, cart);
        order.setStatus(Order.Status.DONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotChangeStatusFromInProgressToCancel() {
        order = new Order(customer, cart);
        order.setStatus(Order.Status.IN_PROGRESS);
        order.setStatus(Order.Status.CANCELED);
    }

/*    @Test
    public void cannotChangeStatusToCurrent() {
        order = new Order(customer,pizzaList);
        for (Order.Status status : Order.Status.values()) {
            order.setStatus(status);
        }
    }*/
}