
package unit.manager;

import exception.ExistException;
import exception.NotExistException;
import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import unit.data.DuocPhamData;
import unit.DuocPhamUnit;
import unit.GlobalData;
import worker.IOMaster;
import worker.MD5Encryption;
import worker.NoMarkText;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class DuocPhamDataManager {

    public static final String FILENAME = "_DuLieuDuocPham.data";
    public static final String FILEPATH = GlobalData.DATAFOLDER + File.separator + FILENAME;

    public static boolean isValidAccessInfo(String usename, String password) throws Exception {
        DuocPhamData data = GlobalData.getDuocPhamData();
        return data.getAccessInfo().equals(MD5Encryption.encrypt(usename.toLowerCase() + password));
    }

    public static DuocPhamData loadDuocPhamData() throws Exception {
        /*
         * Nerver do this for data file if (!new File(FILEPATH).exists()) {
         * createDuocPhamData();}
         */
        return (DuocPhamData) IOMaster.readObject(FILEPATH);
    }

    public static boolean checkIfExist(String tenThuoc, String nsx, String dvt) throws Exception {
        DuocPhamData data = GlobalData.getDuocPhamData();
        for (int i = 0; i < data.getDanhSachDuocPham().size(); i++) {
            if (NoMarkText.removeMark(data.getDanhSachDuocPham().get(i).getTenThuoc()).equalsIgnoreCase(NoMarkText.removeMark(tenThuoc))
                    && NoMarkText.removeMark(data.getDanhSachDuocPham().get(i).getNhaSanXuat()).equalsIgnoreCase(NoMarkText.removeMark(nsx))
                    && NoMarkText.removeMark(data.getDanhSachDuocPham().get(i).getDonViTinh()).equalsIgnoreCase(NoMarkText.removeMark(dvt))) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkIfExist(DuocPhamUnit dp) throws Exception {
        DuocPhamData data = GlobalData.getDuocPhamData();
        return data.getDanhSachDuocPham().contains(dp);
    }

    public static Vector<DuocPhamUnit> getDanhSachDuocPham(String select) throws Exception {
        DuocPhamData data = GlobalData.getDuocPhamData();
        if (select.equals("*")) {
            return data.getDanhSachDuocPham();
        }
        Vector<DuocPhamUnit> v = new Vector();
        for (int i = 0; i < data.getDanhSachDuocPham().size(); i++) {
            if (data.getDanhSachDuocPham().get(i).getTenThuoc().toLowerCase().contains(select.toLowerCase())) {
                v.add(data.getDanhSachDuocPham().get(i));
            }
        }
        return v;
    }

    public static Vector<DuocPhamUnit> getDanhSachDuocPhamLowerThan(DuocPhamData data) {
        Vector<DuocPhamUnit> v = new Vector();
        for (int i = 0; i < data.getDanhSachDuocPham().size(); i++) {
            if (data.getDanhSachDuocPham().get(i).getSoLuongHienCo() <= data.getDanhSachDuocPham().get(i).getSoLuongDuTruToiThieu()) {
                v.add(data.getDanhSachDuocPham().get(i));
            }
        }
        return v;
    }

    public static DuocPhamUnit getDuocPhamUnit(String duocPhamID) throws Exception {
        DuocPhamData data = GlobalData.getDuocPhamData();
        DuocPhamUnit unit2 = new DuocPhamUnit();
        unit2.setId(duocPhamID);
        return data.getDanhSachDuocPham().get(data.getDanhSachDuocPham().indexOf(unit2));
    }

    public static Vector<DuocPhamUnit> getDuocPhamUnitWithNameContains(String contain) throws Exception {
        DuocPhamData data = GlobalData.getDuocPhamData();
        Vector<DuocPhamUnit> v = new Vector();
        Vector<DuocPhamUnit> duocPhams = data.getDanhSachDuocPham();
        for (int i = 0; i < duocPhams.size(); i++) {
            if (duocPhams.get(i).getTenThuoc().toLowerCase().contains(contain.toLowerCase())) {
                v.add(duocPhams.get(i));
            }
        }
        return v;
    }

    public static void addDuocPhamUnit(DuocPhamUnit unit) throws ExistException, Exception {
        DuocPhamData data = GlobalData.getDuocPhamData();
        Vector<DuocPhamUnit> duocPhams = data.getDanhSachDuocPham();
        if (duocPhams.contains(unit) || checkIfExist(unit.getTenThuoc(), unit.getNhaSanXuat(), unit.getDonViTinh())) {
            throw new ExistException();
        }
        duocPhams.add(unit);
        IOMaster.writeObject(FILEPATH, data);
    }

    public static void deleteDuocPhamUnit(DuocPhamUnit unit) throws NotExistException, Exception {
        DuocPhamData data = GlobalData.getDuocPhamData();
        Vector<DuocPhamUnit> duocPhams = data.getDanhSachDuocPham();
        if (!duocPhams.contains(unit)) {
            throw new NotExistException();
        }
        duocPhams.remove(unit);
        IOMaster.writeObject(FILEPATH, data);
    }

    public static void replaceDuocPhamUnit(DuocPhamUnit oldUnit, DuocPhamUnit newUnit) throws NotExistException, Exception {
        DuocPhamData data = GlobalData.getDuocPhamData();
        Vector<DuocPhamUnit> duocPhams = data.getDanhSachDuocPham();
        if (!duocPhams.contains(oldUnit)) {
            throw new NotExistException();
        }
        duocPhams.set(duocPhams.indexOf(oldUnit), newUnit);
        IOMaster.writeObject(FILEPATH, data);
    }

    public static void saveDuocPhamData() throws Exception {
        DuocPhamData data = GlobalData.getDuocPhamData();
        IOMaster.writeObject(FILEPATH, data);
    }
}
