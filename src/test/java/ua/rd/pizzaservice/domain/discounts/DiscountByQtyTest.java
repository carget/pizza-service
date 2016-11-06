package ua.rd.pizzaservice.domain.discounts;

import org.junit.Before;
import org.junit.Test;
import ua.rd.pizzaservice.domain.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * //TODO add comments
 *
 * @author Anton Mishkyroff
 */
public class DiscountByQtyTest {

    private DiscountByQty discountByQty;
    private Customer customer;
    private Map<Pizza, Integer> cart;

    @Before
    public void setUp() throws Exception {
        discountByQty = new DiscountByQty();
        Address address = new Address(null, "Kudryashova", 18, null);
        customer = new Customer("John Black", address);
        cart = new HashMap<>();
        cart.put(new Pizza(0L, new BigDecimal(10), "Pizza 1", Pizza.Type.MEAT), 1);
        cart.put(new Pizza(1L, new BigDecimal(20), "Pizza 2", Pizza.Type.SEA), 2);
        cart.put(new Pizza(2L, new BigDecimal(30), "Pizza 3", Pizza.Type.VEGETARIAN), 3);
    }

    @Test
    public void testDiscountByQty() throws Exception {
        Order order = new Order(customer, cart);
        assertThat(discountByQty.calc(order, order.getTotal()),
                is(new BigDecimal(9).setScale(2, RoundingMode.CEILING)));
    }

}