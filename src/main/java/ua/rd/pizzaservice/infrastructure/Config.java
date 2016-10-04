package ua.rd.pizzaservice.infrastructure;

/**
 * @author Anton_Mishkurov
 */
public interface Config {
    Class<?> getImpl(String name);
}
