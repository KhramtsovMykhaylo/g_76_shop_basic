package app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private Long id;
    private String name;
    private boolean active;
    private final List<Product> cart = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Customer customer = (Customer) object;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        String info = String.format("Customer: id - %d, name -%s, active -%s%n",
                id, name, active ? "yes" : "no");
        StringBuilder builder = new StringBuilder(info);
        builder.append("Cart:");
        cart.forEach(x -> builder.append(" ").append(x.getTitle()));
        return builder.toString();
    }
}
