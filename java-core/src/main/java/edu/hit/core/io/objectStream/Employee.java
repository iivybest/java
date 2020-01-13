package edu.hit.core.io.objectStream;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Employee</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年5月12日 - 下午4:53:29
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 6494465281377029725L;
    private int id;
    private String name;
    private int gender;
    transient private Date birthdate;


    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

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
     * @return the birthdate
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * @param birthdate the birthdate to set
     */
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.hashCode()
                + " ["
                + "id:" + this.getId()
                + ", name:" + this.getName()
                + ", gender:" + this.getGender()
                + ", birthdate:" + this.getBirthdate()
                + "]";
    }
}
