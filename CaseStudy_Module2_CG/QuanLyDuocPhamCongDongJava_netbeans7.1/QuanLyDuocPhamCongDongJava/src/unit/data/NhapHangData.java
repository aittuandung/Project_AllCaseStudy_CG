
package unit.data;

import java.io.Serializable;
import java.util.Vector;
import unit.NhapHangUnit;
/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class NhapHangData implements Serializable {

    private Vector<NhapHangUnit> data = new Vector();

    /**
     * @return the duocPhams
     */
    public Vector<NhapHangUnit> getLichSuNhapHang() {
        return data;
    }
}
