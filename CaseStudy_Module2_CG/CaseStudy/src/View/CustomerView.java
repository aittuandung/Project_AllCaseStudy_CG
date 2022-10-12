package View;

import Controller.AccountController;
import Controller.ComputerController;
import Controller.SignInController;
import Model.Account;
import Model.Computer;
import constant.AccountConstant;
import constant.ComputerConstant;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CustomerView extends JFrame {
    Computer computer;
    Account account;
    JPanel head;
    JPanel body;
    JPanel button;
    JLabel computerName;
    JLabel welcome;
    JLabel start;
    JTextField startTime;
    JLabel used;
    JTextField usedTime;
    JLabel conLai;
    JTextField tgConLai;
    JLabel soDu;
    JTextField soDuT;
    JButton doiPass;
    JButton service;
    JButton logOut;
    JButton chat;
    JFrame jFrame;
    JFrame doiMK;
    ComputerController computerController;
    public CustomerView(Computer computer, Account account, String date, SignInController signInController, AccountController accountController, ComputerController computerController){
        jFrame=this;
        this.computer=computer;
        this.account=account;
        this.computerController =computerController;

        Dimension textfileDimension=new Dimension(140,30);
        Dimension labelDimension=new Dimension(180,30);

        Font indamTO=new Font("indam",Font.BOLD,24);
        Font indam=new Font("indam",Font.BOLD,18);
        Font indamnho=new Font("indamnho",Font.PLAIN,20);

        Container cp=this.getContentPane();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cp.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
        setSize(400,600);

        computerName=new JLabel(computer.getName(),JLabel.CENTER);
        computerName.setPreferredSize(new Dimension(300,40));
        computerName.setFont(indamTO);
        computerName.setForeground(Color.RED);




        welcome=new JLabel("Xin chào, "+account.getUserName(),JLabel.CENTER);
        welcome.setFont(indamTO);
        welcome.setPreferredSize(new Dimension(300,40));



        head=new JPanel(new FlowLayout());
        head.add(computerName);
        head.add(welcome);
        head.setBorder(new TitledBorder(""));
        head.setPreferredSize(new Dimension(350,100));
        cp.add(head);

        start=new JLabel("Bắt đầu: ",JLabel.LEFT);
        start.setFont(indamnho);
        start.setPreferredSize(labelDimension);


        startTime=new JTextField(date);
        startTime.setForeground(Color.RED);
        startTime.setHorizontalAlignment(JTextField.CENTER);
        startTime.setEditable(false);
        startTime.setFont(indamnho);
        startTime.setPreferredSize(textfileDimension);



        TitledBorder bodyTitle=new TitledBorder("Thông tin");
        bodyTitle.setTitleFont(indamnho);
        bodyTitle.setTitleColor(Color.BLUE);
        body=new JPanel(new FlowLayout(FlowLayout.LEFT,0,15));
        body.setBorder(bodyTitle);

        body.setPreferredSize(new Dimension(350,250));

        body.add(start);
        body.add(startTime);

        used=new JLabel("Đã sử dụng: ");
        used.setPreferredSize(labelDimension);
        used.setFont(indamnho);

        usedTime=new JTextField("00:00:00");
        usedTime.setHorizontalAlignment(JTextField.CENTER);
        usedTime.setEditable(false);
        usedTime.setFont(indamnho);
        usedTime.setForeground(Color.RED);
        usedTime.setPreferredSize(textfileDimension);


        conLai=new JLabel("Còn lại: ");
        conLai.setPreferredSize(labelDimension);
        conLai.setFont(indamnho);

        tgConLai=new JTextField("00:00:00");
        tgConLai.setHorizontalAlignment(JTextField.CENTER);
        tgConLai.setEditable(false);
        tgConLai.setFont(indamnho);
        tgConLai.setForeground(Color.RED);
        tgConLai.setPreferredSize(textfileDimension);


        soDu=new JLabel("Số dư: ");
        soDu.setPreferredSize(labelDimension);
        soDu.setFont(indamnho);

        soDuT=new JTextField(String.valueOf(account.getSoDu()));
        soDuT.setHorizontalAlignment(JTextField.CENTER);
        soDuT.setEditable(false);
        soDuT.setFont(indamnho);
        soDuT.setForeground(Color.RED);
        soDuT.setPreferredSize(textfileDimension);



        body.add(used);
        body.add(usedTime);
        body.add(conLai);
        body.add(tgConLai);
        body.add(soDu);
        body.add(soDuT);


        button=new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        button.setPreferredSize(new Dimension(350,150));
        TitledBorder buttonTitle=new TitledBorder("Chức năng");
        buttonTitle.setTitleFont(indamnho);
        buttonTitle.setTitleColor(Color.BLUE);
        button.setBorder(buttonTitle);


        Dimension buttonDimention=new Dimension(160,40);

        service=new JButton("Dịch vụ");
        service.setFont(indam);
        service.setForeground(Color.BLUE);
        service.setPreferredSize(buttonDimention);

        doiPass=new JButton("Đổi mật khẩu");
        doiPass.setFont(indam);
        doiPass.setForeground(Color.BLUE);
        doiPass.setPreferredSize(buttonDimention);
        doiPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setEnabled(false);
                doiMK=  new ChangePassView(account,accountController,null,jFrame);


                }
        });

        chat=new JButton("Chat");
        chat.setFont(indam);
        chat.setForeground(Color.BLUE);
        chat.setPreferredSize(buttonDimention);

        logOut=new JButton("Đăng xuất");
        logOut.setFont(indam);
        logOut.setForeground(Color.BLUE);
        logOut.setPreferredSize(buttonDimention);
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (doiMK!=null){
                    doiMK.setVisible(false);
                }
                computer.setUsedBY(null);
                account.setLoginStatus(AccountConstant.CHUA_DANG_NHAP);
                computer.setStatus(ComputerConstant.ON);
                computerController.save();
                accountController.save();
                signInController.logOut(jFrame);
            }
        });

        button.add(service);
        button.add(doiPass);
        button.add(chat);
        button.add(logOut);


        cp.add(body);
        cp.add(button);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (doiMK!=null){
                    doiMK.setVisible(false);
                }
            }
        });
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        ImageIcon icon=new ImageIcon("D:\\IJ Project\\Module2\\Week4\\Swing\\img\\logo.jpg");
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                account.setLoginStatus(AccountConstant.CHUA_DANG_NHAP);
                computer.setStatus(ComputerConstant.OFF);
                computer.setUsedBY(null);
                computerController.save();
                accountController.save();
            }
            @Override
            public void windowOpened(WindowEvent e) {
                computer.setStatus(ComputerConstant.RUNNING);
                computer.setUsedBY(account.getUserName());
                accountController.save();

            }
        });
    }

}
