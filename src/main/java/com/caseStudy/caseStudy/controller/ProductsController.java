package com.caseStudy.caseStudy.controller;

import com.caseStudy.caseStudy.models.products.Product;
import com.caseStudy.caseStudy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductService productService;

    @PostMapping(path = "/addProducts" , produces="application/json")
    public boolean addProduct(@RequestBody Product productJSON, Principal principal) {
        return productService.addProduct(productJSON,principal);
    }

    @GetMapping(path="/id/{id}")
    public Product getId(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping(path="/category/{category}")
    public ArrayList<Product> getCategory(@PathVariable("category") String productCategory) {
        return productService.getCategoryInformation(productCategory);
    }

    @GetMapping(path="/category/{category}/subcategory/{subcategory}")
    public ArrayList<Product> getSubcategory(@PathVariable("subcategory") String subcategory,
                                              @PathVariable("category") String category) {
        return productService.getSubCategoryInformation(category,subcategory);
    }

     @GetMapping(path = "/category/{category}/{price1}/{price2}")
     public ArrayList<Product> getByCategoryAndPriceBetween(@PathVariable("category") String category,
                                                        @PathVariable("price1") double price1,
                                                        @PathVariable("price2") double price2){
        return productService.getByCategoryAndPrice(category,price1,price2);
     }

     @GetMapping(path="/subcategory/{subcategory}/{price1}/{price2}")
     public ArrayList<Product> getBySubcategoryAndPriceBetween(@PathVariable("subcategory")String subcategory,
                                                                @PathVariable("price1") double price1,
                                                                @PathVariable("price2") double price2) {
         return productService.getBySubcategoryAndPriceBetween(subcategory,price1,price2);
     }

    @GetMapping(path="/deleteProduct/{id}")
    public boolean deleteProduct(@PathVariable Long id,Principal principal) {
        return productService.deleteProduct(id,principal);
    }

    @PostMapping(path="/editItem")
    public boolean editProduct(@RequestBody Product products,Principal principal) {
        return productService.editProduct(products,principal);
    }

    @GetMapping(path="/search/{value}")
    public Set<Product> search(@PathVariable("value") String value) {
        return productService.getItemFromSearch(value);
    }
}


