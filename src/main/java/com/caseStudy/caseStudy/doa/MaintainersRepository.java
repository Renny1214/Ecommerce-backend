package com.caseStudy.caseStudy.doa;

import com.caseStudy.caseStudy.models.Maintainers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaintainersRepository extends CrudRepository<Maintainers,Long> {
    Optional<Maintainers> findByEmail(String email);
}
