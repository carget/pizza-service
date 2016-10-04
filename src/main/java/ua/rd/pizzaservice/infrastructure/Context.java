package ua.rd.pizzaservice.infrastructure;

/**
 * @author Anton_Mishkurov
 */
public interface Context {
    <T> T getBean(String name);
}
