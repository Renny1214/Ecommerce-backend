package com.caseStudy.caseStudy.doa.product;

import com.caseStudy.caseStudy.models.products.StockAndSize;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockAndSizeRepository extends CrudRepository<StockAndSize, Long> {
}
