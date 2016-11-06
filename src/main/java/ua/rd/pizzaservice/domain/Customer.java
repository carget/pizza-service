package ua.rd.pizzaservice.domain;

import javax.persistence.*;

/**
 * @author Anton_Mishkurov
 */
@Entity
public class Customer implements Cloneable{
    @TableGenerator(name = "customerGen", allocationSize = 10, initialValue = 1000,
            pkColumnName = "GEN_NAME", pkColumnValue = "NEXT_CUSTOMER_ID", valueColumnName = "NEXT_VAL")
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customerGen")
    private Long id;
    private String name;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "discount_id")
    private DiscountCard discountCard;

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    @Override
    public Object clone(){
        Customer newCustomer = null;
        try {
            newCustomer = (Customer) super.clone();
            newCustomer.address= (Address) this.address.clone();
            newCustomer.discountCard = (DiscountCard) this.discountCard.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newCustomer;
    }
}
