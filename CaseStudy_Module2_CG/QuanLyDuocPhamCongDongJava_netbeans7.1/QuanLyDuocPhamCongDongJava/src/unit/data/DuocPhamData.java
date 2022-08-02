
package unit.data;

import java.io.Serializable;
import java.util.Vector;
import unit.DuocPhamUnit;
import worker.MD5Encryption;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class DuocPhamData implements Serializable {

    private DuocPhamData() {
    }

    public DuocPhamData(String usename, String password) {
        this.accessInfo = MD5Encryption.encrypt(usename.toLowerCase() + password);
    }
    private int duocPhamID = 0;

    public int getDuocPhamNewID() {
        return duocPhamID+1;
    }

    public void setDuocPhamLastID(int phieuNhapID) {
        this.duocPhamID = phieuNhapID;
    }
    private String accessInfo;

    public String getAccessInfo() {
        return accessInfo;
    }

    public void setAccessInfo(String accessInfo) {
        this.accessInfo = accessInfo;
    }
    private Vector<DuocPhamUnit> duocPhams = new Vector();

    public Vector<DuocPhamUnit> getDanhSachDuocPham() {
        return duocPhams;
    }
}
