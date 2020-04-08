package com.caseStudy.caseStudy.doa;

import com.caseStudy.caseStudy.models.Product;
import com.caseStudy.caseStudy.models.Sellers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    ArrayList<Product> findAllByNameAndBrandAndSellers(String name, String brand, Sellers sellers);
}
