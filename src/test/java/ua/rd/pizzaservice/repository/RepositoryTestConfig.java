package ua.rd.pizzaservice.repository;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.rd.pizzaservice.config.RepoContextForH2;

/**
 * @author Anton_Mishkurov
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:/repoContextForH2.xml"})
@ContextConfiguration(classes = RepoContextForH2.class)
public class RepositoryTestConfig extends AbstractTransactionalJUnit4SpringContextTests{
}
