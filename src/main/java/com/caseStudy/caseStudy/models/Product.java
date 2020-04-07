package com.caseStudy.caseStudy.models;

import javax.persistence.*;
import java.io.File;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String category;
    private String subcategory;
    private String color;
    private double stars;
    private int noOfBuyers;
    private int unitsInStock;
    private String gender;
    private File image1;
    private File image2;
    private File image3;
    private File image4;
    private File image5;
    private String size;

    @ManyToOne
    private Sellers sellers;

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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public File getImage1() {
        return image1;
    }

    public void setImage1(File image1) {
        this.image1 = image1;
    }

    public File getImage2() {
        return image2;
    }

    public void setImage2(File image2) {
        this.image2 = image2;
    }

    public File getImage3() {
        return image3;
    }

    public void setImage3(File image3) {
        this.image3 = image3;
    }

    public File getImage4() {
        return image4;
    }

    public void setImage4(File image4) {
        this.image4 = image4;
    }

    public File getImage5() {
        return image5;
    }

    public void setImage5(File image5) {
        this.image5 = image5;
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
}
