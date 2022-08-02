
package unit.manager;

import unit.setting.XuatHangSetting;
import unit.GlobalData;
import unit.data.XuatHangData;
import unit.XuatHangUnit;
import exception.ExistException;
import exception.NotExistException;
import java.io.File;
import java.util.Vector;
import worker.DateTimeMaster;
import worker.IOMaster;
import worker.NoMarkText;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class XuatHangDataManager {

    public static final String SETTINGFILE = GlobalData.DATAFOLDER + File.separator + "_XuatHangSetting.set";

    public static void saveXuatHangSetting() throws Exception {
        writeData(SETTINGFILE, GlobalData.getXuatHangSetting());
    }

    public static XuatHangSetting loadXuatHangSetting() throws Exception {
        return (XuatHangSetting) IOMaster.readObject(SETTINGFILE);
    }
    /*
     *
     */
    public static String FILENAME = "DuLieuXuatHang" + DateTimeMaster.getMonth() + DateTimeMaster.getYear() + ".data";
    public static String FILEPATH = GlobalData.DATAFOLDER + File.separator + FILENAME;

    public static void setFileName(String fileName) {
        FILENAME = fileName;
        FILEPATH = GlobalData.DATAFOLDER + File.separator + fileName;
    }

    public static void createXuatHangData() throws Exception {
        new File(GlobalData.DATAFOLDER).mkdir();
        IOMaster.writeObject(FILEPATH, new XuatHangData());
    }

    public static Vector<String> getAllSavedFile() {
        Vector<String> v = new Vector();
        String[] ar = new File(GlobalData.DATAFOLDER).list();
        for (int i = 0; i < ar.length; i++) {
            if (ar[i].startsWith("DuLieuXuatHang") && ar[i].endsWith(".data")) {
                v.add(ar[i].substring(0, ar[i].indexOf(".")));
            }
        }
        return v;
    }

    public static XuatHangData loadXuatHangData() throws Exception {
        if (!new File(FILEPATH).exists()) {
            createXuatHangData();
        }
        return (XuatHangData) IOMaster.readObject(FILEPATH);
    }

    public static Vector<XuatHangUnit> getLichSuXuatHang(String select) throws Exception {
        XuatHangData data = GlobalData.getXuatHangData();
        if (select.equals("*")) {
            return data.getLichSuXuatHang();
        }
        Vector<XuatHangUnit> v = new Vector();
        for (int i = 0; i < data.getLichSuXuatHang().size(); i++) {
            if (NoMarkText.removeMark(data.getLichSuXuatHang().get(i).getThuocInfoUnit().getTenThuoc().toLowerCase()).contains(NoMarkText.removeMark(select.toLowerCase()))) {
                v.add(data.getLichSuXuatHang().get(i));
            }
        }
        return v;
    }

    public static Vector<XuatHangUnit> getLichSuXuatHangByGuessName(String guessName) throws Exception {
        XuatHangData data = GlobalData.getXuatHangData();
        if (guessName.equals("*")) {
            return data.getLichSuXuatHang();
        }
        Vector<XuatHangUnit> v = new Vector();
        for (int i = 0; i < data.getLichSuXuatHang().size(); i++) {
            if (NoMarkText.removeMark(data.getLichSuXuatHang().get(i).getKhachHang().toLowerCase()).contains(NoMarkText.removeMark(guessName.toLowerCase()))) {
                v.add(data.getLichSuXuatHang().get(i));
            }
        }
        return v;
    }

    public static XuatHangUnit getXuatHangUnit(String maPhieu) throws Exception {
        XuatHangData data = GlobalData.getXuatHangData();
        XuatHangUnit unit2 = new XuatHangUnit();
        unit2.setMaPhieu(maPhieu);
        return data.getLichSuXuatHang().get(data.getLichSuXuatHang().indexOf(unit2));
    }

    public static void addXuatHangUnit(XuatHangUnit unit) throws ExistException, Exception {
        XuatHangData data = GlobalData.getXuatHangData();
        Vector<XuatHangUnit> data2 = data.getLichSuXuatHang();
        if (data2.contains(unit)) {
            throw new ExistException();
        }
        data2.add(unit);
        writeData(FILEPATH, data);
    }

    public static void deleteXuatUnit(XuatHangUnit unit) throws NotExistException, Exception {
        XuatHangData data = GlobalData.getXuatHangData();
        Vector<XuatHangUnit> duocPhams = data.getLichSuXuatHang();
        if (!duocPhams.contains(unit)) {
            throw new NotExistException();
        }
        duocPhams.remove(unit);
        writeData(FILEPATH, data);
    }

    public static void replaceXuatUnit(XuatHangUnit oldUnit, XuatHangUnit newUnit) throws NotExistException, Exception {
        XuatHangData data = GlobalData.getXuatHangData();
        Vector<XuatHangUnit> duocPhams = data.getLichSuXuatHang();
        if (!duocPhams.contains(oldUnit)) {
            throw new NotExistException();
        }
        duocPhams.set(duocPhams.indexOf(oldUnit), newUnit);
        writeData(FILEPATH, data);
    }

    public static void saveXuatHangData() throws Exception {
        XuatHangData data = GlobalData.getXuatHangData();
        writeData(FILEPATH, data);
    }

    private static void writeData(String filePath, Object obj) throws Exception {
        if (!new File(filePath).exists()) {
            createXuatHangData();
        }
        IOMaster.writeObject(filePath, obj);
    }

    public static boolean checkIfExist(XuatHangUnit dp) throws Exception {
        XuatHangData data = GlobalData.getXuatHangData();
        return data.getLichSuXuatHang().contains(dp);
    }
}
