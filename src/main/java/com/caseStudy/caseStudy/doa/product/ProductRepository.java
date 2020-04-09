package com.caseStudy.caseStudy.doa.product;

import com.caseStudy.caseStudy.models.products.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    ArrayList<Product> findAllByCategoryIgnoreCase(String category);
    ArrayList<Product> findAllBySubcategoryIgnoreCase(String subcategory);
    ArrayList<Product> findAllByCategoryAndSubcategoryIgnoreCase(String category,String subCategory);
    ArrayList<Product> findAllByBrandIgnoreCase(String brand);
    ArrayList<Product> findAllByNameIgnoreCase(String name);

    @Query(value = "select Product.id,Product.brand,Product.category,Product.gender,Product.name,Product.stars,Product.subcategory,PriceColorAndImages.image1,PriceColorAndImages.price from Product inner join PriceColorAndImages where PriceColorAndImages.price>=(:price1) and PriceColorAndImages.price<=(:price2) and Product.category=(:category)",nativeQuery = true)
    ArrayList<Product> getByCategoryAndPriceCustomIgnoreCase(@Param("category") String category,
                                             @Param("price1") double price1,
                                             @Param("price2") double price2);

    @Query(value = "select Product.id,Product.brand,Product.category,Product.gender,Product.name,Product.stars,Product.subcategory,PriceColorAndImages.image1,PriceColorAndImages.price from Product inner join PriceColorAndImages where PriceColorAndImages.price>=(:price1) and PriceColorAndImages.price<=(:price2) and Product.subcategory=(:subcategory)",nativeQuery = true)
    ArrayList<Product> getBySubcategoryAndPriceCustomIgnoreCase(@Param("subcategory") String subcategory,
                                                @Param("price1") double price1,
                                                @Param("price2") double price2);
}
