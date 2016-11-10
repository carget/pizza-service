package ua.rd.pizzaservice.repository.jpa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;
import ua.rd.pizzaservice.repository.RepositoryTestConfig;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Anton_Mishkurov
 */
public class JpaPizzaRepositoryIT extends RepositoryTestConfig {

    @Autowired
    private PizzaRepository pizzaRepository;
    private Pizza testPizza;

    @Before
    public void setUp() throws Exception {
        testPizza = new Pizza(1L, BigDecimal.TEN, "Test Order", Pizza.Type.MEAT);
        jdbcTemplate.execute("INSERT INTO PIZZA (id, name, price, type) VALUES (1, 'Test Order', 10.00, 'MEAT');");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void findPizzaByID() throws Exception {
        Pizza pizza = pizzaRepository.findPizzaByID(1L);
        assertThat(pizza, is(testPizza));
    }

    @Test
    public void save() throws Exception {
        Pizza pizza = new Pizza(null, BigDecimal.TEN, "Sea 1123", Pizza.Type.SEA);
        pizza = pizzaRepository.save(pizza);
        assertThat(pizza.getId(), is(notNullValue()));
    }

}