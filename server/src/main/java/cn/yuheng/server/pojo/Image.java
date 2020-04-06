package cn.yuheng.server.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * image
 *
 * @author
 */
@Data
public class Image implements Serializable {
    private Integer id;

    private byte[] image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    private static final long serialVersionUID = 1L;
}