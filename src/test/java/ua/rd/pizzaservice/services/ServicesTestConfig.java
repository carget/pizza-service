package ua.rd.pizzaservice.services;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.rd.pizzaservice.config.AppConfig;
import ua.rd.pizzaservice.config.RepoConfig;
import ua.rd.pizzaservice.config.RepoContextForH2;

/**
 *
 * @author Anton Mishkyroff
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ServicesTestConfig extends AbstractTransactionalJUnit4SpringContextTests {
}
