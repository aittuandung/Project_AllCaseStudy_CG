
package unit.manager;

import unit.ToaThuocUnit;
import unit.GlobalData;
import unit.data.ToaThuocData;
import unit.setting.ToaThuocSetting;
import exception.ExistException;
import exception.NotExistException;
import java.io.File;
import java.util.Vector;
import unit.DuocPhamToaThuocUnit;
import worker.DateTimeMaster;
import worker.IOMaster;
import worker.NoMarkText;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class ToaThuocDataManager {

    public static final String SETTINGFILE = GlobalData.DATAFOLDER + File.separator + "_ToaThuocSetting.set";

    public static void saveToaThuocSetting() throws Exception {
        writeData(SETTINGFILE, GlobalData.getToaThuocSetting());
    }

    public static ToaThuocSetting loadToaThuocSetting() throws Exception {
        return (ToaThuocSetting) IOMaster.readObject(SETTINGFILE);
    }
    /*
     *
     */
    public static String FILENAME = "DuLieuToaThuoc" + DateTimeMaster.getMonth() + DateTimeMaster.getYear() + ".data";
    public static String FILEPATH = GlobalData.DATAFOLDER + File.separator + FILENAME;

    public static void setFileName(String fileName) {
        FILENAME = fileName;
        FILEPATH = GlobalData.DATAFOLDER + File.separator + fileName;
    }

    public static void createToaThuocData() throws Exception {
        new File(GlobalData.DATAFOLDER).mkdir();
        IOMaster.writeObject(FILEPATH, new ToaThuocData());
    }

    public static Vector<String> getAllSavedFile() {
        Vector<String> v = new Vector();
        String[] ar = new File(GlobalData.DATAFOLDER).list();
        for (int i = 0; i < ar.length; i++) {
            if (ar[i].startsWith("DuLieuToaThuoc") && ar[i].endsWith(".data")) {
                v.add(ar[i].substring(0, ar[i].indexOf(".")));
            }
        }
        return v;
    }

    public static ToaThuocData loadToaThuocData() throws Exception {
        if (!new File(FILEPATH).exists()) {
            createToaThuocData();
        }
        return (ToaThuocData) IOMaster.readObject(FILEPATH);
    }

    public static Vector<ToaThuocUnit> getLichSuToaThuoc(String select) throws Exception {
        ToaThuocData data = GlobalData.getToaThuocData();
        if (select.equals("*")) {
            return data.getLichSuToaThuoc();
        }
        Vector<ToaThuocUnit> v = new Vector();
        for (int i = 0; i < data.getLichSuToaThuoc().size(); i++) {
            Vector<DuocPhamToaThuocUnit> vd = data.getLichSuToaThuoc().get(i).getDuocPhams();
            for (int j = 0; j < vd.size(); j++) {
                if (NoMarkText.removeMark(vd.get(j).getDuocPhamUnit().getTenThuoc().toLowerCase()).contains(NoMarkText.removeMark(select.toLowerCase()))) {
                    v.add(data.getLichSuToaThuoc().get(i));
                }
            }
        }
        return v;
    }

    public static Vector<ToaThuocUnit> getLichSuToaThuocByGuessName(String guessName) throws Exception {
        ToaThuocData data = GlobalData.getToaThuocData();
        Vector<ToaThuocUnit> v = new Vector();
        for (int i = 0; i < data.getLichSuToaThuoc().size(); i++) {
            if (NoMarkText.removeMark(data.getLichSuToaThuoc().get(i).getKhachHang().toLowerCase()).contains(NoMarkText.removeMark(guessName.toLowerCase()))) {
                v.add(data.getLichSuToaThuoc().get(i));
            }
        }
        return v;
    }

    public static ToaThuocUnit getToaThuocUnit(String maPhieu) throws Exception {
        ToaThuocData data = GlobalData.getToaThuocData();
        ToaThuocUnit unit2 = new ToaThuocUnit();
        unit2.setMaPhieu(maPhieu);
        return data.getLichSuToaThuoc().get(data.getLichSuToaThuoc().indexOf(unit2));
    }

    public static void addToaThuocUnit(ToaThuocUnit unit) throws ExistException, Exception {
        ToaThuocData data = GlobalData.getToaThuocData();
        Vector<ToaThuocUnit> data2 = data.getLichSuToaThuoc();
        if (data2.contains(unit)) {
            throw new ExistException();
        }
        data2.add(unit);
        writeData(FILEPATH, data);
    }

    public static void deleteXuatUnit(ToaThuocUnit unit) throws NotExistException, Exception {
        ToaThuocData data = GlobalData.getToaThuocData();
        Vector<ToaThuocUnit> duocPhams = data.getLichSuToaThuoc();
        if (!duocPhams.contains(unit)) {
            throw new NotExistException();
        }
        duocPhams.remove(unit);
        writeData(FILEPATH, data);
    }

    public static void replaceXuatUnit(ToaThuocUnit oldUnit, ToaThuocUnit newUnit) throws NotExistException, Exception {
        ToaThuocData data = GlobalData.getToaThuocData();
        Vector<ToaThuocUnit> duocPhams = data.getLichSuToaThuoc();
        if (!duocPhams.contains(oldUnit)) {
            throw new NotExistException();
        }
        duocPhams.set(duocPhams.indexOf(oldUnit), newUnit);
        writeData(FILEPATH, data);
    }

    public static void saveToaThuocData() throws Exception {
        ToaThuocData data = GlobalData.getToaThuocData();
        writeData(FILEPATH, data);
    }

    private static void writeData(String filePath, Object obj) throws Exception {
        if (!new File(filePath).exists()) {
            createToaThuocData();
        }
        IOMaster.writeObject(filePath, obj);
    }

    public static boolean checkIfExist(ToaThuocUnit unit) throws Exception {
        ToaThuocData data = GlobalData.getToaThuocData();
        return data.getLichSuToaThuoc().contains(unit);
    }
}
