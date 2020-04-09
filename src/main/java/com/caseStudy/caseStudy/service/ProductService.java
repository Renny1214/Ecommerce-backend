package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.product.ProductRepository;
import com.caseStudy.caseStudy.doa.SellerRepository;
import com.caseStudy.caseStudy.models.Sellers;
import com.caseStudy.caseStudy.models.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;

@Service
public class ProductService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private ProductRepository productRepository;

    public boolean addProduct(Product product, Principal principal){
        Optional<Sellers> sellers=sellerRepository.findByEmail(principal.getName());

        if(sellers.isPresent()){
            product.setNoOfBuyers(0);
            product.setStars(0);
            product.setSellers(sellers.get());

            productRepository.save(product);

            return true;
        }

        return false;
    }

    public Product getProduct(Long id){
        return productRepository.findById(id).get();
    }

    public ArrayList<Product> getCategoryInformation(String category){
        return productRepository.findAllByCategoryIgnoreCase(category);
    }

    public ArrayList<Product> getSubCategoryInformation(String category,String subCategory){
        return productRepository.findAllByCategoryAndSubcategoryIgnoreCase(category,subCategory);
    }

    public ArrayList<Product> getByCategoryAndPrice(String category,double price1,double price2){
        return productRepository.getByCategoryAndPriceCustomIgnoreCase(category,
                Math.min(price1,price2),
                Math.max(price1,price2));
    }

    public ArrayList<Product> getBySubcategoryAndPriceBetween(String subcategory,double price1,double price2){
        return productRepository.getBySubcategoryAndPriceCustomIgnoreCase(subcategory,
                Math.min(price1,price2),
                Math.max(price1,price2));
    }

    public boolean deleteProduct(Long id,Principal principal){
        Optional<Sellers> sellers=sellerRepository.findByEmail(principal.getName());

        if(sellers.isPresent()){
            productRepository.deleteById(id);

            return true;
        }

        return false;
    }

    public boolean editProduct(Product product,Principal principal){
        Optional<Sellers> sellers=sellerRepository.findByEmail(principal.getName());

        if(sellers.isPresent()){
            productRepository.save(product);

            return true;
        }

        return false;
    }

    public Set<Product> getItemFromSearch(String search){
        Set<Product> result=new HashSet<>();
        SearchService searchService=new SearchService();
        String[] words=search.split(" ");

        for(int i=0;i<words.length;i++){
            result.addAll(searchService.search(words[i]));
        }

        return result;
    }
}
