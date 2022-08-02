
package ui;

import exception.ExistException;
import exception.NoUnitIsSetException;
import exception.NotExistException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import unit.DuocPhamUnit;
import unit.GlobalData;
import unit.manager.ApplicationSettingManager;
import unit.manager.DuocPhamDataManager;
import worker.ErrorLoger;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class PanelThemXoaSuaDuocPham extends javax.swing.JPanel {

    /**
     * Creates new form PanelThemXoaSuaDuocPham
     */
    public PanelThemXoaSuaDuocPham() {
        initComponents();
        Vector v = new Vector(Arrays.asList(new String[]{"Chai", "Gói", "Hộp", "Tube", "Ống", "Lọ", "Vỉ", "Viên"}));
        Collections.sort(v);
        cbbDonViTinh.setModel(new DefaultComboBoxModel(v));
        setMode(INPUTMODE);
        try {
            splitPane.setDividerLocation(ApplicationSettingManager.getPanelThemXoaSuaDuocPhamDividerLocation());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }
    public static final int VIEWONLYMODE = 1;
    public static final int INPUTMODE = 2;
    public static final int UPDATEMODE = 3;

    public void setMode(int mode) {
        if (mode == VIEWONLYMODE) {
            removeAll();
            add(jPanel2);
            updateUI();
            txtGhiChu.setEditable(false);
            txtNhaSanXuat.setEditable(false);
            txtSoLuongDuTruToiThieu.setEditable(false);
            txtTenThuoc.setEditable(false);
            cbbDonViTinh.setEnabled(false);
            txtSoLuongHienCo.setEnabled(false);
        } else if (mode == INPUTMODE) {
            txtSoLuongHienCo.setEnabled(true);
            butModeThemMoi.setSelected(true);
            cbbDonViTinh.setEnabled(true);
            jPanel8.removeAll();
            jPanel8.add(butThem);
            jPanel8.updateUI();
            txtTenThuoc.setText("");
            txtNhaSanXuat.setText("");
            txtSoLuongDuTruToiThieu.setText("");
            txtSoLuongHienCo.setText("");
            txtGhiChu.setText("");
            txtGiaNhap.setText("");
            txtGiaBan.setText("");
        } else if (mode == UPDATEMODE) {
            txtSoLuongHienCo.setEnabled(false);
            butModeCapNhat.setSelected(true);
            cbbDonViTinh.setEnabled(unit == null ? false : unit.isNewAdded() ? true : false);
            jPanel8.removeAll();
            jPanel8.add(butSua);
            jPanel8.updateUI();
        }
    }

    private boolean checkInputOK() {
        txtTenThuoc.setText(txtTenThuoc.getText().trim());
        txtNhaSanXuat.setText(txtNhaSanXuat.getText().trim());
        txtGhiChu.setText(txtGhiChu.getText().trim());
        txtSoLuongDuTruToiThieu.setText(txtSoLuongDuTruToiThieu.getText().trim());
        txtGiaNhap.setText(txtGiaNhap.getText().trim());
        txtGiaBan.setText(txtGiaBan.getText().trim());
        txtSoLuongHienCo.setText(txtSoLuongHienCo.getText().trim());
        if (txtGiaNhap.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Giá Nhập");
            return false;
        }
        if (txtGiaBan.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Giá Bán");
            return false;
        }
        if (txtTenThuoc.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Tên Thuốc");
            return false;
        }
        if (txtTenThuoc.getText().contains("~")) {
            JOptionPane.showMessageDialog(this, "Tên dược phẩm không được sử dụng ký tự ~");
            return false;
        }
        if (txtNhaSanXuat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Nhà Sản Xuất");
            return false;
        }
        if (txtSoLuongHienCo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Số Lượng Hiện Có");
            return false;
        }
        try {
                if (Integer.parseInt(txtSoLuongDuTruToiThieu.getText()) < 0) {
                    throw new Exception();
                }
                if (Integer.parseInt(txtGiaNhap.getText()) < 0) {
                    throw new Exception();
                }
                if (Integer.parseInt(txtGiaBan.getText()) < 0) {
                    throw new Exception();
                }
                if (Integer.parseInt(txtSoLuongHienCo.getText()) < 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Số lượng, giá nhập, giá bán phải là số >= 0");
                return false;
            }
        return true;
    }
    DuocPhamUnit unit;

    public void setData(DuocPhamUnit unit) {
        this.unit = unit;
        txtTenThuoc.setText(unit.getTenThuoc());
        txtNhaSanXuat.setText(unit.getNhaSanXuat());
        cbbDonViTinh.setSelectedItem(unit.getDonViTinh());
        txtGhiChu.setText(unit.getGhiChu());
        txtGiaNhap.setText(unit.getGiaNhap() + "");
        txtGiaBan.setText(unit.getGiaBan() + "");
        txtSoLuongDuTruToiThieu.setText(unit.getSoLuongDuTruToiThieu() + "");
        txtSoLuongHienCo.setText(unit.getSoLuongHienCo() + "");
    }

    public DuocPhamUnit getDataUnit() throws NoUnitIsSetException {
        if (unit == null) {
            throw new NoUnitIsSetException();
        }
        return unit;
    }

    public void doSwithModeThemMoi(){
        butModeThemMoi.doClick();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        butXoa = new javax.swing.JButton();
        buttonGroup_mode = new javax.swing.ButtonGroup();
        panelControl1 = new javax.swing.JPanel();
        butModeThemMoi = new javax.swing.JToggleButton();
        butModeCapNhat = new javax.swing.JToggleButton();
        splitPane = new javax.swing.JSplitPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtTenThuoc = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtNhaSanXuat = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cbbDonViTinh = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtSoLuongDuTruToiThieu = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtSoLuongHienCo = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiChu = new javax.swing.JTextArea();
        panelControl2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        butThem = new javax.swing.JButton();
        butSua = new javax.swing.JButton();
        labStatus = new javax.swing.JLabel();

        butXoa.setForeground(new java.awt.Color(255, 0, 0));
        butXoa.setText("Xóa Mặt Hàng");
        butXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                butXoaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                butXoaMouseExited(evt);
            }
        });
        butXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butXoaActionPerformed(evt);
            }
        });

        setLayout(new java.awt.BorderLayout());

        panelControl1.setLayout(new java.awt.GridLayout(1, 0));

        buttonGroup_mode.add(butModeThemMoi);
        butModeThemMoi.setSelected(true);
        butModeThemMoi.setText("Thêm Mới");
        butModeThemMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butModeThemMoiActionPerformed(evt);
            }
        });
        panelControl1.add(butModeThemMoi);

        buttonGroup_mode.add(butModeCapNhat);
        butModeCapNhat.setText("Cập Nhật");
        butModeCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butModeCapNhatActionPerformed(evt);
            }
        });
        panelControl1.add(butModeCapNhat);

        add(panelControl1, java.awt.BorderLayout.PAGE_START);

        splitPane.setDividerLocation(401);
        splitPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                splitPanePropertyChange(evt);
            }
        });

        jPanel2.setLayout(new java.awt.GridLayout(7, 0));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel2.setText(" Dược Phẩm*");
        jLabel2.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel1.add(jLabel2, java.awt.BorderLayout.WEST);

        txtTenThuoc.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenThuoc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenThuocFocusGained(evt);
            }
        });
        jPanel1.add(txtTenThuoc, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel1);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel4.setText(" Nhà sản xuất*");
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel3.add(jLabel4, java.awt.BorderLayout.WEST);

        txtNhaSanXuat.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtNhaSanXuat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNhaSanXuatFocusGained(evt);
            }
        });
        jPanel3.add(txtNhaSanXuat, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel7.setText(" Đơn vị tính*");
        jLabel7.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel7.add(jLabel7, java.awt.BorderLayout.WEST);

        cbbDonViTinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cbbDonViTinh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Chai", "Gói", "Hộp", "Hũ", "Ống", "Lọ", "Vỉ", "Viên" }));
        jPanel7.add(cbbDonViTinh, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel7);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel6.setText(" Dự trữ tối thiểu*");
        jLabel6.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel5.add(jLabel6, java.awt.BorderLayout.WEST);

        txtSoLuongDuTruToiThieu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSoLuongDuTruToiThieu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoLuongDuTruToiThieuFocusGained(evt);
            }
        });
        jPanel5.add(txtSoLuongDuTruToiThieu, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel5);

        jPanel14.setLayout(new java.awt.BorderLayout());

        jLabel3.setText(" Giá nhập*");
        jLabel3.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel14.add(jLabel3, java.awt.BorderLayout.WEST);

        txtGiaNhap.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtGiaNhap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGiaNhapFocusGained(evt);
            }
        });
        jPanel14.add(txtGiaNhap, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel14);

        jPanel15.setLayout(new java.awt.BorderLayout());

        jLabel8.setText(" Giá bán*");
        jLabel8.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel15.add(jLabel8, java.awt.BorderLayout.WEST);

        txtGiaBan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtGiaBan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGiaBanFocusGained(evt);
            }
        });
        jPanel15.add(txtGiaBan, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel15);

        jPanel16.setLayout(new java.awt.GridLayout(1, 0));

        jPanel17.setLayout(new java.awt.BorderLayout());

        jLabel9.setText(" SL hiện có*");
        jLabel9.setPreferredSize(new java.awt.Dimension(100, 20));
        jPanel17.add(jLabel9, java.awt.BorderLayout.WEST);

        txtSoLuongHienCo.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtSoLuongHienCo.setForeground(new java.awt.Color(254, 0, 0));
        txtSoLuongHienCo.setDisabledTextColor(new java.awt.Color(254, 0, 0));
        txtSoLuongHienCo.setEnabled(false);
        txtSoLuongHienCo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSoLuongHienCoMouseClicked(evt);
            }
        });
        txtSoLuongHienCo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoLuongHienCoFocusGained(evt);
            }
        });
        jPanel17.add(txtSoLuongHienCo, java.awt.BorderLayout.CENTER);

        jPanel16.add(jPanel17);

        jPanel2.add(jPanel16);

        splitPane.setLeftComponent(jPanel2);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Ghi chú"));
        jPanel4.setLayout(new java.awt.BorderLayout());

        txtGhiChu.setColumns(20);
        txtGhiChu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtGhiChu.setLineWrap(true);
        txtGhiChu.setRows(5);
        txtGhiChu.setWrapStyleWord(true);
        txtGhiChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGhiChuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(txtGhiChu);

        jPanel4.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        splitPane.setRightComponent(jPanel4);

        add(splitPane, java.awt.BorderLayout.CENTER);

        panelControl2.setLayout(new java.awt.BorderLayout());

        butThem.setForeground(new java.awt.Color(0, 0, 255));
        butThem.setText("Thêm Mặt Hàng");
        butThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                butThemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                butThemMouseExited(evt);
            }
        });
        butThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butThemActionPerformed(evt);
            }
        });
        jPanel8.add(butThem);

        butSua.setForeground(new java.awt.Color(0, 153, 255));
        butSua.setText("Cập Nhật Thông Tin");
        butSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                butSuaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                butSuaMouseExited(evt);
            }
        });
        butSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSuaActionPerformed(evt);
            }
        });
        jPanel8.add(butSua);

        panelControl2.add(jPanel8, java.awt.BorderLayout.CENTER);

        labStatus.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        labStatus.setForeground(new java.awt.Color(0, 0, 255));
        labStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelControl2.add(labStatus, java.awt.BorderLayout.PAGE_START);

        add(panelControl2, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void butThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butThemActionPerformed
        try {
            if (!checkInputOK()) {
                return;
            }
            unit = new DuocPhamUnit();
            int iDPID = GlobalData.getDuocPhamData().getDuocPhamNewID();
            unit.setId("DP" + iDPID);
            while(iDPID > 1 && !DuocPhamDataManager.checkIfExist(unit)){
                iDPID--;
                unit.setId("DP" + iDPID);
            }
            while(DuocPhamDataManager.checkIfExist(unit)){
                iDPID++;
                unit.setId("DP" + iDPID);
            }
            unit.setTenThuoc(txtTenThuoc.getText());
            unit.setNhaSanXuat(txtNhaSanXuat.getText());
            unit.setDonViTinh(cbbDonViTinh.getSelectedItem().toString());
            unit.setGiaNhap(Integer.parseInt(txtGiaNhap.getText()));
            unit.setGiaBan(Integer.parseInt(txtGiaBan.getText()));
            unit.setGhiChu(txtGhiChu.getText());
            unit.setSoLuongDuTruToiThieu(Integer.parseInt(txtSoLuongDuTruToiThieu.getText()));
            unit.setSoLuongHienCo(Integer.parseInt(txtSoLuongHienCo.getText()));
            if (unit.getGiaNhap() > unit.getGiaBan()) {
                JOptionPane.showMessageDialog(this, "Giá nhập > Giá bán");
                return;
            }
            System.out.println(unit.getId() + " ~ " + unit);
            DuocPhamDataManager.addDuocPhamUnit(unit);
            labStatus.setText("Đã thêm");
            PanelDanhSachHangLuuTru.it.loadTableData("*");
            GlobalData.getDuocPhamData().setDuocPhamLastID(iDPID);
            DuocPhamDataManager.saveDuocPhamData();
        } catch (ExistException ex) {
            JOptionPane.showMessageDialog(this, "Đã tồn tại");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi chưa xác định:\n" + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }//GEN-LAST:event_butThemActionPerformed

    private void butThemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butThemMouseEntered
        labStatus.setText("");
    }//GEN-LAST:event_butThemMouseEntered

    private void butXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butXoaActionPerformed
        try {
            txtTenThuoc.setText(txtTenThuoc.getText().trim());
            if (!txtTenThuoc.getText().isEmpty()) {
                if (JOptionPane.showConfirmDialog(this, "Chắc chắn xóa", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        DuocPhamDataManager.deleteDuocPhamUnit(unit);
                    labStatus.setText("Đã xóa");
                    PanelDanhSachHangLuuTru.it.loadTableData("*");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn phải chọn dược phẩm cần xóa ở danh sách bên trên");
            }
        } catch (NotExistException ex) {
            JOptionPane.showMessageDialog(this, "Không tồn tại");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi chưa xác định:\n" + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }//GEN-LAST:event_butXoaActionPerformed

    private void butXoaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butXoaMouseEntered
        labStatus.setText("");
    }//GEN-LAST:event_butXoaMouseEntered

    private void butSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSuaActionPerformed
        try {
            if (!checkInputOK()) {
                return;
            }
            if (unit == null) {
                JOptionPane.showMessageDialog(this, "Bạn phải chọn dược phẩm cần sửa ở danh sách bên trên");
                return;
            }
            unit.setTenThuoc(txtTenThuoc.getText());
            unit.setNhaSanXuat(txtNhaSanXuat.getText());
            unit.setDonViTinh(cbbDonViTinh.getSelectedItem().toString());
            unit.setGiaNhap(Integer.parseInt(txtGiaNhap.getText()));
            unit.setGiaBan(Integer.parseInt(txtGiaBan.getText()));
            unit.setGhiChu(txtGhiChu.getText());
            unit.setSoLuongDuTruToiThieu(Integer.parseInt(txtSoLuongDuTruToiThieu.getText()));
            unit.setSoLuongHienCo(Integer.parseInt(txtSoLuongHienCo.getText()));
            if (unit.getGiaNhap() > unit.getGiaBan()) {
                JOptionPane.showMessageDialog(this, "Giá nhập > Giá bán");
                return;
            }
            DuocPhamDataManager.saveDuocPhamData();
            labStatus.setText("Đã cập nhật");
            PanelDanhSachHangLuuTru.it.loadTableData("*");
        } catch (NotExistException ex) {
            JOptionPane.showMessageDialog(this, "Không tồn tại");
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_butSuaActionPerformed

    private void butSuaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butSuaMouseEntered
        labStatus.setText("");
    }//GEN-LAST:event_butSuaMouseEntered

    private void butThemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butThemMouseExited
        labStatus.setText("");
    }//GEN-LAST:event_butThemMouseExited

    private void butSuaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butSuaMouseExited
        labStatus.setText("");
    }//GEN-LAST:event_butSuaMouseExited

    private void butXoaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butXoaMouseExited
        labStatus.setText("");
    }//GEN-LAST:event_butXoaMouseExited

    private void butModeThemMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butModeThemMoiActionPerformed
        setMode(INPUTMODE);
    }//GEN-LAST:event_butModeThemMoiActionPerformed

    private void butModeCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butModeCapNhatActionPerformed
        setMode(UPDATEMODE);
    }//GEN-LAST:event_butModeCapNhatActionPerformed

    private void txtTenThuocFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenThuocFocusGained
        txtTenThuoc.selectAll();
    }//GEN-LAST:event_txtTenThuocFocusGained

    private void txtNhaSanXuatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNhaSanXuatFocusGained
        txtNhaSanXuat.selectAll();
    }//GEN-LAST:event_txtNhaSanXuatFocusGained

    private void txtSoLuongDuTruToiThieuFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoLuongDuTruToiThieuFocusGained
        txtSoLuongDuTruToiThieu.selectAll();
    }//GEN-LAST:event_txtSoLuongDuTruToiThieuFocusGained

    private void txtGiaNhapFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGiaNhapFocusGained
        txtGiaNhap.selectAll();
    }//GEN-LAST:event_txtGiaNhapFocusGained

    private void txtGiaBanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGiaBanFocusGained
        txtGiaBan.selectAll();
    }//GEN-LAST:event_txtGiaBanFocusGained

    private void txtSoLuongHienCoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoLuongHienCoFocusGained
        txtSoLuongHienCo.selectAll();
    }//GEN-LAST:event_txtSoLuongHienCoFocusGained

    private void txtSoLuongHienCoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSoLuongHienCoMouseClicked
        if (evt.getClickCount() == 2){
            txtSoLuongHienCo.setEnabled(true);
        }
    }//GEN-LAST:event_txtSoLuongHienCoMouseClicked

    private void txtGhiChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGhiChuMouseClicked
        if (evt.getClickCount() == 2){
            txtGhiChu.selectAll();
        }
    }//GEN-LAST:event_txtGhiChuMouseClicked

    private void splitPanePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_splitPanePropertyChange
        try {
            ApplicationSettingManager.setPanelThemXoaSuaDuocPhamDividerLocation(splitPane.getDividerLocation());
        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }//GEN-LAST:event_splitPanePropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton butModeCapNhat;
    private javax.swing.JToggleButton butModeThemMoi;
    private javax.swing.JButton butSua;
    private javax.swing.JButton butThem;
    private javax.swing.JButton butXoa;
    private javax.swing.ButtonGroup buttonGroup_mode;
    private javax.swing.JComboBox cbbDonViTinh;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labStatus;
    private javax.swing.JPanel panelControl1;
    private javax.swing.JPanel panelControl2;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JTextArea txtGhiChu;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtNhaSanXuat;
    private javax.swing.JTextField txtSoLuongDuTruToiThieu;
    private javax.swing.JTextField txtSoLuongHienCo;
    private javax.swing.JTextField txtTenThuoc;
    // End of variables declaration//GEN-END:variables
}
