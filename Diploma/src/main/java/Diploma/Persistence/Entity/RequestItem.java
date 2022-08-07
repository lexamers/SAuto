package Diploma.Persistence.Entity;

import javax.persistence.*;
import java.util.Objects;


public class RequestItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name="reguest_id", referencedColumnName = "id")
    private Request request;

    @Column
    private Long quantity;

    public RequestItem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestItem that = (RequestItem) o;
        return Objects.equals(id, that.id)&& Objects.equals(request, that.request) && Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, request, quantity);
    }
}


