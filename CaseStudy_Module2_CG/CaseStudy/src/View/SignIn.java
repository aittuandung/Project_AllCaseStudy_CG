package View;

import Controller.SignInController;
import Model.Computer;
import Validate.PasswordValidate;
import Validate.ValidateUserName;
import constant.ComputerConstant;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class SignIn extends JFrame {
    private JTextField userName;
    private JPasswordField passwordField;

    JPanel general;
    JPanel infor;
    JPanel button;

    PasswordValidate passwordValidate=new PasswordValidate();
    ValidateUserName validateUserName=new ValidateUserName();
    Computer computer;

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }

    public SignIn(SignInController signInController)  {

       computer=signInController.getComputer();


        Font innghieng=new Font("innghieng",Font.ITALIC,13);


        Font inDam=new Font("dam",Font.BOLD ,18);
        Container cp=this.getContentPane();
        cp.setLayout(new FlowLayout());


        JLabel cap1=new JLabel("Bạn phải đăng nhập để sử dụng hệ thống !",JLabel.CENTER);
        cap1.setFont(inDam);


        JLabel vN=new JLabel("Nhập vào UserName có 8 đến 10 ký tự",JLabel.RIGHT);
        vN.setFont(innghieng);
        vN.setPreferredSize(new Dimension(420,20));


        JLabel uNameLabel=new JLabel("User name:");
        uNameLabel.setFont(inDam);
        uNameLabel.setPreferredSize(new Dimension(150,60));


         userName=new JTextField(25);
         userName.setPreferredSize(new Dimension(150,30));

        userName.addKeyListener(new KeyAdapter() {
            @Override
           public void keyReleased(KeyEvent e) {
               String username = userName.getText();
                boolean validName= validateUserName.validate(username);

               if (!validName) {
                   vN.setForeground(Color.RED);
                       vN.setText("Username không  chứa kí tự đặc biệt, 8-15 kí tự ");
               }else {
                   vN.setForeground(Color.GREEN);
                   vN.setText("UserName hợp lệ");
               }
           }
       });


        JLabel vP=new JLabel("Nhập password ít nhất 8 kí tự",JLabel.RIGHT);
        vP.setFont(innghieng);
        vP.setPreferredSize(new Dimension(420,20));


       JLabel passLabel=new JLabel("Password:");
       passLabel.setFont(inDam);
       passLabel.setPreferredSize(new Dimension(150,60));


       passwordField=new JPasswordField(25);
       passwordField.setPreferredSize(new Dimension(150,30));
       passwordField.addKeyListener(new KeyAdapter() {
           @Override
           public void keyReleased(KeyEvent e) {
               String pass=passwordField.getText();
               boolean validPass= passwordValidate.validate(pass);
               if (validPass){
                   vP.setForeground(Color.GREEN);
                   vP.setText("Password hợp lệ");
               }else {
                   vP.setForeground(Color.RED);
                   vP.setText("Password không có khoảng trắng, tiếng việt.Độ dài 8-15 kí tự");

               }
           }
       });



       JButton signIn=new JButton("Sign in");
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=userName.getText();
                String pass=passwordField.getText();

                if (validateUserName.validate(name)&&passwordValidate.validate(pass)){
                       signInController.validate(name,pass);
                }else {
                    JOptionPane.showMessageDialog(null,"Username hoặc password không hợp lệ");
                }
            }
        });

       JButton cancel=new JButton("Cancel");
       cancel.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               if (computer!=null){
                   computer.setStatus(ComputerConstant.OFF);
               }
               setVisible(false);
           }
       });


        infor=new JPanel(new FlowLayout(FlowLayout.LEFT,5,0));
        TitledBorder infoBorder=new TitledBorder("Thông tin đăng nhập");
        infoBorder.setTitleColor(Color.BLUE);

        infor.setBorder(infoBorder);

        infor.setPreferredSize(new Dimension(450,200));
        infor.add(vN);
        infor.add(uNameLabel);
        infor.add(userName);

        infor.add(vP);
        infor.add(passLabel);
        infor.add(passwordField);


        button=new JPanel(new FlowLayout(FlowLayout.CENTER ,80,10));
        button.setPreferredSize(new Dimension(450,50));
        TitledBorder buttonBorder=new TitledBorder("");
        buttonBorder.setTitleColor(Color.BLUE);
        button.setBorder(buttonBorder);

        button.add(signIn);
        button.add(cancel);

        cap1.setForeground(Color.red);

        general=new JPanel(new FlowLayout(FlowLayout.CENTER,0,20));
        general.setBorder(BorderFactory.createEtchedBorder(Color.BLACK,Color.RED));
        general.setPreferredSize(new Dimension(500,350));

        general.add(cap1);
        general.add(infor);
        general.add(button);


        cp.add(general);

        setSize(600,450);

       ImageIcon icon=new ImageIcon("D:\\IJ Project\\Module2\\Week4\\Swing\\img\\logo.jpg");
       setIconImage(icon.getImage());
        setTitle("Sign in");
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       addWindowListener(new WindowListener() {
           @Override
           public void windowOpened(WindowEvent e) {

           }

           @Override
           public void windowClosing(WindowEvent e) {

                if (computer!=null){
                    computer.setStatus(ComputerConstant.OFF);
                }
           }

           @Override
           public void windowClosed(WindowEvent e) {

               if (computer!=null){
                   computer.setStatus(ComputerConstant.OFF);
               }
           }

           @Override
           public void windowIconified(WindowEvent e) {

           }

           @Override
           public void windowDeiconified(WindowEvent e) {

           }

           @Override
           public void windowActivated(WindowEvent e) {

           }

           @Override
           public void windowDeactivated(WindowEvent e) {

           }
       });
       setLocationRelativeTo(null);
        setResizable(false);
       setVisible(true);
    }

    public JTextField getUserName() {
        return userName;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }
}
