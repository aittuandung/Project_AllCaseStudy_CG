
package unit.data;

import java.io.Serializable;
import java.util.Vector;
import unit.XuatHangUnit;
/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class XuatHangData implements Serializable {

    private Vector<XuatHangUnit> data = new Vector();

    /**
     * @return the duocPhams
     */
    public Vector<XuatHangUnit> getLichSuXuatHang() {
        return data;
    }
}
