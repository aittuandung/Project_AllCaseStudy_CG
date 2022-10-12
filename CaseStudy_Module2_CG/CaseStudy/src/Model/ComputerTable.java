package Model;

import constant.ComputerConstant;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ComputerTable extends AbstractTableModel {
    private String coputerCols[]={"TÊN","CHẤT LƯỢNG","TIỀN/H","TRẠNG THÁI","NGƯỜI DÙNG"};
    private ArrayList<Computer> computers;

    public String[] getCoputerCols() {
        return coputerCols;
    }

    public void setCoputerCols(String[] coputerCols) {
        this.coputerCols = coputerCols;
    }

    public ArrayList<Computer> getComputers() {
        return computers;
    }

    public void setComputers(ArrayList<Computer> computers) {
        this.computers = computers;
    }

    public ComputerTable(ArrayList<Computer> computers) {
        this.computers=computers;
    }


    @Override
    public int getRowCount() {
        return computers.size();
    }

    @Override
    public int getColumnCount() {
        return coputerCols.length;
    }

    @Override
    public String getColumnName(int column) {
        return coputerCols[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Computer computer=computers.get(rowIndex);
        switch (columnIndex){
            case 0:
                return computer.getName().toUpperCase();
            case 1:
                return computer.getQuality().toUpperCase();
            case 2:
                return computer.getMoneyPerHour();
            case 3:
                String status="";
                switch (computer.getStatus()){
                    case ComputerConstant.ON:
                        status="Sẵn sàng";
                        break;
                    case ComputerConstant.OFF:
                        status="Đang tắt";
                        break;
                    case ComputerConstant.HONG:
                        status="Hỏng";
                        break;
                    case ComputerConstant.RUNNING:
                        status="Đang sử dụng";
                        break;
                }

                return status.toUpperCase();
            case 4:
                String userName=computer.getUsedBY();
                if (userName==null){
                    userName="KHÔNG CÓ";
                }

                return  userName;
            default:
                return null;
        }
    }
}
