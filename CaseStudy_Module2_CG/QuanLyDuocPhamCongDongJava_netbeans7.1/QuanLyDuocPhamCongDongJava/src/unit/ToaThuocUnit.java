
package unit;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class ToaThuocUnit implements Serializable {

    public ToaThuocUnit() {
        duocPhams = new Vector<>();
    }

    public ToaThuocUnit(String maPhieu, String ngayXuat, String khachHang, int tongTien, String ghiChu, Vector<DuocPhamToaThuocUnit> duocPhams) {
        this.maPhieu = maPhieu;
        this.ngayXuat = ngayXuat;
        this.khachHang = khachHang;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
        this.duocPhams = duocPhams;
    }
    private String maPhieu;
    private String ngayXuat;
    private String khachHang;
    private int tongTien;
    private String ghiChu;
    private Vector<DuocPhamToaThuocUnit> duocPhams;

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
     * @return the ngayXuat
     */
    public String getNgayXuat() {
        return ngayXuat;
    }

    /**
     * @param ngayXuat the ngayXuat to set
     */
    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
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

    /**
     * @return the tongTien
     */
    public int getTongTien() {
        return tongTien;
    }

    /**
     * @param tongTien the tongTien to set
     */
    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
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
     * @return the duocPhams
     */
    public Vector<DuocPhamToaThuocUnit> getDuocPhams() {
        return duocPhams;
    }

    /**
     * @param duocPhams the duocPhams to set
     */
    public void setDuocPhams(Vector<DuocPhamToaThuocUnit> duocPhams) {
        this.duocPhams = duocPhams;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ToaThuocUnit other = (ToaThuocUnit) obj;
        if (!Objects.equals(this.maPhieu, other.maPhieu)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.maPhieu);
        return hash;
    }
}
