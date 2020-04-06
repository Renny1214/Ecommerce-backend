package com.caseStudy.caseStudy.controller;

import com.caseStudy.caseStudy.models.products;
import com.caseStudy.caseStudy.service.ProductService;
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
    ProductService productService;

    @PostMapping(path = "/addProducts" , produces="application/json")
    public String signUp(@RequestBody products product) {
        product.setActive(true);
        productService.add(product);
        return "\"success\"";
    }

    @GetMapping(path="/showProducts" , produces = "application/json")
    public ArrayList<products> showProducts()
    {
        return productService.showProducts();
    }

    @GetMapping(path="/id/{id}")
    public Optional<products> getId(
                      @PathVariable("id") Long productId)
    {
        return productService.getIdInformation(productId);
    }

    @GetMapping(path="/category/{category}")
    public ArrayList<products> getCategory(@PathVariable("category") String productCategory)
    {
        return productService.getCategoryInformation(productCategory);
    }

    @GetMapping(path="/category/subcategory/{subcategory}")
    public ArrayList<products> getSubcategory(@PathVariable("subcategory") String subcategory)
    {
        return productService.getSubCategoryInformation(subcategory);
    }

    @GetMapping(path = "/price/{price1}/{price2}")
    public ArrayList<products> getItemsByPrice(@PathVariable("price1") double price,
                                               @PathVariable("price2") double price2){
        return productService.getByPrice(price,price2);
    }


     @GetMapping(path = "/category/{category}/{price1}/{price2}")
     public ArrayList<products> getByCategoryAndPriceBetween(@PathVariable("category") String category,
                                                        @PathVariable("price1") double price1,
                                                        @PathVariable("price2") double price2){
        return productService.getByCategoryAndPrice(category,price1,price2);
     }

     @GetMapping(path="/subcategory/{subcategory}/{price1}/{price2}")
     public ArrayList<products> getBySubcategoryAndPriceBetween(@PathVariable("subcategory")String subcategory,
                                                                @PathVariable("price1") double price1,
                                                                @PathVariable("price2") double price2)
     {
         return productService.getBySubcategoryAndPriceBetween(subcategory,price1,price2);
     }

    @PostMapping(path="/deleteProduct")
    public String deleteProduct(@RequestBody products products)
    {
        System.out.println("hello");

        return productService.deleteProduct(products);
    }

    @PostMapping(path="/editItem")
    public String editProduct(@RequestBody products products)
    {
        return productService.editProduct(products);
    }

    @GetMapping(path="/search/{value}")
    public Set<products> search(@PathVariable("value") String value)
    {
        return productService.getItemFromSearch(value);
    }
}


