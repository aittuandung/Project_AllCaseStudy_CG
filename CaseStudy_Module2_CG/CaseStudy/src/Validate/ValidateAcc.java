package Validate;

import Model.Account;

import java.util.ArrayList;

public class ValidateAcc {

    public Account validate(String username, String pass, ArrayList<Account> accounts){
        for (int i = 0; i < accounts.size() ; i++) {
            Account account=accounts.get(i);
            if (account.getUserName().equals(username)&&account.getPassWord().equals(pass)){
                return account;
            }
        }
        return null;
    }

}
