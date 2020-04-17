package cn.yuheng.server.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/4/5 下午8:47
 */
public class PasswordUtil {
    public static boolean checkPassword(String inputPostPassword, long time, String databasePostedPassword) {
        return checkPassword(inputPostPassword, time, databasePostedPassword, 20000);
    }

    public static boolean checkPassword(String inputPostPassword, long time, String databasePostedPassword, long timeout) {
        //比对的密码格式(小端序)：MD5Hex(MD5Hex(password+password[0:6]))+long(time))
        //数据库密码字段：MD5Hex(password+password[0:6]))
        if (Math.abs(time - System.currentTimeMillis()) >= timeout) {
            return false;
        }
        String code = DigestUtils.md5Hex(databasePostedPassword + time);
        return inputPostPassword.equals(code);
    }

    public static boolean checkPassword(String inputPassword, String encryptedPassword) {
        return inputPassword.equals(encryptedPassword);
    }
}
