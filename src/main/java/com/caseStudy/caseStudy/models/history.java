package com.caseStudy.caseStudy.models;


import com.caseStudy.caseStudy.models.products.Product;

import javax.annotation.Generated;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class history {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long historyId;

    Date date;

    @ManyToOne
    private Product products;

    @ManyToOne
    private users user;

    public Long getHistoryId() {
        return historyId;
    }
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setHistoryId(Long historyId) {
        this.historyId = historyId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public users getUser() {
        return user;
    }

    public void setUser(users user) {
        this.user = user;
    }
}
