package com.caseStudy.caseStudy.service;


import com.caseStudy.caseStudy.doa.ProductRepository;
import com.caseStudy.caseStudy.models.products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ArrayList<products> showProducts()
    {
       return (ArrayList<products>)productRepository.findAllByIsActive(true);
    }

    public void add(products product)
    {
        productRepository.save(product);
    }

    public Optional<products> getIdInformation(Long productId)
    {
        return productRepository.findById(productId);
    }
    public ArrayList<products> getCategoryInformation(String category)
    {
        return productRepository.findAllByCategoryAndIsActive(category,true);
    }

    public ArrayList<products> getSubCategoryInformation(String subcategory)
    {
        return productRepository.findAllBySubcategoryAndIsActive(subcategory,true);
    }
    public ArrayList<products> getByPrice(double price1,double price2){
        return productRepository.findAllByPriceBetweenAndIsActive(price1,price2,true);
    }

    public Optional<products> getById(Long id)
    {
        return productRepository.findById(id);
    }

    public String deleteProduct(products products) {
        Long id = products.getProductId();
        Optional<products> old =  productRepository.findById(id);
        old.get().setActive(false);
        productRepository.save(old.get());
        return "\"deletion completed\"";
    }

    public String editProduct(products products)
    {

        Long id = products.getProductId();
        Optional<products> oldProduct =  productRepository.findById(id);
        oldProduct.get().setActive(false);
        productRepository.save(oldProduct.get());

        products.setActive(true);
        products.setProductId(null);
        productRepository.save(products);
        return "\"product Edited\"";
    }

    public Set<products> getItemFromSearch(String value) {
        ArrayList<products> products = (ArrayList<products>) productRepository.findAllByIsActive(true);
        Set<products> result = new HashSet<>();
        for(int i=0;i<products.size();i++)
        {
            if(products.get(i).getName().toLowerCase().contains(value.toLowerCase()) ||
                    products.get(i).getBrand().toLowerCase().contains(value.toLowerCase()) ||
                    products.get(i).getCategory().toLowerCase().contains(value.toLowerCase()) ||
                    products.get(i).getSubcategory().toLowerCase().contains(value.toLowerCase()) ||
                    products.get(i).getDetails().toLowerCase().contains(value.toLowerCase())){
                    result.add(products.get(i));
            }
        }
        return result;
    }
    public ArrayList<products> getByCategoryAndPrice(String category,double price1,double price2){
        System.out.println(productRepository.findAllByCategoryAndPriceBetweenAndIsActive(category,price1,price2,true));
        return (ArrayList<products>)productRepository.findAllByCategoryAndPriceBetweenAndIsActive(category,price1,price2,true);
    }
    public ArrayList<products> getBySubcategoryAndPriceBetween(String subcategory, double price1, double price2) {
        return (ArrayList<products>)productRepository.findAllBySubcategoryAndPriceBetweenAndIsActive(subcategory,price1,price2,true);
    }
}
