package ua.rd.pizzaservice.infrastructure;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anton_Mishkurov
 */
public class ApplicationContext implements Context {

    private Config config;
    private Map<String, Object> beans = new HashMap<>();

    public ApplicationContext(Config config) {
        this.config = config;
    }

    @Override
    public <T> T getBean(String name) {
        Class<?> type = config.getImpl(name);
        Object bean = beans.get(name);

        if (bean != null) {
            return (T) bean;
        }

        BeanBuilder builder = new BeanBuilder(type);
        builder.createBean();
        //configuration
//      builder.createBeanProxy();
//      builder.callPostCreateMethod();
//      builder.callInitMethod();
        bean = builder.build();

        beans.put(name, bean);

        return (T) bean;

    }

    private class BeanBuilder {
        private Class<?> type;
        private Object bean;

        public BeanBuilder(Class<?> type) {
            this.type = type;
        }

        private <T> T build() {
            return (T) bean;
        }

        private <T> T createBean() {
            Constructor<?> constructor = type.getConstructors()[0];
            bean = getBeanFromConstructor(constructor);
            return (T) bean;
        }

        private <T> T getBeanFromConstructor(Constructor<?> constructor) {
            try {
                int parameterCount = constructor.getParameterCount();
                Object[] params = new Object[parameterCount];
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                for (int i = 0; i < parameterCount; i++) {
                    String beanName = covertTypeToBeanName(parameterTypes[i].getSimpleName());
                    params[i] = getBean(beanName);
                }
                return (T) constructor.newInstance(params);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

        private String covertTypeToBeanName(String name) {
            char[] chars = name.toCharArray();
            chars[0] = Character.toLowerCase(chars[0]);
            return new String(chars);
        }

    }


}
