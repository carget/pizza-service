package ua.rd.pizzaservice.repository;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Anton_Mishkurov
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/repoContextForH2.xml"})
public class RepositoryTestConfig extends AbstractTransactionalJUnit4SpringContextTests{
}
