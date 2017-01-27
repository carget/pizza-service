package ua.rd.pizzaservice.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.rd.pizzaservice.web.infrastructure.SimpleUrlHandlerMapping;

/**
 * @author Anton_Mishkurov
 */
@Configuration
@ComponentScan("ua.rd.pizzaservice.web")
public class MyWebConfig {

    @Bean
    public SimpleUrlHandlerMapping handlerMappingStrategy() {
        return new SimpleUrlHandlerMapping();
    }
}
