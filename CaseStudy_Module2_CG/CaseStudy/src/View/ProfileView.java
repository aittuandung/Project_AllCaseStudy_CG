package View;

import Controller.AccountController;
import Model.Account;
import Validate.Validate;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class ProfileView extends JFrame {

    Validate validate=new Validate();
    JLabel realName;

    JLabel validateRN;
    JLabel sdt;
    JLabel validateSdt;
    JLabel email;

    JLabel validateEmail;
    JLabel tuoi;
     JLabel validateTuoi;
    JLabel username;

    JLabel pass;


    JTextField rName;
    JTextField sdtT;
    JTextField emailT;
    JTextField age;
    JTextField userName;
    JTextField passW;

    JButton edit;
    JButton save;
    JButton cancel;

    JButton changePass;

    JFrame jFrame;
    JPanel information;
    JPanel chucNang;

    JFrame doimk;

    JFrame adminview;

    public ProfileView(Account account, AccountController accountController,JFrame jFrame1) {
        jFrame=this;
        adminview=jFrame1;
        Container container = this.getContentPane();

        container.setLayout(new FlowLayout());
        Font labelFont=new Font("labelFont", Font.PLAIN,20);
        Font textFont=new Font("textFont",Font.ITALIC,18);
        Font buttonFont=new Font("buttonFont",Font.ROMAN_BASELINE,20);
        Font innghieng=new Font("innghieng",Font.ITALIC,13);

        Dimension labelDimension=new Dimension(150,30);
        Dimension textDimension=new Dimension(250,30);
        Dimension buttonDimension=new Dimension(200,40);
        Dimension validateLabel=new Dimension(370,20);

        information=new JPanel(new FlowLayout(FlowLayout.CENTER,5,10));
        information.setPreferredSize(new Dimension(460,300));
        TitledBorder titledBorder1=new TitledBorder("Thông tin cá nhân");
        titledBorder1.setTitleFont(labelFont);
        titledBorder1.setTitleColor(Color.BLUE);

        information.setBorder(titledBorder1);
        validateRN=new JLabel("Nhập tên",JLabel.RIGHT);
        validateRN.setFont(innghieng);
        validateRN.setPreferredSize(validateLabel);
        validateRN.setForeground(Color.BLUE);

        realName=new JLabel("Họ và tên: ");
        realName.setFont(labelFont);
        realName.setPreferredSize(labelDimension);

        rName=new JTextField(account.getRealName());
        rName.setFont(textFont);
        rName.setEditable(false);
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

        sdtT=new JTextField(account.getPhoneNumber());
        sdtT.setFont(textFont);
        sdtT.setEditable(false);
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

        emailT=new JTextField(account.getEmail());
        emailT.setFont(textFont);
        emailT.setEditable(false);
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

        age=new JTextField(String.valueOf(account.getAge()));
        age.setFont(textFont);
        age.setEditable(false);
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




        username=new JLabel("Tên đăng nhập: ");
        username.setFont(labelFont);
        username.setPreferredSize(labelDimension);

        userName=new JTextField(account.getUserName());
        userName.setFont(textFont);
        userName.setEditable(false);
        userName.setPreferredSize(textDimension);



        pass=new JLabel("Mật khẩu: ");
        pass.setFont(labelFont);
        pass.setPreferredSize(labelDimension);

        passW=new JTextField(account.getPassWord());
        passW.setFont(textFont);
        passW.setEditable(false);
        passW.setPreferredSize(textDimension);

        information.add(validateRN);
        information.add(realName);
        information.add(rName);

        information.add(validateSdt);
        information.add(sdt);
        information.add(sdtT);

        information.add(validateEmail);
        information.add(email);
        information.add(emailT);

        information.add(validateTuoi);
        information.add(tuoi);
        information.add(age);

        information.add(username);
        information.add(userName);

        information.add(pass);
        information.add(passW);
        validateRN.setVisible(false);
        validateSdt.setVisible(false);
        validateEmail.setVisible(false);
        validateTuoi.setVisible(false);

        chucNang=new JPanel(new FlowLayout());


        save=new JButton("Lưu thay đổi");
        save.setFont(buttonFont);
        save.setPreferredSize(buttonDimension);
        save.hide();
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean dk1=validate.rName(rName.getText());
                boolean dk2=validate.validateSdt(sdtT.getText());
                boolean dk3=validate.validateEmail(emailT.getText());
                boolean dk4=validate.validateAge(age.getText());
                if (dk1&dk2&&dk3&&dk4&&Integer.parseInt(age.getText())>17){
                    account.setRealName(rName.getText());
                    account.setPhoneNumber(sdtT.getText());
                    account.setEmail(emailT.getText());
                    account.setAge(Integer.parseInt(age.getText()));
                    information.setPreferredSize(new Dimension(460,300));
                    validateRN.setVisible(false);
                    validateSdt.setVisible(false);
                    validateEmail.setVisible(false);
                    validateTuoi.setVisible(false);
                    rName.setEditable(false);
                    sdtT.setEditable(false);
                    emailT.setEditable(false);
                    age.setEditable(false);
                    setSize(500,450 );
                    save.hide();
                    cancel.hide();
                    edit.show();
                    changePass.show();
                    adminview.repaint();
                    JOptionPane.showMessageDialog(null,"Thay đổi thành công");
                }else {
                    JOptionPane.showMessageDialog(null,"Thay đổi thất bại, vui lòng nhập thông tin đúng định dạng");
                }



            }
        });


        cancel=new JButton("Hủy");
        cancel.setFont(buttonFont);
        cancel.setPreferredSize(buttonDimension);
        cancel.hide();
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rName.setText(account.getRealName());
                sdtT.setText(account.getPhoneNumber());
                emailT.setText(account.getEmail());
                age.setText(String.valueOf(account.getAge()));
                validateRN.setVisible(false);
                validateSdt.setVisible(false);
                validateEmail.setVisible(false);
                validateTuoi.setVisible(false);
                rName.setEditable(false);
                sdtT.setEditable(false);
                emailT.setEditable(false);
                age.setEditable(false);
                information.setPreferredSize(new Dimension(460,300));
                setSize(500,450 );
                save.hide();
                cancel.hide();
                edit.show();
                changePass.show();
            }
        });





        edit=new JButton("Sửa thông tin");
        edit.setPreferredSize(buttonDimension);
        edit.setFont(buttonFont);
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                information.setPreferredSize(new Dimension(460,450));
                setSize(500,600);
                validateRN.setVisible(true);
                validateSdt.setVisible(true);
                validateEmail.setVisible(true);
                validateTuoi.setVisible(true);
                rName.setEditable(true);
                sdtT.setEditable(true);
                emailT.setEditable(true);
                age.setEditable(true);

                edit.hide();
                changePass.hide();
                save.show();
                cancel.show();
                repaint();
            }
        });
        changePass=new JButton("Đổi mật khẩu");
        changePass.setPreferredSize(buttonDimension);
        changePass.setFont(buttonFont);
        changePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setEnabled(false);
                doimk= new ChangePassView(account,accountController,passW,jFrame);

            }
        });
        chucNang.add(edit);
        chucNang.add(changePass);
        chucNang.add(save);
        chucNang.add(cancel);

        TitledBorder titledBorder=new TitledBorder("Chức năng");
        titledBorder.setTitleColor(Color.BLUE);
        titledBorder.setTitleFont(labelFont);
        chucNang.setBorder(titledBorder);
        chucNang.setPreferredSize(new Dimension(460,95));

        container.add(information);
        container.add(chucNang);
        ImageIcon icon=new ImageIcon("D:\\IJ Project\\Module2\\Week4\\Swing\\img\\logo.jpg");
        setIconImage(icon.getImage());

        setSize(500,450 );
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                adminview.setEnabled(true);

                if (doimk!=null){
                    doimk.setVisible(false);
                }
            }
        });

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void closeCon(){
        if (doimk!=null){
            doimk.setVisible(false);
        }
    }


}
