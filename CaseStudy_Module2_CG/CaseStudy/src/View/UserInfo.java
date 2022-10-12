package View;

import Controller.AccountController;
import Model.Account;
import Validate.Validate;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class UserInfo extends JFrame{
    AccountController accountController;
    JFrame adminView;

    Account account;
    JLabel validateTiennap;
    JLabel tiennap;
    JTextField tiennap1;
    JFrame userInfo;
    Validate validate=new Validate();
    JLabel userName;
    JLabel pass;

    JLabel soDu;

    JTextField uN;
    JTextField pw;

    JTextField sd;

    JButton napTien;
    JButton confirm;
    JButton cancel;

    public UserInfo(AccountController accountController, JFrame adminView,Account account) {
        this.accountController = accountController;
        this.adminView = adminView;
        this.account=account;
        userInfo=this;



        Container container=this.getContentPane();
        container.setLayout(new FlowLayout());
        setSize(500,400);
        ImageIcon icon=new ImageIcon("D:\\IJ Project\\Module2\\Week4\\Swing\\img\\logo.jpg");
        setIconImage(icon.getImage());

        Font labelFont=new Font("labelFont", Font.PLAIN,20);
        Font textFont=new Font("textFont",Font.ITALIC,18);
        Font buttonFont=new Font("buttonFont",Font.ROMAN_BASELINE,20);
        Font innghieng=new Font("innghieng",Font.ITALIC,13);

        Dimension labelDimension=new Dimension(150,30);
        Dimension textDimension=new Dimension(250,30);
        Dimension buttonDimension=new Dimension(150,40);






        userName=new JLabel("Tên đăng nhập: ");
        userName.setFont(labelFont);
        userName.setPreferredSize(labelDimension);

        uN=new JTextField(account.getUserName());
        uN.setEditable(false);
        uN.setPreferredSize(textDimension);
        uN.setFont(textFont);

        pass=new JLabel("Mật khẩu: ");
        pass.setPreferredSize(labelDimension);
        pass.setFont(labelFont);

        pw=new JTextField(account.getPassWord());
        pw.setEditable(false);
        pw.setFont(textFont);
        pw.setPreferredSize(textDimension);


        soDu=new JLabel("Số dư: ");
        soDu.setFont(labelFont);
        soDu.setPreferredSize(labelDimension);


      //
        sd=new JTextField( String.valueOf(account.getSoDu()));
        sd.setPreferredSize(textDimension);
        sd.setFont(textFont);
        sd.setEditable(false);

        validateTiennap=new JLabel("*Không được bỏ trống",JLabel.RIGHT);
        validateTiennap.setPreferredSize(new Dimension(415,20));
        validateTiennap.setFont(innghieng);
        validateTiennap.hide();
        validateTiennap.setForeground(Color.BLUE);

        tiennap=new JLabel("Số tiền nạp");
        tiennap.setPreferredSize(labelDimension);
        tiennap.hide();
        tiennap.setFont(labelFont);

        tiennap1=new JTextField();
        tiennap1.hide();
        tiennap1.setFont(textFont);
        tiennap1.setPreferredSize(textDimension);
        tiennap1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (validate.tienGio(tiennap1.getText())){
                    validateTiennap.setForeground(Color.GREEN);
                    validateTiennap.setText("Số tiền nạp hợp lệ");
                }else {
                    validateTiennap.setForeground(Color.RED);
                    validateTiennap.setText("Nhập số tiền là chữ số");
                }
            }
        });


        JPanel jPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,15,15));
        TitledBorder titledBorder=new TitledBorder("Thông tin");
        titledBorder.setTitleColor(Color.BLUE);
        titledBorder.setTitleFont(labelFont);
        jPanel.setBorder(titledBorder);
        jPanel.setPreferredSize(new Dimension(460,250));


        jPanel.add(userName);
        jPanel.add(uN);
        jPanel.add(pass);
        jPanel.add(pw);
        jPanel.add(soDu);
        jPanel.add(sd);


        jPanel.add(validateTiennap);
        jPanel.add(tiennap);
        jPanel.add(tiennap1);

        confirm =new JButton("Nạp");
        confirm.setFont(buttonFont);
        confirm.setPreferredSize(buttonDimension);
        confirm.hide();
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (validate.tienGio(tiennap1.getText())){
                        account.setSoDu(account.getSoDu()+Double.parseDouble(tiennap1.getText()));
                        accountController.save();
                        changeButton(0);
                        confirm.hide();
                        cancel.hide();
                        napTien.show();
                        adminView.repaint();
                        adminView.setEnabled(true);
                        userInfo.setVisible(false);
                        JOptionPane.showMessageDialog(null,"Nạp tiền thành công");

                    }else {
                        JOptionPane.showMessageDialog(null,"Vui lòng nhập thông tin đúng định dạng");
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
                changeButton(0);
                confirm.hide();
                cancel.hide();
                napTien.show();
            }
        });





        napTien =new JButton("Nạp tiền");
        napTien.setPreferredSize(buttonDimension);
        napTien.setFont(buttonFont);
        napTien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                napTien.hide();
                confirm.show();
                cancel.show();
                changeButton(1);
            }
        });


        JPanel button=new JPanel(new FlowLayout(FlowLayout.CENTER,50,10));
        button.setPreferredSize(new Dimension(460,60));

        TitledBorder titledBorder1=new TitledBorder("");
        titledBorder1.setTitleFont(labelFont);
        titledBorder1.setTitleColor(Color.BLUE);
        button.setBorder(titledBorder1);
        button.add(napTien);
        button.add(confirm);
        button.add(cancel);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                adminView.setEnabled(true);
                accountController.save();
            }
        });
        container.add(jPanel);
        container.add(button);

        setVisible(true);

    }

    public void changeButton(int congtac)
    {
        if (congtac==0){
            validateTiennap.hide();
            tiennap.hide();
            tiennap1.hide();
        }else {
            validateTiennap.show();
            tiennap.show();
            tiennap1.show();
        }
    }


}
