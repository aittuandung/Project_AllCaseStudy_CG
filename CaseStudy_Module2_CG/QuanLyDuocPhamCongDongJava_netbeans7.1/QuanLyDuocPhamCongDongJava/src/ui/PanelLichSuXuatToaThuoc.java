
package ui;

import java.util.Arrays;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import unit.GlobalData;
import unit.ToaThuocUnit;
import unit.manager.ToaThuocDataManager;
import worker.ErrorLoger;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class PanelLichSuXuatToaThuoc extends javax.swing.JPanel {

    public static PanelLichSuXuatToaThuoc it;

    /**
     * Creates new form PanelDanhSachHangLuuTru
     */
    public PanelLichSuXuatToaThuoc() {
        it = this;
        initComponents();
        loadTableData("*");
        Vector<String> v = ToaThuocDataManager.getAllSavedFile();
        int index = 0;
        for (int i = 0; i < v.size(); i++) {
            if (ToaThuocDataManager.FILENAME.contains(v.get(i))) {
                index = i;
            }
        }
        cbbSelect.setModel(new DefaultComboBoxModel(v));
        cbbSelect.setSelectedIndex(index);
        butMinimize.doClick();
    }
    Vector vTitle = new Vector(Arrays.asList(new String[]{"Mã Phiếu", "Ngày Xuất", "Khách Hàng", "SL Dược Phẩm", "Tổng Tiền", "Ghi Chú"}));

    public void loadTableData(String select) {
        try {
            Vector vData = new Vector();
            Vector<ToaThuocUnit> v = ToaThuocDataManager.getLichSuToaThuoc(select);
            for (int i = 0; i < v.size(); i++) {
                Vector vT = new Vector();
                vT.add(v.get(i).getMaPhieu());
                vT.add(v.get(i).getNgayXuat());
                vT.add(v.get(i).getKhachHang());
                vT.add(v.get(i).getDuocPhams().size());
                vT.add(v.get(i).getTongTien());
                vT.add(v.get(i).getGhiChu());
                vData.add(vT);
            }
            tableToaThuoc.setModel(new DefaultTableModel(vData, vTitle) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            tableToaThuoc.getColumnModel().getColumn(2).setPreferredWidth(500);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi loadTableData:\n" + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }

    public void loadTableDataByGuessName(String select) {
        try {
            Vector vData = new Vector();
            Vector<ToaThuocUnit> v = ToaThuocDataManager.getLichSuToaThuocByGuessName(select);
            for (int i = 0; i < v.size(); i++) {
                Vector vT = new Vector();
                vT.add(v.get(i).getMaPhieu());
                vT.add(v.get(i).getNgayXuat());
                vT.add(v.get(i).getKhachHang());
                vT.add(v.get(i).getDuocPhams().size());
                vT.add(v.get(i).getTongTien());
                vT.add(v.get(i).getGhiChu());
                vData.add(vT);
            }
            tableToaThuoc.setModel(new DefaultTableModel(vData, vTitle) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            tableToaThuoc.getColumnModel().getColumn(2).setPreferredWidth(500);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi loadTableDataByGuessName:\n" + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }

    private void doMaximine() {
        butMinimize.setText("_");
        panelButMinimize.add(butMinimize);
        panelCenter.add(panelXuatToaThuoc);
        panelButAddNew.removeAll();
        panelRoot.setPreferredSize(new java.awt.Dimension(454, 400));
        panelRoot.updateUI();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableToaThuoc = new javax.swing.JTable();
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
        panelButAddNew = new javax.swing.JPanel();
        butAddNew = new javax.swing.JButton();
        panelCenter = new javax.swing.JPanel();
        panelXuatToaThuoc = new ui.PanelXuatToaThuoc();
        panelButMinimize = new javax.swing.JPanel();
        butMinimize = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        tableToaThuoc.setAutoCreateRowSorter(true);
        tableToaThuoc.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tableToaThuoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableToaThuoc.getTableHeader().setReorderingAllowed(false);
        tableToaThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableToaThuocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableToaThuoc);

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

        panelButAddNew.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        butAddNew.setForeground(new java.awt.Color(0, 0, 254));
        butAddNew.setText("Thêm Toa Thuốc Mới");
        butAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAddNewActionPerformed(evt);
            }
        });
        panelButAddNew.add(butAddNew);

        panelRoot.add(panelButAddNew, java.awt.BorderLayout.WEST);

        panelCenter.setLayout(new java.awt.GridLayout(1, 0));
        panelCenter.add(panelXuatToaThuoc);

        panelRoot.add(panelCenter, java.awt.BorderLayout.CENTER);

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

    private void tableToaThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableToaThuocMouseClicked
        try {
            if (tableToaThuoc.getSelectedRowCount() != 0) {
                ToaThuocUnit tt = ToaThuocDataManager.getToaThuocUnit(tableToaThuoc.getValueAt(tableToaThuoc.getSelectedRow(), 0).toString());
                panelXuatToaThuoc.setData(tt);
                doMaximine();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi tableToaThuocMouseClicked:\n" + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }//GEN-LAST:event_tableToaThuocMouseClicked

    private void cbbSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSelectActionPerformed
        ToaThuocDataManager.setFileName(cbbSelect.getSelectedItem() + ".data");
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
        panelXuatToaThuoc.doNewForm();
        doMaximine();
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
    private ui.PanelXuatToaThuoc panelXuatToaThuoc;
    private javax.swing.JTable tableToaThuoc;
    private javax.swing.JTextField txtFilter;
    private javax.swing.JTextField txtFilterByGuessName;
    // End of variables declaration//GEN-END:variables
}
