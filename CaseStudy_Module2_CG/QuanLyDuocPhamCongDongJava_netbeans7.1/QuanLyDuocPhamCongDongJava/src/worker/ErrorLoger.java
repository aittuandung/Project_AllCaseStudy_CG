
package worker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class ErrorLoger {

    public static String FOLDER = "LoiPhamMem";

    public static PrintStream getLogStream() {
        if (!new File(FOLDER).exists()) {
            new File(FOLDER).mkdir();
        }
        PrintStream ps = null;
        try {
            String file = FOLDER + File.separator + "Logged-" + DateTimeMaster.getGioPhutGiayHienTaiCachBoiDauCham() + "-" + DateTimeMaster.getNgayThangHienTaiCachBoiDauCham()+".log";
            ps = new PrintStream(new FileOutputStream(new File(file)));
        } catch (FileNotFoundException ex) {
        }
        return ps;
    }
}
