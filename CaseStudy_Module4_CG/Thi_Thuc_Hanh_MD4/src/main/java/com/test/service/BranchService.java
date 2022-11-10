package com.test.service;

import com.test.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;
import com.test.repository.BranchRepo;

import java.util.List;

public class BranchService implements IBranchService{
    @Autowired
    BranchRepo branchRepo;
    @Override
    public List<Branch> findAll() {
        return (List<Branch>) branchRepo.findAll();
    }

    @Override
    public Branch findById(long id) {
        return branchRepo.findById(id).get();
    }
}



