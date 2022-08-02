
package unit;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class DuocPhamToaThuocUnit implements Serializable {

    public DuocPhamToaThuocUnit() {
    }

    public DuocPhamToaThuocUnit(DuocPhamUnit duocPhamUnit, int soLuong) {
        this.duocPhamUnit = duocPhamUnit;
        this.soLuong = soLuong;
    }
    private DuocPhamUnit duocPhamUnit;
    private int soLuong;

    /**
     * @return the duocPhamUnit
     */
    public DuocPhamUnit getDuocPhamUnit() {
        return duocPhamUnit;
    }

    /**
     * @param duocPhamUnit the duocPhamUnit to set
     */
    public void setDuocPhamUnit(DuocPhamUnit duocPhamUnit) {
        this.duocPhamUnit = duocPhamUnit;
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

    public String toString() {
        return duocPhamUnit.toString().substring(0, duocPhamUnit.toString().lastIndexOf("~")) + "~ " + soLuong + " " + duocPhamUnit.getDonViTinh();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DuocPhamToaThuocUnit other = (DuocPhamToaThuocUnit) obj;
        if (!Objects.equals(this.duocPhamUnit, other.duocPhamUnit)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.duocPhamUnit);
        return hash;
    }
}