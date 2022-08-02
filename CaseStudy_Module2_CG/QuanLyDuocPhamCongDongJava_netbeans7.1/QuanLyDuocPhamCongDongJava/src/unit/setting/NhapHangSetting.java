
package unit.setting;

import java.io.Serializable;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class NhapHangSetting  implements Serializable{

    private int phieuNhapID = 0;

    public int getPhieuNhapNewID() {
        return phieuNhapID+1;
    }

    public void setPhieuNhapLastID(int phieuNhapID) {
        this.phieuNhapID = phieuNhapID;
    }
}
