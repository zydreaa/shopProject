package product;

import java.util.ArrayList;
import java.util.UUID;

public class Product {
    private final UUID id;
    private String name;
    private Double price;
    private Long quantity;
    private String description;
    private Boolean isAvailable;

    public  Product(){
        this.id = UUID.randomUUID();
    }

    public Product(String name, Double price, Long quantity, String description, Boolean isAvailable) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.isAvailable = isAvailable;
    }

    public Product(UUID id, String name, Double price, Long quantity, String description, Boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.isAvailable = isAvailable;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    private ArrayList<Product> products = new ArrayList<>();

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name +
                ", price=" + price +
                ", quantity=" + quantity +
                ", description='" + description +
                ", isAvailable=" + isAvailable +
                '\n';
    }
}
