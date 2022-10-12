package View;

import Controller.ComputerController;
import Model.Computer;
import Validate.Validate;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;


public class InforComputer extends JFrame {
    JLabel computerName;
    JLabel chatLuong;
    JLabel tienGio;
    JLabel status;
    JLabel usedBy;

    JTextField cN;

    String oldName;

    JComboBox<String> chatLuongMay;
    int oldindex;
    Validate validate=new Validate();
    JTextField tienGioT;
    String oldTien;
    JComboBox<String> trangThai;
    JTextField usedByWho;


    JLabel validateN;
    JLabel validateTien;

    String state;


    JButton edit;
    JButton save;
    JButton cancel;


    JPanel info;
    JPanel chucNang;

    int index=-1;

    JFrame thisFrame;
    JTabbedPane jTabbedPane;
    JFrame adminview;



    public InforComputer(ComputerController computerController, Computer computer, String state1,JFrame jFrame1) {
        this.state=state1;
        thisFrame =this;
        this.adminview=jFrame1;

        Container container=this.getContentPane();
        ImageIcon icon=new ImageIcon("D:\\IJ Project\\Module2\\Week4\\Swing\\img\\logo.jpg");
        setIconImage(icon.getImage());
        setLayout(new FlowLayout());
        setSize(500,500);
        Font labelFont=new Font("labelFont", Font.PLAIN,20);
        Font textFont=new Font("textFont",Font.ITALIC,18);
        Font buttonFont=new Font("buttonFont",Font.ROMAN_BASELINE,20);
        Font innghieng=new Font("innghieng",Font.ITALIC,13);

        Dimension labelDimension=new Dimension(150,30);
        Dimension textDimension=new Dimension(200,30);
        Dimension buttonDimension=new Dimension(200,40);


        validateN=new JLabel("*Không được bỏ trống",JLabel.RIGHT);
        validateN.setFont(innghieng);
        validateN.setForeground(Color.BLUE);
        validateN.setPreferredSize(new Dimension(350,20));


        computerName=new JLabel("Tên: ");
        computerName.setFont(labelFont);
        computerName.setPreferredSize(labelDimension);

        cN=new JTextField(computer.getName());
        cN.setFont(textFont);
        cN.setPreferredSize(textDimension);
        cN.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (validate.tenMay(cN.getText())){
                    validateN.setForeground(Color.GREEN);
                    validateN.setText("Tên hợp lệ");
                }else {
                    validateN.setForeground(Color.RED);
                    validateN.setText("Tên máy không chứ kí tự đặc biệt, 6-15 ký tự");
                }
            }
        });


        chatLuong=new JLabel("Chất lượng: ");
        chatLuong.setPreferredSize(labelDimension);
        chatLuong.setFont(labelFont);

        String [] chatluongMay1={"Tốt","Khá","Trung bình"};
        chatLuongMay=new JComboBox<>(chatluongMay1);
        chatLuongMay.setPreferredSize(textDimension);
        String cl=computer.getQuality();

        if (cl.equalsIgnoreCase("tốt")){
            index=0;
        }else if (cl.equalsIgnoreCase("khá")){
            index=1;
        }else if (cl.equalsIgnoreCase("trung bình"))
        {
            index=2;
        }
        chatLuongMay.setSelectedIndex(index);



        validateTien=new JLabel("*Không được bỏ trống",JLabel.RIGHT);
        validateTien.setFont(innghieng);
        validateTien.setForeground(Color.BLUE);
        validateTien.setPreferredSize(new Dimension(350,20));


        tienGio=new JLabel("Tiền/h: ");
        tienGio.setFont(labelFont);
        tienGio.setPreferredSize(labelDimension);



        tienGioT=new JTextField(String.valueOf(computer.getMoneyPerHour()));
        tienGioT.setFont(textFont);
        tienGioT.setPreferredSize(textDimension);
        tienGioT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (validate.tienGio(tienGioT.getText())){
                    validateTien.setForeground(Color.GREEN);
                    validateTien.setText("Giá hợp lệ");
                }else {
                    validateTien.setForeground(Color.RED);
                    validateTien.setText("Nhập giá có là chữ số");
                }
            }
        });




        status=new JLabel("Trạng thái: ");
        status.setPreferredSize(labelDimension);
        status.setFont(labelFont);

        String [] trangthai={"Đang sử dụng","Đang tắt","Hỏng","Sẵn sàng"};
        trangThai =new JComboBox<>(trangthai);
        trangThai.setPreferredSize(textDimension);
        trangThai.setSelectedIndex(computer.getStatus());
        trangThai.setEnabled(false);

        usedBy=new JLabel("Người sử dụng: ");
        usedBy.setPreferredSize(labelDimension);
        usedBy.setFont(labelFont);
        String ngSD=computer.getUsedBY();
        if (ngSD==null){
            ngSD="Không có";
        }
        usedByWho=new JTextField(ngSD);
        usedByWho.setPreferredSize(textDimension);
        usedByWho.setFont(textFont);
        usedByWho.setEditable(false);


        TitledBorder titledBorder=new TitledBorder("Thông tin máy");
        titledBorder.setTitleFont(labelFont);
        titledBorder.setTitleColor(Color.BLUE);





        info=new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        info.setBorder(titledBorder);
        info.setPreferredSize(new Dimension(400,300));


        info.add(validateN);
        info.add(computerName);
        info.add(cN);




        info.add(chatLuong);
        info.add(chatLuongMay);

        info.add(validateTien);
        info.add(tienGio);
        info.add(tienGioT);

        info.add(status);
        info.add(trangThai);

        info.add(usedBy);
        info.add(usedByWho);



        edit=new JButton("Sửa Thông tin");
        edit.setFont(labelFont);
        edit.setPreferredSize(new Dimension(300,40));
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               oldindex= chatLuongMay.getSelectedIndex();
               oldName=cN.getText();
               oldTien= tienGioT.getText();
                state="edit";
                show(state);
            }
        });

        cancel=new JButton("Hủy");
        cancel.setPreferredSize(labelDimension);
        cancel.setFont(labelFont);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                state="view";
                chatLuongMay.setSelectedIndex(oldindex);
                cN.setText(oldName);
                tienGioT.setText(oldTien);
                show(state);


            }
        });

        save=new JButton("Lưu");
        save.setFont(labelFont);
        save.setPreferredSize(labelDimension);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=cN.getText();

                if ( computerController.validateCName(name)==null||name.equalsIgnoreCase(oldName)){
                    if (validate.tienGio(tienGioT.getText())){
                        computer.setMoneyPerHour(Double.parseDouble(tienGioT.getText()));
                        computer.setQuality((String) chatLuongMay.getSelectedItem());
                        computer.setName(name);
                        computerController.save();
                        adminview.repaint();
                        JOptionPane.showMessageDialog(null,"Thay đổi thông tin thành công");
                        state="view";
                        show(state);

                    }else {
                        JOptionPane.showMessageDialog(null,"Vui lòng nhập thông tin đúng định dạng ");
                    }

                }else {
                    JOptionPane.showMessageDialog(null,"Tên máy đã được sử dụng, vui lòng chọn tên khác");
                }

            }
        });



        chucNang=new JPanel(new FlowLayout());
        TitledBorder titledBorder1=new TitledBorder("Chức năng");
        titledBorder1.setTitleColor(Color.BLUE);
        titledBorder1.setTitleFont(labelFont);
        chucNang.setPreferredSize(new Dimension(400,100));
        chucNang.setBorder(titledBorder1);
        chucNang.add(edit);
        chucNang.add(save);
        chucNang.add(cancel);
        addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        adminview.setEnabled(true);
                    }
                    @Override
                    public  void windowOpened(WindowEvent e){
                        oldindex= chatLuongMay.getSelectedIndex();
                        oldName=cN.getText();
                        oldTien= tienGioT.getText();
                    }
                }

        );
        show(this.state);
        container.add(info);
        container.add(chucNang);
        setLocationRelativeTo(null);
        setVisible(true);


    }
        public  void show(String state2){
            if (state2.equals("edit")){
                validateTien.setVisible(true);
                validateN.setVisible(true);
                tienGioT.setEditable(true);
                chatLuongMay.setEnabled(true);
                cN.setEditable(true);
                save.setVisible(true);
                cancel.setVisible(true);
                edit.setVisible(false);

            }else {
                validateN.setVisible(false);
                validateTien.setVisible(false);
                tienGioT.setEditable(false);
                chatLuongMay.setEnabled(false);
                cN.setEditable(false);
                save.setVisible(false);
                cancel.setVisible(false);
                edit.setVisible(true);
            }
        }


}
