package ua.rd.pizzaservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Anton_Mishkurov
 */
@Entity
public class Pizza implements Serializable {
    @TableGenerator(name = "pizzaGen", allocationSize = 10, initialValue = 1000,
            pkColumnName = "GEN_NAME", pkColumnValue = "NEXT_PIZZA_ID", valueColumnName = "NEXT_VAL")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pizzaGen")
    private Long id;
    private BigDecimal price;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Pizza() {
    }

    public Pizza(Long id, BigDecimal price, String name, Type type) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        VEGETARIAN, SEA, MEAT
    }
}
