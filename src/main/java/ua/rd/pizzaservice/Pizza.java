package ua.rd.pizzaservice;

import java.math.BigDecimal;

/**
 * @author Anton_Mishkurov
 */
public class Pizza {
    private Integer id;
    private BigDecimal price;
    private String name;
    private PizzaType type;

    public Pizza(Integer id, BigDecimal price, String name, PizzaType type) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public PizzaType getType() {
        return type;
    }

    public void setType(PizzaType type) {
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
}
