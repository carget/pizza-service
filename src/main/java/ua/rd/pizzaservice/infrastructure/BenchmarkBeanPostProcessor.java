package ua.rd.pizzaservice.infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anton_Mishkurov
 */
public class BenchmarkBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return createBeanProxy(o);
    }

    private Object createBeanProxy(Object bean) {

        if (!isAnnotatedByBenchmark(bean)) return bean;

        System.out.println("Created proxy " + bean);
        final Object original = bean;
        Class<?>[] declaredInterfaces = getDeclaredInterfaces(bean);
        bean = Proxy.newProxyInstance(bean.getClass().getClassLoader(), declaredInterfaces,
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
                            System.out.println("Class:" + proxy.getClass()
                                    + "Method (" + method.getName() + ") nanoseconds: " +
                                    Math.abs(System.nanoTime() - start));
                            return result;
                        } else {
                            return method.invoke(original, args);
                        }
                    }
                }
        );
        return bean;
    }

    private Class<?>[] getDeclaredInterfaces(Object o) {
        List<Class<?>> interfaces = new ArrayList<>();
        Class<?> klazz = o.getClass();
        while (klazz != null) {
            Collections.addAll(interfaces, klazz.getInterfaces());
            klazz = klazz.getSuperclass();
        }
        return interfaces.stream().toArray(Class<?>[]::new);
    }

    private boolean isAnnotatedByBenchmark(Object bean) {
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
