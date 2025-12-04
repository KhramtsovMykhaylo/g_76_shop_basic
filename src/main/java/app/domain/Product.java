package app.domain;

import java.util.Objects;

public class Product {
    private Long id;
    private String title;
    private double price;
    private boolean active;

    public String getTitle() {
        return title;
    }

    public boolean isActive() {
        return active;
    }

    public Long getId() {
        return id;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getPrice() {
        return price;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("Product: id - %d, title -%s, price - %.2f, active -%s",
                id, title, price, active ? "yes" : "no");
    }
}
