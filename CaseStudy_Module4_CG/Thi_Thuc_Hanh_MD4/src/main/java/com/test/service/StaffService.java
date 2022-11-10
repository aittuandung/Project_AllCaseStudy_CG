package com.test.service;
import com.test.model.Staff;
import com.test.repository.StaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StaffService implements IStaffService {
    @Autowired
    StaffRepo staffRepo;

    @Override
    public List<Staff> findAll() {
        return (List<Staff>) staffRepo.findAll();
    }

    @Override
    public Page<Staff> findAll(Pageable pageable) {
        return staffRepo.findAll(pageable);
    }


    @Override
    public void save(Staff staff) {
        staffRepo.save(staff);
    }

    @Override
    public void delete(long id) {
        staffRepo.deleteById(id);
    }

    @Override
    public Staff findById(long id) {
        return staffRepo.findById(id).get();
    }

    @Override
    public ArrayList<Staff> findByName(String name) {
        return null;
    }

    @Override
    public List<Staff> sortsalary() {
        List<Staff> list = findAll();
        list.sort(Comparator.comparing(Staff::getSalary));
        return list;
    }

    @Override
    public List<Staff> sortage() {
        List<Staff> list = findAll();
        list.sort(Comparator.comparing(Staff::getAge));
        return list;
    }

}