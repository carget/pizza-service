package ua.rd.pizzaservice.domain.discounts;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.rd.pizzaservice.domain.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 *
 * @author Anton Mishkyroff
 */
public class DiscountByCardTest {
    DiscountByCard discountByCard;
    private Customer customer;
    private Address address;
    private Map<Pizza, Integer> cart;
    private Order order;

    @Before
    public void setUp() throws Exception {
        discountByCard = new DiscountByCard();
        address = new Address(null, "Kudryashova", 18, null);
        customer = new Customer("John Black", address);
        cart = new HashMap<>();
        cart.put(new Pizza(0L, new BigDecimal(10), "Pizza 1", Pizza.Type.MEAT), 1);
        cart.put(new Pizza(1L, new BigDecimal(20), "Pizza 2", Pizza.Type.SEA), 2);
        cart.put(new Pizza(2L, new BigDecimal(30), "Pizza 3", Pizza.Type.VEGETARIAN), 3);
    }

    @Test
    public void testDiscountWithCard() {
        DiscountCard discountCard = new DiscountCard(new BigDecimal(100));
        customer.setDiscountCard(discountCard);
        order = new Order(customer, cart);
        assertThat(discountByCard.calc(order, order.getTotal()),
                is(new BigDecimal(10).setScale(2, RoundingMode.CEILING)));
    }
}