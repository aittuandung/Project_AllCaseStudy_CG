package Model;

import constant.AccountConstant;

import java.io.Serializable;

public class Account implements Serializable {
    private String userName;
    private String passWord;
    private String phoneNumber;
    private String email;

    private String realName;
    private int age;
    private int permisson=AccountConstant.USER;
    private double soDu=10000;

    private int loginStatus=AccountConstant.CHUA_DANG_NHAP;


    public Account(String userName, String passWord, String phoneNumber, String email, String realName, int age, int permisson) {
        this.userName = userName;
        this.passWord = passWord;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.realName = realName;
        this.age = age;
        this.permisson = permisson;
    }

    public Account(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPermisson() {
        return permisson;
    }

    public void setPermisson(int permisson) {
        this.permisson = permisson;
    }

    public double getSoDu() {
        return soDu;
    }

    public void setSoDu(double soDu) {
        this.soDu = soDu;
    }

    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    @Override
    public String toString() {
        String quyen="";
        switch (permisson){
            case AccountConstant.ADMIN :
                quyen="Quản lí";
                break;
            case AccountConstant.STAFF:
                quyen="Nhân viên";
                break;
            case AccountConstant.USER:
                quyen="Người dùng";
                break;
            default:
                break;


        }



        return "Account: " +
                "userName: " + userName +
                ", passWord:" + passWord +
                ", phoneNumber: " + phoneNumber +
                ", email: " + email +
                ", realName:'" + realName +
                ", age: " + age +
                ", permisson: " + quyen+
                "so du: "+soDu
                ;
    }




}
