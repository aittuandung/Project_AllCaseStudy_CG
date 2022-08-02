package unit;

import unit.data.XuatHangData;
import unit.data.DuocPhamData;
import unit.data.NhapHangData;
import unit.data.ToaThuocData;
import unit.manager.*;
import unit.setting.NhapHangSetting;
import unit.setting.XuatHangSetting;
import unit.setting.ApplicationSetting;
import unit.setting.ToaThuocSetting;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class GlobalData {

    public static final String DATAFOLDER = "DuLieu";
    private static DuocPhamData duocPhamData;

    public static DuocPhamData getDuocPhamData() throws Exception {
        if (duocPhamData == null) {
            duocPhamData = DuocPhamDataManager.loadDuocPhamData();
        }
        return duocPhamData;
    }
    private static NhapHangSetting nhapHangSetting;

    public static NhapHangSetting getNhapHangSetting() throws Exception {
        if (nhapHangSetting == null) {
            nhapHangSetting = NhapHangDataManager.loadNhapHangSetting();
        }
        return nhapHangSetting;
    }

    public static NhapHangData getNhapHangData() throws Exception {
        return NhapHangDataManager.loadNhapHangData();
    }
    private static XuatHangSetting xuatHangSetting;

    public static XuatHangSetting getXuatHangSetting() throws Exception {
        if (xuatHangSetting == null) {
            xuatHangSetting = XuatHangDataManager.loadXuatHangSetting();
        }
        return xuatHangSetting;
    }

    public static XuatHangData getXuatHangData() throws Exception {
        return XuatHangDataManager.loadXuatHangData();
    }
    private static ToaThuocSetting toaThuocSetting;

    public static ToaThuocSetting getToaThuocSetting() throws Exception {
        if (toaThuocSetting == null) {
            toaThuocSetting = ToaThuocDataManager.loadToaThuocSetting();
        }
        return toaThuocSetting;
    }

    public static ToaThuocData getToaThuocData() throws Exception {
        return ToaThuocDataManager.loadToaThuocData();
    }

    private static ApplicationSetting applicationSetting;
    
    public static ApplicationSetting getApplicationSetting() throws Exception {
        if (applicationSetting == null) {
            applicationSetting = ApplicationSettingManager.loadApplicationSetting();
        }
        return applicationSetting;
    }
}
