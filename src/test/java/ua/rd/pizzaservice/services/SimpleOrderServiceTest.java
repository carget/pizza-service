package ua.rd.pizzaservice.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.rd.pizzaservice.repository.OrderRepository;

import static ua.rd.pizzaservice.services.SimpleOrderService.MAX_PIZZA_COUNT;

/**
 * @author Anton_Mishkurov
 */
@RunWith(MockitoJUnitRunner.class)
public class SimpleOrderServiceTest {


    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PizzaService pizzaService;

    @Before
    public void setUp() throws Exception {
        orderService = new SimpleOrderService(orderRepository, pizzaService);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void placeNewBigOrder() throws Exception {
        orderService.placeNewOrder(null, new Integer[MAX_PIZZA_COUNT + 1]);
    }

}