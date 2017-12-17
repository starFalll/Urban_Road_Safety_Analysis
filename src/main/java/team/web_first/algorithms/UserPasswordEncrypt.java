package team.web_first.algorithms;

import org.apache.commons.codec.binary.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserPasswordEncrypt {
    public static String encrypt(String userOriginalPassword) {
        String userEncryptPassword="";
        try {
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            userEncryptPassword = Base64.encodeBase64String(mDigest.digest(userOriginalPassword.getBytes()));
        } catch (NoSuchAlgorithmException e1) {
            // TODO 自动生成的 catch 块
            e1.printStackTrace();
        }finally {
            return userEncryptPassword;
        }
    }
}
