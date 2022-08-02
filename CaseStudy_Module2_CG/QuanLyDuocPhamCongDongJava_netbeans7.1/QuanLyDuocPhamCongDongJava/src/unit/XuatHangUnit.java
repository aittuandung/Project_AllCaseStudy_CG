
package unit;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class XuatHangUnit implements Serializable {

    public XuatHangUnit() {
    }

    public XuatHangUnit(String maPhieu, String ngayXuat, String khachHang, DuocPhamUnit thuocInfoUnit, String donViTinh, int soLuong, String ghiChu) {
        this.ngayXuat = ngayXuat;
        this.maPhieu = maPhieu;
        this.khachHang = khachHang;
        this.thuocInfoUnit = thuocInfoUnit;
        this.donViTinh = donViTinh;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
    }

   
    private String ngayXuat;
    private String maPhieu;
    private String khachHang;
    private DuocPhamUnit thuocInfoUnit;
    private String donViTinh;
    private int soLuong;
    private String ghiChu;

    /**
     * @return the ngay
     */
    public String getNgayXuat() {
        return ngayXuat;
    }

    /**
     * @param ngay the ngay to set
     */
    public void setNgay(String ngay) {
        this.ngayXuat = ngay;
    }

    /**
     * @return the thuocInfoUnit
     */
    public DuocPhamUnit getThuocInfoUnit() {
        return thuocInfoUnit;
    }

    /**
     * @param thuocInfoUnit the thuocInfoUnit to set
     */
    public void setThuocInfoUnit(DuocPhamUnit thuocInfoUnit) {
        this.thuocInfoUnit = thuocInfoUnit;
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

    /**
     * @return the khachHang
     */
    public String getKhachHang() {
        return khachHang;
    }

    /**
     * @param khachHang the khachHang to set
     */
    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final XuatHangUnit other = (XuatHangUnit) obj;
        if (!Objects.equals(this.maPhieu, other.maPhieu)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.maPhieu);
        return hash;
    }
    
    
}
