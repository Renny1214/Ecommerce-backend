package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.ProductRepository;
import com.caseStudy.caseStudy.doa.SellerRepository;
import com.caseStudy.caseStudy.models.Product;
import com.caseStudy.caseStudy.models.Sellers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.security.Principal;
import java.util.*;

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
                product.setPrice(Double.parseDouble(jsonObject.getString("price")));
                product.setDescription(jsonObject.getString("description"));
                product.setNoOfBuyers(0);
                product.setColor(jsonObject.getString("color"));
                product.setSellers(sellers.get());

                productRepository.save(product);
            }

            return true;
        }

        return false;
    }

    public String getProduct(String name,String brand,Long sellerId){
        Optional<Sellers> sellers=sellerRepository.findById(sellerId);

        if(sellers.isPresent()){
            ArrayList<Product> products=productRepository.findAllByNameAndBrandAndSellers(name,brand,sellers.get());

            if(!products.isEmpty()){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("name",products.get(0).getName());
                jsonObject.put("brand",products.get(0).getBrand());
                jsonObject.put("category",products.get(0).getCategory());
                jsonObject.put("subcategory",products.get(0).getSubcategory());
                jsonObject.put("gender",products.get(0).getGender());
                jsonObject.put("noOfBuyers",products.get(0).getNoOfBuyers());
                jsonObject.put("stars",products.get(0).getStars());

                JSONObject sellerObject=getSellerInfo(products.get(0).getSellers());

                jsonObject.put("seller",sellerObject);

                Set<String> sizes=getAvailableSizes(products);
                JSONArray colorArray=getColorsAndImagesAndPrice(products);
                jsonObject.put("colorImagesAndPrice",colorArray);

                Iterator<String> sizeIterator=sizes.iterator();
                JSONArray sizesArray=new JSONArray();
                while (sizeIterator.hasNext()){
                    sizesArray.put(sizeIterator.next());
                }
                jsonObject.put("size",sizesArray);

                return jsonObject.toString();
            }
        }

        return "Product not found";
    }

    private JSONArray getColorsAndImagesAndPrice(ArrayList<Product> products){
        JSONArray jsonArray=new JSONArray();
        Set<String> colorsAdded=new HashSet<>();

        for(Product product: products){
            if(!colorsAdded.contains(product.getColor())){
                colorsAdded.add(product.getColor());

                JSONObject jsonObject=new JSONObject();
                jsonObject.put("color",product.getColor());
                jsonObject.put("images",new JSONArray().put(product.getImage1())
                .put(product.getImage2())
                .put(product.getImage3())
                .put(product.getImage4())
                .put(product.getImage5()));
                jsonObject.put("price",product.getPrice());

                jsonArray.put(jsonObject);
            }
        }

        return jsonArray;
    }
    private Set<String> getAvailableSizes(ArrayList<Product> products){
        Set<String> set=new HashSet<>();

        for(Product product: products){
            if(product.getUnitsInStock() != 0){
                set.add(product.getSize());
            }
        }

        return set;
    }
    private JSONObject getSellerInfo(Sellers sellers){
        return new JSONObject()
                .put("firstName",sellers.getFirstName())
                .put("lastName",sellers.getLastName())
                .put("email",sellers.getEmail())
                .put("address",sellers.getAddress())
                .put("gstNo",sellers.getGstNo());
    }
}
