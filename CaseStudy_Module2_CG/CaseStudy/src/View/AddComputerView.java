package View;

import Controller.ComputerController;
import Model.Computer;
import Validate.Validate;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddComputerView extends JFrame {
    JPanel addC;
    JPanel buttonPanel;

    Validate validate=new Validate();
    JLabel validateCN;
    JLabel computerName;
    JLabel chatLuong;

    JTextField textFieldName;
    JComboBox chatluongTextfield;

    JButton add;
    JButton cancel;

    JFrame admin;

    public AddComputerView(ArrayList<Computer> computers,JTable component1,ComputerController computerController,JFrame jFrame) {

        admin=jFrame;
        Container container=this.getContentPane();
        setLayout(new FlowLayout());
        setSize(500,400);
        Font labelFont=new Font("labelFont", Font.PLAIN,20);
        Font textFont=new Font("textFont",Font.ITALIC,18);
        Font buttonFont=new Font("buttonFont",Font.ROMAN_BASELINE,20);
        Font innghieng=new Font("innghieng",Font.ITALIC,13);

        Dimension labelDimension=new Dimension(220,30);
        Dimension textDimension=new Dimension(200,30);
        Dimension buttonDimension=new Dimension(200,40);


        validateCN=new JLabel("*Không được bỏ trống",JLabel.RIGHT);
        validateCN.setForeground(Color.BLUE);
        validateCN.setPreferredSize(new Dimension(420,20));
        validateCN.setFont(innghieng);

        addC=new JPanel(new FlowLayout(FlowLayout.LEFT  ,5,15));

        TitledBorder addComputer=new TitledBorder("Thêm máy tính");
        addComputer.setTitleColor(Color.BLUE);
        addComputer.setTitleFont(labelFont);

        addC.setPreferredSize(new Dimension(460,200));
        addC.setBorder(addComputer);



        computerName=new JLabel("Nhập tên máy: ");
        computerName.setPreferredSize(labelDimension);
        computerName.setFont(labelFont);

        textFieldName =new JTextField();
        textFieldName.setPreferredSize(textDimension);
        textFieldName.setFont(textFont);
        textFieldName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                if (validate.tenMay(textFieldName.getText())){
                    validateCN.setForeground(Color.GREEN);
                    validateCN.setText("Tên hợp lệ");
                }else {
                    validateCN.setForeground(Color.RED);
                    validateCN.setText("Tên máy không chứ kí tự đặc biệt, 6-15 ký tự");
                }
            }
        });



        chatLuong=new JLabel("Nhập chất lượng máy");
        chatLuong.setFont(labelFont);
        chatLuong.setPreferredSize(labelDimension);


        String [] chatluongMay1={"Tốt","Khá","Trung bình"};
        chatluongTextfield=new JComboBox(chatluongMay1);
        chatluongTextfield.setFont(textFont);
        chatluongTextfield.setPreferredSize(textDimension);


        addC.add(validateCN);
        addC.add(computerName);
        addC.add(textFieldName);
        addC.add(chatLuong);
        addC.add(chatluongTextfield);



        add=new JButton("Thêm máy");
        add.setFont(buttonFont);
        add.setPreferredSize(buttonDimension);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (computerController.validateCName(textFieldName.getText())==null){
                    computerController.addP(textFieldName.getText(), String.valueOf(chatluongTextfield.getSelectedItem()));
                    computerController.save();
                    component1.repaint();
                    admin.setEnabled(true);
                    setVisible(false);
                    JOptionPane.showMessageDialog(null,"Thêm máy thành công");




                }else {
                    JOptionPane.showMessageDialog(null,"Tên máy đã được sử dụng, vui lòng chọn tên khác");

                }


            }
        });

        cancel=new JButton("Hủy");
        cancel.setPreferredSize(buttonDimension);
        cancel.setFont(buttonFont);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin.setEnabled(true);
                setVisible(false);

            }
        });


        TitledBorder buttonTitleBorder=new TitledBorder("Chức năng");
        buttonTitleBorder.setTitleColor(Color.BLUE);
        buttonTitleBorder.setTitleFont(labelFont);

        buttonPanel=new JPanel(new FlowLayout());
        buttonPanel.setPreferredSize(new Dimension(460,100));
        buttonPanel.setBorder(buttonTitleBorder);
        buttonPanel.add(add);
        buttonPanel.add(cancel);



        container.add(addC);
        container.add(buttonPanel);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                admin.setEnabled(true);
                setVisible(false);

            }
        });

        setTitle("Thêm máy tính");
        ImageIcon icon=new ImageIcon("D:\\IJ Project\\Module2\\Week4\\Swing\\img\\logo.jpg");
        setIconImage(icon.getImage());
        setResizable(false);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);




    }
}
