
package unit.setting;

import java.io.Serializable;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class XuatHangSetting implements Serializable {

    private int phieuXuatID = 0;

    public int getPhieuXuatNewID() {
        return phieuXuatID + 1;
    }

    public void setPhieuXuatLastID(int phieuXuatID) {
        this.phieuXuatID = phieuXuatID;
    }
}
