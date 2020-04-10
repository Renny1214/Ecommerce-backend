package com.caseStudy.caseStudy.doa.product;

import com.caseStudy.caseStudy.models.products.PriceColorAndImages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceColorAndImagesRepository extends CrudRepository<PriceColorAndImages, Long> {
}
