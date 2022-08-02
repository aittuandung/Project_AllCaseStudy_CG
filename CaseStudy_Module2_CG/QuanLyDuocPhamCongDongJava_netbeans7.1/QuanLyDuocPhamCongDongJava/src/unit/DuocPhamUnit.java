
package unit;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class DuocPhamUnit implements Serializable {

    public DuocPhamUnit() {
    }

    public DuocPhamUnit(String id, String tenThuoc, String nhaSanXuat, String donViTinh, int soLuongDuTruToiThieu, String ghiChu) {
        this.id = id;
        this.tenThuoc = tenThuoc;
        this.nhaSanXuat = nhaSanXuat;
        this.donViTinh = donViTinh;
        this.soLuongDuTruToiThieu = soLuongDuTruToiThieu;
        this.ghiChu = ghiChu;
    }
    private String id;
    private String tenThuoc;
    private String nhaSanXuat;
    private String donViTinh;
    private int soLuongHienCo = 0;
    private int soLuongDuTruToiThieu;
    private String ghiChu;
    private boolean newAdded = true;
    private int giaNhap;
    private int giaBan;

    /**
     * @return the tenThuoc
     */
    public String getTenThuoc() {
        return tenThuoc;
    }

    /**
     * @param tenThuoc the tenThuoc to set
     */
    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    /**
     * @return the nhaSanXuat
     */
    public String getNhaSanXuat() {
        return nhaSanXuat;
    }

    /**
     * @param nhaSanXuat the nhaSanXuat to set
     */
    public void setNhaSanXuat(String nhaSanXuat) {
        this.nhaSanXuat = nhaSanXuat;
    }

    /**
     * @return the luuY
     */
    public String getGhiChu() {
        return ghiChu;
    }

    /**
     * @param luuY the luuY to set
     */
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    /**
     * @return the soLuongDuTruToiThieu
     */
    public int getSoLuongDuTruToiThieu() {
        return soLuongDuTruToiThieu;
    }

    /**
     * @param soLuongDuTruToiThieu the soLuongDuTruToiThieu to set
     */
    public void setSoLuongDuTruToiThieu(int soLuongDuTruToiThieu) {
        this.soLuongDuTruToiThieu = soLuongDuTruToiThieu;
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
     * @return the soLuongHienCo
     */
    public int getSoLuongHienCo() {
        return soLuongHienCo;
    }

    /**
     * @param soLuongHienCo the soLuongHienCo to set
     */
    public void setSoLuongHienCo(int soLuongHienCo) {
        this.soLuongHienCo = soLuongHienCo;
    }

    public String toString() {
        return tenThuoc + " ~ " + nhaSanXuat + " ~ " + donViTinh + " | SL: " + soLuongHienCo + " " + donViTinh;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DuocPhamUnit other = (DuocPhamUnit) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * @return the newAdded
     */
    public boolean isNewAdded() {
        return newAdded;
    }

    /**
     * @param newAdded the newAdded to set
     */
    public void setNewAdded(boolean newAdded) {
        this.newAdded = newAdded;
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