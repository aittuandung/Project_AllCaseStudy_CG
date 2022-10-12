package View;

import Controller.AccountController;
import Controller.ComputerController;
import Model.Account;
import constant.AccountConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SelectionButon extends JFrame {
    JButton edit;
    JButton delete;
    JButton  cancel;

    InforComputer inforComputer;

    public SelectionButon(int index, ComputerController computerController, AccountController accountController, JFrame jFrame) {
        setUndecorated(true);
        Container cp=this.getContentPane();

        edit =new JButton("Edit");
        edit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (computerController!=null){
                            inforComputer=new InforComputer(computerController,computerController.getComputers().get(index),"edit",jFrame);
                            setVisible(false);
                        }else if (accountController!=null){
                            Account account=accountController.getAccounts().get(index);
                            if (account.getPermisson()== AccountConstant.STAFF){
                                new ProfileView(account,accountController,jFrame);
                            }else {
                                new UserInfo(accountController,jFrame,account);
                            }

                            setVisible(false);
                        }


                    }
                }
        );
        delete=new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setEnabled(true);
                setVisible(false);

                int confirm=JOptionPane.showConfirmDialog(null,"Bạn  muốn xóa ?");
                if (confirm==JOptionPane.OK_OPTION){
                   if (computerController!=null){
                       computerController.delete(index);
                       computerController.save();


                   }else if(accountController!=null){
                       accountController.delete(index);
                       accountController.save();

                   }
                    jFrame.repaint();
                    JOptionPane.showMessageDialog(null,"Đã xóa thành công");
                }

            }
        });

        cancel=new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setEnabled(true);
                setVisible(false);

            }
        });


        JPanel jPanel=new JPanel(new GridLayout(3,0,0,5));
        jPanel.setBorder(BorderFactory.createEtchedBorder(Color.BLACK,Color.BLUE));
        jPanel.setPreferredSize(new Dimension(100,100));
        jPanel.add(edit);
        jPanel.add(delete);
        jPanel.add(cancel);


        cp.add(jPanel);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                jFrame.setEnabled(true);
            }
        });

        pack();

        setLocationRelativeTo(null);

        setResizable(false);
        setVisible(true);



    }
}
