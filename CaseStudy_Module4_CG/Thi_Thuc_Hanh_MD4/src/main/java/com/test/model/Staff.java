package com.test.model;

import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.validation.Validator;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Staff  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String maNhanVien;
    @NotEmpty(message = "Tên Không Được Để Trống")
    @Size(min = 3, max = 15, message = "Tối Thiểu 3 Ký Tự - Tối Đa 15 Ký Tự")
    private String name;
    @Min(value = 18,message = "Tuổi Phải Lớn Hơn 18")
    @Max(value = 55,message = "Tuổi Phải Nhỏ Hơn 55")
    private int age;
    @NumberFormat(pattern = "500" + "USD")
    private double salary;

    @ManyToOne
    private Branch branch;

    public Staff() {
    }

    public Staff(String maNhanVien, String name, int age, double salary, Branch branch) {
        this.maNhanVien = maNhanVien;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.branch = branch;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }


}