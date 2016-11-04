package ua.rd.pizzaservice.web.infrastructure;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Anton_Mishkurov
 */
public interface HandlerMapping {
    MyController getController(HttpServletRequest request);
}
