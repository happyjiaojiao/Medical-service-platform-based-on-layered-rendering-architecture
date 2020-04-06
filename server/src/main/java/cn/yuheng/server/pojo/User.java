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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getSex() {
        return sex;
    }

    public void setSex(Object sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWechatId() {
        return wechatId;
    }

    public void setWechatId(String wechatId) {
        this.wechatId = wechatId;
    }

    public Integer getHeadPortraitId() {
        return headPortraitId;
    }

    public void setHeadPortraitId(Integer headPortraitId) {
        this.headPortraitId = headPortraitId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    private Object type;

    private String status;

    private static final long serialVersionUID = 1L;
}