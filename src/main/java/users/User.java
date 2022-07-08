package users;

import java.util.UUID;

public class User {
    private final UUID id;
    private final String username;
    private final String pin;
    private Double balance;

    public User(UUID id, String username, String pin, Double balance) {
        this.id = id;
        this.username = username;
        this.pin = pin;
        this.balance = balance;
    }

    public User(String username, String pin, Double balance) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.pin = pin;
        this.balance = balance;
    }

    protected boolean verifyPin(String pin){
        return this.pin.equals(pin);
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
