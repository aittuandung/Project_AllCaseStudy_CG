
package unit.data;

import java.io.Serializable;
import unit.DuocPhamUnit;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class DuTruUnit implements Serializable {

    public DuTruUnit(DuocPhamUnit thuocInfoUnit, String donViTinh, int soLuong) {
        this.thuocInfoUnit = thuocInfoUnit;
        this.donViTinh = donViTinh;
        this.soLuong = soLuong;
    }
    private DuocPhamUnit thuocInfoUnit;
    private String donViTinh;
    private int soLuong;

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
}
