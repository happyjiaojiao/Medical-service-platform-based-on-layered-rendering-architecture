package cn.yuheng.server.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * user
 *
 * @author
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String name;

    private Object sex;

    private String email;

    private String wechatId;

    private Integer headPortraitId;

    private String password;

    private Date registrationTime;

    private String phone;

    private Object type;

    private String status;

    private static final long serialVersionUID = 1L;
}