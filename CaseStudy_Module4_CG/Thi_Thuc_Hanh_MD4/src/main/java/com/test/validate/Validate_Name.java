package com.test.validate;

import com.test.model.Staff;
import com.test.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class Validate_Name implements Validator {
    @Autowired
    IStaffService staffService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Staff staff = (Staff) target;
        List<Staff> staffList = staffService.findAll();
        for (Staff s : staffList) {
            if (staff.getName().equals(s.getName())) {
                errors.rejectValue("name", "", "tên đã tồn tại, mời nhập tên khác");
                return;
            }
        }
    }
}