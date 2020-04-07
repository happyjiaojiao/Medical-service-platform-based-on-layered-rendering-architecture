package cn.yuheng.server.controller;

import cn.yuheng.server.dao.ImageDao;
import cn.yuheng.server.pojo.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 王子陶
 * @version 1.0
 * @date 2020/4/6 下午5:46
 */
@RestController
public class ImageController {
    @Autowired
    ImageDao imageDao;

    /**
     * @param id       图片ID
     * @param response
     * @throws IOException
     */
    @GetMapping("/image/get/{id}.jpeg")
    public void getImage(@PathVariable("id") int id, HttpServletResponse response) throws IOException {
        Image image = imageDao.selectByPrimaryKey(id);
        if (image == null) {
            response.setStatus(404);
        } else {
            response.setContentType("image/jpeg");
            response.getOutputStream().write(image.getImage());
        }
    }
}
