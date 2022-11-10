package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.test.model.Staff;
import com.test.service.IBranchService;
import com.test.service.IStaffService;
import com.test.validate.Validate_Name;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StaffController {
    @Autowired
    IBranchService branchService;
    @Autowired
    IStaffService staffService;
    @Autowired
    Validate_Name validateName;

    @GetMapping("/staff")
    public ModelAndView findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "name") String option) {
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("staff", staffService.findAll(PageRequest.of(page, 5, Sort.by(option))));
        modelAndView.addObject("option", option);
        return modelAndView;
    }

    @GetMapping("/creates")
    public ModelAndView createform() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("staff", new Staff());
        modelAndView.addObject("branch", branchService.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createStaff(@Valid @ModelAttribute(value = "staff") Staff staff, BindingResult bindingResult) {
        validateName.validate(staff, bindingResult); // validate trung ten

        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("create");
            modelAndView.addObject("branch", branchService.findAll());
            return modelAndView;
        }
        staffService.save(staff);
        ModelAndView modelAndView = new ModelAndView("redirect:/staff");
        return modelAndView;
    }

    @GetMapping("/edit")
    public ModelAndView editform(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("staff", staffService.findById(id));
        modelAndView.addObject("branch", branchService.findAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView editStaff(@Valid @ModelAttribute(value = "staff") Staff staff,BindingResult bindingResult,@RequestParam long id) {

        if (bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("branch", branchService.findAll());
            return modelAndView;
        }
        staffService.save(staff);
        ModelAndView modelAndView = new ModelAndView("redirect:/staff");
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView deleteform(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("mess", staffService.findById(id));
        return modelAndView;
    }

    @PostMapping("/delete")
    public String delete(@RequestParam long id) {
        staffService.delete(id);
        return "redirect:/staff";
    }

    @PostMapping("/search")
    public ModelAndView searchByName(@RequestParam String search) {
        ModelAndView modelAndView = new ModelAndView("show");
        modelAndView.addObject("staff", staffService.findByName(search));
        return modelAndView;
    }

    @GetMapping("/sortsalary")
    public ModelAndView sortsalary() {
        ModelAndView modelAndView = new ModelAndView("show");
        List<Staff> sortSalary = staffService.sortsalary();
        modelAndView.addObject("staff", sortSalary);
        return modelAndView;

    }

    @GetMapping("/sortage")
    public ModelAndView sortage() {
        ModelAndView modelAndView = new ModelAndView("show");
        List<Staff> sortAge = staffService.sortage();
        modelAndView.addObject("staff", sortAge);
        return modelAndView;

    }

    @GetMapping("/detail")
    public ModelAndView detailForm(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("staff", staffService.findById(id));
        return modelAndView;
    }
}