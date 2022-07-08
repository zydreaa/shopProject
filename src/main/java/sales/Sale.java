package sales;

import product.Product;
import users.User;

import javax.jws.soap.SOAPBinding;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

public class Sale {
    private UUID id;
    private Product product;
    private User user;
    private Double totalCost;
    private Integer totalAmount;
    private LocalDateTime purchaseDate;

    public Sale(Product product, User user, Double totalCost, Integer totalAmount, LocalDateTime purchaseDate) {
        this.id = UUID.randomUUID();
        this.product = product;
        this.user = user;
        this.totalCost = totalCost;
        this.totalAmount = totalAmount;
        this.purchaseDate = purchaseDate;
    }

    public UUID getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public User getUser() {
        return user;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", product=" + product +
                ", user=" + user +
                ", totalCost=" + totalCost +
                ", quantity=" + totalAmount +
                ", purchaseDate=" + purchaseDate;
    }
}
