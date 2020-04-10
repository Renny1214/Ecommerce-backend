package com.caseStudy.caseStudy.models;

import javax.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cartId;
//    private int quantity;
//    @ManyToOne
//    private products products;
//    @ManyToOne
//    private users user;
//
//    public Long getCartId() {
//        return cartId;
//    }
//
//    public void setCartId(Long cartId) {
//        this.cartId = cartId;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public com.caseStudy.caseStudy.models.products getProducts() {
//        return products;
//    }
//
//    public void setProducts(com.caseStudy.caseStudy.models.products products) {
//        this.products = products;
//    }
//
//    public users getUser() {
//        return user;
//    }
//
//    public void setUser(users user) {
//        this.user = user;
//    }
}
