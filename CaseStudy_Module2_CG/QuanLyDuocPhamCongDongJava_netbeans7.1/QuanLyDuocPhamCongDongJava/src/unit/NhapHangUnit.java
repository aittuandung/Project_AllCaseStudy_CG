package unit;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class NhapHangUnit implements Serializable {

    public NhapHangUnit() {
    }

    public NhapHangUnit(String maPhieu, String ngayNhap, DuocPhamUnit thuocInfoUnit, String soLo, String donViTinh, String ngaySanXuat, String hanSuDung, int soLuong, int giaNhap, int giaBan, String ghiChu) {
        this.duocPhamUnit = thuocInfoUnit;
        this.ngayNhap = ngayNhap;
        this.maPhieu = maPhieu;
        this.soLo = soLo;
        this.donViTinh = donViTinh;
        this.ngaySanXuat = ngaySanXuat;
        this.hanSuDung = hanSuDung;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.ghiChu = ghiChu;
    }
    private DuocPhamUnit duocPhamUnit;
    private String ngayNhap;
    private String maPhieu;
    private String soLo;
    private String donViTinh;
    private String ngaySanXuat;
    private String hanSuDung;
    private int soLuong;
    private int giaNhap;
    private int giaBan;
    private String ghiChu;

    /**
     * @return the thuocInfoUnit
     */
    public DuocPhamUnit getDuocPhamUnit() {
        return duocPhamUnit;
    }

    /**
     * @param thuocInfoUnit the thuocInfoUnit to set
     */
    public void setThuocInfoUnit(DuocPhamUnit thuocInfoUnit) {
        this.duocPhamUnit = thuocInfoUnit;
    }

    /**
     * @return the ngayNhap
     */
    public String getNgayNhap() {
        return ngayNhap;
    }

    /**
     * @param ngayNhap the ngayNhap to set
     */
    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    /**
     * @return the soLo
     */
    public String getSoLo() {
        return soLo;
    }

    /**
     * @param soLo the soLo to set
     */
    public void setSoLo(String soLo) {
        this.soLo = soLo;
    }

    /**
     * @return the donViTinh
     */
    public String getDonViTinh() {
        return donViTinh;
    }

    /**
     * @param donViTinh the donViTinh to set
     */
    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    /**
     * @return the ngaySanXuat
     */
    public String getNgaySanXuat() {
        return ngaySanXuat;
    }

    /**
     * @param ngaySanXuat the ngaySanXuat to set
     */
    public void setNgaySanXuat(String ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    /**
     * @return the hanSuDung
     */
    public String getHanSuDung() {
        return hanSuDung;
    }

    /**
     * @param hanSuDung the hanSuDung to set
     */
    public void setHanSuDung(String hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the ghiChu
     */
    public String getGhiChu() {
        return ghiChu;
    }

    /**
     * @param ghiChu the ghiChu to set
     */
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    /**
     * @return the maPhieu
     */
    public String getMaPhieu() {
        return maPhieu;
    }

    /**
     * @param maPhieu the maPhieu to set
     */
    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NhapHangUnit other = (NhapHangUnit) obj;
        if (!Objects.equals(this.maPhieu, other.maPhieu)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.maPhieu);
        return hash;
    }

    /**
     * @return the giaNhap
     */
    public int getGiaNhap() {
        return giaNhap;
    }

    /**
     * @param giaNhap the giaNhap to set
     */
    public void setGiaNhap(int giaNhap) {
        this.giaNhap = giaNhap;
    }

    /**
     * @return the giaBan
     */
    public int getGiaBan() {
        return giaBan;
    }

    /**
     * @param giaBan the giaBan to set
     */
    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }
    
    
}
