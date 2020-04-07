package com.caseStudy.caseStudy.doa;

import com.caseStudy.caseStudy.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
