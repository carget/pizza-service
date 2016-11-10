package ua.rd.pizzaservice.services;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.rd.pizzaservice.domain.*;
import ua.rd.pizzaservice.repository.PizzaRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ua.rd.pizzaservice.services.SimpleOrderService.MAX_PIZZA_COUNT;

/**
 * @author Anton_Mishkurov
 */
public class SimpleOrderServiceIT extends ServicesTestConfig {

    private Order testOrder;
    private Customer customer;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private DiscountService discountService;

    @Before
    public void setUp() throws Exception {
        Address address = new Address(null, "Kudryashova", 18, null);
        Pizza pizza1 = new Pizza(0L, new BigDecimal(10), "Pizza 1", Pizza.Type.MEAT);
        Pizza pizza2 = new Pizza(1L, new BigDecimal(20), "Pizza 2", Pizza.Type.SEA);
        Pizza pizza3 = new Pizza(2L, new BigDecimal(30), "Pizza 3", Pizza.Type.VEGETARIAN);
        pizzaRepository.save(pizza1);
        pizzaRepository.save(pizza2);
        pizzaRepository.save(pizza3);

        Map<Pizza, Integer> cart = new HashMap<>();
        cart.put(pizza1, 1);
        cart.put(pizza2, 1);
        cart.put(pizza3, 1);
        customer = new Customer("John Black", address);
        DiscountCard discountCard = new DiscountCard(new BigDecimal(100));
        customer.setDiscountCard(discountCard);
        testOrder = new Order(customer, cart);
    }

    @Ignore
    @Test(expected = IllegalStateException.class)
    public void placeNewBigOrder() throws Exception {
        assert (orderService != null);
        orderService.placeNewOrder(customer, new Long[MAX_PIZZA_COUNT + 1]);
    }

    @Test
    @Ignore
    public void submitOrder() throws Exception {
        BigDecimal initDiscountAmount = testOrder.getCustomer().getDiscountCard().getAmount();
        BigDecimal subtrahend = discountService.getDiscount(testOrder);
        BigDecimal expectedDiscountAmount = initDiscountAmount.subtract(subtrahend);
        orderService.submitOrder(testOrder);
        assertThat(testOrder.getStatus(), is(Order.Status.IN_PROGRESS));
        assertThat(testOrder.getCustomer().getDiscountCard().getAmount(), is(expectedDiscountAmount));
    }

    @Test
    @Ignore
    public void cancelOrder() throws Exception {
        BigDecimal initDiscountAmount = testOrder.getCustomer().getDiscountCard().getAmount();
        BigDecimal returnedDiscount = discountService.getDiscount(testOrder);
        BigDecimal expectedDiscountAmount = initDiscountAmount.add(returnedDiscount);
        orderService.cancelOrder(testOrder.getId());
        assertThat(testOrder.getStatus(), is(Order.Status.CANCELED));
        assertThat(testOrder.getCustomer().getDiscountCard().getAmount(), is(expectedDiscountAmount));
    }

    @Test
    public void completeOrder() throws Exception {

    }

}