package ua.rd.pizzaservice.domain;

import java.time.LocalDate;

/**
 * @author Anton_Mishkurov
 */
public class Customer {
    private static Long nextId;
    private Long id;
    private String name;
    private String address;
    private DiscountCard discountCard;

    public Customer() {

    }
}
