package com.caseStudy.caseStudy.models.products;

import com.caseStudy.caseStudy.models.Sellers;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String category;
    private String subcategory;
    private double stars;
    private int noOfBuyers;
    private String gender;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    private Sellers sellers;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PriceColorAndImages> priceColorAndImages;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public int getNoOfBuyers() {
        return noOfBuyers;
    }

    public void setNoOfBuyers(int noOfBuyers) {
        this.noOfBuyers = noOfBuyers;
    }

    public Sellers getSellers() {
        return sellers;
    }

    public void setSellers(Sellers sellers) {
        this.sellers = sellers;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PriceColorAndImages> getPriceColorAndImages() {
        return priceColorAndImages;
    }

    public void setPriceColorAndImages(List<PriceColorAndImages> priceColorAndImages) {
        this.priceColorAndImages = priceColorAndImages;
    }
}
