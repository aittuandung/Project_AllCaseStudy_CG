
package ui;

import exception.ExistException;
import exception.NoUnitIsSetException;
import exception.NotExistException;
import java.text.ParseException;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel; 
import javax.swing.JOptionPane;
import unit.DuocPhamUnit;
import unit.GlobalData;
import unit.NhapHangUnit;
import unit.manager.ApplicationSettingManager;
import worker.DateTimeMaster;
import unit.manager.DuocPhamDataManager;
import unit.manager.NhapHangDataManager;
import worker.ErrorLoger;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class PanelNhapHang extends javax.swing.JPanel {

    /**
     * Creates new form PanelNhapHang
     */
    public PanelNhapHang() {
        initComponents();
        txtNgayNhap.setText(DateTimeMaster.getNgayThangHienTai());
        cbbNgay1.setSelectedItem(DateTimeMaster.getDate());
        cbbNgay2.setSelectedItem(DateTimeMaster.getDate());
        cbbThang1.setSelectedItem(DateTimeMaster.getMonth());
        cbbThang2.setSelectedItem(DateTimeMaster.getMonth());
        //year
        int y = Integer.parseInt(DateTimeMaster.getYear());
        Vector v = new Vector();
        for (int i = y - 5; i <= y + 5; i++) {
            v.add(i + "");
        }
        cbbNam1.setModel(new DefaultComboBoxModel(v));
        cbbNam2.setModel(new DefaultComboBoxModel(v));
        //
        cbbNam1.setSelectedItem(DateTimeMaster.getYear());
        cbbNam2.setSelectedItem(DateTimeMaster.getYear());
        txtNgaySanXuat.setText("");
        txtHanSuDung.setText("");
        try {
            taoMaPhieuMoi();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gặp lỗi khi tự sinh mã số phiếu");
            return;
        }
        panelChonDuocPham.setShowChuYOnDataSet(false);
        try {
            splitPane.setDividerLocation(ApplicationSettingManager.getPanelNhapHangDividerLocation());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }
    NhapHangUnit unit;

    public void setData(NhapHangUnit unit, DuocPhamUnit duocUnit) {
        this.unit = unit;
        txtMaPhieu.setText(unit.getMaPhieu());
        txtNgayNhap.setText(unit.getNgayNhap());
        txtSoLo.setText(unit.getSoLo());
        txtNgaySanXuat.setText(unit.getNgaySanXuat());
        txtHanSuDung.setText(unit.getHanSuDung());
        txtSoLuong.setText(unit.getSoLuong() + "");
        txtSoLuong.setToolTipText(unit.getSoLuong() + "");//for update
        txtDonViTinh.setText(unit.getDonViTinh());
        txtGhiChu.setText(unit.getGhiChu());
        txtGiaNhap.setText(unit.getGiaNhap() + "");
        txtGiaBan.setText(unit.getGiaBan() + "");
        panelChonDuocPham.setDataUnitFromNhapHangData(duocUnit);
    }

    private boolean checkFieldsOK() {
        txtMaPhieu.setText(txtMaPhieu.getText().trim());
        txtNgayNhap.setText(txtNgayNhap.getText().trim());
        txtSoLo.setText(txtSoLo.getText().trim());
        txtNgaySanXuat.setText(txtNgaySanXuat.getText().trim());
        txtHanSuDung.setText(txtHanSuDung.getText().trim());
        txtSoLuong.setText(txtSoLuong.getText().trim());
        txtGhiChu.setText(txtGhiChu.getText().trim());
        if (txtMaPhieu.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Mã Phiếu");
            return false;
        }
        if (txtNgayNhap.getText().isEmpty()) {
            return false;
        }
        try {
            DateTimeMaster.convertToDate(txtNgayNhap.getText());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Ngày nhập không tồn tại");
            return false;
        }
        if (txtSoLo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Số Lô");
            return false;
        }
        if (txtNgaySanXuat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Ngày Sản Xuất");
            return false;
        }
        if (txtHanSuDung.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Hạn Sử Dụng");
            return false;
        }
        Date d1, d2;
        int tm = 0;
        try {
            tm = 0;
            d1 = DateTimeMaster.convertToDate(txtNgaySanXuat.getText());
            tm = 1;
            d2 = DateTimeMaster.convertToDate(txtHanSuDung.getText());
            if (d1.compareTo(d2) >= 0) {
                JOptionPane.showMessageDialog(this, "Ngày sản xuất >= Hạn sử dụng");
                return false;
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, (tm == 0 ? "Ngày sản xuất" : "Hạn sử dụng") + " không tồn tại");
            return false;
        }
        if (txtSoLuong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập Số Lượng");
            return false;
        }
        if (Integer.parseInt(txtGiaNhap.getText()) > Integer.parseInt(txtGiaBan.getText())) {
            JOptionPane.showMessageDialog(this, "Giá nhập > Giá bán");
            return false;
        }
        try {
            if (Integer.parseInt(txtSoLuong.getText()) < 0) {
                throw new Exception();
            }
            if (Integer.parseInt(txtGiaNhap.getText()) < 0) {
                throw new Exception();
            }
            if (Integer.parseInt(txtGiaBan.getText()) < 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Số lượng, giá nhập, giá bán phải là số >= 0");
            return false;
        }
        return true;
    }

    private void taoMaPhieuMoi() throws Exception {
        try {
            txtMaPhieu.setText("PN" + GlobalData.getNhapHangSetting().getPhieuNhapNewID());
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

        jPanel21 = new javax.swing.JPanel();
        splitPane = new javax.swing.JSplitPane();
        panelChonDuocPham = new ui.PanelChonDuocPham();
        jPanel2 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtMaPhieu = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        txtNgayNhap = new javax.swing.JTextField();
        butNgay = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtSoLo = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtDonViTinh = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txtNgaySanXuat = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        cbbNgay1 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        cbbThang1 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        cbbNam1 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        txtHanSuDung = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        cbbNgay2 = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        cbbThang2 = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        cbbNam2 = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        lbStatus = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        butPhieuMoi = new javax.swing.JButton();
        butMaPhieuMoi = new javax.swing.JButton();
        butNhapHang = new javax.swing.JButton();
        butSuaPhieuNhap = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Nhập Hàng"));
        setLayout(new java.awt.BorderLayout());

        jPanel21.setPreferredSize(new java.awt.Dimension(50, 10));
        add(jPanel21, java.awt.BorderLayout.WEST);

        splitPane.setDividerLocation(500);
        splitPane.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                splitPanePropertyChange(evt);
            }
        });
        splitPane.setLeftComponent(panelChonDuocPham);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin nhập hàng"));
        jPanel2.setLayout(new java.awt.GridLayout(10, 0));

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel9.setText(" Mã phiếu*");
        jLabel9.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel10.add(jLabel9, java.awt.BorderLayout.WEST);

        txtMaPhieu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtMaPhieu.setEnabled(false);
        jPanel10.add(txtMaPhieu, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel10);

        jPanel11.setLayout(new java.awt.BorderLayout());

        txtNgayNhap.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel11.add(txtNgayNhap, java.awt.BorderLayout.CENTER);

        butNgay.setText(" Ngày*");
        butNgay.setFocusable(false);
        butNgay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        butNgay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        butNgay.setMargin(new java.awt.Insets(2, 2, 2, 2));
        butNgay.setPreferredSize(new java.awt.Dimension(70, 20));
        butNgay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        butNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butNgayActionPerformed(evt);
            }
        });
        jPanel11.add(butNgay, java.awt.BorderLayout.LINE_START);

        jPanel2.add(jPanel11);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel3.setText(" Số lô*");
        jLabel3.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel6.add(jLabel3, java.awt.BorderLayout.WEST);

        txtSoLo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSoLo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSoLoFocusGained(evt);
            }
        });
        jPanel6.add(txtSoLo, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel6);

        jPanel20.setLayout(new java.awt.GridLayout(1, 0));

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

        jPanel20.add(jPanel5);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel7.setText(" Đơn vị tính*");
        jLabel7.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel7.add(jLabel7, java.awt.BorderLayout.WEST);

        txtDonViTinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtDonViTinh.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDonViTinh.setEnabled(false);
        jPanel7.add(txtDonViTinh, java.awt.BorderLayout.CENTER);

        jPanel20.add(jPanel7);

        jPanel2.add(jPanel20);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel2.setText(" Ngày SX*");
        jLabel2.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel1.add(jLabel2, java.awt.BorderLayout.WEST);

        jPanel13.setLayout(new java.awt.BorderLayout());

        txtNgaySanXuat.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtNgaySanXuat.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtNgaySanXuat.setEnabled(false);
        jPanel13.add(txtNgaySanXuat, java.awt.BorderLayout.CENTER);

        jPanel16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 5));

        cbbNgay1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cbbNgay1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNgay1ActionPerformed(evt);
            }
        });
        jPanel16.add(cbbNgay1);

        jLabel10.setText("/");
        jPanel16.add(jLabel10);

        cbbThang1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbbThang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThang1ActionPerformed(evt);
            }
        });
        jPanel16.add(cbbThang1);

        jLabel12.setText("/");
        jPanel16.add(jLabel12);

        cbbNam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013" }));
        cbbNam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNam1ActionPerformed(evt);
            }
        });
        jPanel16.add(cbbNam1);

        jLabel14.setText(" ");
        jPanel16.add(jLabel14);

        jPanel13.add(jPanel16, java.awt.BorderLayout.WEST);

        jPanel1.add(jPanel13, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel1);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel4.setText(" Hạn SD*");
        jLabel4.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel3.add(jLabel4, java.awt.BorderLayout.WEST);

        jPanel14.setLayout(new java.awt.BorderLayout());

        txtHanSuDung.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtHanSuDung.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtHanSuDung.setEnabled(false);
        jPanel14.add(txtHanSuDung, java.awt.BorderLayout.CENTER);

        jPanel17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 5));

        cbbNgay2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cbbNgay2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNgay2ActionPerformed(evt);
            }
        });
        jPanel17.add(cbbNgay2);

        jLabel15.setText("/");
        jPanel17.add(jLabel15);

        cbbThang2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cbbThang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbThang2ActionPerformed(evt);
            }
        });
        jPanel17.add(cbbThang2);

        jLabel16.setText("/");
        jPanel17.add(jLabel16);

        cbbNam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2013" }));
        cbbNam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNam2ActionPerformed(evt);
            }
        });
        jPanel17.add(cbbNam2);

        jLabel17.setText(" ");
        jPanel17.add(jLabel17);

        jPanel14.add(jPanel17, java.awt.BorderLayout.WEST);

        jPanel3.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3);

        jPanel18.setLayout(new java.awt.BorderLayout());

        jLabel13.setText(" Giá nhập*");
        jLabel13.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel18.add(jLabel13, java.awt.BorderLayout.WEST);

        txtGiaNhap.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtGiaNhap.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGiaNhapFocusGained(evt);
            }
        });
        jPanel18.add(txtGiaNhap, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel18);

        jPanel19.setLayout(new java.awt.BorderLayout());

        jLabel18.setText(" Giá bán*");
        jLabel18.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel19.add(jLabel18, java.awt.BorderLayout.WEST);

        txtGiaBan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtGiaBan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGiaBanFocusGained(evt);
            }
        });
        jPanel19.add(txtGiaBan, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel19);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel5.setText(" Ghi chú");
        jLabel5.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel4.add(jLabel5, java.awt.BorderLayout.WEST);

        txtGhiChu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtGhiChu.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGhiChuFocusGained(evt);
            }
        });
        jPanel4.add(txtGhiChu, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel4);

        jPanel9.setLayout(new java.awt.BorderLayout());

        lbStatus.setForeground(new java.awt.Color(0, 0, 254));
        lbStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel9.add(lbStatus, java.awt.BorderLayout.PAGE_START);

        butPhieuMoi.setText("Phiếu Mới");
        butPhieuMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butPhieuMoiActionPerformed(evt);
            }
        });
        jPanel12.add(butPhieuMoi);

        butMaPhieuMoi.setText("Mã Phiếu Mới");
        butMaPhieuMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butMaPhieuMoiActionPerformed(evt);
            }
        });
        jPanel12.add(butMaPhieuMoi);

        butNhapHang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        butNhapHang.setText("Nhập Hàng");
        butNhapHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                butNhapHangMouseExited(evt);
            }
        });
        butNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butNhapHangActionPerformed(evt);
            }
        });
        jPanel12.add(butNhapHang);

        butSuaPhieuNhap.setText("Sửa Phiếu Nhập");
        butSuaPhieuNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                butSuaPhieuNhapMouseExited(evt);
            }
        });
        butSuaPhieuNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSuaPhieuNhapActionPerformed(evt);
            }
        });
        jPanel12.add(butSuaPhieuNhap);
        jPanel12.add(jPanel15);

        jPanel9.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel9);

        splitPane.setRightComponent(jPanel2);

        add(splitPane, java.awt.BorderLayout.CENTER);

        jPanel22.setPreferredSize(new java.awt.Dimension(50, 10));
        add(jPanel22, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void butNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butNhapHangActionPerformed
        try {
            if (!checkFieldsOK()) {
                return;
            }
            DuocPhamUnit unit2 = panelChonDuocPham.getDataUnit();
            //update DuocPhamUnit so luong
            unit2.setSoLuongHienCo(unit2.getSoLuongHienCo() + Integer.parseInt(txtSoLuong.getText()));
            unit2.setGiaNhap(Integer.parseInt(txtGiaNhap.getText()));
            unit2.setGiaBan(Integer.parseInt(txtGiaBan.getText()));
            unit2.setNewAdded(false);
            //write Nhap Info
            unit = new NhapHangUnit(txtMaPhieu.getText(), txtNgayNhap.getText(), unit2, txtSoLo.getText(), txtDonViTinh.getText(), txtNgaySanXuat.getText(), txtHanSuDung.getText(), Integer.parseInt(txtSoLuong.getText()), Integer.parseInt(txtGiaNhap.getText()), Integer.parseInt(txtGiaBan.getText()), txtGhiChu.getText());
            int iDPID = GlobalData.getNhapHangSetting().getPhieuNhapNewID();
            unit.setMaPhieu("PN" + iDPID);
            while(NhapHangDataManager.checkIfExist(unit)){
                iDPID++;
                unit.setMaPhieu("PN" + iDPID);
            }
            NhapHangDataManager.addNhapHangUnit(unit);
            DuocPhamDataManager.saveDuocPhamData();
            //update settings
            GlobalData.getNhapHangSetting().setPhieuNhapLastID(iDPID);
            NhapHangDataManager.saveNhapHangSetting();
            lbStatus.setText("Đã nhập");
            butPhieuMoi.doClick();
            //update table
            PanelLichSuNhapHang.it.loadTableData("*");
            PanelDanhSachHangLuuTru.it.loadTableData("*");
        } catch (ExistException ex) {
            JOptionPane.showMessageDialog(this, "Phiếu nhập có mã " + txtMaPhieu.getText() + " đã tồn tại\nNhấn nút Mã phiếu mới để đổi mã phiếu nhập mới");
        } catch (NoUnitIsSetException ex) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dược phẩm\nXin vui lòng chọn ở mục bên trái");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi chưa xác định:\n" + ex.getMessage());
        }
    }//GEN-LAST:event_butNhapHangActionPerformed

    private void butNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butNgayActionPerformed
        txtNgayNhap.setText(DateTimeMaster.getNgayThangHienTai());
    }//GEN-LAST:event_butNgayActionPerformed

    private void txtSoLuongFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoLuongFocusGained
        try {
            txtDonViTinh.setText(panelChonDuocPham.getDataUnit().getDonViTinh() + "");
            txtGiaNhap.setText(panelChonDuocPham.getDataUnit().getGiaNhap() + "");
            txtGiaBan.setText(panelChonDuocPham.getDataUnit().getGiaBan() + "");
        } catch (NoUnitIsSetException ex) {
            panelChonDuocPham.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dược phẩm\nXin vui lòng chọn ở mục bên trái");
        }
    }//GEN-LAST:event_txtSoLuongFocusGained

    private void txtSoLoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSoLoFocusGained
        try {
            txtDonViTinh.setText(panelChonDuocPham.getDataUnit().getDonViTinh() + "");
            txtGiaNhap.setText(panelChonDuocPham.getDataUnit().getGiaNhap() + "");
            txtGiaBan.setText(panelChonDuocPham.getDataUnit().getGiaBan() + "");
        } catch (NoUnitIsSetException ex) {
            panelChonDuocPham.requestFocus();
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dược phẩm\nXin vui lòng chọn ở mục bên trái");
        }
    }//GEN-LAST:event_txtSoLoFocusGained

    private void butSuaPhieuNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSuaPhieuNhapActionPerformed
        try {
            if (!checkFieldsOK()) {
                return;
            }
            if (txtSoLuong.getToolTipText() == null || txtSoLuong.getToolTipText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn phiếu nhập ở bảng bên trên");
                return;
            }
            DuocPhamUnit unit2 = panelChonDuocPham.getDataUnit();
            //update DuocPhamUnit so luong
            unit2.setSoLuongHienCo(unit2.getSoLuongHienCo() + (Integer.parseInt(txtSoLuong.getText()) - Integer.parseInt(txtSoLuong.getToolTipText())));
            unit2.setGiaNhap(Integer.parseInt(txtGiaNhap.getText()));
            unit2.setGiaBan(Integer.parseInt(txtGiaBan.getText()));
            txtSoLuong.setToolTipText(txtSoLuong.getText());
            //write Nhap Info
            unit.setMaPhieu(txtMaPhieu.getText());
            unit.setNgayNhap(txtNgayNhap.getText());
            unit.setThuocInfoUnit(unit2);
            unit.setSoLo(txtSoLo.getText());
            unit.setDonViTinh(txtDonViTinh.getText());
            unit.setNgaySanXuat(txtNgaySanXuat.getText());
            unit.setHanSuDung(txtHanSuDung.getText());
            unit.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
            unit.setGiaNhap(Integer.parseInt(txtGiaNhap.getText()));
            unit.setGiaBan(Integer.parseInt(txtGiaBan.getText()));
            unit.setGhiChu(txtGhiChu.getText());
            NhapHangDataManager.replaceNhapUnit(unit, unit);
            DuocPhamDataManager.saveDuocPhamData();
            lbStatus.setText("Đã sửa");
            //update table
            PanelLichSuNhapHang.it.loadTableData("*");
            PanelDanhSachHangLuuTru.it.loadTableData("*");
        } catch (NotExistException ex) {
            JOptionPane.showMessageDialog(this, "Phiếu nhập " + txtMaPhieu.getText() + " không tồn tại");
        } catch (NoUnitIsSetException ex) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn dược phẩm\nXin vui lòng chọn ở mục bên trái");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi chưa xác định:\n" + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }//GEN-LAST:event_butSuaPhieuNhapActionPerformed

    private void butNhapHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butNhapHangMouseExited
        lbStatus.setText("");
    }//GEN-LAST:event_butNhapHangMouseExited

    private void butSuaPhieuNhapMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butSuaPhieuNhapMouseExited
        lbStatus.setText("");
    }//GEN-LAST:event_butSuaPhieuNhapMouseExited

    private void cbbNgay1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNgay1ActionPerformed
        txtNgaySanXuat.setText(cbbNgay1.getSelectedItem() + "/" + cbbThang1.getSelectedItem() + "/" + cbbNam1.getSelectedItem());
    }//GEN-LAST:event_cbbNgay1ActionPerformed

    private void cbbThang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThang1ActionPerformed
        txtNgaySanXuat.setText(cbbNgay1.getSelectedItem() + "/" + cbbThang1.getSelectedItem() + "/" + cbbNam1.getSelectedItem());
    }//GEN-LAST:event_cbbThang1ActionPerformed

    private void cbbNam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNam1ActionPerformed
        txtNgaySanXuat.setText(cbbNgay1.getSelectedItem() + "/" + cbbThang1.getSelectedItem() + "/" + cbbNam1.getSelectedItem());
    }//GEN-LAST:event_cbbNam1ActionPerformed

    private void cbbNgay2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNgay2ActionPerformed
        txtHanSuDung.setText(cbbNgay2.getSelectedItem() + "/" + cbbThang2.getSelectedItem() + "/" + cbbNam2.getSelectedItem());
    }//GEN-LAST:event_cbbNgay2ActionPerformed

    private void cbbThang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbThang2ActionPerformed
        txtHanSuDung.setText(cbbNgay2.getSelectedItem() + "/" + cbbThang2.getSelectedItem() + "/" + cbbNam2.getSelectedItem());
    }//GEN-LAST:event_cbbThang2ActionPerformed

    private void cbbNam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNam2ActionPerformed
        txtHanSuDung.setText(cbbNgay2.getSelectedItem() + "/" + cbbThang2.getSelectedItem() + "/" + cbbNam2.getSelectedItem());
    }//GEN-LAST:event_cbbNam2ActionPerformed

    private void txtGiaNhapFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGiaNhapFocusGained
        txtGiaNhap.selectAll();
    }//GEN-LAST:event_txtGiaNhapFocusGained

    private void txtGiaBanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGiaBanFocusGained
        txtGiaBan.selectAll();
    }//GEN-LAST:event_txtGiaBanFocusGained

    private void txtGhiChuFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGhiChuFocusGained
        txtGhiChu.selectAll();
    }//GEN-LAST:event_txtGhiChuFocusGained

    private void butPhieuMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butPhieuMoiActionPerformed
        try {
            taoMaPhieuMoi();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gặp lỗi khi tự sinh mã số phiếu");
            return;
        }
        txtSoLo.setText("");
        txtDonViTinh.setText("");
        txtNgaySanXuat.setText("");
        txtHanSuDung.setText("");
        txtSoLuong.setText("");
        txtSoLuong.setToolTipText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");
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
            //ApplicationSettingManager.setPanelNhapHangDividerLocation(splitPane.getDividerLocation());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());
        }
    }//GEN-LAST:event_splitPanePropertyChange

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butMaPhieuMoi;
    private javax.swing.JButton butNgay;
    private javax.swing.JButton butNhapHang;
    private javax.swing.JButton butPhieuMoi;
    private javax.swing.JButton butSuaPhieuNhap;
    private javax.swing.JComboBox cbbNam1;
    private javax.swing.JComboBox cbbNam2;
    private javax.swing.JComboBox cbbNgay1;
    private javax.swing.JComboBox cbbNgay2;
    private javax.swing.JComboBox cbbThang1;
    private javax.swing.JComboBox cbbThang2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbStatus;
    private ui.PanelChonDuocPham panelChonDuocPham;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JTextField txtDonViTinh;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtHanSuDung;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtNgayNhap;
    private javax.swing.JTextField txtNgaySanXuat;
    private javax.swing.JTextField txtSoLo;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
