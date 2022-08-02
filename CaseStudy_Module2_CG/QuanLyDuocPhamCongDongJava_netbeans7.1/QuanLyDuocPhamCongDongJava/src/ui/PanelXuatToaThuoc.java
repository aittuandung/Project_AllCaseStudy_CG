package ui;

import exception.ExistException;
import exception.NoUnitIsSetException;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Vector;
import javax.swing.JOptionPane;
import unit.DuocPhamToaThuocUnit;
import unit.DuocPhamUnit;
import unit.GlobalData;
import unit.ToaThuocUnit;
import unit.manager.DuocPhamDataManager;
import unit.manager.ToaThuocDataManager;
import unit.manager.ApplicationSettingManager;
import worker.DateTimeMaster;
import worker.ErrorLoger;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class PanelXuatToaThuoc extends javax.swing.JPanel {

    /**
     * Creates new form PanelNhapHang
     */
    public PanelXuatToaThuoc() {
        initComponents();
        txtNgayXuat.setText(DateTimeMaster.getNgayThangHienTai());
        try {
            taoMaPhieuMoi();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gặp lỗi khi tự sinh mã số phiếu");
        }
        panelControlListThuocSelect.removeAll();
        panelControlListThuocSelect.updateUI();
        try {
            splitPane1.setDividerLocation(ApplicationSettingManager.getPanelXuatToaThuocDivider1Location());
            splitPane2.setDividerLocation(ApplicationSettingManager.getPanelXuatToaThuocDivider2Location());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }
    ActionListener panelChonDuocPhamAL = new ActionListener() {

        public void actionPerformed(java.awt.event.ActionEvent evt) {
            try {
                int iSoLuong = 0;
                
                DuocPhamUnit dpu = panelChonDuocPham.getDataUnit();
                try {
                    iSoLuong = Integer.parseInt(panelChonDuocPham.getSoLuong());
                    if (iSoLuong <= 0) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(panelChonDuocPham, "Số lượng phải là số > 0");
                    panelChonDuocPham.setFocusOnTextFieldSoLuong();
                    return;
                }
                if (unit.getDuocPhams().contains(new DuocPhamToaThuocUnit(dpu, iSoLuong))) {
                    JOptionPane.showMessageDialog(panelChonDuocPham, "Dược phẩm đã có trong toa");
                    return;
                }
                if (iSoLuong > dpu.getSoLuongHienCo()) {
                    JOptionPane.showMessageDialog(panelChonDuocPham, "Số lượng xuất > Số lượng hiện có (" + dpu.getSoLuongHienCo() + ")");
                    panelChonDuocPham.setFocusOnTextFieldSoLuong();
                    return;
                }
                DuocPhamToaThuocUnit dpttu = new DuocPhamToaThuocUnit(dpu, iSoLuong);
                unit.getDuocPhams().add(dpttu);
                vAddedDuocPhamToaThuocUnit.add(dpttu);
                Vector v = new Vector();
                int tong = 0;
                for (int i = 0; i < unit.getDuocPhams().size(); i++) {
                    v.add(unit.getDuocPhams().get(i));
                    tong += unit.getDuocPhams().get(i).getSoLuong() * unit.getDuocPhams().get(i).getDuocPhamUnit().getGiaBan();
                }
                txtTongTien.setText(tong + "");
                lstThuoc.setListData(v);
                panelChonDuocPham.setSelectingView();
            } catch (NoUnitIsSetException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panelChonDuocPham, "Bạn chưa chọn dược phẩm");
            }
        }
    };
    ToaThuocUnit unit = new ToaThuocUnit();
    Vector<DuocPhamToaThuocUnit> vRemovedDuocPhamToaThuocUnit = new Vector();
    Vector<DuocPhamToaThuocUnit> vAddedDuocPhamToaThuocUnit = new Vector();

    public void setData(ToaThuocUnit unit) {
        this.unit = unit;
        vRemovedDuocPhamToaThuocUnit.removeAllElements();
        vAddedDuocPhamToaThuocUnit.removeAllElements();
        txtMaPhieu.setText(unit.getMaPhieu());
        txtKhachHang.setText(unit.getKhachHang());
        txtNgayXuat.setText(unit.getNgayXuat());
        Vector v = new Vector();
        for (int i = 0; i < unit.getDuocPhams().size(); i++) {
            v.add(unit.getDuocPhams().get(i));
        }
        lstThuoc.setListData(v);
        txtTongTien.setText(unit.getTongTien() + "");
        txtGhiChu.setText(unit.getGhiChu());
    }

    private boolean checkFieldsOK() {
        txtMaPhieu.setText(txtMaPhieu.getText().trim());
        txtNgayXuat.setText(txtNgayXuat.getText().trim());
        txtTongTien.setText(txtTongTien.getText().trim());
        txtKhachHang.setText(txtKhachHang.getText().trim());
        txtGhiChu.setText(txtGhiChu.getText().trim());
        if (txtKhachHang.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập thông tin Khách Hàng");
            return false;
        }
        if (txtMaPhieu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Mã Phiếu");
            return false;
        }
        if (txtNgayXuat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Ngày Xuất");
            return false;
        }
        try {
            DateTimeMaster.convertToDate(txtNgayXuat.getText());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Ngày xuất không tồn tại");
            return false;
        }
        if (txtTongTien.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Tổng số tiền");
            return false;
        }
        try {
            if (Integer.parseInt(txtTongTien.getText()) <= 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Tổng tiền phải là số > 0");
            return false;
        }
        return true;
    }

    private void taoMaPhieuMoi() throws Exception {
        txtMaPhieu.setText("TT" + GlobalData.getToaThuocSetting().getToaThuocNewID());
    }

    public void doNewForm() {
        butToaThuocMoi.doClick();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        splitPane1 = new javax.swing.JSplitPane();
        jPanel13 = new javax.swing.JPanel();
        panelChonDuocPham = new ui.PanelChonDuocPham(panelChonDuocPhamAL);
        splitPane2 = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstThuoc = new javax.swing.JList();
        panelControlListThuocSelect = new javax.swing.JPanel();
        butLoaBoDuocPham = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtMaPhieu = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        txtNgayXuat = new javax.swing.JTextField();
        butNgay = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtKhachHang = new javax.swing.JTextField();
        panelTongTien = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        butToaThuocMoi = new javax.swing.JButton();
        butMaPhieuMoi = new javax.swing.JButton();
        butXuatToaThuoc = new javax.swing.JButton();
        butCapNhatThongTin = new javax.swing.JButton();
        lbStatus = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Xuất Toa Thuốc"));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        splitPane1.setDividerLocation(400);
        splitPane1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                splitPane1PropertyChange(evt);
            }
        });

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.setLayout(new java.awt.BorderLayout());
        jPanel13.add(panelChonDuocPham, java.awt.BorderLayout.CENTER);

        splitPane1.setLeftComponent(jPanel13);

        splitPane2.setDividerLocation(400);
        splitPane2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                splitPane2PropertyChange(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Các Dược Phẩm Đã Chọn"));

        lstThuoc.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lstThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstThuocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstThuoc);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panelControlListThuocSelect.setMinimumSize(new java.awt.Dimension(0, 0));

        butLoaBoDuocPham.setText("Loại Bỏ Dược Phẩm");
        butLoaBoDuocPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butLoaBoDuocPhamActionPerformed(evt);
            }
        });
        panelControlListThuocSelect.add(butLoaBoDuocPham);

        jPanel5.add(panelControlListThuocSelect, java.awt.BorderLayout.PAGE_END);

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

        splitPane2.setLeftComponent(jPanel2);

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel12.setPreferredSize(new java.awt.Dimension(250, 264));
        jPanel12.setLayout(new java.awt.GridLayout(1, 0));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin khách hàng"));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.GridLayout(4, 0));

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel5.setText("Mã Phiếu*");
        jLabel5.setPreferredSize(new java.awt.Dimension(80, 14));
        jPanel10.add(jLabel5, java.awt.BorderLayout.WEST);

        txtMaPhieu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtMaPhieu.setEnabled(false);
        txtMaPhieu.setPreferredSize(new java.awt.Dimension(120, 27));
        jPanel10.add(txtMaPhieu, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel10);

        jPanel8.setLayout(new java.awt.BorderLayout());

        txtNgayXuat.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtNgayXuat.setPreferredSize(new java.awt.Dimension(120, 27));
        jPanel8.add(txtNgayXuat, java.awt.BorderLayout.CENTER);

        butNgay.setText(" Ngày*");
        butNgay.setFocusable(false);
        butNgay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        butNgay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        butNgay.setMargin(new java.awt.Insets(2, 2, 2, 2));
        butNgay.setPreferredSize(new java.awt.Dimension(80, 20));
        butNgay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        butNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butNgayActionPerformed(evt);
            }
        });
        jPanel8.add(butNgay, java.awt.BorderLayout.LINE_START);

        jPanel6.add(jPanel8);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jLabel6.setText("Khách hàng*");
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 14));
        jPanel11.add(jLabel6, java.awt.BorderLayout.WEST);

        txtKhachHang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtKhachHang.setPreferredSize(new java.awt.Dimension(120, 27));
        jPanel11.add(txtKhachHang, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel11);

        panelTongTien.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Tổng số tiền*");
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 14));
        panelTongTien.add(jLabel3, java.awt.BorderLayout.WEST);

        txtTongTien.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTongTien.setPreferredSize(new java.awt.Dimension(120, 27));
        panelTongTien.add(txtTongTien, java.awt.BorderLayout.CENTER);

        jPanel6.add(panelTongTien);

        jPanel9.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Ghi chú"));
        jPanel7.setLayout(new java.awt.BorderLayout());

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setRows(5);
        txtGhiChu.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtGhiChu);

        jPanel7.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel9.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel12.add(jPanel9);

        splitPane2.setRightComponent(jPanel12);

        splitPane1.setRightComponent(splitPane2);

        jPanel1.add(splitPane1);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel4.setLayout(new java.awt.BorderLayout());

        butToaThuocMoi.setText("Toa Thuốc Mới");
        butToaThuocMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butToaThuocMoiActionPerformed(evt);
            }
        });
        jPanel3.add(butToaThuocMoi);

        butMaPhieuMoi.setText("Mã Phiếu Mới");
        butMaPhieuMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butMaPhieuMoiActionPerformed(evt);
            }
        });
        jPanel3.add(butMaPhieuMoi);

        butXuatToaThuoc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        butXuatToaThuoc.setText("Xuất Toa Thuốc");
        butXuatToaThuoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                butXuatToaThuocMouseExited(evt);
            }
        });
        butXuatToaThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butXuatToaThuocActionPerformed(evt);
            }
        });
        jPanel3.add(butXuatToaThuoc);

        butCapNhatThongTin.setText("Cập Nhật Thông Tin Toa Thuốc");
        butCapNhatThongTin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                butCapNhatThongTinMouseExited(evt);
            }
        });
        butCapNhatThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCapNhatThongTinActionPerformed(evt);
            }
        });
        jPanel3.add(butCapNhatThongTin);

        jPanel4.add(jPanel3, java.awt.BorderLayout.CENTER);

        lbStatus.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(0, 0, 254));
        lbStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel4.add(lbStatus, java.awt.BorderLayout.PAGE_START);

        add(jPanel4, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void butNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butNgayActionPerformed
        txtNgayXuat.setText(DateTimeMaster.getNgayThangHienTai());
    }//GEN-LAST:event_butNgayActionPerformed

    private void lstThuocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstThuocMouseClicked
        if (lstThuoc.getSelectedIndex() != -1) {
            panelControlListThuocSelect.add(butLoaBoDuocPham);
            panelControlListThuocSelect.updateUI();
        }
    }//GEN-LAST:event_lstThuocMouseClicked

    private void butXuatToaThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butXuatToaThuocActionPerformed
        if (!checkFieldsOK()) {
            return;
        }
        try {
            int iDPID = GlobalData.getToaThuocSetting().getToaThuocNewID();
            unit.setMaPhieu("TT" + iDPID);
            while (ToaThuocDataManager.checkIfExist(unit)) {
                iDPID++;
                unit.setMaPhieu("TT" + iDPID);
            }
            String sThongBao = "";
            unit.setGhiChu(txtGhiChu.getText());
            unit.setKhachHang(txtKhachHang.getText());
            unit.setNgayXuat(txtNgayXuat.getText());
            unit.setTongTien(Integer.parseInt(txtTongTien.getText()));
            //update DuocPhamUnit so luong
            for (int i = 0; i < vAddedDuocPhamToaThuocUnit.size(); i++) {
                DuocPhamUnit unit2 = DuocPhamDataManager.getDuocPhamUnit(vAddedDuocPhamToaThuocUnit.get(i).getDuocPhamUnit().getId());
                unit2.setSoLuongHienCo(unit2.getSoLuongHienCo() - vAddedDuocPhamToaThuocUnit.get(i).getSoLuong());
                unit2.setNewAdded(false);
                if (unit2.getSoLuongHienCo() <= unit2.getSoLuongDuTruToiThieu()) {
                    sThongBao += "Số lượng dược phẩm " + unit2 + " đã <= số lượng dự trữ tối thiểu\n";
                }
            }
            ToaThuocDataManager.addToaThuocUnit(unit);
            DuocPhamDataManager.saveDuocPhamData();
            lbStatus.setText("Đã xuất");
            PanelLichSuXuatToaThuoc.it.loadTableData("*");
            PanelDanhSachHangLuuTru.it.loadTableData("*");
            butToaThuocMoi.doClick();
            //update settings
            GlobalData.getToaThuocSetting().setToaThuocLastID(iDPID);
            ToaThuocDataManager.saveToaThuocSetting();
            if (!sThongBao.isEmpty()) {
                JOptionPane.showMessageDialog(this, sThongBao);
            }
        } catch (ExistException ex) {
            JOptionPane.showMessageDialog(this, "Toa thuốc có mã phiếu " + unit.getMaPhieu() + " đã tồn tại");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }//GEN-LAST:event_butXuatToaThuocActionPerformed

    private void butLoaBoDuocPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butLoaBoDuocPhamActionPerformed
        if (lstThuoc.getSelectedIndex() != -1) {
            for (int index = 0; index < lstThuoc.getSelectedValuesList().size(); index++) {
                if (unit.getDuocPhams().contains((DuocPhamToaThuocUnit) lstThuoc.getSelectedValuesList().get(index))) {
                    vRemovedDuocPhamToaThuocUnit.add((DuocPhamToaThuocUnit) lstThuoc.getSelectedValuesList().get(index));
                    unit.getDuocPhams().remove(lstThuoc.getSelectedValuesList().get(index));
                } else {
                    JOptionPane.showMessageDialog(this, "Dược phẩm " + lstThuoc.getSelectedValuesList().get(index) + " không tồn tại. Đây là lỗi phần mềm");
                }
            }
            Vector v = new Vector();
            int tong = 0;
            for (int i = 0; i < unit.getDuocPhams().size(); i++) {
                v.add(unit.getDuocPhams().get(i));
                tong += unit.getDuocPhams().get(i).getSoLuong() * unit.getDuocPhams().get(i).getDuocPhamUnit().getGiaBan();
            }
            txtTongTien.setText(tong + "");
            lstThuoc.setListData(v);
            panelControlListThuocSelect.removeAll();
            panelControlListThuocSelect.updateUI();
        }
    }//GEN-LAST:event_butLoaBoDuocPhamActionPerformed

    private void butToaThuocMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butToaThuocMoiActionPerformed
        unit = new ToaThuocUnit();
        lstThuoc.setListData(new Vector());
        try {
            taoMaPhieuMoi();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gặp lỗi khi tự sinh mã số phiếu");
        }
        txtGhiChu.setText("");
        txtKhachHang.setText("");
        txtTongTien.setText("");
        vRemovedDuocPhamToaThuocUnit.removeAllElements();
        vAddedDuocPhamToaThuocUnit.removeAllElements();
    }//GEN-LAST:event_butToaThuocMoiActionPerformed

    private void butCapNhatThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCapNhatThongTinActionPerformed
        if (!checkFieldsOK()) {
            return;
        }
        try {
            unit.setMaPhieu(txtMaPhieu.getText());
            unit.setGhiChu(txtGhiChu.getText());
            unit.setKhachHang(txtKhachHang.getText());
            unit.setNgayXuat(txtNgayXuat.getText());
            unit.setTongTien(Integer.parseInt(txtTongTien.getText()));
            //update DuocPhamUnit so luong
            for (int i = 0; i < vAddedDuocPhamToaThuocUnit.size(); i++) {
                DuocPhamUnit unit2 = DuocPhamDataManager.getDuocPhamUnit(vAddedDuocPhamToaThuocUnit.get(i).getDuocPhamUnit().getId());
                unit2.setSoLuongHienCo(unit2.getSoLuongHienCo() - vAddedDuocPhamToaThuocUnit.get(i).getSoLuong());
                unit2.setNewAdded(false);
            }
            for (int i = 0; i < vRemovedDuocPhamToaThuocUnit.size(); i++) {
                DuocPhamUnit unit2 = DuocPhamDataManager.getDuocPhamUnit(vRemovedDuocPhamToaThuocUnit.get(i).getDuocPhamUnit().getId());
                unit2.setSoLuongHienCo(unit2.getSoLuongHienCo() + vRemovedDuocPhamToaThuocUnit.get(i).getSoLuong());
            }
            ToaThuocDataManager.replaceXuatUnit(unit, unit);
            DuocPhamDataManager.saveDuocPhamData();
            lbStatus.setText("Đã cập nhật");
            PanelLichSuXuatToaThuoc.it.loadTableData("*");
            PanelDanhSachHangLuuTru.it.loadTableData("*");
        } catch (ExistException ex) {
            JOptionPane.showMessageDialog(this, "Toa thuốc có mã phiếu " + unit.getMaPhieu() + " đã tồn tại");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
        }
    }//GEN-LAST:event_butCapNhatThongTinActionPerformed

    private void butCapNhatThongTinMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butCapNhatThongTinMouseExited
        lbStatus.setText("");
    }//GEN-LAST:event_butCapNhatThongTinMouseExited

    private void butXuatToaThuocMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butXuatToaThuocMouseExited
        lbStatus.setText("");
    }//GEN-LAST:event_butXuatToaThuocMouseExited

    private void butMaPhieuMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butMaPhieuMoiActionPerformed
        try {
            taoMaPhieuMoi();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gặp lỗi khi tự sinh mã số phiếu");
        }
    }//GEN-LAST:event_butMaPhieuMoiActionPerformed

    private void splitPane1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_splitPane1PropertyChange
        try {
            ApplicationSettingManager.setPanelXuatToaThuocDivider1Location(splitPane1.getDividerLocation());
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }//GEN-LAST:event_splitPane1PropertyChange

    private void splitPane2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_splitPane2PropertyChange
        try {
            ApplicationSettingManager.setPanelXuatToaThuocDivider2Location(splitPane2.getDividerLocation());
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }//GEN-LAST:event_splitPane2PropertyChange
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butCapNhatThongTin;
    private javax.swing.JButton butLoaBoDuocPham;
    private javax.swing.JButton butMaPhieuMoi;
    private javax.swing.JButton butNgay;
    private javax.swing.JButton butToaThuocMoi;
    private javax.swing.JButton butXuatToaThuoc;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JList lstThuoc;
    private ui.PanelChonDuocPham panelChonDuocPham;
    private javax.swing.JPanel panelControlListThuocSelect;
    private javax.swing.JPanel panelTongTien;
    private javax.swing.JSplitPane splitPane1;
    private javax.swing.JSplitPane splitPane2;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtNgayXuat;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
