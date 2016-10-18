package ua.rd.pizzaservice.domain;

/**
 * @author Anton_Mishkurov
 */
public class Customer {
    private Long id;
    private String name;
    private String address;
    private DiscountCard discountCard;

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
        this.discountCard= new DiscountCard();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }
}
