
package unit.manager;

import java.io.File;
import unit.GlobalData;
import unit.setting.ApplicationSetting;
import worker.IOMaster;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class ApplicationSettingManager {

    public static final String SETTINGFILE = GlobalData.DATAFOLDER + File.separator + "_ApplicationSetting.set";

    public static void saveApplicationSetting() throws Exception {
        IOMaster.writeObject(SETTINGFILE, GlobalData.getApplicationSetting());
    }

    public static ApplicationSetting loadApplicationSetting() throws Exception {
        return (ApplicationSetting) IOMaster.readObject(SETTINGFILE);
    }

    /*
     */
    public static int getPanelNhapHangDividerLocation() throws Exception {
        ApplicationSetting as = GlobalData.getApplicationSetting();
        return as.getPanelNhapHangDividerLocation();
    }

    public static void setPanelNhapHangDividerLocation(int PanelNhapHangDividerLocation) throws Exception {
        ApplicationSetting as = GlobalData.getApplicationSetting();
        as.setPanelNhapHangDividerLocation(PanelNhapHangDividerLocation);
        saveApplicationSetting();
    }

    public static int getPanelXuatHangDividerLocation() throws Exception {
        ApplicationSetting as = GlobalData.getApplicationSetting();
        return as.getPanelXuatHangDividerLocation();
    }

    public static void setPanelXuatHangDividerLocation(int PanelXuatHangDividerLocation) throws Exception {
        ApplicationSetting as = GlobalData.getApplicationSetting();
        as.setPanelXuatHangDividerLocation(PanelXuatHangDividerLocation);
        saveApplicationSetting();
    }

    public static int getPanelXuatToaThuocDivider1Location() throws Exception {
        ApplicationSetting as = GlobalData.getApplicationSetting();
        return as.getPanelXuatToaThuocDivider1Location();
    }

    public static void setPanelXuatToaThuocDivider1Location(int PanelXuatToaThuocDivider1Location) throws Exception {
        ApplicationSetting as = GlobalData.getApplicationSetting();
        as.setPanelXuatToaThuocDivider1Location(PanelXuatToaThuocDivider1Location);
        saveApplicationSetting();
    }

    public static int getPanelXuatToaThuocDivider2Location() throws Exception {
        ApplicationSetting as = GlobalData.getApplicationSetting();
        return as.getPanelXuatToaThuocDivider2Location();
    }

    public static void setPanelXuatToaThuocDivider2Location(int PanelXuatToaThuocDivider2Location) throws Exception {
        ApplicationSetting as = GlobalData.getApplicationSetting();
        as.setPanelXuatToaThuocDivider2Location(PanelXuatToaThuocDivider2Location);
        saveApplicationSetting();
    }

    public static int getPanelThemXoaSuaDuocPhamDividerLocation() throws Exception {
        ApplicationSetting as = GlobalData.getApplicationSetting();
        return as.getPanelThemXoaSuaDuocPhamDividerLocation();
    }

    public static void setPanelThemXoaSuaDuocPhamDividerLocation(int panelThemXoaSuaDuocPhamDividerLocation) throws Exception {
        ApplicationSetting as = GlobalData.getApplicationSetting();
        as.setPanelThemXoaSuaDuocPhamDividerLocation(panelThemXoaSuaDuocPhamDividerLocation);
        saveApplicationSetting();
    }
}
