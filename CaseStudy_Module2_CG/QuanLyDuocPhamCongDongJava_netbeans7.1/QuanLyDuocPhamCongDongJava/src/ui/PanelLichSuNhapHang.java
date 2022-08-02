
package ui;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import unit.DuocPhamUnit;
import unit.GlobalData;
import unit.NhapHangUnit;
import unit.manager.DuocPhamDataManager;
import unit.manager.NhapHangDataManager;
import worker.ErrorLoger;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class PanelLichSuNhapHang extends javax.swing.JPanel {

    public static PanelLichSuNhapHang it;

    /**
     * Creates new form PanelDanhSachHangLuuTru
     */
    public PanelLichSuNhapHang() {
        it = this;
        initComponents();
        Vector<String> v = NhapHangDataManager.getAllSavedFile();
        int index = 0;
        for (int i = 0; i < v.size(); i++) {
            if (NhapHangDataManager.FILENAME.contains(v.get(i))) {
                index = i;
            }
        }
        cbbSelect.setModel(new DefaultComboBoxModel(v));
        cbbSelect.setSelectedIndex(index);
        loadTableData("*");
        butMinimize.doClick();
    }

    public void loadTableData(String select) {
        try {
            Vector vTitle = new Vector(Arrays.asList(new String[]{"Mã Phiếu", "Ngày Nhập", "Dược Phẩm", "Số Lô", "Số Lượng", "Đơn Vị Tính", "Ngày SX", "Hạn SD", "Giá Nhập", "Giá Bán", "Ghi Chú"}));
            Vector vData = new Vector();
            Vector<NhapHangUnit> v = NhapHangDataManager.getLichSuNhapHang(select);
            for (int i = 0; i < v.size(); i++) {
                Vector vT = new Vector();
                vT.add(v.get(i).getMaPhieu());
                vT.add(v.get(i).getNgayNhap());
                vT.add(v.get(i).getDuocPhamUnit());
                vT.add(v.get(i).getSoLo());
                vT.add(v.get(i).getSoLuong());
                vT.add(v.get(i).getDonViTinh());
                vT.add(v.get(i).getNgaySanXuat());
                vT.add(v.get(i).getHanSuDung());
                vT.add(v.get(i).getGiaNhap());
                vT.add(v.get(i).getGiaBan());
                vT.add(v.get(i).getGhiChu());
                vData.add(vT);
            }
            tableNhapHang.setModel(new DefaultTableModel(vData, vTitle) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            tableNhapHang.getColumnModel().getColumn(2).setPreferredWidth(500);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi loadTableData:\n" + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }

    private void doMaximize(){
        butMinimize.setText("_");
        panelButMinimize.add(butMinimize);
        panelCenter.add(panelNhapHang);
        panelButAddNew.removeAll();
        panelRoot.setPreferredSize(new java.awt.Dimension(454, 400));
        panelRoot.updateUI();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableNhapHang = new javax.swing.JTable();
        panelControl = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbbSelect = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFilter = new javax.swing.JTextField();
        panelRoot = new javax.swing.JPanel();
        panelCenter = new javax.swing.JPanel();
        panelNhapHang = new ui.PanelNhapHang();
        panelButAddNew = new javax.swing.JPanel();
        butAddNew = new javax.swing.JButton();
        panelButMinimize = new javax.swing.JPanel();
        butMinimize = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        tableNhapHang.setAutoCreateRowSorter(true);
        tableNhapHang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tableNhapHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableNhapHang.getTableHeader().setReorderingAllowed(false);
        tableNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNhapHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableNhapHang);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panelControl.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel1.setText("Chọn danh sách cần xem ");
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

        add(panelControl, java.awt.BorderLayout.PAGE_START);

        panelRoot.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelRoot.setPreferredSize(new java.awt.Dimension(454, 350));
        panelRoot.setLayout(new java.awt.BorderLayout());

        panelCenter.setLayout(new java.awt.GridLayout(1, 0));

        panelNhapHang.setPreferredSize(new java.awt.Dimension(820, 350));
        panelCenter.add(panelNhapHang);

        panelRoot.add(panelCenter, java.awt.BorderLayout.CENTER);

        panelButAddNew.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        butAddNew.setForeground(new java.awt.Color(0, 0, 254));
        butAddNew.setText("Thêm Phiếu Nhập Mới");
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

    private void tableNhapHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNhapHangMouseClicked
        try {
            if (tableNhapHang.getSelectedRowCount() != 0) {
                DuocPhamUnit duocUnit = DuocPhamDataManager.getDuocPhamUnit(((DuocPhamUnit)tableNhapHang.getValueAt(tableNhapHang.getSelectedRow(), 2)).getId());
                //DuocPhamUnit duocUnit = (DuocPhamUnit)tableNhapHang.getValueAt(tableNhapHang.getSelectedRow(), 2);
                NhapHangUnit nUnit = NhapHangDataManager.getNhapHangUnit(tableNhapHang.getValueAt(tableNhapHang.getSelectedRow(), 0).toString());
                panelNhapHang.setData(nUnit, duocUnit);
                doMaximize();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi tableNhapHangMouseClicked:\n" + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }//GEN-LAST:event_tableNhapHangMouseClicked

    private void cbbSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSelectActionPerformed
        NhapHangDataManager.setFileName(cbbSelect.getSelectedItem() + ".data");
        loadTableData("*");
    }//GEN-LAST:event_cbbSelectActionPerformed

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        loadTableData(txtFilter.getText());
    }//GEN-LAST:event_txtFilterKeyReleased

    private void txtFilterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFilterFocusGained
        txtFilter.selectAll();
    }//GEN-LAST:event_txtFilterFocusGained

    private void butAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAddNewActionPerformed
        panelNhapHang.doNewForm();
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel panelButAddNew;
    private javax.swing.JPanel panelButMinimize;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelControl;
    private ui.PanelNhapHang panelNhapHang;
    private javax.swing.JPanel panelRoot;
    private javax.swing.JTable tableNhapHang;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables
}
