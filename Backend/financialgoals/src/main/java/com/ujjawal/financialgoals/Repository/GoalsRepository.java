package com.ujjawal.financialgoals.Repository;

import com.ujjawal.financialgoals.Entity.Goals;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GoalsRepository extends MongoRepository<Goals,String> {
    Optional<Goals> findById(String goal);
}
