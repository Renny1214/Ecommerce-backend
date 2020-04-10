package com.caseStudy.caseStudy.doa;

import com.caseStudy.caseStudy.models.Cart;
import com.caseStudy.caseStudy.models.products.Product;
import com.caseStudy.caseStudy.models.users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    ArrayList<Cart> findAllByUser(users user);
    void deleteByUserAndProducts(users user, Product product);
    Optional<Cart> findByUserAndProducts(users user,Product product);
}
