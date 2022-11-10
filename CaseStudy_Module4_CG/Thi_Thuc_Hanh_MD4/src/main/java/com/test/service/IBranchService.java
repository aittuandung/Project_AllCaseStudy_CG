package com.test.service;


import com.test.model.Branch;

import java.util.List;

public interface IBranchService {

    public List<Branch> findAll();
    public Branch findById(long id);
}