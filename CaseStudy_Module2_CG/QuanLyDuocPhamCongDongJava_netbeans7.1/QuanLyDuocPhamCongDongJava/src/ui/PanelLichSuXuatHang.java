
package ui;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import unit.DuocPhamUnit;
import unit.GlobalData;
import unit.XuatHangUnit;
import unit.manager.DuocPhamDataManager;
import unit.manager.XuatHangDataManager;
import worker.ErrorLoger;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class PanelLichSuXuatHang extends javax.swing.JPanel {
    public static PanelLichSuXuatHang it;
    /**
     * Creates new form PanelDanhSachHangLuuTru
     */
    public PanelLichSuXuatHang() {
        it = this;
        initComponents();
        loadTableData("*");
        Vector<String> v = XuatHangDataManager.getAllSavedFile();
        int index = 0;
        for (int i = 0; i < v.size(); i++) {
            if (XuatHangDataManager.FILENAME.contains(v.get(i))) {
                index = i;
            }
        }
        cbbSelect.setModel(new DefaultComboBoxModel(v));
        cbbSelect.setSelectedIndex(index);
        butMinimize.doClick();
    }

    public void loadTableData(String select) {
        try {
            Vector vTitle = new Vector(Arrays.asList(new String[]{"Mã Phiếu", "Ngày Xuất", "Khách Hàng", "Dược Phẩm", "Đơn Vị Tính", "Số Lượng", "Ghi Chú"}));
            Vector vData = new Vector();
            Vector<XuatHangUnit> v = XuatHangDataManager.getLichSuXuatHang(select);
            for (int i = 0; i < v.size(); i++) {
                Vector vT = new Vector();
                vT.add(v.get(i).getMaPhieu());
                vT.add(v.get(i).getNgayXuat());
                vT.add(v.get(i).getKhachHang());
                vT.add(v.get(i).getThuocInfoUnit());
                vT.add(v.get(i).getDonViTinh());
                vT.add(v.get(i).getSoLuong());
                vT.add(v.get(i).getGhiChu());
                vData.add(vT);
            }
            tableXuatHang.setModel(new DefaultTableModel(vData, vTitle) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            tableXuatHang.getColumnModel().getColumn(3).setPreferredWidth(500);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi loadTableData:\n" + ex.getMessage());
            ex.printStackTrace();ex.printStackTrace(ErrorLoger.getLogStream());
        }
    } 

    public void loadTableDataByGuessName(String select) {
        try {
            Vector vTitle = new Vector(Arrays.asList(new String[]{"Mã Phiếu", "Ngày Xuất", "Khách Hàng", "Dược Phẩm", "Đơn Vị Tính", "Số Lượng", "Ghi Chú"}));
            Vector vData = new Vector();
            Vector<XuatHangUnit> v = XuatHangDataManager.getLichSuXuatHangByGuessName(select);
            for (int i = 0; i < v.size(); i++) {
                Vector vT = new Vector();
                vT.add(v.get(i).getMaPhieu());
                vT.add(v.get(i).getNgayXuat());
                vT.add(v.get(i).getKhachHang());
                vT.add(v.get(i).getThuocInfoUnit());
                vT.add(v.get(i).getDonViTinh());
                vT.add(v.get(i).getSoLuong());
                vT.add(v.get(i).getGhiChu());
                vData.add(vT);
            }
            tableXuatHang.setModel(new DefaultTableModel(vData, vTitle) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            tableXuatHang.getColumnModel().getColumn(2).setPreferredWidth(500);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi loadTableDataByGuessName:\n" + ex.getMessage());
            ex.printStackTrace();ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }

    private void doMaximize(){
        butMinimize.setText("_");
        panelButMinimize.add(butMinimize);
        panelCenter.add(panelXuatHang);
        panelButAddNew.removeAll();
        panelRoot.setPreferredSize(new java.awt.Dimension(454, 350));
        panelRoot.updateUI();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableXuatHang = new javax.swing.JTable();
        panelControl = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbbSelect = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFilter = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtFilterByGuessName = new javax.swing.JTextField();
        panelRoot = new javax.swing.JPanel();
        panelCenter = new javax.swing.JPanel();
        panelXuatHang = new ui.PanelXuatHang();
        panelButAddNew = new javax.swing.JPanel();
        butAddNew = new javax.swing.JButton();
        panelButMinimize = new javax.swing.JPanel();
        butMinimize = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        tableXuatHang.setAutoCreateRowSorter(true);
        tableXuatHang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tableXuatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableXuatHang.getTableHeader().setReorderingAllowed(false);
        tableXuatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableXuatHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableXuatHang);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panelControl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel1.setText("Chọn danh sách cần xem");
        jPanel3.add(jLabel1);

        cbbSelect.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả" }));
        cbbSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSelectActionPerformed(evt);
            }
        });
        jPanel3.add(cbbSelect);

        panelControl.add(jPanel3);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(1, 20));
        panelControl.add(jSeparator1);

        jLabel2.setText("Tìm kiếm theo tên thuốc ");
        jPanel4.add(jLabel2);

        txtFilter.setPreferredSize(new java.awt.Dimension(100, 30));
        txtFilter.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFilterFocusGained(evt);
            }
        });
        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilterKeyReleased(evt);
            }
        });
        jPanel4.add(txtFilter);

        panelControl.add(jPanel4);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setPreferredSize(new java.awt.Dimension(1, 20));
        panelControl.add(jSeparator2);

        jLabel3.setText("Tìm kiếm theo tên khách hàng ");
        jPanel5.add(jLabel3);

        txtFilterByGuessName.setPreferredSize(new java.awt.Dimension(100, 30));
        txtFilterByGuessName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFilterByGuessNameFocusGained(evt);
            }
        });
        txtFilterByGuessName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilterByGuessNameKeyReleased(evt);
            }
        });
        jPanel5.add(txtFilterByGuessName);

        panelControl.add(jPanel5);

        add(panelControl, java.awt.BorderLayout.PAGE_START);

        panelRoot.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelRoot.setPreferredSize(new java.awt.Dimension(454, 350));
        panelRoot.setLayout(new java.awt.BorderLayout());

        panelCenter.setLayout(new java.awt.GridLayout(1, 0));

        panelXuatHang.setPreferredSize(new java.awt.Dimension(664, 350));
        panelCenter.add(panelXuatHang);

        panelRoot.add(panelCenter, java.awt.BorderLayout.CENTER);

        panelButAddNew.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        butAddNew.setForeground(new java.awt.Color(0, 0, 254));
        butAddNew.setText("Thêm Phiếu Xuất Mới");
        butAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAddNewActionPerformed(evt);
            }
        });
        panelButAddNew.add(butAddNew);

        panelRoot.add(panelButAddNew, java.awt.BorderLayout.WEST);

        panelButMinimize.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));

        butMinimize.setForeground(new java.awt.Color(254, 0, 0));
        butMinimize.setText("_");
        butMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butMinimizeActionPerformed(evt);
            }
        });
        panelButMinimize.add(butMinimize);

        panelRoot.add(panelButMinimize, java.awt.BorderLayout.EAST);

        add(panelRoot, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void tableXuatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableXuatHangMouseClicked
        try {
            if (tableXuatHang.getSelectedRowCount() != 0) {
                DuocPhamUnit duocUnit = DuocPhamDataManager.getDuocPhamUnit(((DuocPhamUnit)tableXuatHang.getValueAt(tableXuatHang.getSelectedRow(), 3)).getId());
                //DuocPhamUnit duocUnit = (DuocPhamUnit)tableNhapHang.getValueAt(tableNhapHang.getSelectedRow(), 3);
                XuatHangUnit nUnit = XuatHangDataManager.getXuatHangUnit(tableXuatHang.getValueAt(tableXuatHang.getSelectedRow(), 0).toString());
                panelXuatHang.setData(nUnit, duocUnit);
                doMaximize();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi tableXuatHangMouseClicked:\n" + ex.getMessage());
			ex.printStackTrace();ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }//GEN-LAST:event_tableXuatHangMouseClicked

    private void cbbSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSelectActionPerformed
        XuatHangDataManager.setFileName(cbbSelect.getSelectedItem() + ".data");
        loadTableData("*");
    }//GEN-LAST:event_cbbSelectActionPerformed

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        loadTableData(txtFilter.getText());
    }//GEN-LAST:event_txtFilterKeyReleased

    private void txtFilterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFilterFocusGained
        txtFilter.selectAll();
    }//GEN-LAST:event_txtFilterFocusGained

    private void txtFilterByGuessNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFilterByGuessNameFocusGained
        txtFilterByGuessName.selectAll();
    }//GEN-LAST:event_txtFilterByGuessNameFocusGained

    private void txtFilterByGuessNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterByGuessNameKeyReleased
        loadTableDataByGuessName(txtFilterByGuessName.getText());
    }//GEN-LAST:event_txtFilterByGuessNameKeyReleased

    private void butAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAddNewActionPerformed
        panelXuatHang.doNewForm();
        doMaximize();
    }//GEN-LAST:event_butAddNewActionPerformed

    private void butMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butMinimizeActionPerformed
        panelCenter.removeAll();
        panelButMinimize.removeAll();
        panelButAddNew.add(butAddNew);
        panelRoot.setPreferredSize(new java.awt.Dimension(454, 35));
        panelRoot.updateUI();
    }//GEN-LAST:event_butMinimizeActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAddNew;
    private javax.swing.JButton butMinimize;
    private javax.swing.JComboBox cbbSelect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panelButAddNew;
    private javax.swing.JPanel panelButMinimize;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelControl;
    private javax.swing.JPanel panelRoot;
    private ui.PanelXuatHang panelXuatHang;
    private javax.swing.JTable tableXuatHang;
    private javax.swing.JTextField txtFilter;
    private javax.swing.JTextField txtFilterByGuessName;
    // End of variables declaration//GEN-END:variables
}
