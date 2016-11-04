package ua.rd.pizzaservice.web.infrastructure;

import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Anton_Mishkurov
 */
public class DispatcherServlet extends HttpServlet {

    private ConfigurableApplicationContext[] rootSpringContexts;
    private ConfigurableApplicationContext webContext;

    @Override
    public void init() throws ServletException {
        String[] rootContexts = getRootContextsNames();
        rootSpringContexts = createRootSpringContexts(rootContexts);
        webContext = createWebSpringContext(rootSpringContexts);
    }

    private String[] getRootContextsNames() {
        String contexts = getServletContext().getInitParameter("contextConfigLocation");
        String[] contextsNames = contexts.split(" ");
        return contextsNames;
    }

    private ConfigurableApplicationContext[] createRootSpringContexts(String[] contexts) throws BeansException {
        ConfigurableApplicationContext[] applicationContexts
                = new ConfigurableApplicationContext[contexts.length];

        for (int i = 0; i < applicationContexts.length; i++) {
            ConfigurableApplicationContext context;
            if (i == 0) {
                context = new ClassPathXmlApplicationContext(contexts[i]);
            } else {
                context = new ClassPathXmlApplicationContext(
                        new String[]{contexts[i]},
                        applicationContexts[i - 1]);
            }
            applicationContexts[i] = context;
        }
        return applicationContexts;
    }

    private ConfigurableApplicationContext createWebSpringContext(ConfigurableApplicationContext[] rootContexts) throws BeansException {
        String webContextConfigLocation = getInitParameter("contextConfigLocation");
        if (rootContexts.length == 0) {
            return new ClassPathXmlApplicationContext(webContextConfigLocation);
        } else {
            return new ClassPathXmlApplicationContext(
                    new String[]{webContextConfigLocation},
                    rootContexts[rootContexts.length - 1]
            );
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        String url = request.getRequestURI();
//        String controllerName = getControllerName(url);

        HandlerMapping handlerMapping = webContext.getBean("handleMappingStrategy", HandlerMapping.class);
        MyController controller = handlerMapping.getController(request);

//        MyController controller
//                = webContext.getBean(controllerName, MyController.class); //getController(controllerName);
        if (controller != null) {
            controller.handleRequest(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private String getControllerName(String url) {
        return url.substring(url.lastIndexOf("/"));
    }

    @Override
    public void destroy() {
        webContext.close();
        for (int i = rootSpringContexts.length - 1; i >= 0; i--) {
            rootSpringContexts[i].close();
        }
    }

}

