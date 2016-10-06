package ua.rd.pizzaservice.services;

/**
 * @author Anton_Mishkurov
 */
public class Test2SomeService implements SomeService {
    @Override
    public String getString() {
        return "TestChildContext";
    }
}
