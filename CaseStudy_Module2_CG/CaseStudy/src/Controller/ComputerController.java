package Controller;

import Model.Computer;
import io.ReadAndWrite;

import java.util.ArrayList;

public class ComputerController {

    private String path="/mnt/54805B1C158071A5/JetBrain-CodeGym/CodeGym_AllCaseStudy/CaseStudy_Module2_CG/CaseStudy/data/computer.csv";
     private ReadAndWrite<Computer> readAndWrite=new ReadAndWrite<>();

    private   ArrayList<Computer> computers=readAndWrite.read(path);
//    {
//        computers.add(new Computer("Computer1","tốt"));
//        computers.add(new Computer("Computer2","tốt"));
//        computers.add(new Computer("Computer3","tốt"));
//        computers.add(new Computer("Computer4","tốt"));
//
//    }
            //readAndWrite.read(path);

    public  void delete(int index){
        computers.remove(index);

    }

    public void addP(Computer computer){
        computers.add(computer);
    }
    public void addP (String name,String chatLuong){
        computers.add(create(name,chatLuong));
    }
    public Computer create(String name,String chatLuong){
        return new Computer(name,chatLuong);

    }
    public Computer validateCName(String comupterName){
        for (Computer computer:computers){
            if (computer.getName().equalsIgnoreCase(comupterName)){
                return computer;
            }
        }
        return null;

    }


    public  ArrayList<Computer> getComputers() {
        return computers;
    }

    public void setComputers(ArrayList<Computer> computers) {
        this.computers = computers;
    }

    public synchronized void save(){
        readAndWrite.write(computers,path);
    }

    public ArrayList<Computer> read(){
        return readAndWrite.read(path);
    }



}
