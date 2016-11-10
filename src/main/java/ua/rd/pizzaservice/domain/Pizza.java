package ua.rd.pizzaservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Anton_Mishkurov
 */
@Entity
public class Pizza implements Serializable {
    @TableGenerator(name = "pizzaGen", allocationSize = 10, initialValue = 5000,
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza)) return false;

        Pizza pizza = (Pizza) o;

        if (id != null && pizza.id != null) {
            return Objects.equals(id, pizza.id);
        } else {
            if (price != null ? !price.equals(pizza.price) : pizza.price != null) return false;
            if (name != null ? !name.equals(pizza.name) : pizza.name != null) return false;
            return type == pizza.type;
        }
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
