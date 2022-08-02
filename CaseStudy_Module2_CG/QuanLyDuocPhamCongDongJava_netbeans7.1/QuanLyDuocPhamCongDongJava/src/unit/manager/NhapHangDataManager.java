
package unit.manager;

import unit.data.NhapHangData;
import unit.GlobalData;
import unit.NhapHangUnit;
import unit.setting.NhapHangSetting;
import exception.ExistException;
import exception.FileNotExistException;
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
public class NhapHangDataManager {

    public static final String SETTINGFILE = GlobalData.DATAFOLDER + File.separator + "_NhapHangSetting.set";

    public static void saveNhapHangSetting() throws Exception {
        writeData(SETTINGFILE, GlobalData.getNhapHangSetting());
    }

    public static NhapHangSetting loadNhapHangSetting() throws Exception {
        return (NhapHangSetting) IOMaster.readObject(SETTINGFILE);
    }
    /*
     *
     */
    public static String FILENAME = "DuLieuNhapHang" + DateTimeMaster.getMonth() + DateTimeMaster.getYear() + ".data";
    public static String FILEPATH = GlobalData.DATAFOLDER + File.separator + FILENAME;

    public static void setFileName(String fileName) {
        FILENAME = fileName;
        FILEPATH = GlobalData.DATAFOLDER + File.separator + fileName;
    }

    public static void createNhapHangData() throws Exception {
        new File(GlobalData.DATAFOLDER).mkdir();
        IOMaster.writeObject(FILEPATH, new NhapHangData());
    }

    public static Vector<String> getAllSavedFile() {
        Vector<String> v = new Vector();
        String[] ar = new File(GlobalData.DATAFOLDER).list();
        for (int i = 0; i < ar.length; i++) {
            if (ar[i].startsWith("DuLieuNhapHang") && ar[i].endsWith(".data")) {
                v.add(ar[i].substring(0, ar[i].indexOf(".")));
            }
        }
        return v;
    }

    public static NhapHangData loadNhapHangData() throws Exception {
        if (!new File(FILEPATH).exists()) {
            createNhapHangData();
        }
        return (NhapHangData) IOMaster.readObject(FILEPATH);
    }

    public static Vector<NhapHangUnit> getLichSuNhapHang(String select) throws Exception {
        NhapHangData data = GlobalData.getNhapHangData();
        if (select.equals("*")) {
            return data.getLichSuNhapHang();
        }
        Vector<NhapHangUnit> v = new Vector();
        for (int i = 0; i < data.getLichSuNhapHang().size(); i++) {
            if (NoMarkText.removeMark(data.getLichSuNhapHang().get(i).getDuocPhamUnit().getTenThuoc().toLowerCase()).contains(NoMarkText.removeMark(select.toLowerCase()))) {
                v.add(data.getLichSuNhapHang().get(i));
            }
        }
        return v;
    }

    public static NhapHangUnit getNhapHangUnit(String maPhieu) throws Exception {
        NhapHangData data = GlobalData.getNhapHangData();
        NhapHangUnit unit2 = new NhapHangUnit();
        unit2.setMaPhieu(maPhieu);
        return data.getLichSuNhapHang().get(data.getLichSuNhapHang().indexOf(unit2));
    }

    public static void addNhapHangUnit(NhapHangUnit unit) throws ExistException, Exception {
        NhapHangData data = GlobalData.getNhapHangData();
        Vector<NhapHangUnit> data2 = data.getLichSuNhapHang();
        if (data2.contains(unit)) {
            throw new ExistException();
        }
        data2.add(unit);
        writeData(FILEPATH, data);
    }

    public static void deleteNhapUnit(NhapHangUnit unit) throws NotExistException, Exception {
        NhapHangData data = GlobalData.getNhapHangData();
        Vector<NhapHangUnit> duocPhams = data.getLichSuNhapHang();
        if (!duocPhams.contains(unit)) {
            throw new NotExistException();
        }
        duocPhams.remove(unit);
        writeData(FILEPATH, data);
    }

    public static void replaceNhapUnit(NhapHangUnit oldUnit, NhapHangUnit newUnit) throws NotExistException, Exception {
        NhapHangData data = GlobalData.getNhapHangData();
        Vector<NhapHangUnit> duocPhams = data.getLichSuNhapHang();
        if (!duocPhams.contains(oldUnit)) {
            throw new NotExistException();
        }
        duocPhams.set(duocPhams.indexOf(oldUnit), newUnit);
        writeData(FILEPATH, data);
    }

    public static void saveNhapHangData() throws Exception {
        NhapHangData data = GlobalData.getNhapHangData();
        writeData(FILEPATH, data);
    }

    private static void writeData(String filePath, Object obj) throws Exception {
        if (!new File(filePath).exists()) {
            createNhapHangData();
        }
        IOMaster.writeObject(filePath, obj);
    }

    public static boolean checkIfExist(NhapHangUnit unit) throws Exception {
        NhapHangData data = GlobalData.getNhapHangData();
        return data.getLichSuNhapHang().contains(unit);
    }
}
