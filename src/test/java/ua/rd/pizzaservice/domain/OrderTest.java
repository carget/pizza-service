package ua.rd.pizzaservice.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

/**
 * @author Anton_Mishkurov
 */
public class OrderTest {

    private Customer customer;
    private List<Pizza> pizzaList;
    private Order order;


    @Before
    public void setUp() throws Exception {
        customer = new Customer("John", "Kudryashova str. 18");
        pizzaList = new ArrayList<>();
        pizzaList.add(new Pizza(0, new BigDecimal(10), "Pizza 1", Pizza.Type.MEAT));
        pizzaList.add(new Pizza(1, new BigDecimal(20), "Pizza 2", Pizza.Type.SEA));
        pizzaList.add(new Pizza(2, new BigDecimal(30), "Pizza 3", Pizza.Type.VEGETARIAN));
        pizzaList.add(new Pizza(3, new BigDecimal(40), "Pizza 4", Pizza.Type.SEA));
        pizzaList.add(new Pizza(4, new BigDecimal(50), "Pizza 5", Pizza.Type.MEAT));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getTotalWithoutDiscountByQty() throws Exception {
        pizzaList.remove(4);
        pizzaList.remove(3);
        order = new Order(customer, pizzaList);
        assertThat(order.getTotal().compareTo(new BigDecimal(60)), equalTo(0));
    }

    @Test
    public void getTotalWithDiscountByQty() {
        order = new Order(customer, pizzaList);
        assertThat(order.getTotal().compareTo(new BigDecimal(135)), equalTo(0));
    }

    @Test
    public void testDiscountWithCard() {
        DiscountCard discountCard = new DiscountCard();
        discountCard.addAmount(new BigDecimal(100));
        customer.setDiscountCard(discountCard);
        order = new Order(customer, pizzaList);
        assertEquals(new BigDecimal(125), order.getTotal());
//        assertThat(order.getTotal().compareTo(new BigDecimal(125)), equalTo(0));
    }

    @Test
    public void canChangeStatusFromNewToCancel() {
        order = new Order(customer, pizzaList);
        order.setStatus(Order.Status.CANCELED);
        assertThat(order.getStatus(), is(Order.Status.CANCELED));
    }

    @Test
    public void canChangeStatusFromNewToInProgress() {
        order = new Order(customer, pizzaList);
        order.setStatus(Order.Status.IN_PROGRESS);
        assertThat(order.getStatus(), is(Order.Status.IN_PROGRESS));
    }

    @Test
    public void canChangeStatusFromInProgressToDone() {
        order = new Order(customer, pizzaList);
        order.setStatus(Order.Status.IN_PROGRESS);
        order.setStatus(Order.Status.DONE);
        assertThat(order.getStatus(), is(Order.Status.DONE));
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotChangeStatusFromNewToDone() {
        order = new Order(customer, pizzaList);
        order.setStatus(Order.Status.DONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotChangeStatusFromInProgressToCancel() {
        order = new Order(customer, pizzaList);
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