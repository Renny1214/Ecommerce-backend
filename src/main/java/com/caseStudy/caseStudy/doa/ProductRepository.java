package com.caseStudy.caseStudy.doa;

import com.caseStudy.caseStudy.models.products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductRepository extends CrudRepository<products, Long> {
    ArrayList<products> findAllByCategoryAndIsActive(String Category, Boolean isActive);

    ArrayList<products> findAllByCategoryAndPriceBetweenAndIsActive(String category, double price1, double price2, Boolean isActive);

    ArrayList<products> findAllBySubcategoryAndPriceBetweenAndIsActive(String subcategory, double price1, double price2, Boolean isActive);

    ArrayList<products> findAllBySubcategoryAndIsActive(String subcategory, Boolean isActive);

    ArrayList<products> findAllByPriceBetweenAndIsActive(double price1, double price2, Boolean isActive);

    ArrayList<products> findAllByIsActive(Boolean isActive);


}
