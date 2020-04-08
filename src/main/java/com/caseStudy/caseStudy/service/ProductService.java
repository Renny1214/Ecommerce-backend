package com.caseStudy.caseStudy.service;

import com.caseStudy.caseStudy.doa.product.PriceColorAndImagesRepository;
import com.caseStudy.caseStudy.doa.product.ProductRepository;
import com.caseStudy.caseStudy.doa.SellerRepository;
import com.caseStudy.caseStudy.doa.product.StockAndSizeRepository;
import com.caseStudy.caseStudy.models.Sellers;
import com.caseStudy.caseStudy.models.products.PriceColorAndImages;
import com.caseStudy.caseStudy.models.products.Product;
import com.caseStudy.caseStudy.models.products.StockAndSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PriceColorAndImagesRepository priceColorAndImagesRepository;
    @Autowired
    private StockAndSizeRepository stockAndSizeRepository;

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

}
