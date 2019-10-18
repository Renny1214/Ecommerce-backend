package com.caseStudy.caseStudy.doa;


import com.caseStudy.caseStudy.models.history;
import com.caseStudy.caseStudy.models.users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface HistoryRepository extends CrudRepository<history,Long> {
    ArrayList<history> findAllByUser(Optional<users> user);
}
