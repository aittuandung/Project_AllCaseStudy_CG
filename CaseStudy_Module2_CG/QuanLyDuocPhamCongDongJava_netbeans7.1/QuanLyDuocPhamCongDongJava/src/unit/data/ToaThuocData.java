
package unit.data;

import java.io.Serializable;
import java.util.Vector;
import unit.ToaThuocUnit;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class ToaThuocData implements Serializable {

    private Vector<ToaThuocUnit> data = new Vector();
    /**
     * @return the duocPhams
     */
    public Vector<ToaThuocUnit> getLichSuToaThuoc() {
        return data;
    }
}
