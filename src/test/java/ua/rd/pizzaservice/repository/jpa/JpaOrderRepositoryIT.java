package ua.rd.pizzaservice.repository.jpa;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.rd.pizzaservice.domain.Order;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.OrderRepository;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.repository.RepositoryTestConfig;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Anton_Mishkurov
 */
public class JpaOrderRepositoryIT extends RepositoryTestConfig {

    @Autowired
    OrderRepository orderRepository;
    Map<Pizza, Integer> pizzaIntegerMap;
    Order testOrder;

    @Before
    public void setUp() throws Exception {
        pizzaIntegerMap = new HashMap<>();
        testOrder = new Order(null, pizzaIntegerMap);
    }

    @Test
    public void saveOrder() {
        testOrder = orderRepository.saveOrder(testOrder);
        assertThat(testOrder.getId(), is(notNullValue()));
    }

    @Test
    public void getOrderById() throws Exception {
        jdbcTemplate.execute("INSERT INTO _ORDER (id, status, customer_id) VALUES (1, 'NEW', null);");
        Order order = orderRepository.getOrderById(1L);
        assertThat(testOrder, is(order));
    }

}