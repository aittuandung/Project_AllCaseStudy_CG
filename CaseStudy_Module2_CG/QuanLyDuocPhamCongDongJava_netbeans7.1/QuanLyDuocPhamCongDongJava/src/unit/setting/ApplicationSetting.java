
package unit.setting;

import java.io.Serializable;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class ApplicationSetting implements Serializable{
    private int panelThemXoaSuaDuocPhamDividerLocation = 400;
    private int panelXuatHangDividerLocation = 500;
    private int panelNhapHangDividerLocation = 500;
    private int panelXuatToaThuocDivider1Location = 400;
    private int panelXuatToaThuocDivider2Location = 400;

    public int getPanelNhapHangDividerLocation() {
        return panelNhapHangDividerLocation;
    }

    public void setPanelNhapHangDividerLocation(int panelNhapHangDividerLocation) {
        this.panelNhapHangDividerLocation = panelNhapHangDividerLocation;
    }

    public int getPanelXuatHangDividerLocation() {
        return panelXuatHangDividerLocation;
    }

    public void setPanelXuatHangDividerLocation(int panelXuatHangDividerLocation) {
        this.panelXuatHangDividerLocation = panelXuatHangDividerLocation;
    }

    public int getPanelXuatToaThuocDivider1Location() {
        return panelXuatToaThuocDivider1Location;
    }

    public void setPanelXuatToaThuocDivider1Location(int panelXuatToaThuocDivider1Location) {
        this.panelXuatToaThuocDivider1Location = panelXuatToaThuocDivider1Location;
    }

    public int getPanelXuatToaThuocDivider2Location() {
        return panelXuatToaThuocDivider2Location;
    }

    public void setPanelXuatToaThuocDivider2Location(int panelXuatToaThuocDivider2Location) {
        this.panelXuatToaThuocDivider2Location = panelXuatToaThuocDivider2Location;
    }

    public int getPanelThemXoaSuaDuocPhamDividerLocation() {
        return panelThemXoaSuaDuocPhamDividerLocation;
    }

    public void setPanelThemXoaSuaDuocPhamDividerLocation(int panelThemXoaSuaDuocPhamDividerLocation) {
        this.panelThemXoaSuaDuocPhamDividerLocation = panelThemXoaSuaDuocPhamDividerLocation;
    }

    
}
