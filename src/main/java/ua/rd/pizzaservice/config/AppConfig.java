package ua.rd.pizzaservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ua.rd.pizzaservice.domain.discounts.Discount;
import ua.rd.pizzaservice.domain.discounts.DiscountByCard;
import ua.rd.pizzaservice.domain.discounts.DiscountByQty;
import ua.rd.pizzaservice.infrastructure.BenchmarkBeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

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

    @Bean("discountList")
    public List<Discount> discountList(){
        List<Discount> discounts = new ArrayList<>();
        discounts.add(new DiscountByQty());
        discounts.add(new DiscountByCard());
        return discounts;
    }

}
