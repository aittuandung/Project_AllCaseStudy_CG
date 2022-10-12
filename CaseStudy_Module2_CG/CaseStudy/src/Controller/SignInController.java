package Controller;

import Model.*;
import Validate.ValidateAcc;
import View.AdminView;
import View.CustomerView;
import View.SignIn;
import constant.AccountConstant;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SignInController {

    String state="login";
    SignIn signIn;
    ArrayList<Account> accounts;
    ComputerController computerController;
    Account account=null;
    ComputerTable computerTable ;
    ArrayList<Computer> computers;
    ValidateAcc validateAcc;
    Computer computer;

    AccountTable accountTable;

    StaffAccTable staffAccTable;
    AccountController accountController;


    public SignInController(ValidateAcc validateAcc, ComputerController computerController, ComputerTable computerTable, AccountController accountController, AccountTable accountTable, StaffAccTable staffAccTable) {
        this.accounts = accountController.getAccounts();
        this.validateAcc=validateAcc;
        this.computerController=computerController;
        this.computers=computerController.getComputers();
        this.computerTable=computerTable;
        this.accountController=accountController;
        this.accountTable=accountTable;
        this.staffAccTable =staffAccTable;

    }

    public  void signIn(){
            signIn=new SignIn(this);

    }



    int c=0;
    public  void validate(String userName, String pass){
        account=validateAcc.validate(userName,pass,accounts);
        if (account!=null){
            if (account.getLoginStatus()==AccountConstant.CHUA_DANG_NHAP){
                account.setLoginStatus(AccountConstant.DA_DANG_NHAP);
                JOptionPane.showMessageDialog(null,"Đăng nhập thành công");
                signIn.setVisible(false);

                switch (account.getPermisson()){
                    case AccountConstant.ADMIN:
                    case AccountConstant.STAFF:
                        new AdminView(computerTable,account, computerController,this,accountController,accountTable,staffAccTable);
                        break;
                    case AccountConstant.USER:

                        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("hh:mm - dd/MM");
                        LocalDateTime now=LocalDateTime.now();
                            if(computer==null){
                                do {
                                    int index= (int) (Math.random()*(computerController.getComputers().size()));
                                    computer=computers.get(index);
                                }while (computer.getUsedBY()!=null);
                            }

                            new CustomerView(computer,account,dateTimeFormatter.format(now),this,accountController,computerController);

                        break;

                }
                JOptionPane.showMessageDialog(null,"Xin chào "+userName);
            }else {
                JOptionPane.showMessageDialog(null,"Tài khoản đã được đang nhập ở nơi khác");
            }




        }else {

           if (state.equals("login")){
               JOptionPane.showMessageDialog(null,"Username hoặc password không đúng");
           }else {
                signIn.getUserName().setText("");
                signIn.getPasswordField().setText("");
                state="login";
                signIn.setVisible(true);

           }
        }

    }
    public  void  logOut(JFrame jFrame){
        jFrame.setVisible(false);
        this.account=null;
        state="logout";

        validate(null,null);
    }

    public Account getAccount() {
        return account;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }
}


