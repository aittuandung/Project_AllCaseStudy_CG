package View;

import Controller.AccountController;
import Validate.PasswordValidate;
import Validate.ValidateUserName;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class CreateUserAcc extends JFrame {


    JFrame adminview;

    AccountController accountController;

    PasswordValidate passwordValidate=new PasswordValidate();
    ValidateUserName validateUserName=new ValidateUserName();
    JLabel title;

    JLabel validateUN;
    JLabel userName;
    JLabel pass;
    JLabel validatePass;


    JTextField uN;
    JPasswordField passwordField;


    JButton confirm;
    JButton cancel;

    JFrame create;


    public CreateUserAcc(JFrame adminview, AccountController accountController)  {
        this.adminview = adminview;
        this.accountController = accountController;
        create=this;

        Container container=this.getContentPane();
        ImageIcon icon=new ImageIcon("D:\\IJ Project\\Module2\\Week4\\Swing\\img\\logo.jpg");
        setIconImage(icon.getImage());
        setResizable(false);
        container.setLayout(new FlowLayout());
        setSize(550,380);

        Font labelFont=new Font("labelFont", Font.PLAIN,20);
        Font textFont=new Font("textFont",Font.ITALIC,18);
        Font buttonFont=new Font("buttonFont",Font.ROMAN_BASELINE,20);
        Font innghieng=new Font("innghieng",Font.ITALIC,13);

        Dimension labelDimension=new Dimension(150,30);
        Dimension textDimension=new Dimension(250,30);
        Dimension buttonDimension=new Dimension(200,40);
        Dimension validateLabel=new Dimension(400,20);


        title=new JLabel("Tạo tài khoản",JLabel.CENTER);
        title.setFont(new Font("d",Font.BOLD,30));
        title.setForeground(Color.red);
        title.setPreferredSize(new Dimension(300,40));


        container.add(title);

        JPanel info=new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        TitledBorder titledBorder=new TitledBorder("Thông tin tài khoản");
        titledBorder.setTitleColor(Color.BLUE);
        titledBorder.setTitleFont(labelFont);
        info.setBorder(titledBorder);
        info.setPreferredSize(new Dimension(460,200));


        validateUN=new JLabel("* Không được để trống",JLabel.RIGHT);
        validateUN.setFont(innghieng);
        validateUN.setPreferredSize(validateLabel);
        validateUN.setForeground(Color.BLUE);

        userName=new JLabel("Tên đăng nhập: ");
        userName.setPreferredSize(labelDimension);
        userName.setFont(labelFont);

        uN=new JTextField();
        uN.setFont(textFont);
        uN.setPreferredSize(textDimension);
        uN.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String userName=uN.getText();
                boolean validName= validateUserName.validate(userName);

                if (!validName) {
                    validateUN.setForeground(Color.RED);
                    validateUN.setText("Username không  chứa kí tự đặc biệt, 8-15 kí tự ");
                }else {
                    validateUN.setForeground(Color.GREEN);
                    validateUN.setText("UserName hợp lệ");
                }
            }
        });


        validatePass=new JLabel("* Không được để trống",JLabel.RIGHT);
        validatePass.setFont(innghieng);
        validatePass.setPreferredSize(validateLabel);
        validatePass.setForeground(Color.BLUE);

        pass=new JLabel("Mật khẩu: ");
        pass.setPreferredSize(labelDimension);
        pass.setFont(labelFont);

        passwordField=new JPasswordField();

        passwordField.setPreferredSize(textDimension);
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String pass=passwordField.getText();
                boolean validPass= passwordValidate.validate(pass);
                if (validPass){
                    validatePass.setForeground(Color.GREEN);
                    validatePass.setText("Password hợp lệ");
                }else {
                    validatePass.setForeground(Color.RED);
                    validatePass.setText("Password không có khoảng trắng, tiếng việt.Độ dài 8-15 kí tự");

                }
            }
        });


        info.add(validateUN);
        info.add(userName);
        info.add(uN);

        info.add(validatePass);
        info.add(pass);
        info.add(passwordField);
        container.add(info);


        JPanel buttonP=new JPanel(new FlowLayout(FlowLayout.CENTER,60,10));

        TitledBorder titledBorder1=new TitledBorder("");
        titledBorder1.setTitleColor(Color.BLUE);
        titledBorder1.setTitleFont(labelFont);
        buttonP.setBorder(titledBorder1);
        buttonP.setPreferredSize(new Dimension(460,60));


        confirm=new JButton("Tạo");
        confirm.setFont(buttonFont);
        confirm.setPreferredSize(new Dimension(150,40));
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uname=uN.getText();
                String passW=passwordField.getText();
                if (passwordValidate.validate(passW)&&validateUserName.validate(uname)){
                   int index= accountController.getIndexByName(uname);
                   if (index==-1){
                       accountController.addA(accountController.createA(uname,passW));
                       adminview.repaint();
                       adminview.setEnabled(true);
                       setVisible(false);
                       JOptionPane.showMessageDialog(null,"Tạo tài khoản thành công");
                   }else {
                       JOptionPane.showMessageDialog(null,"Tên đăng nhập đã được sử dụng, vui lòng chọn tên khác");
                   }
                }else {
                    JOptionPane.showMessageDialog(null,"Username hoặc password không hợp lệ");
                }


            }
        });


        cancel=new JButton("Hủy");
        cancel.setPreferredSize(new Dimension(150,40));
        cancel.setFont(buttonFont);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminview.setEnabled(true);
                create.setVisible(false);
            }
        });
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                adminview.setEnabled(true);
                create.setVisible(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                adminview.setEnabled(true);
                create.setVisible(false);
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

        buttonP.add(confirm);
        buttonP.add(cancel);


        container.add(buttonP);

        setLocationRelativeTo(null);
        setVisible(true);



    }


}
