package team.gfr.phone.entity;

import java.io.Serializable;

/**
 * (TbPhone)实体类
 *
 * @author makejava
 * @since 2021-01-05 01:22:48
 */
public class TbPhone implements Serializable {
    private static final long serialVersionUID = 550129564004531631L;

    private Integer id;
    /**
     * 手机类型
     */
    private String type;

    private String name;

    private String image;
    /**
     * 手机介绍
     */
    private String intro;

    private Integer price;
    /**
     * 是否推荐1表示推荐0不推荐
     */
    private String recom;
    /**
     * 活动价
     */
    private Integer act;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRecom() {
        return recom;
    }

    public void setRecom(String recom) {
        this.recom = recom;
    }

    public Integer getAct() {
        return act;
    }

    public void setAct(Integer act) {
        this.act = act;
    }

}