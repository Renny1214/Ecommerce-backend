package com.caseStudy.caseStudy.doa.product;

import com.caseStudy.caseStudy.models.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    ArrayList<Product> findAllByCategory(String category);
    ArrayList<Product> findAllBySubcategory(String subcategory);
    ArrayList<Product> findAllByCategoryAndSubcategory(String category,String subCategory);
    ArrayList<Product> findAllByBrand(String brand);
    ArrayList<Product> findAllByName(String name);

    @Query(value = "select Product.id,Product.brand,Product.description,Product.category,Product.gender,Product.name,Product.stars,Product.subcategory,Product.no_of_buyers,Product.sellers_id,price_color_and_images.image1,price_color_and_images.price from Product inner join price_color_and_images where price_color_and_images.price>=(:price1) and price_color_and_images.price<=(:price2) and Product.category=(:category)",nativeQuery = true)
    ArrayList<Product> getByCategoryAndPriceCustom(@Param("category") String category,
                                             @Param("price1") double price1,
                                             @Param("price2") double price2);

    @Query(value = "select Product.id,Product.brand,Product.description,Product.category,Product.gender,Product.name,Product.stars,Product.subcategory,Product.no_of_buyers,Product.sellers_id,price_color_and_images.image1,price_color_and_images.price from Product inner join price_color_and_images where price_color_and_images.price>=(:price1) and price_color_and_images.price<=(:price2) and Product.subcategory=(:subcategory)",nativeQuery = true)
    ArrayList<Product> getBySubcategoryAndPriceCustom(@Param("subcategory") String subcategory,
                                                @Param("price1") double price1,
                                                @Param("price2") double price2);
}
