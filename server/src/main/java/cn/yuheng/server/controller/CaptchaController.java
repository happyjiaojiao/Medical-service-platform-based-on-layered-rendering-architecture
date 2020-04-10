package cn.yuheng.server.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/4/10 下午3:19
 */
@RestController
public class CaptchaController {
    @Autowired
    @Setter
    private Producer captchaProducer;

    @GetMapping("/api/captcha/get")
    public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String text = captchaProducer.createText();
        HttpSession session = request.getSession();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        getImage(response, text);
    }

    public void getImage(HttpServletResponse response, String text) throws IOException {
        ServletOutputStream jpgOutputStream = response.getOutputStream();
        BufferedImage challenge = captchaProducer.createImage(text);
        ImageIO.write(challenge, "jpg", jpgOutputStream);
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        jpgOutputStream.close();
    }
}
