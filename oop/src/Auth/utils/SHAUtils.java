package Auth.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @desc: 加密工具类
 * @author: AruNi_Lu
 * @date: 2023/3/22
 */
public class SHAUtils {

    public static String SHA(String msg) {
        MessageDigest sha256 = null;
        try {
            sha256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        sha256.update(msg.getBytes());
        byte[] sha256Bin = sha256.digest();
        byte[] base64 = Base64.getEncoder().encode(sha256Bin);
        return new String(base64).replace("=","eq");
    }

}
