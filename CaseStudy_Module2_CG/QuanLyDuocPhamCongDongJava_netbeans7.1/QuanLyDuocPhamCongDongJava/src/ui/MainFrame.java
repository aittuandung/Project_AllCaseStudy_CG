
package ui;

import java.awt.GraphicsEnvironment;
import javax.swing.JOptionPane;
import worker.IconSetter;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class MainFrame extends javax.swing.JFrame {

    public static MainFrame it;
    private final boolean DEBUGMODE = false;

    public MainFrame() {
        it = this;
        initComponents();
        IconSetter is = new IconSetter();
        is.setIcon(butDanhSachHangLuuTru, "manage");
        is.setIcon(butLichSuNhapHang, "input");
        is.setIcon(butLichSuXuatHang, "output");
        is.setIcon(butXuatDonThuoc, "list");
        is.setIcon(butAccountInfo, "user");

        setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
        setVisible(true);
        panelFunction.removeAll();
        if (DEBUGMODE) {
            loadPanelControl();
        } else {
            panelFunction.add(new PanelLogin());
        }
        panelFunction.updateUI();
        new InfoThread();
    }
    class InfoThread extends Thread {

        String s = lbInfo.getText();

        public InfoThread() {
            start();
        }

        public void run() {
            int index = 0;
            while (true) {
                index++;
                lbInfo.setText("<html>" + s.substring(0, index < s.length() ? index : s.length()));
                if (index == s.length() + 50) {
                    index = 0;
                }
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                }
            }
        }
    }
    PanelDanhSachHangLuuTru luuTru;
    PanelLichSuNhapHang nhapHang;
    PanelLichSuXuatHang xuatHang;
    PanelLichSuXuatToaThuoc panelToaThuoc;

    public void loadPanelControl() {
        panelFunction.removeAll();
        panelFunction.add(panelControl);
        panelFunction.updateUI();
        reloadPanel(1);
    }

    /**
     * Load panel
     *
     * @param i:
     * 0-panelControl,1-PanelDanhSachHangLuuTru,2-PanelLichSuNhapHang,3-PanelLichSuXuatHang,4-PanelAccount
     */
    public void reloadPanel(int i) {
        panelCenter.removeAll();
        if (i == 1) {//panel lich su luu tru
            if (luuTru == null) {
                luuTru = new PanelDanhSachHangLuuTru();
            }
            panelCenter.add(luuTru);
        } else if (i == 2) {//panel lich su luu tru
            if (nhapHang == null) {
                nhapHang = new PanelLichSuNhapHang();
            }
            panelCenter.add(nhapHang);
        } else if (i == 3) {//panel lich su luu tru
            if (xuatHang == null) {
                xuatHang = new PanelLichSuXuatHang();
            }
            panelCenter.add(xuatHang);
        } else if (i == 6) {//panel lich su luu tru
            if (panelToaThuoc == null) {
                panelToaThuoc = new PanelLichSuXuatToaThuoc();
            }
            panelCenter.add(panelToaThuoc);
        }
        panelCenter.updateUI();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupList_control = new javax.swing.ButtonGroup();
        panelFunction = new javax.swing.JPanel();
        panelControl = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        butDanhSachHangLuuTru = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        butLichSuNhapHang = new javax.swing.JToggleButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        butLichSuXuatHang = new javax.swing.JToggleButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        butXuatDonThuoc = new javax.swing.JToggleButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        butAccountInfo = new javax.swing.JToggleButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        panelCenter = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbInfo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quan Ly Duoc Pham v1.1 - Develop by JackV congdongjava.com 0909569156");

        panelFunction.setLayout(new java.awt.GridLayout());

        panelControl.setLayout(new java.awt.BorderLayout());

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setPreferredSize(new java.awt.Dimension(651, 45));

        buttonGroupList_control.add(butDanhSachHangLuuTru);
        butDanhSachHangLuuTru.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        butDanhSachHangLuuTru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/manage.png"))); // NOI18N
        butDanhSachHangLuuTru.setSelected(true);
        butDanhSachHangLuuTru.setText("Danh Mục Dược Phẩm");
        butDanhSachHangLuuTru.setFocusable(false);
        butDanhSachHangLuuTru.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        butDanhSachHangLuuTru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butDanhSachHangLuuTruActionPerformed(evt);
            }
        });
        jToolBar1.add(butDanhSachHangLuuTru);
        jToolBar1.add(jSeparator1);

        buttonGroupList_control.add(butLichSuNhapHang);
        butLichSuNhapHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        butLichSuNhapHang.setText("Nhập Hàng");
        butLichSuNhapHang.setFocusable(false);
        butLichSuNhapHang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        butLichSuNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butLichSuNhapHangActionPerformed(evt);
            }
        });
        jToolBar1.add(butLichSuNhapHang);
        jToolBar1.add(jSeparator2);

        buttonGroupList_control.add(butLichSuXuatHang);
        butLichSuXuatHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        butLichSuXuatHang.setText("Xuất Hàng");
        butLichSuXuatHang.setFocusable(false);
        butLichSuXuatHang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        butLichSuXuatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butLichSuXuatHangActionPerformed(evt);
            }
        });
        jToolBar1.add(butLichSuXuatHang);
        jToolBar1.add(jSeparator3);

        buttonGroupList_control.add(butXuatDonThuoc);
        butXuatDonThuoc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        butXuatDonThuoc.setText("Xuất Toa Thuốc");
        butXuatDonThuoc.setFocusable(false);
        butXuatDonThuoc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        butXuatDonThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butXuatDonThuocActionPerformed(evt);
            }
        });
        jToolBar1.add(butXuatDonThuoc);
        jToolBar1.add(jSeparator5);

        buttonGroupList_control.add(butAccountInfo);
        butAccountInfo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        butAccountInfo.setFocusable(false);
        butAccountInfo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        butAccountInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAccountInfoActionPerformed(evt);
            }
        });
        jToolBar1.add(butAccountInfo);
        jToolBar1.add(jSeparator4);

        panelControl.add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        panelCenter.setLayout(new java.awt.GridLayout());
        panelControl.add(panelCenter, java.awt.BorderLayout.CENTER);

        panelFunction.add(panelControl);

        getContentPane().add(panelFunction, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<html><p align=\"center\"><font color=\"#000080\"><b>Hệ Thống Quản Lý Dược Phẩm</b></font><b><font color=\"#000080\"><font size=\"6\"><br> Nhà</font><font size=\"6\"> Thuốc </font></font><font color=\"#FF0000\"> <font size=\"6\">Cộng Đồng Java</font></font></b></p>");
        jPanel2.add(jLabel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        lbInfo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbInfo.setText("Nhà Thuốc <b>Cong Dong Java</b> congdongjava.com");
        lbInfo.setPreferredSize(new java.awt.Dimension(300, 14));
        jPanel3.add(lbInfo);

        getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butDanhSachHangLuuTruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butDanhSachHangLuuTruActionPerformed
        reloadPanel(1);
    }//GEN-LAST:event_butDanhSachHangLuuTruActionPerformed

    private void butLichSuNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butLichSuNhapHangActionPerformed
        reloadPanel(2);
    }//GEN-LAST:event_butLichSuNhapHangActionPerformed

    private void butLichSuXuatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butLichSuXuatHangActionPerformed
        reloadPanel(3);
    }//GEN-LAST:event_butLichSuXuatHangActionPerformed

    private void butAccountInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAccountInfoActionPerformed
        JOptionPane.showMessageDialog(this, "Thay đổi thông tin tài khoản\n\nChức năng này không có ở phiên bản demo");
        reloadPanel(1);
        butDanhSachHangLuuTru.setSelected(true);
    }//GEN-LAST:event_butAccountInfoActionPerformed

    private void butXuatDonThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butXuatDonThuocActionPerformed
        reloadPanel(6);
    }//GEN-LAST:event_butXuatDonThuocActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton butAccountInfo;
    private javax.swing.JToggleButton butDanhSachHangLuuTru;
    private javax.swing.JToggleButton butLichSuNhapHang;
    private javax.swing.JToggleButton butLichSuXuatHang;
    private javax.swing.JToggleButton butXuatDonThuoc;
    private javax.swing.ButtonGroup buttonGroupList_control;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lbInfo;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelControl;
    private javax.swing.JPanel panelFunction;
    // End of variables declaration//GEN-END:variables
}
