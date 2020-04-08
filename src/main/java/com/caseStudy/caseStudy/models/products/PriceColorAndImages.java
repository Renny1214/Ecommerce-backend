package com.caseStudy.caseStudy.models.products;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Entity
public class PriceColorAndImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String price;
    private String color;
    private File image1;
    private File image2;
    private File image3;
    private File image4;
    private File image5;

    @OneToMany(cascade = CascadeType.ALL)
    private List<StockAndSize> stockAndSizes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public List<StockAndSize> getStockAndSizes() {
        return stockAndSizes;
    }

    public void setStockAndSizes(List<StockAndSize> stockAndSizes) {
        this.stockAndSizes = stockAndSizes;
    }
}
