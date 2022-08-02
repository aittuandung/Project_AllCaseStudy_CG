
package worker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 *
 * @author VoongThuNhan <luyenlaptrinh.com>
 */
public class DateTimeMaster {

    public static String getHour() {
        Calendar cal = new GregorianCalendar();
        return cal.get(cal.HOUR_OF_DAY) < 10 ? "0" + cal.get(cal.HOUR_OF_DAY) : "" + cal.get(cal.HOUR_OF_DAY);
    }

    public static String getMinute() {
        Calendar cal = new GregorianCalendar();
        return cal.get(cal.MINUTE) < 10 ? "0" + cal.get(cal.MINUTE) : "" + cal.get(cal.MINUTE);
    }

    public static String getSecond() {
        Calendar cal = new GregorianCalendar();
        return cal.get(cal.SECOND) < 10 ? "0" + cal.get(cal.SECOND) : "" + cal.get(cal.SECOND);
    }

    public static String getDate() {
        Calendar cal = new GregorianCalendar();
        return cal.get(cal.DAY_OF_MONTH) < 10 ? "0" + cal.get(cal.DAY_OF_MONTH) : "" + cal.get(cal.DAY_OF_MONTH);
    }

    public static String getMonth() {
        Calendar cal = new GregorianCalendar();
        return (1 + cal.get(cal.MONTH)) < 10 ? "0" + (1 + cal.get(cal.MONTH)) : "" + (1 + cal.get(cal.MONTH));
    }

    public static String getYear() {
        Calendar cal = new GregorianCalendar();
        return cal.get(cal.YEAR) + "";
    }

    public static String getSecondAndMilisecond() {
        Calendar cal = new GregorianCalendar();
        return cal.get(cal.SECOND) + "::" + cal.get(cal.MILLISECOND);
    }

    public static String getMinuteAndSecondAndMilisecond() {
        Calendar cal = new GregorianCalendar();
        return cal.get(cal.MINUTE) + "::" + cal.get(cal.SECOND) + "::" + cal.get(cal.MILLISECOND);
    }

    public static String getNgayGioHienTaiCachBoiDauCham() {
        return getDate() + "." + getMonth() + "." + getYear() + "-" + getHour() + "." + getMinute();
    }

    public static String getGioPhutHienTaiCachBoiDauCham() {
        return getHour() + "h." + getMinute() + "min";
    }

    public static String getGioPhutGiayHienTaiCachBoiDauHaiCham() {
        return getHour() + ":" + getMinute() + ":" + getSecond();
    }
    public static String getGioPhutGiayHienTaiCachBoiDauCham() {
        return getHour() + "." + getMinute() + "." + getSecond();
    }

    public static String getNgayGioHienTai() {
        return getHour() + ":" + getMinute() + "-" + getDate() + "/" + getMonth() + "/" + getYear();
    }

    public static String getNgayThangHienTai() {
        return getDate() + "/" + getMonth() + "/" + getYear();
    }

    public static String getNgayThangHienTaiCachBoiDauCham() {
        return getDate() + "." + getMonth() + "." + getYear();
    }

    public static String getToday() {
        return getYear() + "-" + getMonth() + "-" + getDate();
    }

    public static String getNgayThangHienTaiCachBoiDauGach() {
        return getDate() + "-" + getMonth() + "-" + getYear();
    }

    public static String getNgayHienTai() {
        return getDate() + "";
    }

    public static String getThangTiepTheo() {
        int iThang = Integer.parseInt(getMonth());
        int iNam = Integer.parseInt(getYear());

        if (iThang + 1 > 12) {
            iThang = 1;
            iNam += 1;
        } else {
            iThang += 1;
        }

        return getDate() + "/" + iThang + "/" + iNam;
    }

    public static boolean checkThoiGian(String time, boolean bIgnoreEmpty) {
        return checkTime(time, bIgnoreEmpty);
    }

    public static boolean checkThoiGian(String time) {
        return checkTime(time, false);
    }

    public static boolean checkTime(String time, boolean bIgnoreEmpty) {
        if (bIgnoreEmpty && time.length() == 0) {
            return true;
        }

        if (time.length() > 16) {
            return false;
        }
        StringTokenizer st = new StringTokenizer(time, ":-/");
        if (st.countTokens() != 5) {
            return false;
        }
        for (int i = 0; i < 5; i++) {
            String sE = st.nextToken();
            if (i == 0 && sE.length() > 2 || i == 0 && sE.length() < 2 || i == 1 && sE.length() > 2 || i == 1 && sE.length() < 2 || i == 2 && sE.length() > 2 || i == 2 && sE.length() < 2 || i == 3 && sE.length() > 2 || i == 3 && sE.length() < 2 || i == 4 && sE.length() > 4 || i == 4 && sE.length() < 4) {
                return false;
            }
            try {
                Integer.parseInt(sE);
            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkNgayThang(String time, boolean bIgnoreEmpty) {
        return checkNgayThangs(time, bIgnoreEmpty);
    }

    public static boolean checkNgayThang(String time) {
        return checkNgayThangs(time, false);
    }

    public static boolean checkNgayThangs(String time, boolean bIgnoreEmpty) {
        if (bIgnoreEmpty && time.length() == 0) {
            return true;
        }

        if (time.length() > 10) {
            return false;
        }
        StringTokenizer st = new StringTokenizer(time, "/");
        if (st.countTokens() != 3) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            String sE = st.nextToken();
            if (i == 0 && sE.length() > 2 || i == 0 && sE.length() < 2 || i == 1 && sE.length() > 2 || i == 1 && sE.length() < 2 || i == 2 && sE.length() > 4 || i == 2 && sE.length() < 4) {
                return false;
            }
            try {
                Integer.parseInt(sE);
            } catch (Exception e) {
                return false;
            }
        }

        return true;
    }
    final static String DATE_FORMAT = "dd/MM/yyyy";

    public static Date convertToDate(String date) throws ParseException {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        df.setLenient(false);
        return df.parse(date);
    }
}
