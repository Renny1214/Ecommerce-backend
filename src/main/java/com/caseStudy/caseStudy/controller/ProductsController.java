package com.caseStudy.caseStudy.controller;

import com.caseStudy.caseStudy.doa.ProductRepositoryClass;
import com.caseStudy.caseStudy.models.products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductRepositoryClass productRepositoryClass;

    @PostMapping(path = "/addProducts" , produces="application/json")
    public String signUp(@RequestBody products product) {
        product.setActive(true);
        productRepositoryClass.add(product);
        return "\"success\"";
    }

    @GetMapping(path="/showProducts" , produces = "application/json")
    public ArrayList<products> showProducts()
    {
        return productRepositoryClass.showProducts();
    }

    @GetMapping(path="/id/{id}")
    public Optional<products> getId(
                      @PathVariable("id") Long productId)
    {
        return productRepositoryClass.getIdInformation(productId);
    }

    @GetMapping(path="/category/{category}")
    public ArrayList<products> getCategory(@PathVariable("category") String productCategory)
    {
        return productRepositoryClass.getCategoryInformation(productCategory);
    }

    @GetMapping(path="/category/subcategory/{subcategory}")
    public ArrayList<products> getSubcategory(@PathVariable("subcategory") String subcategory)
    {
        return productRepositoryClass.getSubCategoryInformation(subcategory);
    }

    @GetMapping(path = "/price/{price1}/{price2}")
    public ArrayList<products> getItemsByPrice(@PathVariable("price1") double price,
                                               @PathVariable("price2") double price2){
        return productRepositoryClass.getByPrice(price,price2);
    }


     @GetMapping(path = "/category/{category}/{price1}/{price2}")
     public ArrayList<products> getByCategoryAndPriceBetween(@PathVariable("category") String category,
                                                        @PathVariable("price1") double price1,
                                                        @PathVariable("price2") double price2){
        return productRepositoryClass.getByCategoryAndPrice(category,price1,price2);
     }

     @GetMapping(path="/subcategory/{subcategory}/{price1}/{price2}")
     public ArrayList<products> getBySubcategoryAndPriceBetween(@PathVariable("subcategory")String subcategory,
                                                                @PathVariable("price1") double price1,
                                                                @PathVariable("price2") double price2)
     {
         return productRepositoryClass.getBySubcategoryAndPriceBetween(subcategory,price1,price2);
     }

    @PostMapping(path="/deleteProduct")
    public String deleteProduct(@RequestBody products products)
    {
        System.out.println("hello");

        return productRepositoryClass.deleteProduct(products);
    }

    @PostMapping(path="/editItem")
    public String editProduct(@RequestBody products products)
    {
        return productRepositoryClass.editProduct(products);
    }

    @GetMapping(path="/search/{value}")
    public Set<products> search(@PathVariable("value") String value)
    {
        return productRepositoryClass.getItemFromSearch(value);
    }
}


