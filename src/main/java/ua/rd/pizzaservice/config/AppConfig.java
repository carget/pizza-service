package ua.rd.pizzaservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ua.rd.pizzaservice.infrastructure.BenchmarkBeanPostProcessor;

/**
 * @author Anton_Mishkurov
 */
@Configuration
@ComponentScan("ua.rd.pizzaservice.domain")
@ComponentScan("ua.rd.pizzaservice.services")
@Import({RepoConfig.class})
public class AppConfig {

    @Bean
    public BenchmarkBeanPostProcessor benchmarkBeanPostProcessor(){
        return new BenchmarkBeanPostProcessor();
    }

}
