package Validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    private final static String  TEN_MAY="\\w{8,15}";

    private final  static String TIEN_GIO="\\d+(.\\d)?";

    public final static String VN_NAME_REGEX="([A-ZÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ][a-zàáâãèéêìíòóôõùúăđĩũơưăạảấầẩẫậắằẳẵặẹẻẽềềểếễệỉịọỏốồổỗộớờởỡợụủứừđ]+\\s?)+";
    private final  static String SDT_REGEX="0\\d{9}";


    private final  static String EMAIL_REGEX="^\\w+(.?\\w+)+@gmail.com$";

    private final  static  String AGE_REGEX="\\d{2,3}";



    Pattern pattern;

    Matcher matcher;



    public boolean validateAge(String age){
        return age.matches(AGE_REGEX);
    }

    public  boolean validateEmail(String email){
        return email.matches(EMAIL_REGEX);
    }

    public boolean validateSdt(String sdt){
        return sdt.matches(SDT_REGEX);
    }

    public  boolean rName(String input) {
        return input.matches(VN_NAME_REGEX);
    }

    public boolean tenMay(String tenM){
        pattern=Pattern.compile(TEN_MAY);
        matcher=pattern.matcher(tenM);
        return matcher.matches();
    }
    public boolean tienGio(String tien){
        pattern=Pattern.compile(TIEN_GIO);
        matcher=pattern.matcher(tien);
        return matcher.matches();
    }

}
