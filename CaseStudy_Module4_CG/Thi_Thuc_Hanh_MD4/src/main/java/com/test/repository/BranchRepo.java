package com.test.repository;

import com.test.model.Branch;
import org.springframework.data.repository.CrudRepository;

public interface BranchRepo extends CrudRepository<Branch, Long> {
}