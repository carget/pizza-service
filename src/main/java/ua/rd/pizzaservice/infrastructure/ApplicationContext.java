package ua.rd.pizzaservice.infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
        builder.callPostCreateMethod();
        builder.callInitMethod();
        builder.createBeanProxy();
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

        //todo change signature to (Class<?> paramtype)
        private String covertTypeToBeanName(String name) {
            char[] chars = name.toCharArray();
            chars[0] = Character.toLowerCase(chars[0]);
            return new String(chars);
        }

        private void callInitMethod() {
            try {
                type.getMethod("init").invoke(bean);
            } catch (NoSuchMethodException ignored) {
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private void callPostCreateMethod() {
            for (Method method : type.getMethods()) {
                if (method.isAnnotationPresent(PostCreate.class)) {
                    try {
                        method.invoke(bean);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        public void createBeanProxy() {

            if (!isAnnotatedByBenchmark()) return;

            final Object original = bean;
            bean = Proxy.newProxyInstance(bean.getClass().getClassLoader(), bean.getClass().getInterfaces(),
                    new InvocationHandler() {

                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            Class<?>[] types = new Class<?>[args.length];
                            Arrays.stream(args).map(Object::getClass).collect(Collectors.toList()).toArray(types);
                            Method originalMethod = original.getClass().getMethod(method.getName(), types);
                            Benchmark benchmark = originalMethod.getAnnotation(Benchmark.class);
                            if (benchmark != null && benchmark.value()) {
                                long start = System.nanoTime();
                                Object result = method.invoke(original, args);
                                System.out.println("Method (" + method.getName() + ") nanoseconds: " +
                                        Math.abs(System.nanoTime() - start));
                                return result;
                            } else {
                                return method.invoke(original, args);
                            }
                        }
                    }
            );
        }

        private boolean isAnnotatedByBenchmark() {
            Method[] methods = bean.getClass().getMethods();
            for (Method method : methods) {
                Benchmark benchmark = method.getAnnotation(Benchmark.class);
                if (benchmark != null && benchmark.value()) {
                    return true;
                }
            }
            return false;
        }
    }


}
