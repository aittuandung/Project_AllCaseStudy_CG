package Controller;

import Model.Account;
import io.ReadAndWrite;

import java.util.ArrayList;

public class AccountController {

    private ReadAndWrite<Account> readAndWrite=new ReadAndWrite<>();

    private String path="/mnt/54805B1C158071A5/JetBrain-CodeGym/CodeGym_AllCaseStudy/CaseStudy_Module2_CG/CaseStudy/data/accounts.csv";
    private ArrayList<Account>accounts=readAndWrite.read(path);


//   {
//        accounts.add(new Account("hauhc1203","abcd1234","0345066663","hauhc1203@gmail.com","Hoàng Công Hậu",24, AccountConstant.ADMIN));
//        accounts.add(new Account("haimt1234","abcd1234","0345123123","hai.mt@gmail.com","Mai Thanh Hải",18, AccountConstant.STAFF));
//        accounts.add(new Account("thechot1","codeban1"));
//    }

    public void changePass(Account account,String newPass){
        account.setPassWord(newPass);
    }

    public synchronized void save(){

        readAndWrite.write(accounts,path);

    }

    public int getIndexByName(String name){

        for (int i = 0; i <accounts.size() ; i++) {
            if (accounts.get(i).getUserName().equals(name)){
                return i;
            }
        }
        return -1;
    }


    public Account createA(String uName,String pass){

       return new Account(uName,pass);
    }
    public void addA(Account account){
       accounts.add(account);
    }
    public void delete(int index){
        accounts.remove(index);
    }

    public ArrayList<Account> read(){
        return readAndWrite.read(path);
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}
