package Model;

import constant.AccountConstant;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StaffAccTable extends AbstractTableModel {



    private String accCols[]={"TÊN ĐĂNG NHẬP","MẬT KHẨU","SỐ ĐIỆN THOẠI","EMAIL","TÊN","TUỔI","TRẠNG THÁI"};

    private ArrayList<Account> accounts;





    public ArrayList<Account> taiKhoanNV(){
        ArrayList<Account> accounts1=new ArrayList<>();
        for (int i = 0; i <accounts.size() ; i++) {
            if (accounts.get(i).getPermisson()== AccountConstant.STAFF){

                accounts1.add(accounts.get(i));

            }
        }


        return accounts1;

    }
    public StaffAccTable(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public int getRowCount() {
        int count=0;
        for (int i = 0; i <accounts.size() ; i++) {
            if (accounts.get(i).getPermisson()== AccountConstant.STAFF){
                count++;
            }
        }
        return count;
    }

    @Override
    public int getColumnCount() {
        return accCols.length;
    }
    @Override
    public String getColumnName(int column) {
        return accCols[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ArrayList<Account> nv=taiKhoanNV();
        Account account=nv.get(rowIndex);

            switch (columnIndex){
                case 0:
                    return account.getUserName().toUpperCase() ;
                case 1:
                    return account.getPassWord();
                case 2:
                    return account.getPhoneNumber();
                case 3:
                    return account.getEmail();
                case 4:
                    return account.getRealName();

                case 5:
                    return account.getAge();
                case 6:
                    if (account.getLoginStatus()==AccountConstant.CHUA_DANG_NHAP){
                        return "OFFLINE";

                    }else return "ONLINE";
            }

        return null;
    }


    public String[] getAccCols() {
        return accCols;
    }

    public void setAccCols(String[] accCols) {
        this.accCols = accCols;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}
