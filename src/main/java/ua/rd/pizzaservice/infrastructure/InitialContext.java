package ua.rd.pizzaservice.infrastructure;

/**
 * @author Anton_Mishkurov
 */
public class InitialContext {

    private static final Config config = new JavaConfig();

    public <T> T getInstance(String name) {
        Class<?> type = config.getImpl(name);
        //todo check null
        try {
            return (T) type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
