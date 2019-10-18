package com.caseStudy.caseStudy.doa;


import com.caseStudy.caseStudy.models.Cart;
import com.caseStudy.caseStudy.models.products;
import com.caseStudy.caseStudy.models.users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart,Long> {
    ArrayList<Cart> findAllByUser(Optional<users> user);
     void deleteByUserAndProducts(Optional<users> user,Optional<products> products);

    Optional<Cart> findByUserAndProducts(Optional<users> user, Optional<products> product);
}
