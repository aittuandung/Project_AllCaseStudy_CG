
package ui;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import unit.DuocPhamUnit;
import unit.GlobalData;
import unit.manager.DuocPhamDataManager;
import worker.ErrorLoger;
import worker.NoMarkText;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class PanelDanhSachHangLuuTru extends javax.swing.JPanel {

    public static PanelDanhSachHangLuuTru it;

    public PanelDanhSachHangLuuTru() {
        it = this;
        initComponents();
        loadTableData("*");
        butMinimize.doClick();
    }
    Vector vTitle = new Vector(Arrays.asList(new String[]{"Mã DP", "Dược Phẩm", "Quy Cách", "Nhà Sản Xuất", "Đơn Vị Tính", "Số Lượng Hiện Có", "SL Dự Trữ  Tối Thiểu", "Giá Nhập", "Giá Bán", "Ghi Chú"}));

    public void loadTableData(String select) {
        try {
            Vector<DuocPhamUnit> v = DuocPhamDataManager.getDanhSachDuocPham(select);
            sortList(v);
            showList(v);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi chưa xác định:\n" + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());

        }
    }

    private void loadTableDataByLowerThan() {
        try {
            Vector<DuocPhamUnit> v = DuocPhamDataManager.getDanhSachDuocPhamLowerThan(GlobalData.getDuocPhamData());
            sortList(v);
            showList(v);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi chưa xác định:\n" + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());

        }
    }

    private void sortList(Vector<DuocPhamUnit> v) {
        Collections.sort(v, new Comparator<Object>() {

            @Override
            public int compare(Object t, Object t1) {
                return NoMarkText.removeMark(((DuocPhamUnit) t).getTenThuoc().toLowerCase()).compareTo(NoMarkText.removeMark(((DuocPhamUnit) t1).getTenThuoc().toLowerCase()));
            }
        });
    }

    private void showList(Vector<DuocPhamUnit> v) {
        Vector vData = new Vector();
        String sO = "";
        int ix = 0;
        try {
            ix = GlobalData.getDuocPhamData().getDuocPhamNewID();
        } catch (Exception exception) {
            sO = "000000";
        }
        for (int i = 0; i < String.valueOf(ix).length(); i++) {
            sO += "0";
        }
        for (int i = 0; i < v.size(); i++) {
            Vector vT = new Vector();
            vT.add(v.get(i).getId().substring(0, 2) + sO.substring(0, sO.length() - v.get(i).getId().substring(2).length()) + v.get(i).getId().substring(2));
            vT.add(v.get(i).getTenThuoc().contains("|") ? v.get(i).getTenThuoc().substring(0, v.get(i).getTenThuoc().indexOf("|")).trim() : v.get(i).getTenThuoc());
            vT.add(v.get(i).getTenThuoc().contains("|") ? v.get(i).getTenThuoc().substring(v.get(i).getTenThuoc().indexOf("|") + 1).trim() : "");
            vT.add(v.get(i).getNhaSanXuat());
            vT.add(v.get(i).getDonViTinh());
            vT.add(v.get(i).getSoLuongHienCo());
            vT.add(v.get(i).getSoLuongDuTruToiThieu());
            vT.add(v.get(i).getGiaNhap());
            vT.add(v.get(i).getGiaBan());
            vT.add(v.get(i).getGhiChu());
            vData.add(vT);
        }
        tableLuuTru.setModel(new DefaultTableModel(vData, vTitle) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tableLuuTru.getColumnModel().getColumn(1).setPreferredWidth(250);
        tableLuuTru.getColumnModel().getColumn(2).setPreferredWidth(150);
        tableLuuTru.getColumnModel().getColumn(3).setPreferredWidth(150);
    }

    private String removeZeroInID(String id) {
        String s = id.substring(0, 2);
        id = id.substring(2);
        while (id.charAt(0) == '0') {
            id = id.substring(1);
        }
        return s += id;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFilter = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        butLowerThan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLuuTru = new javax.swing.JTable();
        panelRoot = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        panelButAddNew = new javax.swing.JPanel();
        butAddNew = new javax.swing.JButton();
        panelCenter = new javax.swing.JPanel();
        panelThemXoaSuaDuocPham = new ui.PanelThemXoaSuaDuocPham();
        jPanel3 = new javax.swing.JPanel();
        panelButMinimize = new javax.swing.JPanel();
        butMinimize = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));

        jLabel2.setText("Tìm kiếm theo tên thuốc ");
        jPanel4.add(jLabel2);

        txtFilter.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtFilter.setPreferredSize(new java.awt.Dimension(100, 25));
        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFilterKeyReleased(evt);
            }
        });
        jPanel4.add(txtFilter);

        jPanel2.add(jPanel4);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator2.setPreferredSize(new java.awt.Dimension(1, 20));
        jPanel2.add(jSeparator2);

        butLowerThan.setText("SL hiện có <= SL dự trữ tối thiểu");
        butLowerThan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butLowerThanActionPerformed(evt);
            }
        });
        jPanel2.add(butLowerThan);

        add(jPanel2, java.awt.BorderLayout.PAGE_START);

        tableLuuTru.setAutoCreateRowSorter(true);
        tableLuuTru.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tableLuuTru.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableLuuTru.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableLuuTru.getTableHeader().setReorderingAllowed(false);
        tableLuuTru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableLuuTruMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableLuuTru);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panelRoot.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelRoot.setPreferredSize(new java.awt.Dimension(454, 300));
        panelRoot.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(155, 10));
        jPanel1.setLayout(new java.awt.BorderLayout());

        panelButAddNew.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 0));

        butAddNew.setForeground(new java.awt.Color(0, 0, 254));
        butAddNew.setText("Thêm Dược Phẩm Mới");
        butAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAddNewActionPerformed(evt);
            }
        });
        panelButAddNew.add(butAddNew);

        jPanel1.add(panelButAddNew, java.awt.BorderLayout.PAGE_START);

        panelRoot.add(jPanel1, java.awt.BorderLayout.WEST);

        panelCenter.setLayout(new java.awt.GridLayout(1, 0));

        panelThemXoaSuaDuocPham.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelThemXoaSuaDuocPham.setPreferredSize(new java.awt.Dimension(450, 170));
        panelCenter.add(panelThemXoaSuaDuocPham);

        panelRoot.add(panelCenter, java.awt.BorderLayout.CENTER);

        jPanel3.setPreferredSize(new java.awt.Dimension(155, 10));
        jPanel3.setLayout(new java.awt.BorderLayout());

        panelButMinimize.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 0, 0));

        butMinimize.setForeground(new java.awt.Color(254, 0, 0));
        butMinimize.setText("_");
        butMinimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butMinimizeActionPerformed(evt);
            }
        });
        panelButMinimize.add(butMinimize);

        jPanel3.add(panelButMinimize, java.awt.BorderLayout.PAGE_START);

        panelRoot.add(jPanel3, java.awt.BorderLayout.EAST);

        add(panelRoot, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void tableLuuTruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableLuuTruMouseClicked
        try {
            if (tableLuuTru.getSelectedRowCount() != 0) {
                panelThemXoaSuaDuocPham.setData(DuocPhamDataManager.getDuocPhamUnit(removeZeroInID(tableLuuTru.getValueAt(tableLuuTru.getSelectedRow(), 0).toString())));
                panelThemXoaSuaDuocPham.setMode(panelThemXoaSuaDuocPham.UPDATEMODE);
                panelButMinimize.add(butMinimize);
                butMinimize.setText("_");
                panelCenter.add(panelThemXoaSuaDuocPham);
                panelButAddNew.removeAll();
                panelRoot.updateUI();
                panelRoot.setPreferredSize(new java.awt.Dimension(454, 300));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi chưa xác định:\n" + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }//GEN-LAST:event_tableLuuTruMouseClicked

    private void txtFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyReleased
        loadTableData(txtFilter.getText());
    }//GEN-LAST:event_txtFilterKeyReleased

    private void butLowerThanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butLowerThanActionPerformed
        loadTableDataByLowerThan();
    }//GEN-LAST:event_butLowerThanActionPerformed

    private void butMinimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butMinimizeActionPerformed
        panelCenter.removeAll();
        panelButMinimize.removeAll();
        panelButAddNew.add(butAddNew);
        panelRoot.setPreferredSize(new java.awt.Dimension(454, 35));
        panelRoot.updateUI();
    }//GEN-LAST:event_butMinimizeActionPerformed

    private void butAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAddNewActionPerformed
        butMinimize.setText("_");
        panelButMinimize.add(butMinimize);
        panelCenter.add(panelThemXoaSuaDuocPham);
        panelThemXoaSuaDuocPham.doSwithModeThemMoi();
        panelButAddNew.removeAll();
        panelRoot.setPreferredSize(new java.awt.Dimension(454, 300));
        panelRoot.updateUI();
    }//GEN-LAST:event_butAddNewActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAddNew;
    private javax.swing.JButton butLowerThan;
    private javax.swing.JButton butMinimize;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel panelButAddNew;
    private javax.swing.JPanel panelButMinimize;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelRoot;
    private ui.PanelThemXoaSuaDuocPham panelThemXoaSuaDuocPham;
    private javax.swing.JTable tableLuuTru;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables
}
