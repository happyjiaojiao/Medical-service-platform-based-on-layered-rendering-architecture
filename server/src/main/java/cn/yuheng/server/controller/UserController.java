package cn.yuheng.server.controller;

import cn.yuheng.server.pojo.User;
import cn.yuheng.server.server.LoginHistoryServer;
import cn.yuheng.server.server.UserServer;
import cn.yuheng.server.util.PasswordUtil;
import cn.yuheng.server.util.Result;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    public Result<User> getUser(@RequestParam("id") Integer id) {
        User user = userServer.getByID(id);
        return Result.successOrFail(user);
    }

    @GetMapping(value = "/api/user/get/by-email")
    public Result<User> getUserByEmail(@RequestParam("email") String email) {
        User user = userServer.getByEmail(email);
        return Result.successOrFail(user);
    }

    @PostMapping(value = "/api/user/create/by-email")
    public Result createUser(@RequestBody Map<String, String> userJson) {
        String email = userJson.get("email");
        String password = userJson.get("password");
        if (email == null || password == null) {
            return Result.fail(null, "参数错误");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        if (null != userServer.getByEmail(email)) {
            return Result.fail(null, "邮箱账号已被注册");
        }
        return Result.successOrFail(userServer.addUser(user));
    }

    @PostMapping(value = "/api/user/login/by-email")
    public Result<User> login(HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password, Long time) {
        User user = userServer.getByEmail(email);
        if (user == null) {
            return Result.fail();
        }
        if (PasswordUtil.checkPassword(password, time, user.getPassword())) {
            login(session, user);
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
            return Result.fail(null, "未登录");
        }
        session.removeAttribute("user");
        return Result.success();
    }
}
