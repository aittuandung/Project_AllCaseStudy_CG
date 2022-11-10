package com.test.repository;

import com.test.model.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import java.util.ArrayList;

public interface StaffRepo extends PagingAndSortingRepository<Staff, Long> {

}