package com.bawei.gaochenkai20190611.bean;

/**
 * @Auther: 高晨凯
 * @Date: 2019/6/11 09:41:15
 * @Description:  封装接口数据
 */
public class JsonBean {
    private String profile_image;
    private String theme_name;
    private String passtime;
    private String text;

    public JsonBean(String profile_image, String theme_name, String passtime, String text) {
        this.profile_image = profile_image;
        this.theme_name = theme_name;
        this.passtime = passtime;
        this.text = text;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getTheme_name() {
        return theme_name;
    }

    public void setTheme_name(String theme_name) {
        this.theme_name = theme_name;
    }

    public String getPasstime() {
        return passtime;
    }

    public void setPasstime(String passtime) {
        this.passtime = passtime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "JsonBean{" +
                "profile_image='" + profile_image + '\'' +
                ", theme_name='" + theme_name + '\'' +
                ", passtime='" + passtime + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
