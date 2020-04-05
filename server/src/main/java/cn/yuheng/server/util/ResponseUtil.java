package cn.yuheng.server.util;

import java.util.Map;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/4/5 下午10:03
 */
public class ResponseUtil {
    public static Map<String, String> responseSuccessOrNot(boolean success) {
        return Map.of("result", success ? "success" : "fail");
    }
}
