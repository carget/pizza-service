package ua.rd.pizzaservice.services;

/**
 * @author Anton_Mishkurov
 */
public class Test1SomeService implements SomeService {
    @Override
    public String getString() {
        return "TestParentContext";
    }

    public void destroy() {
        System.out.println("Destroy");
    }
}
