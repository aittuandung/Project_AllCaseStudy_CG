package View;

import Controller.AccountController;
import Model.Account;
import Validate.PasswordValidate;
import Validate.Validate;
import Validate.ValidateUserName;
import constant.AccountConstant;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class CreateStaffAcc extends JFrame{
    JLabel title;
    JFrame adminview;

    JFrame create;
    AccountController accountController;
    PasswordValidate passwordValidate=new PasswordValidate();
    ValidateUserName validateUserName=new ValidateUserName();

    Validate validate=new Validate();
    JLabel realName;
    JLabel validateRN;
    JLabel sdt;
    JLabel validateSdt;
    JLabel email;
    JLabel validateEmail;
    JLabel tuoi;
    JLabel validateTuoi;


    JLabel validateUN;
    JLabel userName;
    JLabel pass;
    JLabel validatePass;


    JTextField uN;
    JPasswordField passwordField;


    JTextField rName;
    JTextField sdtT;
    JTextField emailT;
    JTextField age;

    JTextField passW;

    JButton confirm;
    JButton cancel;

    public CreateStaffAcc(JFrame adminview, AccountController accountController) throws HeadlessException {
        this.adminview = adminview;
        this.accountController = accountController;

        create=this;

        Container container=this.getContentPane();
        ImageIcon icon=new ImageIcon("D:\\IJ Project\\Module2\\Week4\\Swing\\img\\logo.jpg");
        setIconImage(icon.getImage());
        setResizable(false);
        container.setLayout(new FlowLayout());
        setSize(550,600);

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
        info.setPreferredSize(new Dimension(460,420));


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

        validateRN=new JLabel("Nhập tên",JLabel.RIGHT);
        validateRN.setFont(innghieng);
        validateRN.setPreferredSize(validateLabel);
        validateRN.setForeground(Color.BLUE);

        realName=new JLabel("Họ và tên: ");
        realName.setFont(labelFont);
        realName.setPreferredSize(labelDimension);

        rName=new JTextField();
        rName.setFont(textFont);

        rName.setPreferredSize(textDimension);
        rName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String rN=rName.getText();
                if (validate.rName(rN)){
                    validateRN.setForeground(Color.GREEN);
                    validateRN.setText("Tên hợp lệ");
                }else {
                    validateRN.setForeground(Color.RED);
                    validateRN.setText("Tên không chứ kí tự đặc biệt, phải viết hoa chữ cái đầu");
                }
            }
        });

        validateSdt=new JLabel("*Nhập số điện thoại",JLabel.RIGHT);
        validateSdt.setFont(innghieng);
        validateSdt.setPreferredSize(validateLabel);
        validateSdt.setForeground(Color.BLUE);

        sdt=new JLabel("Số điện thoại: ");
        sdt.setFont(labelFont);
        sdt.setPreferredSize(labelDimension);

        sdtT=new JTextField();
        sdtT.setFont(textFont);

        sdtT.setPreferredSize(textDimension);
        sdtT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String sdtR=sdtT.getText();
                if (validate.validateSdt(sdtR)){
                    validateSdt.setForeground(Color.GREEN);
                    validateSdt.setText("Số điện thoại hợp lệ");
                }else {
                    validateSdt.setForeground(Color.RED);
                    validateSdt.setText("Nhập số điện thoại bắt đầu từ 0, gồm 10 chữ số");
                }
            }
        });

        validateEmail=new JLabel("*Nhập vào email",JLabel.RIGHT);
        validateEmail.setPreferredSize(validateLabel);
        validateEmail.setFont(innghieng);
        validateEmail.setForeground(Color.BLUE);

        email=new JLabel("Email: ");
        email.setFont(labelFont);
        email.setPreferredSize(labelDimension);

        emailT=new JTextField();
        emailT.setFont(textFont);

        emailT.setPreferredSize(textDimension);
        emailT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String emailR=emailT.getText();
                if (validate.validateEmail(emailR)){
                    validateEmail.setForeground(Color.GREEN);
                    validateEmail.setText("Email hợp lệ");
                }else {
                    validateEmail.setForeground(Color.RED);
                    validateEmail.setText("Nhập email có dạng abc.xyz@gmail.com");
                }
            }
        });

        validateTuoi=new JLabel("Nhập vào tuổi",JLabel.RIGHT);
        validateTuoi.setFont(innghieng);
        validateTuoi.setPreferredSize(validateLabel);
        validateTuoi.setForeground(Color.BLUE);

        tuoi=new JLabel("Tuổi: ");
        tuoi.setFont(labelFont);
        tuoi.setPreferredSize(labelDimension);

        age=new JTextField();
        age.setFont(textFont);

        age.setPreferredSize(textDimension);
        age.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String ageT=age.getText();
                if (validate.validateAge(ageT)&&Integer.parseInt(ageT)>17){
                    validateTuoi.setForeground(Color.GREEN);
                    validateTuoi.setText("Tuổi hợp lệ");
                }else {
                    validateTuoi.setForeground(Color.RED);
                    validateTuoi.setText("Nhập tuổi là số , >= 18 tuổi");
                }
            }
        });



        info.add(validateUN);
        info.add(userName);
        info.add(uN);

        info.add(validatePass);
        info.add(pass);
        info.add(passwordField);

        info.add(validateRN);
        info.add(realName);
        info.add(rName);

        info.add(validateTuoi);
        info.add(tuoi);
        info.add(age);

        info.add(validateSdt);
        info.add(sdt);
        info.add(sdtT);

        info.add(validateEmail);
        info.add(email);
        info.add(emailT);



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




                boolean dk1=validate.rName(rName.getText());
                boolean dk2=validate.validateSdt(sdtT.getText());
                boolean dk3=validate.validateEmail(emailT.getText());
                boolean dk4=validate.validateAge(age.getText());
                if (passwordValidate.validate(passW)&&validateUserName.validate(uname)&&dk1&dk2&&dk3&&dk4&&Integer.parseInt(age.getText())>17){
                    int index= accountController.getIndexByName(uname);
                    if (index==-1){
                        accountController.addA(new Account(uname,passW,sdtT.getText(),emailT.getText(),rName.getText(),Integer.parseInt(age.getText()), AccountConstant.STAFF));
                        adminview.repaint();
                        adminview.setEnabled(true);
                        setVisible(false);
                        JOptionPane.showMessageDialog(null,"Tạo tài khoản thành công");
                    }else {
                        JOptionPane.showMessageDialog(null,"Tên đăng nhập đã được sử dụng, vui lòng chọn tên khác");
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Thông tin không hợp lệ");
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


        buttonP.add(confirm);
        buttonP.add(cancel);
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

        container.add(buttonP);

        setLocationRelativeTo(null);
        setVisible(true);






    }


}
