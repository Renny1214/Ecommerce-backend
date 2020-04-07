package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.ProductRepository;
import com.caseStudy.caseStudy.doa.SellerRepository;
import com.caseStudy.caseStudy.models.Product;
import com.caseStudy.caseStudy.models.Sellers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;

    public boolean addProduct(String productJSON, Principal principal){
        Optional<Sellers> sellers=sellerRepository.findByEmail(principal.getName());

        if(sellers.isPresent()){
            JSONObject jsonObject=new JSONObject(productJSON);
            JSONArray jsonArray=jsonObject.getJSONArray("size");

            for(int i=0;i<jsonArray.length();i++){
                JSONObject temp= (JSONObject) jsonArray.get(i);
                Product product=new Product();

                product.setSize(temp.getString("size"));
                product.setUnitsInStock(Integer.parseInt(temp.getString("unitsInStock")));

                product.setName(jsonObject.getString("name"));
                product.setBrand(jsonObject.getString("brand"));
                product.setCategory(jsonObject.getString("category"));
                product.setSubcategory(jsonObject.getString("subcategory"));
                product.setGender(jsonObject.getString("gender"));
                product.setStars(0);
                product.setNoOfBuyers(0);
                product.setColor(jsonObject.getString("color"));
                product.setSellers(sellers.get());

                productRepository.save(product);
            }

            return true;
        }

        return false;
    }
}
