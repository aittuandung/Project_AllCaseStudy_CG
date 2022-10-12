package Main;

import Controller.AccountController;
import Controller.ComputerController;
import Controller.SignInController;
import Model.AccountTable;
import Model.ComputerTable;
import Model.StaffAccTable;
import Validate.ValidateAcc;

public class Main extends Thread {
    AccountController accountController;
    ComputerController computerController;
    ComputerTable computerTable;
    AccountTable accountTable;
    StaffAccTable staffAccTable;
    ValidateAcc validateAcc;

    public Main(AccountController accountController, ComputerController computerController, ComputerTable computerTable, AccountTable accountTable, StaffAccTable staffAccTable, ValidateAcc validateAcc) {
        this.accountController = accountController;
        this.computerController = computerController;
        this.computerTable = computerTable;
        this.accountTable = accountTable;
        this.staffAccTable = staffAccTable;
        this.validateAcc = validateAcc;
    }

    @Override
    public void run(){

        SignInController signInController=new SignInController(validateAcc,computerController,computerTable,accountController,accountTable,staffAccTable);
        signInController.signIn();

    }
    public static void main(String[] args) {
        AccountController accountController=new AccountController();

        ComputerController computerController=new ComputerController();

        ComputerTable computerTable=new ComputerTable(computerController.getComputers());
        AccountTable accountTable=new AccountTable(accountController.getAccounts());
        StaffAccTable staffAccTable=new StaffAccTable(accountController.getAccounts());
        ValidateAcc validateAcc=new ValidateAcc();

        Main thread=new Main(accountController,computerController,computerTable,accountTable,staffAccTable,validateAcc);
        Main thread1=new Main(accountController,computerController,computerTable,accountTable,staffAccTable,validateAcc);
        Main thread2=new Main(accountController,computerController,computerTable,accountTable,staffAccTable,validateAcc);

        thread.start();
//        thread1.start();
//        thread2.start();


    }
}
