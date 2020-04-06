package cn.yuheng.server.controller;

import cn.yuheng.server.dao.UserDao;
import cn.yuheng.server.pojo.User;
import cn.yuheng.server.util.PasswordUtil;
import cn.yuheng.server.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private UserDao userDao;

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User getUser(@RequestParam("id") Integer id) {
        return userDao.selectByID(id);
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public Map<String, String> createUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        int statusCode = userDao.insertSelective(user);
        return ResponseUtil.responseSuccessOrNot(statusCode == 0);
    }

    @RequestMapping(value = "/loginEmail", method = RequestMethod.POST)
    public User login(HttpSession session, @RequestParam("email") String email, @RequestParam("password") String password, Long time) {
        User user = userDao.selectByEmail(email);
        if (PasswordUtil.checkPassword(password, time, user.getPassword())) {
            session.setAttribute("user", user);
            return user;
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(HttpSession session) {
        session.removeAttribute("user");
    }
}
