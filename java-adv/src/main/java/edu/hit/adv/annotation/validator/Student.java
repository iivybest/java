package edu.hit.adv.annotation.validator;

import edu.hit.adv.annotation.validator.annotations.LengthRange;
import edu.hit.adv.annotation.validator.annotations.Required;
import edu.hit.adv.annotation.validator.annotations.Validator;
import edu.hit.adv.annotation.validator.annotations.ValueRange;

import java.io.Serializable;

/**
 * Student
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2019/12/27 15:48
 */
public class Student implements Serializable {
    private static final long serialVersionUID = -5187738872503172300L;

    /**
     * 姓名
     */
    @LengthRange(min = 3, max = 10)
    private String name;
    /**
     * 地址
     */
    @Required
    private String addr;
    /**
     * 性别 - 0女 1男
     */
    @ValueRange({"0", "1"})
    private int gender;
    /**
     * 说明
     * 提供用户自定义的复杂的复合型校验
     */
    @Validator({"MyValidator", "MyExtValidator"})
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
