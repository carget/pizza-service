package ua.rd.pizzaservice.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

/**
 * @author Anton_Mishkurov
 */
@Configuration
@ComponentScan("ua.rd.pizzaservice.web.rest")
@EnableWebMvc
public class RestConfig {}
