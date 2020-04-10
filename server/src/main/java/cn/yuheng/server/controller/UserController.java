package cn.yuheng.server.controller;

import cn.yuheng.server.pojo.User;
import cn.yuheng.server.server.LoginHistoryServer;
import cn.yuheng.server.server.UserServer;
import cn.yuheng.server.util.PasswordUtil;
import cn.yuheng.server.util.Result;
import com.google.code.kaptcha.Constants;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/4/5 下午7:18
 */
@RestController
public class UserController {

    @Autowired
    @Setter
    private UserServer userServer;

    @Autowired
    @Setter
    private LoginHistoryServer loginHistoryServer;

    @GetMapping(value = "/api/user/get")
    public Result<User> getUser(Integer id) {
        User user = userServer.getByID(id);
        return Result.successOrFail(user);
    }

    @GetMapping(value = "/api/user/get/by-email")
    public Result<User> getUserByEmail(String email) {
        User user = userServer.getByEmail(email);
        return Result.successOrFail(user);
    }

    @PostMapping("/api/user/set-password/by-email")
    public Result setPassword(HttpSession session, @RequestBody Map<String, String> userJson) {
        String email = userJson.get("email");
        String password = userJson.get("password");
        String checkCode = userJson.get("checkCode");
        Date mailSendTinme = (Date) session.getAttribute("email-send-time");
        Date now = new Date();
        if (password == null || email == null || checkCode == null) {
            return Result.fail("参数错误");
        }
        String rightCheckCode = (String) session.getAttribute("checkCode");
        if (rightCheckCode == null) {
            return Result.fail("请获取邮箱验证码");
        }
        if (now.getTime() - mailSendTinme.getTime() > 600000) {
            return Result.fail("邮箱验证码已过期");
        }
        if (!checkCode.equals(rightCheckCode)) {
            return Result.fail("邮箱验证码错误");
        }
        if (!email.equals(session.getAttribute("email"))) {
            return Result.fail("邮箱错误");
        }
        User user = userServer.getByEmail(email);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        User userClone = new User();
        userClone.setId(user.getId());
        userClone.setPassword(password);
        userServer.updateSelective(userClone);
        return Result.success();
    }

    @PostMapping(value = "/api/user/create/by-email")
    public Result createUser(HttpSession session, @RequestBody Map<String, String> userJson) {
        String email = userJson.get("email");
        String password = userJson.get("password");
        String captcha = userJson.get("captcha");
        if (email == null || password == null) {
            return Result.fail("参数错误");
        }
        if (captcha != session.getAttribute(Constants.KAPTCHA_SESSION_KEY)) {
            return Result.fail("验证码错误");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        if (null != userServer.getByEmail(email)) {
            return Result.fail("邮箱账号已被注册");
        }
        return Result.successOrFail(userServer.addUser(user));
    }

    @PostMapping(value = "/api/user/login/by-email")
    public Result<User> login(HttpSession session, String email, String password, Long time) {
        User user = userServer.getByEmail(email);
        if (user == null) {
            return Result.fail();
        }
        if (PasswordUtil.checkPassword(password, time, user.getPassword())) {
            login(session, user);
            user.setPassword(null);
            return Result.success(user);
        } else {
            return Result.fail();
        }
    }

    public void login(HttpSession session, User user) {
        session.setAttribute("user", user);
        loginHistoryServer.addLoginHistory(user.getId());
    }

    @PostMapping(value = "/api/user/logout")
    public Result logout(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return Result.fail("未登录");
        }
        session.removeAttribute("user");
        return Result.success();
    }
}
