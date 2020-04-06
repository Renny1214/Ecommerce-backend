package com.caseStudy.caseStudy.doa;

import com.caseStudy.caseStudy.models.Sellers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends CrudRepository<Sellers, Long> {
    Optional<Sellers> findByEmail(String email);
}
