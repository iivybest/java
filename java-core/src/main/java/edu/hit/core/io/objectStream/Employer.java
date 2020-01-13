package edu.hit.core.io.objectStream;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

/**
 * <p>Employer</p>
 * <p>Description : 实现Externalizable，可自行定义序列化与反序列化过程</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年5月12日 - 下午6:49:41
 */
public class Employer implements Externalizable {
    private int id;
    private String name;
    private int gender;
    transient private Date birthdate;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(this.id);
        out.writeChars(this.name);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {
        this.id = in.readInt();
        int len = 9;
        char[] ca = new char[len];
        for (int i = 0; i < len; i++) {
            ca[i] = in.readChar();
        }
        this.name = new String(ca);
    }

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
