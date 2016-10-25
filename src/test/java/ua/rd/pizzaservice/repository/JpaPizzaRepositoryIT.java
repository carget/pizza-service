package ua.rd.pizzaservice.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.Pizza;

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

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void findPizzaByID() throws Exception {
    }

    @Test
    public void save() throws Exception {
        Pizza pizza = new Pizza(null, BigDecimal.TEN, "Sea 1123", Pizza.Type.SEA);
        pizza = pizzaRepository.save(pizza);
        assertThat(pizza.getId(), is(notNullValue()));
    }

}