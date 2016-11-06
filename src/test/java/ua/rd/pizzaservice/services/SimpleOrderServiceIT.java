package ua.rd.pizzaservice.services;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ua.rd.pizzaservice.domain.*;
import ua.rd.pizzaservice.domain.discounts.Discount;
import ua.rd.pizzaservice.domain.discounts.DiscountByCard;
import ua.rd.pizzaservice.domain.discounts.DiscountByQty;
import ua.rd.pizzaservice.repository.OrderRepository;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.repository.RepositoryTestConfig;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static ua.rd.pizzaservice.services.SimpleOrderService.MAX_PIZZA_COUNT;

/**
 * @author Anton_Mishkurov
 */
@Ignore
public class SimpleOrderServiceIT extends ServicesTestConfig {

    private Order testOrder;
    private Customer customer;
    @Qualifier("orderService")
    private OrderService orderService;
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private DiscountService discountService;
    private Address address;

    @Before
    public void setUp() throws Exception {
        address = new Address(null, "Kudryashova", 18, null);
        pizzaRepository.save(new Pizza(null, new BigDecimal(10), "Pizza 1", Pizza.Type.MEAT));
        pizzaRepository.save(new Pizza(null, new BigDecimal(20), "Pizza 2", Pizza.Type.SEA));
        pizzaRepository.save(new Pizza(null, new BigDecimal(30), "Pizza 3", Pizza.Type.VEGETARIAN));

        Map<Pizza, Integer> cart = new HashMap<>();
        cart.put(new Pizza(0L, new BigDecimal(10), "Pizza 1", Pizza.Type.MEAT), 1);
        cart.put(new Pizza(1L, new BigDecimal(20), "Pizza 2", Pizza.Type.SEA), 1);
        cart.put(new Pizza(2L, new BigDecimal(30), "Pizza 3", Pizza.Type.VEGETARIAN), 1);
        customer = new Customer("John Black", address);
        DiscountCard discountCard = new DiscountCard(new BigDecimal(100));
        customer.setDiscountCard(discountCard);
        testOrder = new Order(customer, cart);
    }

    @Test(expected = IllegalStateException.class)
    public void placeNewBigOrder() throws Exception {
        orderService.placeNewOrder(null, new Long[MAX_PIZZA_COUNT + 1]);
    }

    @Test@Ignore
    public void submitOrder() throws Exception {
        BigDecimal initDiscountAmount = testOrder.getCustomer().getDiscountCard().getAmount();
        BigDecimal subtrahend = discountService.getDiscount(testOrder);
        BigDecimal expectedDiscountAmount = initDiscountAmount.subtract(subtrahend);
        orderService.submitOrder(testOrder);
        assertThat(testOrder.getStatus(), is(Order.Status.IN_PROGRESS));
        assertThat(testOrder.getCustomer().getDiscountCard().getAmount(), is(expectedDiscountAmount));
    }

    @Test@Ignore
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