package ua.rd.pizzaservice.domain;

import javax.persistence.*;

/**
 * @author Anton_Mishkurov
 */
@Entity
public class Address {
    @TableGenerator(name = "addressGen", allocationSize = 10, initialValue = 1000,
            pkColumnName = "GEN_NAME", pkColumnValue = "NEXT_ADDRESS_ID", valueColumnName = "NEXT_VAL")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "addressGen")
    private Integer id;
    private String street;
    private Integer house;
    private Integer apartment;

    public Address(Integer id, String street, Integer house, Integer apartment) {
        this.id = id;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public Integer getHouse() {
        return house;
    }

    public Integer getApartment() {
        return apartment;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", house=" + house +
                ", apartment=" + apartment +
                '}';
    }
}
