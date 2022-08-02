
package worker;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Thanh Nhan <JackV at congdongjava.com>
 */
public class MD5Encryption {

    public static String encrypt(String text) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(text.getBytes());
            BigInteger dis = new BigInteger(1, md5.digest());
            //byte []dis=md5.digest();
            text = dis.toString(16);
        } catch (NoSuchAlgorithmException e) {
        }
        return text;
    }
    public static void main(String[] args) {
        System.out.println(encrypt("abc"));
    }
}
