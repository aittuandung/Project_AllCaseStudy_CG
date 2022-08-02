
package unit.setting;

import java.io.Serializable;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class ToaThuocSetting implements Serializable{

    private int toaThuocID;

    public int getToaThuocNewID() {
        return toaThuocID + 1;
    }

    public void setToaThuocLastID(int toaThuocID) {
        this.toaThuocID = toaThuocID;
    }
}
