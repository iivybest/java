package edu.hit.adv.reflect.bean;

/**
 * <p>people</p>
 * <p>Description:</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2014年8月7日 上午10:17:50
 */
public class people implements PowerfulAwareAble {

    public String gender;
    private String height;
    private String weight;

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the height
     */
    public String getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * @return the weight
     */
    public String getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(String weight) {
        this.weight = weight;
    }

}
