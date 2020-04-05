package cn.yuheng.server.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/4/5 下午8:47
 */
public class PasswordUtil {
    public static boolean checkPassword(String userPostPassword, long time, String databasePostedPassword) {
        //比对的密码格式(小端序)：MD5Hex(MD5Hex(Hex(password+"yuheng"))+long(time))
        //数据库密码字段：MD5(Hex(password+"yuheng"))
        if (Math.abs(time - System.currentTimeMillis()) >= 60000) {
            return false;
        }
        String code = DigestUtils.md5Hex(databasePostedPassword + time);
        return userPostPassword.equals(code);
    }
}
