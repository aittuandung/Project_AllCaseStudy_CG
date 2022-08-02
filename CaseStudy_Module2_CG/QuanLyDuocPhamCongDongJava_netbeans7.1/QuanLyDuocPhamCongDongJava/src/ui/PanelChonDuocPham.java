package ui;

import exception.NoUnitIsSetException;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import unit.DuocPhamUnit;
import unit.GlobalData;
import unit.manager.DuocPhamDataManager;
import worker.ErrorLoger;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class PanelChonDuocPham extends javax.swing.JPanel {

    /**
     * Creates new form PanelChonDuocPham
     */
    public PanelChonDuocPham() {
        initComponents();
        scrollChuY.setPreferredSize(new java.awt.Dimension(180, 0));
    }
    ActionListener butSoLuongAL = new java.awt.event.ActionListener() {

        public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtSoLuong.setText(txtSoLuong.getText() + evt.getActionCommand());
            txtSoLuong.requestFocus();
        }
    };
    boolean bToaThuocMod = false;

    public PanelChonDuocPham(ActionListener al) {
        initComponents();
        scrollChuY.setPreferredSize(new java.awt.Dimension(180, 0));
        butThemVaoToa.addActionListener(al);
        bToaThuocMod = true;
        but0.addActionListener(butSoLuongAL);
        but1.addActionListener(butSoLuongAL);
        but2.addActionListener(butSoLuongAL);
        but3.addActionListener(butSoLuongAL);
        but4.addActionListener(butSoLuongAL);
        but5.addActionListener(butSoLuongAL);
        but6.addActionListener(butSoLuongAL);
        but7.addActionListener(butSoLuongAL);
        but8.addActionListener(butSoLuongAL);
        but9.addActionListener(butSoLuongAL);
    }
    DuocPhamUnit unitSelected;

    public DuocPhamUnit getDataUnit() throws NoUnitIsSetException {
        if (unitSelected == null && listData.getSelectedIndex() != -1) {
            unitSelected = (DuocPhamUnit) listData.getSelectedValue();
        }
        if (unitSelected == null) {
            throw new NoUnitIsSetException();
        }
        DuocPhamUnit r = unitSelected;
        unitSelected = null;
        return r;
    }

    public void setDataUnit(DuocPhamUnit unit) {
        try {
            if (listData.getSelectedIndex() != -1) {
                unitSelected = unit;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi chưa xác định:\n" + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());

        }
    }

    public void setDataUnitFromNhapHangData(DuocPhamUnit unit) {
        try {
            unitSelected = unit;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi chưa xác định:\n" + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());

        }
    }
    boolean bViewChuY = true;

    public void setShowChuYOnDataSet(boolean b) {
        bViewChuY = b;
    }

    public String getSoLuong() {
        txtSoLuong.setText(txtSoLuong.getText().trim());
        String s = txtSoLuong.getText();
        txtSoLuong.setText("");
        return s;
    }

    public void setSelectingView() {
        panelCenter.removeAll();
        panelCenter.add(panelChonDP);
        panelCenter.updateUI();
        txtSoLuong.setText("");
    }
    
    public void setFocusOnTextFieldSoLuong() {
        txtSoLuong.requestFocus();
        txtSoLuong.selectAll();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSlect = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lbDuocPham = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        but1 = new javax.swing.JButton();
        but2 = new javax.swing.JButton();
        but3 = new javax.swing.JButton();
        but4 = new javax.swing.JButton();
        but5 = new javax.swing.JButton();
        but6 = new javax.swing.JButton();
        but7 = new javax.swing.JButton();
        but8 = new javax.swing.JButton();
        but9 = new javax.swing.JButton();
        but0 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        butThemVaoToa = new javax.swing.JButton();
        butBoQua = new javax.swing.JButton();
        panelCenter = new javax.swing.JPanel();
        panelChonDP = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listData = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtKeyword = new javax.swing.JTextField();
        scrollChuY = new javax.swing.JScrollPane();
        txtChuY = new javax.swing.JTextArea();

        panelSlect.setBorder(javax.swing.BorderFactory.createTitledBorder("Thêm vào toa"));
        panelSlect.setLayout(new java.awt.BorderLayout());

        jPanel8.setLayout(new java.awt.BorderLayout());

        lbDuocPham.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel8.add(lbDuocPham, java.awt.BorderLayout.CENTER);

        jLabel5.setText(" DP: ");
        jPanel8.add(jLabel5, java.awt.BorderLayout.WEST);

        panelSlect.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel4.setLayout(new java.awt.GridLayout(2, 0));

        but1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        but1.setText("1");
        jPanel4.add(but1);

        but2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        but2.setText("2");
        jPanel4.add(but2);

        but3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        but3.setText("3");
        jPanel4.add(but3);

        but4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        but4.setText("4");
        jPanel4.add(but4);

        but5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        but5.setText("5");
        jPanel4.add(but5);

        but6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        but6.setText("6");
        jPanel4.add(but6);

        but7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        but7.setText("7");
        jPanel4.add(but7);

        but8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        but8.setText("8");
        jPanel4.add(but8);

        but9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        but9.setText("9");
        jPanel4.add(but9);

        but0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        but0.setText("0");
        jPanel4.add(but0);

        jPanel7.add(jPanel4, java.awt.BorderLayout.CENTER);

        jLabel2.setText(" Click nhập số lượng");
        jPanel7.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel7, java.awt.BorderLayout.CENTER);

        panelSlect.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.GridLayout(2, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Số lượng");
        jPanel5.add(jLabel3);

        txtSoLuong.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSoLuong.setPreferredSize(new java.awt.Dimension(60, 25));
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });
        jPanel5.add(txtSoLuong);

        jPanel3.add(jPanel5);

        butThemVaoToa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        butThemVaoToa.setText("Thêm Vào Toa");
        jPanel6.add(butThemVaoToa);

        butBoQua.setText("Bỏ Qua");
        butBoQua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butBoQuaActionPerformed(evt);
            }
        });
        jPanel6.add(butBoQua);

        jPanel3.add(jPanel6);

        panelSlect.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        setBorder(javax.swing.BorderFactory.createTitledBorder("Chọn dược phẩm"));
        setLayout(new java.awt.BorderLayout());

        panelCenter.setLayout(new java.awt.GridLayout(1, 0));

        panelChonDP.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(258, 100));

        listData.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        listData.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listData);

        panelChonDP.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setText(" Tên dược phẩm cần tìm ");
        jPanel1.add(jLabel1, java.awt.BorderLayout.WEST);

        txtKeyword.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtKeyword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKeywordKeyReleased(evt);
            }
        });
        jPanel1.add(txtKeyword, java.awt.BorderLayout.CENTER);

        panelChonDP.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        panelCenter.add(panelChonDP);

        add(panelCenter, java.awt.BorderLayout.CENTER);

        scrollChuY.setBorder(javax.swing.BorderFactory.createTitledBorder("Chú ý"));
        scrollChuY.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollChuY.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollChuY.setPreferredSize(new java.awt.Dimension(180, 150));

        txtChuY.setColumns(20);
        txtChuY.setLineWrap(true);
        txtChuY.setRows(5);
        txtChuY.setWrapStyleWord(true);
        txtChuY.setPreferredSize(new java.awt.Dimension(164, 50));
        txtChuY.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtChuYMouseClicked(evt);
            }
        });
        scrollChuY.setViewportView(txtChuY);

        add(scrollChuY, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents

    private void txtKeywordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeywordKeyReleased
        if (txtKeyword.getText().trim().isEmpty()) {
            listData.setListData(new Vector());
        } else {
            try {
                Vector<DuocPhamUnit> v = DuocPhamDataManager.getDuocPhamUnitWithNameContains(txtKeyword.getText().trim());
                listData.setListData(v);
            } catch (Exception ex) {
                ex.printStackTrace();
                ex.printStackTrace(ErrorLoger.getLogStream());
                JOptionPane.showMessageDialog(this, "Lỗi chưa xác định:\n" + ex.getMessage());

            }
        }
        scrollChuY.setPreferredSize(new java.awt.Dimension(180, 0));
        updateUI();
    }//GEN-LAST:event_txtKeywordKeyReleased

    private void listDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listDataMouseClicked
        try {
            if (listData.getSelectedIndex() != -1) {
                unitSelected = (DuocPhamUnit) listData.getSelectedValue();
                if (bViewChuY) {
                    txtChuY.setText(unitSelected.getGhiChu());
                    txtChuY.select(0, 0);
                    scrollChuY.setPreferredSize(new java.awt.Dimension(180, unitSelected.getGhiChu().isEmpty() ? 0 : 70));
                }
                if (bToaThuocMod) {
                    panelCenter.removeAll();
                    panelCenter.add(panelSlect);
                    lbDuocPham.setText(unitSelected.toString());
                    txtSoLuong.requestFocus();
                }
                panelCenter.updateUI();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Lỗi chưa xác định:\n" + ex.getMessage());
            ex.printStackTrace();
            ex.printStackTrace(ErrorLoger.getLogStream());

        }
    }//GEN-LAST:event_listDataMouseClicked

    private void txtChuYMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtChuYMouseClicked
        DialogDuocPhamGhiChu d = new DialogDuocPhamGhiChu(MainFrame.it, true, txtChuY.getText());
        Dimension dim = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize();
        d.setSize((int) dim.getWidth() / 2, (int) dim.getHeight() / 2);
        d.setLocationRelativeTo(MainFrame.it);
        d.setVisible(true);
    }//GEN-LAST:event_txtChuYMouseClicked

    private void butBoQuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butBoQuaActionPerformed
        setSelectingView();
    }//GEN-LAST:event_butBoQuaActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        butThemVaoToa.doClick();
    }//GEN-LAST:event_txtSoLuongActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton but0;
    private javax.swing.JButton but1;
    private javax.swing.JButton but2;
    private javax.swing.JButton but3;
    private javax.swing.JButton but4;
    private javax.swing.JButton but5;
    private javax.swing.JButton but6;
    private javax.swing.JButton but7;
    private javax.swing.JButton but8;
    private javax.swing.JButton but9;
    private javax.swing.JButton butBoQua;
    private javax.swing.JButton butThemVaoToa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbDuocPham;
    private javax.swing.JList listData;
    private javax.swing.JPanel panelCenter;
    private javax.swing.JPanel panelChonDP;
    private javax.swing.JPanel panelSlect;
    private javax.swing.JScrollPane scrollChuY;
    private javax.swing.JTextArea txtChuY;
    private javax.swing.JTextField txtKeyword;
    private javax.swing.JTextField txtSoLuong;
    // End of variables declaration//GEN-END:variables
}
