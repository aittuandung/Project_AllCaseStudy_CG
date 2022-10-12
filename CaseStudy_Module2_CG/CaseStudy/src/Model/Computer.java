package Model;

import constant.ComputerConstant;

import java.io.Serializable;

public class Computer implements Serializable {
    private String name;

    private String quality;
    private double tien=5000;
    private double moneyPerHour=0;


    private int status=ComputerConstant.OFF;

    private String usedBY=null ;

    public Computer(String name,String quality) {
        this.name = name;
        this.quality=quality;
        this.moneyPerHour=tinhTien();
    }

    public Computer(String name, String quality, double moneyPerHour, int status) {
        this.name = name;
        this.quality = quality;
        this.moneyPerHour = moneyPerHour;
        this.status = status;

    }

    public double tinhTien(){
        double heSo=1;

        if (this.quality.equalsIgnoreCase("khá")){
            heSo=2;
        }else if (this.quality.equalsIgnoreCase("tốt")){
            heSo=3;
        }

        return heSo*this.tien;


    }


    public Computer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }
    public double getMoneyPerHour() {
        return moneyPerHour;
    }

    public void setMoneyPerHour(double moneyPerHour) {
        this.moneyPerHour = moneyPerHour;
    }

    public String getUsedBY() {
        return usedBY;
    }

    public void setUsedBY(String usedBY) {
        this.usedBY = usedBY;
    }


    @Override
    public String toString() {
        String status1="";
        switch (status){
            case ComputerConstant.RUNNING:
                status1="Đang sử dụng";
                break;
            case ComputerConstant.OFF:
                status1="Đang tắt";
                break;
            case ComputerConstant.HONG:
                status1="Hỏng";
                break;
            case ComputerConstant.ON:
                status1="Sẵn sàng";
                break;
            default:
                break;
        }


        return "Computer: " +
                "name: " + name +
                ", quality: "+ quality+
                ", status: " + status1
                ;
    }
}
