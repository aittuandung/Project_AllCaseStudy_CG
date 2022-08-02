package gui;

import model.IncomeProtection;
import model.Insured;
import model.InsuredCar;
import model.InsuredHouse;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;
import java.util.Objects;

public class QuanLyBaoHiemGUI extends JFrame {
    private JRadioButton radioBtnBaoHiemOto, radioBtnBaoHiemNha, radioBtnBaoHiemThuNhap;
    private JButton btnTinhChiPhi, btnTinhGiaTri, btnXuatThongTin;
    private JTextField txtTenKH, txtTuoiKH, txtDiaChiKH, txtThuNhapKH;
    private JTextField txtHangSanXuat, txtMauXe, txtBienSo, txtNamMuaBHOto, txtGiaTriDinhGiaOto, txtNamKtraBHOto;
    private JComboBox<String> comboRateTaiXe;
    private JTextField txtNamMuaBaoHiemNha, txtGiaTriDinhGiaNha, txtGiaTriVatDung, txtNamKtraBaoHiemNha;
    private JTabbedPane tabbedPane;
    private JPanel pnBaoHiemOto, pnBaoHiemNha, pnBaoHiemThuNhap;
    private String tenKH, strTuoiKH, diaChiKH, strThuNhapKH;
    private String hangSanXuat, mauXe, bienSo, strNamMuaBHOto, strGiaTriOto, strNamKtraBHOto, rateTaiXe;
    private String strNamMuaBHNha, strGiaTriNha, strGiaTriVatDung, strNamKtraBHNha;
    private JMenuItem exitMenu, aboutMenu;

    // Triển khai phương thức khởi tạo
    public QuanLyBaoHiemGUI(String title) {
        super(title);
        addControls();
        addEvents();
    }

    // Triển khai phương thức addControls
    private void addControls() {
        // Thêm menubar
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        menuFile.setMnemonic('F');
        exitMenu = new JMenuItem("Exit");
        exitMenu.setAccelerator(KeyStroke.getKeyStroke('Q', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        exitMenu.setIcon(new ImageIcon(Objects.requireNonNull(QuanLyBaoHiemGUI.class.getResource("/images/exit.png"))));
        menuFile.add(exitMenu);
        menuBar.add(menuFile);
        JMenu menuHelp = new JMenu("Help");
        menuHelp.setMnemonic('H');
        aboutMenu = new JMenuItem("About");
        aboutMenu.setAccelerator(KeyStroke.getKeyStroke('I', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        aboutMenu.setIcon(new ImageIcon(Objects.requireNonNull(QuanLyBaoHiemGUI.class.getResource("/images/about.png"))));
        menuHelp.add(aboutMenu);
        menuBar.add(menuHelp);
        this.setJMenuBar(menuBar);

        // Tạo khu vực hiển thị tên công ty
        JPanel pnTenCongTy = new JPanel();
        pnTenCongTy.setLayout(new BorderLayout());
        JLabel lblTenCongTy = new JLabel("Bảo hiểm Thịnh Vượng");
        lblTenCongTy.setIcon(new ImageIcon(Objects.requireNonNull(QuanLyBaoHiemGUI.class.getResource("/images/logoCongTy.png"))));
        lblTenCongTy.setFont(new Font(Font.SERIF, Font.BOLD, 28));
        lblTenCongTy.setHorizontalAlignment(JLabel.CENTER);
        lblTenCongTy.setVerticalAlignment(JLabel.CENTER);
        pnTenCongTy.add(lblTenCongTy, BorderLayout.CENTER);

        // Tạo khu vực lựa chọn loại bảo hiểm
        JPanel pnChonLoaiBaoHiem = new JPanel();
        pnChonLoaiBaoHiem.setLayout(new BoxLayout(pnChonLoaiBaoHiem, BoxLayout.Y_AXIS));
        pnChonLoaiBaoHiem.setPreferredSize(new Dimension(230, 0));
        Border borderChonLoaiBaoHiem = BorderFactory.createLineBorder(Color.BLUE);
        TitledBorder titledBorderChonLoaiBaoHiem = new TitledBorder(borderChonLoaiBaoHiem, "Chọn loại bảo hiểm");
        titledBorderChonLoaiBaoHiem.setTitleJustification(TitledBorder.LEFT);
        titledBorderChonLoaiBaoHiem.setTitleFont(new Font(Font.SERIF, Font.BOLD, 15));
        titledBorderChonLoaiBaoHiem.setTitleColor(Color.RED);
        pnChonLoaiBaoHiem.setBorder(titledBorderChonLoaiBaoHiem);
        radioBtnBaoHiemOto = new JRadioButton("Bảo hiểm ô tô");
        radioBtnBaoHiemOto.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        radioBtnBaoHiemNha = new JRadioButton("Bảo hiểm nhà");
        radioBtnBaoHiemNha.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        radioBtnBaoHiemThuNhap = new JRadioButton("Bảo hiểm thu nhập");
        radioBtnBaoHiemThuNhap.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        radioBtnBaoHiemOto.doClick();
        ButtonGroup radioBtnGroup = new ButtonGroup();
        radioBtnGroup.add(radioBtnBaoHiemOto);
        radioBtnGroup.add(radioBtnBaoHiemNha);
        radioBtnGroup.add(radioBtnBaoHiemThuNhap);
        pnChonLoaiBaoHiem.add(radioBtnBaoHiemOto);
        pnChonLoaiBaoHiem.add(radioBtnBaoHiemNha);
        pnChonLoaiBaoHiem.add(radioBtnBaoHiemThuNhap);

        // Tạo khu vực hiển thị thông tin khách hàng và thông tin bảo hiểm
        JPanel pnThongTin = new JPanel();
        pnThongTin.setLayout(new BorderLayout());

        /*Thông tin khách hàng*/
        JPanel pnThongTinKhachHang = new JPanel();
        pnThongTinKhachHang.setLayout(new BoxLayout(pnThongTinKhachHang, BoxLayout.Y_AXIS));
        Border borderThongTinKH = BorderFactory.createLineBorder(Color.BLUE);
        TitledBorder titledBorderThongTinKH = new TitledBorder(borderThongTinKH, "Thông tin khách hàng");
        titledBorderThongTinKH.setTitleJustification(TitledBorder.LEFT);
        titledBorderThongTinKH.setTitleFont(new Font(Font.SERIF, Font.BOLD, 15));
        titledBorderThongTinKH.setTitleColor(Color.RED);
        pnThongTinKhachHang.setBorder(titledBorderThongTinKH);

        JLabel lblTenKH, lblTuoiKH, lblDiaChiKH, lblThuNhapHienTaiKH;
        lblTenKH = new JLabel("Họ và tên: ");
        lblTuoiKH = new JLabel("Tuổi: ");
        lblDiaChiKH = new JLabel("Địa chỉ: ");
        lblThuNhapHienTaiKH = new JLabel("Thu nhập hiện tại ($):");
        lblTenKH.setPreferredSize(lblThuNhapHienTaiKH.getPreferredSize());
        lblTuoiKH.setPreferredSize(lblThuNhapHienTaiKH.getPreferredSize());
        lblDiaChiKH.setPreferredSize(lblThuNhapHienTaiKH.getPreferredSize());

        JPanel pnTenKH = new JPanel();
        pnTenKH.setLayout(new BorderLayout());
        pnTenKH.setBorder(BorderFactory.createEmptyBorder(10, 30, 5, 50));
        JPanel pnLblTenKH = new JPanel();
        pnLblTenKH.setLayout(new BorderLayout());
        pnLblTenKH.add(lblTenKH, BorderLayout.NORTH);
        JPanel pnTxtTenKH = new JPanel();
        pnTxtTenKH.setLayout(new BorderLayout());
        txtTenKH = new JTextField();
        pnTxtTenKH.add(txtTenKH, BorderLayout.NORTH);
        pnTenKH.add(pnLblTenKH, BorderLayout.WEST);
        pnTenKH.add(pnTxtTenKH, BorderLayout.CENTER);

        JPanel pnTuoiKH = new JPanel();
        pnTuoiKH.setLayout(new BorderLayout());
        pnTuoiKH.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 50));
        JPanel pnLblTuoiKH = new JPanel();
        pnLblTuoiKH.setLayout(new BorderLayout());
        pnLblTuoiKH.add(lblTuoiKH, BorderLayout.NORTH);
        JPanel pnTxtTuoiKH = new JPanel();
        pnTxtTuoiKH.setLayout(new BorderLayout());
        txtTuoiKH = new JTextField();
        pnTxtTuoiKH.add(txtTuoiKH, BorderLayout.NORTH);
        pnTuoiKH.add(pnLblTuoiKH, BorderLayout.WEST);
        pnTuoiKH.add(pnTxtTuoiKH, BorderLayout.CENTER);

        JPanel pnDiaChiKH = new JPanel();
        pnDiaChiKH.setLayout(new BorderLayout());
        pnDiaChiKH.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 50));
        JPanel pnLblDiaChiKH = new JPanel();
        pnLblDiaChiKH.setLayout(new BorderLayout());
        pnLblDiaChiKH.add(lblDiaChiKH, BorderLayout.NORTH);
        JPanel pnTxtDiaChiKH = new JPanel();
        pnTxtDiaChiKH.setLayout(new BorderLayout());
        txtDiaChiKH = new JTextField();
        pnTxtDiaChiKH.add(txtDiaChiKH, BorderLayout.NORTH);
        pnDiaChiKH.add(pnLblDiaChiKH, BorderLayout.WEST);
        pnDiaChiKH.add(pnTxtDiaChiKH, BorderLayout.CENTER);

        JPanel pnThuNhapKH = new JPanel();
        pnThuNhapKH.setLayout(new BorderLayout());
        pnThuNhapKH.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 50));
        JPanel pnLblThuNhapKH = new JPanel();
        pnLblThuNhapKH.setLayout(new BorderLayout());
        pnLblThuNhapKH.add(lblThuNhapHienTaiKH, BorderLayout.NORTH);
        JPanel pnTxtThuNhapKH = new JPanel();
        pnTxtThuNhapKH.setLayout(new BorderLayout());
        txtThuNhapKH = new JTextField();
        pnTxtThuNhapKH.add(txtThuNhapKH, BorderLayout.NORTH);
        pnThuNhapKH.add(pnLblThuNhapKH, BorderLayout.WEST);
        pnThuNhapKH.add(pnTxtThuNhapKH, BorderLayout.CENTER);

        pnThongTinKhachHang.add(pnTenKH);
        pnThongTinKhachHang.add(pnTuoiKH);
        pnThongTinKhachHang.add(pnDiaChiKH);
        pnThongTinKhachHang.add(pnThuNhapKH);


        /*Thông tin bảo hiểm*/
        JPanel pnThongTinBaoHiem = new JPanel();
        pnThongTinBaoHiem.setLayout(new BorderLayout());
        Border borderThongTinBH = BorderFactory.createLineBorder(Color.BLUE);
        TitledBorder titledBorderThongTinBH = new TitledBorder(borderThongTinBH, "Thông tin bảo hiểm");
        titledBorderThongTinBH.setTitleFont(new Font(Font.SERIF, Font.BOLD, 15));
        titledBorderThongTinBH.setTitleColor(Color.RED);
        titledBorderThongTinBH.setTitleJustification(TitledBorder.LEFT);
        pnThongTinBaoHiem.setBorder(titledBorderThongTinBH);
        Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);

        /*Bảo hiển ô tô*/
        pnBaoHiemOto = new JPanel();
        pnBaoHiemOto.setLayout(new BorderLayout());
        JPanel pnThongTinBaoHiemOto = new JPanel();
        pnThongTinBaoHiemOto.setLayout(new BoxLayout(pnThongTinBaoHiemOto, BoxLayout.Y_AXIS));
        JLabel lblHangSanXuat, lblMauXe, lblBienSo, lblNamMuaBaoHiemOto, lblGiaTriDinhGiaOto, lblNamKtraBaoHiemOto, lblDoRate;
        lblHangSanXuat = new JLabel("Hãng sản xuất: ");
        lblHangSanXuat.setPreferredSize(lblThuNhapHienTaiKH.getPreferredSize());
        lblMauXe = new JLabel("Mẫu xe: ");
        lblMauXe.setPreferredSize(lblThuNhapHienTaiKH.getPreferredSize());
        lblBienSo = new JLabel("Biển số đăng ký: ");
        lblBienSo.setPreferredSize(lblThuNhapHienTaiKH.getPreferredSize());
        lblNamMuaBaoHiemOto = new JLabel("Năm mua BH: ");
        lblNamMuaBaoHiemOto.setPreferredSize(lblThuNhapHienTaiKH.getPreferredSize());
        lblGiaTriDinhGiaOto = new JLabel("Giá trị BH ($): ");
        lblGiaTriDinhGiaOto.setPreferredSize(lblThuNhapHienTaiKH.getPreferredSize());
        lblNamKtraBaoHiemOto = new JLabel("Năm kiểm tra: ");
        lblNamKtraBaoHiemOto.setPreferredSize(lblThuNhapHienTaiKH.getPreferredSize());
        lblDoRate = new JLabel("Rate tài xế: ");
        lblDoRate.setPreferredSize(lblThuNhapHienTaiKH.getPreferredSize());

        JPanel pnHangSanXuat = new JPanel();
        pnHangSanXuat.setLayout(new BorderLayout());
        pnHangSanXuat.setBorder(BorderFactory.createEmptyBorder(10, 30, 5, 30));
        JPanel pnLblHangSanXuat = new JPanel();
        pnLblHangSanXuat.setLayout(new BorderLayout());
        pnLblHangSanXuat.add(lblHangSanXuat, BorderLayout.NORTH);
        JPanel pnTxtHangSanXuat = new JPanel();
        pnTxtHangSanXuat.setLayout(new BorderLayout());
        txtHangSanXuat = new JTextField();
        pnTxtHangSanXuat.add(txtHangSanXuat, BorderLayout.NORTH);
        pnHangSanXuat.add(pnLblHangSanXuat, BorderLayout.WEST);
        pnHangSanXuat.add(pnTxtHangSanXuat, BorderLayout.CENTER);

        JPanel pnMauXe = new JPanel();
        pnMauXe.setLayout(new BorderLayout());
        pnMauXe.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
        JPanel pnLblMauXe = new JPanel();
        pnLblMauXe.setLayout(new BorderLayout());
        pnLblMauXe.add(lblMauXe, BorderLayout.NORTH);
        JPanel pnTxtMauXe = new JPanel();
        pnTxtMauXe.setLayout(new BorderLayout());
        txtMauXe = new JTextField();
        pnTxtMauXe.add(txtMauXe, BorderLayout.NORTH);
        pnMauXe.add(pnLblMauXe, BorderLayout.WEST);
        pnMauXe.add(pnTxtMauXe, BorderLayout.CENTER);

        JPanel pnBienSoDangKy = new JPanel();
        pnBienSoDangKy.setLayout(new BorderLayout());
        pnBienSoDangKy.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
        JPanel pnLblBienSoDangKy = new JPanel();
        pnLblBienSoDangKy.setLayout(new BorderLayout());
        pnLblBienSoDangKy.add(lblBienSo, BorderLayout.NORTH);
        JPanel pnTxtBienSoDangKy = new JPanel();
        pnTxtBienSoDangKy.setLayout(new BorderLayout());
        txtBienSo = new JTextField();
        pnTxtBienSoDangKy.add(txtBienSo, BorderLayout.NORTH);
        pnBienSoDangKy.add(pnLblBienSoDangKy, BorderLayout.WEST);
        pnBienSoDangKy.add(pnTxtBienSoDangKy, BorderLayout.CENTER);

        JPanel pnNamMuaBHOto = new JPanel();
        pnNamMuaBHOto.setLayout(new BorderLayout());
        pnNamMuaBHOto.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
        JPanel pnLblNamMuaBHOto = new JPanel();
        pnLblNamMuaBHOto.setLayout(new BorderLayout());
        pnLblNamMuaBHOto.add(lblNamMuaBaoHiemOto, BorderLayout.NORTH);
        JPanel pnTxtNamMuaBHOto = new JPanel();
        pnTxtNamMuaBHOto.setLayout(new BorderLayout());
        txtNamMuaBHOto = new JTextField();
        pnTxtNamMuaBHOto.add(txtNamMuaBHOto, BorderLayout.NORTH);
        pnNamMuaBHOto.add(pnLblNamMuaBHOto, BorderLayout.WEST);
        pnNamMuaBHOto.add(pnTxtNamMuaBHOto, BorderLayout.CENTER);

        JPanel pnGiaTriDinhGiaOto = new JPanel();
        pnGiaTriDinhGiaOto.setLayout(new BorderLayout());
        pnGiaTriDinhGiaOto.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
        JPanel pnLblGiaTriDinhGiaOto = new JPanel();
        pnLblGiaTriDinhGiaOto.setLayout(new BorderLayout());
        pnLblGiaTriDinhGiaOto.add(lblGiaTriDinhGiaOto, BorderLayout.NORTH);
        JPanel pnTxtGiaTriDinhGiaOto = new JPanel();
        pnTxtGiaTriDinhGiaOto.setLayout(new BorderLayout());
        txtGiaTriDinhGiaOto = new JTextField();
        pnTxtGiaTriDinhGiaOto.add(txtGiaTriDinhGiaOto, BorderLayout.NORTH);
        pnGiaTriDinhGiaOto.add(pnLblGiaTriDinhGiaOto, BorderLayout.WEST);
        pnGiaTriDinhGiaOto.add(pnTxtGiaTriDinhGiaOto, BorderLayout.CENTER);

        JPanel pnNamKtraBaoHiemOto = new JPanel();
        pnNamKtraBaoHiemOto.setLayout(new BorderLayout());
        pnNamKtraBaoHiemOto.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
        JPanel pnLblNamKtraBaoHiemOto = new JPanel();
        pnLblNamKtraBaoHiemOto.setLayout(new BorderLayout());
        pnLblNamKtraBaoHiemOto.add(lblNamKtraBaoHiemOto, BorderLayout.NORTH);
        JPanel pnTxtNamKtraBaoHiemOto = new JPanel();
        pnTxtNamKtraBaoHiemOto.setLayout(new BorderLayout());
        txtNamKtraBHOto = new JTextField();
        txtNamKtraBHOto.setText(String.valueOf(yearNow));
        pnTxtNamKtraBaoHiemOto.add(txtNamKtraBHOto, BorderLayout.NORTH);
        pnNamKtraBaoHiemOto.add(pnLblNamKtraBaoHiemOto, BorderLayout.WEST);
        pnNamKtraBaoHiemOto.add(pnTxtNamKtraBaoHiemOto, BorderLayout.CENTER);

        JPanel pnDoRateTaiXe = new JPanel();
        pnDoRateTaiXe.setLayout(new BorderLayout());
        pnDoRateTaiXe.setBorder(BorderFactory.createEmptyBorder(5, 30, 10, 30));
        JPanel pnLblDoRateTaiXe = new JPanel();
        pnLblDoRateTaiXe.setLayout(new BorderLayout());
        pnLblDoRateTaiXe.add(lblDoRate, BorderLayout.NORTH);
        JPanel pnTxtDoRateTaiXe = new JPanel();
        pnTxtDoRateTaiXe.setLayout(new BorderLayout());
        String[] rate = {"1", "2", "3", "4", "5"};
        comboRateTaiXe = new JComboBox<>(rate);
        pnTxtDoRateTaiXe.add(comboRateTaiXe, BorderLayout.NORTH);
        pnDoRateTaiXe.add(pnLblDoRateTaiXe, BorderLayout.WEST);
        pnDoRateTaiXe.add(pnTxtDoRateTaiXe, BorderLayout.CENTER);

        pnThongTinBaoHiemOto.add(pnHangSanXuat);
        pnThongTinBaoHiemOto.add(pnMauXe);
        pnThongTinBaoHiemOto.add(pnBienSoDangKy);
        pnThongTinBaoHiemOto.add(pnNamMuaBHOto);
        pnThongTinBaoHiemOto.add(pnGiaTriDinhGiaOto);
        pnThongTinBaoHiemOto.add(pnNamKtraBaoHiemOto);
        pnThongTinBaoHiemOto.add(pnDoRateTaiXe);
        pnBaoHiemOto.add(pnThongTinBaoHiemOto, BorderLayout.NORTH);

        /*Bảo hiểm nhà*/
        pnBaoHiemNha = new JPanel();
        pnBaoHiemNha.setLayout(new BorderLayout());
        JPanel pnThongTinBaoHiemNha = new JPanel();
        pnThongTinBaoHiemNha.setLayout(new BoxLayout(pnThongTinBaoHiemNha, BoxLayout.Y_AXIS));
        JLabel lblNamMuaBaoHiemNha, lblGiaTriDinhGiaNha, lblNamKtraBaoHiemNha, lblGiaTriVatDung;
        lblNamKtraBaoHiemNha = new JLabel("Năm kiểm tra BH: ");
        lblNamKtraBaoHiemNha.setPreferredSize(lblThuNhapHienTaiKH.getPreferredSize());
        lblNamMuaBaoHiemNha = new JLabel("Năm mua BH: ");
        lblNamMuaBaoHiemNha.setPreferredSize(lblThuNhapHienTaiKH.getPreferredSize());
        lblGiaTriDinhGiaNha = new JLabel("Giá trị nhà ($): ");
        lblGiaTriDinhGiaNha.setPreferredSize(lblThuNhapHienTaiKH.getPreferredSize());
        lblGiaTriVatDung = new JLabel("Giá trị vật dụng ($): ");
        lblGiaTriVatDung.setPreferredSize(lblThuNhapHienTaiKH.getPreferredSize());

        JPanel pnNamMuaBaoHiemNha = new JPanel();
        pnNamMuaBaoHiemNha.setLayout(new BorderLayout());
        pnNamMuaBaoHiemNha.setBorder(BorderFactory.createEmptyBorder(10, 30, 5, 30));
        JPanel pnLblNamMuaBaoHiemNha = new JPanel();
        pnLblNamMuaBaoHiemNha.setLayout(new BorderLayout());
        pnLblNamMuaBaoHiemNha.add(lblNamMuaBaoHiemNha, BorderLayout.NORTH);
        JPanel pnTxtNamMuaBaoHiemNha = new JPanel();
        pnTxtNamMuaBaoHiemNha.setLayout(new BorderLayout());
        txtNamMuaBaoHiemNha = new JTextField();
        pnTxtNamMuaBaoHiemNha.add(txtNamMuaBaoHiemNha, BorderLayout.NORTH);
        pnNamMuaBaoHiemNha.add(pnLblNamMuaBaoHiemNha, BorderLayout.WEST);
        pnNamMuaBaoHiemNha.add(pnTxtNamMuaBaoHiemNha, BorderLayout.CENTER);

        JPanel pnGiaTriDinhGiaNha = new JPanel();
        pnGiaTriDinhGiaNha.setLayout(new BorderLayout());
        pnGiaTriDinhGiaNha.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
        JPanel pnLblGiaTriDinhGiaNha = new JPanel();
        pnLblGiaTriDinhGiaNha.setLayout(new BorderLayout());
        pnLblGiaTriDinhGiaNha.add(lblGiaTriDinhGiaNha, BorderLayout.NORTH);
        JPanel pnTxtGiaTriDinhGiaNha = new JPanel();
        pnTxtGiaTriDinhGiaNha.setLayout(new BorderLayout());
        txtGiaTriDinhGiaNha = new JTextField();
        pnTxtGiaTriDinhGiaNha.add(txtGiaTriDinhGiaNha, BorderLayout.NORTH);
        pnGiaTriDinhGiaNha.add(pnLblGiaTriDinhGiaNha, BorderLayout.WEST);
        pnGiaTriDinhGiaNha.add(pnTxtGiaTriDinhGiaNha, BorderLayout.CENTER);

        JPanel pnGiaTriVatDung = new JPanel();
        pnGiaTriVatDung.setLayout(new BorderLayout());
        pnGiaTriVatDung.setBorder(BorderFactory.createEmptyBorder(5, 30, 5, 30));
        JPanel pnLblGiaTriVatDung = new JPanel();
        pnLblGiaTriVatDung.setLayout(new BorderLayout());
        pnLblGiaTriVatDung.add(lblGiaTriVatDung, BorderLayout.NORTH);
        JPanel pnTxtGiaTriVatDung = new JPanel();
        pnTxtGiaTriVatDung.setLayout(new BorderLayout());
        txtGiaTriVatDung = new JTextField();
        pnTxtGiaTriVatDung.add(txtGiaTriVatDung, BorderLayout.NORTH);
        pnGiaTriVatDung.add(pnLblGiaTriVatDung, BorderLayout.WEST);
        pnGiaTriVatDung.add(pnTxtGiaTriVatDung, BorderLayout.CENTER);

        JPanel pnNamKtraBaoHiemNha = new JPanel();
        pnNamKtraBaoHiemNha.setLayout(new BorderLayout());
        pnNamKtraBaoHiemNha.setBorder(BorderFactory.createEmptyBorder(5, 30, 10, 30));
        JPanel pnLblNamKtraBaoHiemNha = new JPanel();
        pnLblNamKtraBaoHiemNha.setLayout(new BorderLayout());
        pnLblNamKtraBaoHiemNha.add(lblNamKtraBaoHiemNha, BorderLayout.NORTH);
        JPanel pnTxtNamKtraBaoHiemNha = new JPanel();
        pnTxtNamKtraBaoHiemNha.setLayout(new BorderLayout());
        txtNamKtraBaoHiemNha = new JTextField();
        txtNamKtraBaoHiemNha.setText(String.valueOf(yearNow));
        pnTxtNamKtraBaoHiemNha.add(txtNamKtraBaoHiemNha, BorderLayout.NORTH);
        pnNamKtraBaoHiemNha.add(pnLblNamKtraBaoHiemNha, BorderLayout.WEST);
        pnNamKtraBaoHiemNha.add(pnTxtNamKtraBaoHiemNha, BorderLayout.CENTER);

        pnThongTinBaoHiemNha.add(pnNamMuaBaoHiemNha);
        pnThongTinBaoHiemNha.add(pnGiaTriDinhGiaNha);
        pnThongTinBaoHiemNha.add(pnGiaTriVatDung);
        pnThongTinBaoHiemNha.add(pnNamKtraBaoHiemNha);
        pnBaoHiemNha.add(pnThongTinBaoHiemNha, BorderLayout.NORTH);

        /*Bảo hiểm thu nhập*/
        pnBaoHiemThuNhap = new JPanel();
        pnBaoHiemThuNhap.setLayout(new BorderLayout());

        //
        tabbedPane = new JTabbedPane();
        JScrollPane scrollPane = new JScrollPane(pnBaoHiemOto, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tabbedPane.addTab("Bảo hiểm Ôtô", scrollPane);
        pnThongTinBaoHiem.add(tabbedPane, BorderLayout.CENTER);


        //
        pnThongTin.add(pnThongTinKhachHang, BorderLayout.NORTH);
        pnThongTin.add(pnThongTinBaoHiem, BorderLayout.CENTER);


        // Tạo khu vực hiển thị các nút chức năng
        JPanel pnChucNang = new JPanel();
        pnChucNang.setLayout(new FlowLayout());
        Border borderChucNang = BorderFactory.createLineBorder(Color.BLUE);
        TitledBorder titledBorderChucNang = new TitledBorder(borderChucNang, "Chọn chức năng");
        titledBorderChucNang.setTitleFont(new Font(Font.SERIF, Font.BOLD, 15));
        titledBorderChucNang.setTitleJustification(TitledBorder.CENTER);
        titledBorderChucNang.setTitleColor(Color.RED);
        pnChucNang.setBorder(titledBorderChucNang);
        btnTinhChiPhi = new JButton("Tính chi phí");
        btnTinhChiPhi.setFont(new Font(Font.SERIF, Font.BOLD, 13));
        btnTinhGiaTri = new JButton("Tính giá trị");
        btnTinhGiaTri.setFont(new Font(Font.SERIF, Font.BOLD, 13));
        btnXuatThongTin = new JButton("Xuất thông tin");
        btnXuatThongTin.setFont(new Font(Font.SERIF, Font.BOLD, 13));

        pnChucNang.add(btnTinhChiPhi);
        pnChucNang.add(btnTinhGiaTri);
        pnChucNang.add(btnXuatThongTin);


        //
        Container con = getContentPane();
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout(5, 5));
        pnMain.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        pnMain.add(pnTenCongTy, BorderLayout.NORTH);
        pnMain.add(pnChonLoaiBaoHiem, BorderLayout.WEST);
        pnMain.add(pnThongTin, BorderLayout.CENTER);
        pnMain.add(pnChucNang, BorderLayout.SOUTH);
        con.add(pnMain);
    }

    // Triển khai phương thức addEvents
    private void addEvents() {
        // Thêm sự kiện cho các menu
        exitMenu.addActionListener(new ButtonListener());
        aboutMenu.addActionListener(new ButtonListener());

        // Thêm sự kiện cho các radioButton lựa chọn loại bảo hiểm
        radioBtnBaoHiemOto.addItemListener(new RadioButtonListener());
        radioBtnBaoHiemNha.addItemListener(new RadioButtonListener());
        radioBtnBaoHiemThuNhap.addItemListener(new RadioButtonListener());

        // Thêm sự kiện cho các button
        btnTinhChiPhi.addActionListener(new ButtonListener());
        btnTinhGiaTri.addActionListener(new ButtonListener());
        btnXuatThongTin.addActionListener(new ButtonListener());
        comboRateTaiXe.addActionListener(new ButtonListener());

        // Thêm sự kiện cho các TextField
        txtTenKH.getDocument().addDocumentListener(new TextFieldListener());
        txtTuoiKH.getDocument().addDocumentListener(new TextFieldListener());
        txtDiaChiKH.getDocument().addDocumentListener(new TextFieldListener());
        txtThuNhapKH.getDocument().addDocumentListener(new TextFieldListener());

        txtHangSanXuat.getDocument().addDocumentListener(new TextFieldListener());
        txtMauXe.getDocument().addDocumentListener(new TextFieldListener());
        txtBienSo.getDocument().addDocumentListener(new TextFieldListener());
        txtNamMuaBHOto.getDocument().addDocumentListener(new TextFieldListener());
        txtGiaTriDinhGiaOto.getDocument().addDocumentListener(new TextFieldListener());
        txtNamKtraBHOto.getDocument().addDocumentListener(new TextFieldListener());


    }

    // Triển khai phương thức showWindow
    public void showWindow() {
        this.setIconImage(new ImageIcon(Objects.requireNonNull(QuanLyBaoHiemGUI.class.getResource("/images/logoCongTy.png"))).getImage());
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setVisible(true);
    }

    private void docDuLieu() {
        tenKH = txtTenKH.getText().trim();
        strTuoiKH = txtTuoiKH.getText().trim();
        diaChiKH = txtDiaChiKH.getText().trim();
        strThuNhapKH = txtThuNhapKH.getText().trim();

        hangSanXuat = txtHangSanXuat.getText().trim();
        mauXe = txtMauXe.getText().trim();
        bienSo = txtBienSo.getText().trim();
        strNamMuaBHOto = txtNamMuaBHOto.getText().trim();
        strNamKtraBHOto = txtNamKtraBHOto.getText().trim();
        strGiaTriOto = txtGiaTriDinhGiaOto.getText().trim();
        rateTaiXe = (String) comboRateTaiXe.getSelectedItem();

        strNamMuaBHNha = txtNamMuaBaoHiemNha.getText().trim();
        strGiaTriNha = txtGiaTriDinhGiaNha.getText().trim();
        strGiaTriVatDung = txtGiaTriVatDung.getText().trim();
        strNamKtraBHNha = txtNamKtraBaoHiemNha.getText().trim();
    }

    private class ButtonListener implements ActionListener {

        private int tuoiKH, namMuaBaoHiemOto, namKtraBaoHiemOto, doRateTaiXe;
        private double thuNhapKH, giaTriOto;
        private int namMuaBaoHiemNha, namKtraBaoHiemNha;
        private double giaTriNha, giaTriVatDung;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == exitMenu) {
                System.exit(0);
            }
            if (e.getSource() == aboutMenu) {
                String strAuthorInfor = """
                        Tác giả: Phạm Ngọc Hải
                        Email: ngochai285nd@gmail.com
                        """;
                JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, strAuthorInfor, "Thông tin", JOptionPane.INFORMATION_MESSAGE);
            }
            if (e.getSource() == btnTinhChiPhi) {
                if (radioBtnBaoHiemOto.isSelected()) {
                    docDuLieu();
                    if (kiemTraThongTinKH() && kiemTraThongTinBaoHiemOto()) {
                        Insured baoHiemOto = new InsuredCar(hangSanXuat, mauXe, bienSo, namMuaBaoHiemOto, giaTriOto, tenKH, doRateTaiXe, tuoiKH);
                        double chiPhiBaoHiem = baoHiemOto.getInsurancePremium(namKtraBaoHiemOto);
                        JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Chi phí bảo hiểm ô tô là: "
                                + String.format("%.2f", chiPhiBaoHiem) + "$");
                    }
                }
                if (radioBtnBaoHiemNha.isSelected()) {
                    docDuLieu();
                    if (kiemTraThongTinKH() && kiemTraThongTinBaoHiemNha()) {
                        Insured baoHiemNha = new InsuredHouse(tenKH, tuoiKH, diaChiKH, giaTriNha, namMuaBaoHiemNha, giaTriVatDung);
                        double chiPhiBaoHiem = baoHiemNha.getInsurancePremium(namKtraBaoHiemNha);
                        JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Chi phí bảo hiểm nhà là: "
                                + String.format("%.2f", chiPhiBaoHiem) + "$");
                    }
                }
                if (radioBtnBaoHiemThuNhap.isSelected()) {
                    docDuLieu();
                    if (kiemTraThongTinKH()) {
                        Insured baoHiemThuNhap = new IncomeProtection(tenKH, tuoiKH, thuNhapKH);
                        double chiPhiBaoHiem = baoHiemThuNhap.getInsurancePremium(Calendar.getInstance().get(Calendar.YEAR));
                        JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Chi phí bảo hiểm thu nhập là: "
                                + String.format("%.2f", chiPhiBaoHiem) + "$");
                    }
                }
            }
            if (e.getSource() == btnTinhGiaTri) {
                if (radioBtnBaoHiemOto.isSelected()) {
                    docDuLieu();
                    if (kiemTraThongTinKH() && kiemTraThongTinBaoHiemOto()) {
                        Insured baoHiemOto = new InsuredCar(hangSanXuat, mauXe, bienSo, namMuaBaoHiemOto, giaTriOto, tenKH, doRateTaiXe, tuoiKH);
                        double giaTriBaoHiem = baoHiemOto.getInsuranceValue(namKtraBaoHiemOto);
                        JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Giá trị bảo hiểm ô tô là: "
                                + String.format("%.2f", giaTriBaoHiem) + "$");
                    }
                }
                if (radioBtnBaoHiemNha.isSelected()) {
                    docDuLieu();
                    if (kiemTraThongTinKH() && kiemTraThongTinBaoHiemNha()) {
                        Insured baoHiemNha = new InsuredHouse(tenKH, tuoiKH, diaChiKH, giaTriNha, namMuaBaoHiemNha, giaTriVatDung);
                        double giaTriBaoHiem = baoHiemNha.getInsuranceValue(namKtraBaoHiemNha);
                        JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Giá trị bảo hiểm nhà là: "
                                + String.format("%.2f", giaTriBaoHiem) + "$");
                    }
                }
                if (radioBtnBaoHiemThuNhap.isSelected()) {
                    docDuLieu();
                    if (kiemTraThongTinKH()) {
                        Insured baoHiemThuNhap = new IncomeProtection(tenKH, tuoiKH, thuNhapKH);
                        double giaTriBaoHiem = baoHiemThuNhap.getInsuranceValue(Calendar.getInstance().get(Calendar.YEAR));
                        JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Giá trị bảo hiểm thu nhập là: "
                                + String.format("%.2f", giaTriBaoHiem) + "$");
                    }
                }
            }
            if (e.getSource() == btnXuatThongTin) {
                if (radioBtnBaoHiemOto.isSelected()) {
                    docDuLieu();
                    if (kiemTraThongTinKH() && kiemTraThongTinBaoHiemOto()) {
                        String strTenKhachHang = "Họ và tên: " + tenKH + "\n";
                        String strTuoiKhachHang = "Tuổi: " + tuoiKH + "\n";
                        String strDiaChiKhachHang = "Địa chỉ: " + diaChiKH + "\n";
                        String strThuNhapKhachHang = "Thu nhập hiện tại: " + String.format("%.2f", thuNhapKH) + "$\n";
                        String strHangXe = "Hãng xe: " + hangSanXuat + "\n";
                        String strMauXe = "Mẫu xe: " + mauXe + "\n";
                        String strBienSo = "Biển số: " + bienSo + "\n";
                        String strNamMuaBaoHiem = "Năm mua bảo hiểm: " + namMuaBaoHiemOto + "\n";
                        String strNamKtraGiaTriBaoHiem = "Năm kiểm tra giá trị bảo hiểm: " + namKtraBaoHiemOto + "\n";
                        Insured baoHiemOto = new InsuredCar(hangSanXuat, mauXe, bienSo, namMuaBaoHiemOto, giaTriOto, tenKH, doRateTaiXe, tuoiKH);
                        double chiPhiBaoHiem = baoHiemOto.getInsurancePremium(namKtraBaoHiemOto);
                        double giaTriBaoHiem = baoHiemOto.getInsuranceValue(namKtraBaoHiemOto);
                        String strChiPhiBaoHiem = "Chi phí bảo hiểm: " + String.format("%.2f", chiPhiBaoHiem) + "$\n";
                        String strGiaTriBaoHiem = "Giá trị bảo hiểm: " + String.format("%.2f", giaTriBaoHiem) + "$\n";
                        JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this,
                                strTenKhachHang + strTuoiKhachHang + strDiaChiKhachHang + strThuNhapKhachHang + strHangXe + strMauXe
                                        + strBienSo + strNamMuaBaoHiem + strNamKtraGiaTriBaoHiem + strChiPhiBaoHiem + strGiaTriBaoHiem
                                , "Bảo hiểm Ôtô", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                if (radioBtnBaoHiemNha.isSelected()) {
                    docDuLieu();
                    if (kiemTraThongTinKH() && kiemTraThongTinBaoHiemNha()) {
                        String strTenKhachHang = "Họ và tên: " + tenKH + "\n";
                        String strTuoiKhachHang = "Tuổi: " + tuoiKH + "\n";
                        String strDiaChiKhachHang = "Địa chỉ: " + diaChiKH + "\n";
                        String strThuNhapKhachHang = "Thu nhập hiện tại: " + String.format("%.2f", thuNhapKH) + "$\n";
                        String strNamMuaBaoHiem = "Năm mua bảo hiểm: " + namMuaBaoHiemNha + "\n";
                        String strNamKtraGiaTriBaoHiem = "Năm kiểm tra giá trị bảo hiểm: " + namKtraBaoHiemNha + "\n";
                        Insured baoHiemNha = new InsuredHouse(tenKH, tuoiKH, diaChiKH, giaTriNha, namMuaBaoHiemNha, giaTriVatDung);
                        double chiPhiBaoHiem = baoHiemNha.getInsurancePremium(namKtraBaoHiemNha);
                        double giaTriBaoHiem = baoHiemNha.getInsuranceValue(namKtraBaoHiemNha);
                        String strChiPhiBaoHiem = "Chi phí bảo hiểm: " + String.format("%.2f", chiPhiBaoHiem) + "$\n";
                        String strGiaTriBaoHiem = "Giá trị bảo hiểm: " + String.format("%.2f", giaTriBaoHiem) + "$\n";
                        JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this,
                                strTenKhachHang + strTuoiKhachHang + strDiaChiKhachHang + strThuNhapKhachHang + strNamMuaBaoHiem +
                                        strNamKtraGiaTriBaoHiem + strChiPhiBaoHiem + strGiaTriBaoHiem, "Bảo hiểm nhà",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                if (radioBtnBaoHiemThuNhap.isSelected()) {
                    docDuLieu();
                    if (kiemTraThongTinKH()) {
                        String strTenKhachHang = "Họ và tên: " + tenKH + "\n";
                        String strTuoiKhachHang = "Tuổi: " + tuoiKH + "\n";
                        String strDiaChiKhachHang = "Địa chỉ: " + diaChiKH + "\n";
                        String strThuNhapKhachHang = "Thu nhập hiện tại: " + String.format("%.2f", thuNhapKH) + "$\n";
                        Insured baoHiemThuNhap = new IncomeProtection(tenKH, tuoiKH, thuNhapKH);
                        double giaTriBaoHiem = baoHiemThuNhap.getInsuranceValue(Calendar.getInstance().get(Calendar.YEAR));
                        double chiPhiBaoHiem = baoHiemThuNhap.getInsurancePremium(Calendar.getInstance().get(Calendar.YEAR));
                        String strChiPhiBaoHiem = "Chi phí bảo hiểm thu nhập: " + String.format("%.2f", chiPhiBaoHiem) + "$\n";
                        String strGiaTriBaoHiem = "Giá trị bảo hiểm thu nhập: " + String.format("%.2f", giaTriBaoHiem) + "$\n";
                        JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this,
                                strTenKhachHang + strTuoiKhachHang + strDiaChiKhachHang + strThuNhapKhachHang + strChiPhiBaoHiem
                                        + strGiaTriBaoHiem, "Bảo hiểm thu nhập", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            if (e.getSource() == comboRateTaiXe) {
                rateTaiXe = (String) comboRateTaiXe.getSelectedItem();
            }
        }

        private boolean kiemTraThongTinKH() {
            if (tenKH.equals("") || strTuoiKH.equals("") || diaChiKH.equals("") || strThuNhapKH.equals("")) {
                JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Thông tin khách hàng không được để trống!", "Error"
                        , JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                try {
                    tuoiKH = Integer.parseInt(strTuoiKH);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Tuổi khách hàng không hợp lệ!", "Error"
                            , JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                try {
                    thuNhapKH = Double.parseDouble(strThuNhapKH);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Thu nhập khách hàng không hợp lệ!", "Error"
                            , JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            return true;
        }

        private boolean kiemTraThongTinBaoHiemOto() {
            if (hangSanXuat.equals("") || mauXe.equals("") || bienSo.equals("") || strNamMuaBHOto.equals("") || strGiaTriOto.equals("") || strNamKtraBHOto.equals("") || rateTaiXe.equals("")) {
                JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Thông tin bảo hiểm không được để trống!", "Error"
                        , JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                try {
                    namMuaBaoHiemOto = Integer.parseInt(strNamMuaBHOto);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Năm mua bảo hiểm không hợp lệ!", "Error"
                            , JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                try {
                    giaTriOto = Double.parseDouble(strGiaTriOto);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Giá trị bảo hiểm không hợp lệ!", "Error"
                            , JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                try {
                    namKtraBaoHiemOto = Integer.parseInt(strNamKtraBHOto);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Năm kiểm tra không hợp lệ!", "Error"
                            , JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                doRateTaiXe = Integer.parseInt(rateTaiXe);
            }
            return true;
        }

        private boolean kiemTraThongTinBaoHiemNha() {
            if (strNamMuaBHNha.equals("") || strGiaTriNha.equals("") || strGiaTriVatDung.equals("") || strNamKtraBHNha.equals("")) {
                JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Thông tin bảo hiểm không được để trống!", "Error"
                        , JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                try {
                    namMuaBaoHiemNha = Integer.parseInt(strNamMuaBHNha);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Năm mua bảo hiểm không hợp lệ!", "Error"
                            , JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                try {
                    giaTriNha = Double.parseDouble(strGiaTriNha);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Giá trị bảo hiểm không hợp lệ!", "Error"
                            , JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                try {
                    giaTriVatDung = Double.parseDouble(strGiaTriVatDung);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Giá trị vật dụng không hợp lệ!", "Error"
                            , JOptionPane.ERROR_MESSAGE);
                    return false;
                }

                try {
                    namKtraBaoHiemNha = Integer.parseInt(strNamKtraBHNha);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(QuanLyBaoHiemGUI.this, "Năm mua bảo hiểm không hợp lệ!", "Error"
                            , JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            return true;
        }
    }

    private class RadioButtonListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource() == radioBtnBaoHiemOto && radioBtnBaoHiemOto.isSelected()) {
                tabbedPane.removeTabAt(0);
                JScrollPane scrollPane = new JScrollPane(pnBaoHiemOto, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                tabbedPane.addTab("Bảo hiểm Ôtô", scrollPane);
            }
            if (e.getSource() == radioBtnBaoHiemNha && radioBtnBaoHiemNha.isSelected()) {
                tabbedPane.removeTabAt(0);
                JScrollPane scrollPane = new JScrollPane(pnBaoHiemNha, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                tabbedPane.addTab("Bảo hiểm nhà", scrollPane);
            }
            if (e.getSource() == radioBtnBaoHiemThuNhap && radioBtnBaoHiemThuNhap.isSelected()) {
                tabbedPane.removeTabAt(0);
                JScrollPane scrollPane = new JScrollPane(pnBaoHiemThuNhap, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                tabbedPane.addTab("Bảo hiểm thu nhập", scrollPane);
            }
        }
    }

    private class TextFieldListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            docDuLieu();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            docDuLieu();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            docDuLieu();
        }
    }
}

