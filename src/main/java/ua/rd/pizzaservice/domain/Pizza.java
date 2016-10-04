package ua.rd.pizzaservice.domain;

import java.math.BigDecimal;

/**
 * @author Anton_Mishkurov
 */
public class Pizza {
    private Integer id;
    private BigDecimal price;
    private String name;
    private Type type;

    public Pizza(Integer id, BigDecimal price, String name, Type type) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }

    public enum Type {
        VEGETARIAN, SEA, MEAT;
    }
}
