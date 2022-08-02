package ui;

import exception.ExistException;
import exception.NoUnitIsSetException;
import exception.NotExistException;
import java.text.ParseException;
import javax.swing.JOptionPane;
import unit.DuocPhamUnit;
import unit.GlobalData;
import unit.XuatHangUnit;
import unit.manager.ApplicationSettingManager;
import worker.DateTimeMaster;
import unit.manager.DuocPhamDataManager;
import unit.manager.XuatHangDataManager;
import worker.ErrorLoger;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class PanelXuatHang extends javax.swing.JPanel {

    /**
     * Creates new form PanelNhapHang
     */
    public PanelXuatHang() {
        initComponents();

        txtNgayXuat.setText(DateTimeMaster.getNgayThangHienTai());
        try {
            taoMaPhieuMoi();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gặp lỗi khi tự sinh mã số phiếu");
        }
        try {
            splitPane.setDividerLocation(ApplicationSettingManager.getPanelXuatHangDividerLocation());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }
    XuatHangUnit unit;

    public void setData(XuatHangUnit unit, DuocPhamUnit duocUnit) {
        this.unit = unit;
        txtMaPhieu.setText(unit.getMaPhieu());
        txtKhachHang.setText(unit.getKhachHang());
        txtNgayXuat.setText(unit.getNgayXuat());
        txtSoLuong.setText(unit.getSoLuong() + "");
        txtSoLuong.setToolTipText(unit.getSoLuong() + "");//for update
        txtDonViTinh.setText(unit.getDonViTinh());
        txtGhiChu.setText(unit.getGhiChu());
        panelChonDuocPham.setDataUnitFromNhapHangData(duocUnit);
    }

    private boolean checkFieldsOK() {
        txtMaPhieu.setText(txtMaPhieu.getText().trim());
        txtNgayXuat.setText(txtNgayXuat.getText().trim());
        txtSoLuong.setText(txtSoLuong.getText().trim());
        txtGhiChu.setText(txtGhiChu.getText().trim());
        if (txtMaPhieu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Mã Phiếu");
            return false;
        }
        if (txtNgayXuat.getText().isEmpty()) {
            return false;
        }
        try {
            DateTimeMaster.convertToDate(txtNgayXuat.getText());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Ngày xuất không tồn tại");
            return false;
        }
        if (txtSoLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Số Lượng");
            return false;
        }
        try {
            if (Integer.parseInt(txtSoLuong.getText()) <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng phải là số > 0");
            return false;
        }
        return true;
    }

    private void taoMaPhieuMoi() throws Exception {
        try {
            txtMaPhieu.setText("PX" + GlobalData.getXuatHangSetting().getPhieuXuatNewID());
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
            throw ex;
        }
    }
    
    public void doNewForm(){
        butPhieuMoi.doClick();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        splitPane = new javax.swing.JSplitPane();
        panelChonDuocPham = new ui.PanelChonDuocPham();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtMaPhieu = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtNgayXuat = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        butNgay = new javax.swing.JButton();
        ButNgayGio = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtKhachHang = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtDonViTinh = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        lbStatus = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        butPhieuMoi = new javax.swing.JButton();
        butMaPhieuMoi = new javax.swing.JButton();
        butXuatHang = new javax.swing.JButton();
        butSuaPhieuXuat = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Xuất Hàng"));
        setLayout(new java.awt.BorderLayout());

        jPanel13.setPreferredSize(new java.awt.Dimension(50, 10));
        add(jPanel13, java.awt.BorderLayout.WEST);

        splitPane.setDividerLocation(500);
        splitPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                splitPanePropertyChange(evt);
            }
        });
        splitPane.setLeftComponent(panelChonDuocPham);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin xuất hàng"));
        jPanel2.setLayout(new java.awt.GridLayout(7, 0));

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel9.setText(" Mã phiếu*");
        jLabel9.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel10.add(jLabel9, java.awt.BorderLayout.WEST);

        txtMaPhieu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtMaPhieu.setEnabled(false);
        jPanel10.add(txtMaPhieu, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel10);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jLabel8.setText(" Ngày*");
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel8.add(jLabel8, java.awt.BorderLayout.WEST);

        jPanel11.setLayout(new java.awt.BorderLayout());

        txtNgayXuat.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtNgayXuat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNgayXuatFocusGained(evt);
            }
        });
        jPanel11.add(txtNgayXuat, java.awt.BorderLayout.CENTER);

        jPanel12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 5));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("<");
        jPanel12.add(jLabel11);

        butNgay.setText("Ngày");
        butNgay.setFocusable(false);
        butNgay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        butNgay.setMargin(new java.awt.Insets(2, 2, 2, 2));
        butNgay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        butNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butNgayActionPerformed(evt);
            }
        });
        jPanel12.add(butNgay);

        ButNgayGio.setText("Giờ- Ngày");
        ButNgayGio.setFocusable(false);
        ButNgayGio.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ButNgayGio.setMargin(new java.awt.Insets(2, 2, 2, 2));
        ButNgayGio.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ButNgayGio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButNgayGioActionPerformed(evt);
            }
        });
        jPanel12.add(ButNgayGio);

        jPanel11.add(jPanel12, java.awt.BorderLayout.EAST);

        jPanel8.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel8);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel10.setText(" Khách hàng");
        jLabel10.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel6.add(jLabel10, java.awt.BorderLayout.WEST);

        txtKhachHang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtKhachHang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtKhachHangFocusGained(evt);
            }
        });
        jPanel6.add(txtKhachHang, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel6);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel6.setText(" Số lượng*");
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel5.add(jLabel6, java.awt.BorderLayout.WEST);

        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSoLuong.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoLuongFocusGained(evt);
            }
        });
        jPanel5.add(txtSoLuong, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel5);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel7.setText(" Đơn vị tính");
        jLabel7.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel7.add(jLabel7, java.awt.BorderLayout.WEST);

        txtDonViTinh.setEditable(false);
        txtDonViTinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtDonViTinh.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jPanel7.add(txtDonViTinh, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel7);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel5.setText(" Ghi chú");
        jLabel5.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel4.add(jLabel5, java.awt.BorderLayout.WEST);

        txtGhiChu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel4.add(txtGhiChu, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4);

        jPanel9.setLayout(new java.awt.BorderLayout());

        lbStatus.setForeground(new java.awt.Color(0, 0, 254));
        jPanel9.add(lbStatus, java.awt.BorderLayout.PAGE_START);

        butPhieuMoi.setText("Phiếu Mới");
        butPhieuMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butPhieuMoiActionPerformed(evt);
            }
        });
        jPanel1.add(butPhieuMoi);

        butMaPhieuMoi.setText("Mã Phiếu Mới");
        butMaPhieuMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butMaPhieuMoiActionPerformed(evt);
            }
        });
        jPanel1.add(butMaPhieuMoi);

        butXuatHang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        butXuatHang.setText("Xuất Hàng");
        butXuatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                butXuatHangMouseExited(evt);
            }
        });
        butXuatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butXuatHangActionPerformed(evt);
            }
        });
        jPanel1.add(butXuatHang);

        butSuaPhieuXuat.setText("Sửa Phiếu Xuất");
        butSuaPhieuXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                butSuaPhieuXuatMouseExited(evt);
            }
        });
        butSuaPhieuXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSuaPhieuXuatActionPerformed(evt);
            }
        });
        jPanel1.add(butSuaPhieuXuat);

        jPanel9.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel9);

        splitPane.setRightComponent(jPanel2);

        add(splitPane, java.awt.BorderLayout.CENTER);

        jPanel14.setPreferredSize(new java.awt.Dimension(50, 10));
        add(jPanel14, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void butNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butNgayActionPerformed
        txtNgayXuat.setText(DateTimeMaster.getNgayThangHienTai());
    }//GEN-LAST:event_butNgayActionPerformed

    private void ButNgayGioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButNgayGioActionPerformed
        txtNgayXuat.setText(DateTimeMaster.getNgayGioHienTai());
    }//GEN-LAST:event_ButNgayGioActionPerformed

    private void txtNgayXuatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayXuatFocusGained
        try {
            txtDonViTinh.setText(panelChonDuocPham.getDataUnit().getDonViTinh());
        } catch (NoUnitIsSetException ex) {
            panelChonDuocPham.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dược phẩm\nXin vui lòng chọn ở mục bên trái");
        }
    }//GEN-LAST:event_txtNgayXuatFocusGained

    private void txtKhachHangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtKhachHangFocusGained
        try {
            txtDonViTinh.setText(panelChonDuocPham.getDataUnit().getDonViTinh());
        } catch (NoUnitIsSetException ex) {
            panelChonDuocPham.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dược phẩm\nXin vui lòng chọn ở mục bên trái");
        }
    }//GEN-LAST:event_txtKhachHangFocusGained

    private void txtSoLuongFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoLuongFocusGained
        try {
            txtDonViTinh.setText(panelChonDuocPham.getDataUnit().getDonViTinh());
        } catch (NoUnitIsSetException ex) {
            panelChonDuocPham.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dược phẩm\nXin vui lòng chọn ở mục bên trái");
        }
    }//GEN-LAST:event_txtSoLuongFocusGained

    private void butXuatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butXuatHangActionPerformed
        try {
            if (!checkFieldsOK()) {
                return;
            }
            DuocPhamUnit unit2 = panelChonDuocPham.getDataUnit();
            if (Integer.parseInt(txtSoLuong.getText()) > unit2.getSoLuongHienCo()) {
                JOptionPane.showMessageDialog(this, "Số lượng xuất > Số lượng hiện có");
                return;
            }
            unit = new XuatHangUnit(txtMaPhieu.getText(), txtNgayXuat.getText(), txtKhachHang.getText(), unit2, txtDonViTinh.getText(), Integer.parseInt(txtSoLuong.getText()), txtGhiChu.getText());
            int iDPID = GlobalData.getXuatHangSetting().getPhieuXuatNewID();
            unit.setMaPhieu("PX" + iDPID);
            while (XuatHangDataManager.checkIfExist(unit)) {
                iDPID++;
                unit.setMaPhieu("PX" + iDPID);
            }
            //write Nhap Info
            XuatHangDataManager.addXuatHangUnit(unit);
            //update DuocPhamUnit so luong
            unit2.setSoLuongHienCo(unit2.getSoLuongHienCo() - Integer.parseInt(txtSoLuong.getText()));
            unit2.setNewAdded(false);
            DuocPhamDataManager.saveDuocPhamData();
            lbStatus.setText("Đã xuất");
            butPhieuMoi.doClick();
            //update table
            PanelLichSuXuatHang.it.loadTableData("*");
            PanelDanhSachHangLuuTru.it.loadTableData("*");
            //update settings
            GlobalData.getXuatHangSetting().setPhieuXuatLastID(iDPID);
            XuatHangDataManager.saveXuatHangSetting();
            if (unit2.getSoLuongHienCo() <= unit2.getSoLuongDuTruToiThieu()) {
                JOptionPane.showMessageDialog(this, "Số lượng dược phẩm " + unit2 + " đã <= số lượng dự trữ tối thiểu");
            }
        } catch (ExistException ex) {
            JOptionPane.showMessageDialog(this, "Phiếu nhập có mã " + txtMaPhieu.getText() + " đã tồn tại.");
        } catch (NoUnitIsSetException ex) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dược phẩm\nXin vui lòng chọn ở mục bên trái");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi chưa xác định:\n" + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }//GEN-LAST:event_butXuatHangActionPerformed

    private void butXuatHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butXuatHangMouseExited
        lbStatus.setText("");
    }//GEN-LAST:event_butXuatHangMouseExited

    private void butSuaPhieuXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butSuaPhieuXuatMouseExited
        lbStatus.setText("");
    }//GEN-LAST:event_butSuaPhieuXuatMouseExited

private void butSuaPhieuXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSuaPhieuXuatActionPerformed
    try {
        if (!checkFieldsOK()) {
            return;
        }
        if (txtSoLuong.getToolTipText() == null || txtSoLuong.getToolTipText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn phiếu xuất ở bảng bên trên");
            return;
        }
        DuocPhamUnit unit2 = panelChonDuocPham.getDataUnit();
        //update DuocPhamUnit so luong
        unit2.setSoLuongHienCo(unit2.getSoLuongHienCo() + (Integer.parseInt(txtSoLuong.getText()) - Integer.parseInt(txtSoLuong.getToolTipText())));
        DuocPhamDataManager.saveDuocPhamData();
        txtSoLuong.setToolTipText(txtSoLuong.getText());
        //write Nhap Info
        unit.setNgay(txtNgayXuat.getText());
        unit.setKhachHang(txtKhachHang.getText());
        unit.setDonViTinh(txtDonViTinh.getText());
        unit.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
        unit.setGhiChu(txtGhiChu.getText());
        unit.setThuocInfoUnit(unit2);
        XuatHangDataManager.replaceXuatUnit(unit, unit);
        lbStatus.setText("Đã sửa");
        //update table
        PanelLichSuXuatHang.it.loadTableData("*");
        PanelDanhSachHangLuuTru.it.loadTableData("*");
        if (unit2.getSoLuongHienCo() <= unit2.getSoLuongDuTruToiThieu()) {
            JOptionPane.showMessageDialog(this, "Số lượng dược phẩm " + unit2 + " đã <= số lượng dự trữ tối thiểu");
        }
    } catch (NotExistException ex) {
        JOptionPane.showMessageDialog(this, "Phiếu xuất " + txtMaPhieu.getText() + " không tồn tại");
    } catch (NoUnitIsSetException ex) {
        JOptionPane.showMessageDialog(this, "Bạn chưa chọn dược phẩm\nXin vui lòng chọn ở mục bên trái");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Lỗi chưa xác định:\n" + ex.getMessage());
        ex.printStackTrace();
        ex.printStackTrace(ErrorLoger.getLogStream());
    }
    }//GEN-LAST:event_butSuaPhieuXuatActionPerformed

    private void butPhieuMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butPhieuMoiActionPerformed
        try {
            taoMaPhieuMoi();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gặp lỗi khi tự sinh mã số phiếu");
            return;
        }
        txtKhachHang.setText("");
        txtDonViTinh.setText("");
        txtSoLuong.setText("");
        txtSoLuong.setToolTipText("");
        txtGhiChu.setText("");
    }//GEN-LAST:event_butPhieuMoiActionPerformed

    private void butMaPhieuMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butMaPhieuMoiActionPerformed
        try {
            taoMaPhieuMoi();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gặp lỗi khi tự sinh mã số phiếu");
            return;
        }
    }//GEN-LAST:event_butMaPhieuMoiActionPerformed

    private void splitPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_splitPanePropertyChange
        try {
            ApplicationSettingManager.setPanelXuatHangDividerLocation(splitPane.getDividerLocation());
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }//GEN-LAST:event_splitPanePropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButNgayGio;
    private javax.swing.JButton butMaPhieuMoi;
    private javax.swing.JButton butNgay;
    private javax.swing.JButton butPhieuMoi;
    private javax.swing.JButton butSuaPhieuXuat;
    private javax.swing.JButton butXuatHang;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbStatus;
    private ui.PanelChonDuocPham panelChonDuocPham;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JTextField txtDonViTinh;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtNgayXuat;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
