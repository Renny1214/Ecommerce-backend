package com.caseStudy.caseStudy.models.products;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Entity
public class PriceColorAndImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;
    private String color;

    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;

    @OneToMany(cascade = CascadeType.ALL)
    private List<StockAndSize> stockAndSizes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getImage5() {
        return image5;
    }

    public void setImage5(String image5) {
        this.image5 = image5;
    }

    public List<StockAndSize> getStockAndSizes() {
        return stockAndSizes;
    }

    public void setStockAndSizes(List<StockAndSize> stockAndSizes) {
        this.stockAndSizes = stockAndSizes;
    }
}
