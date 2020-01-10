package edu.hit.adv.generic.dao.domain;

import java.io.Serializable;

/**
 * <p>Student</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年2月18日-上午9:34:32
 */
public class Student implements Serializable {
    private static final long serialVersionUID = -5187738872503172300L;

    // 姓名
    private String name;
    // 地址
    private String addr;
    // 性别 - 0女1男
    private int gender;
    // 说明
    private String view;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the addr
     */
    public String getAddr() {
        return addr;
    }

    /**
     * @param addr the addr to set
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * @return the gender
     */
    public int getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * @return the view
     */
    public String getView() {
        return view;
    }

    /**
     * @param view the view to set
     */
    public void setView(String view) {
        this.view = view;
    }
}
