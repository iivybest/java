package edu.hit.adv.rmi.demo.api;

import java.io.Serializable;

/**
 * <p>ClubEntity</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2014年11月4日 - 下午1:07:40
 */
public class ClubEntity implements Serializable {
    private static final long serialVersionUID = 474626111112862941L;

    private String id;
    private String name;

    public static ClubEntity newInstance() {
        return new ClubEntity();
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public ClubEntity setId(String id) {
        this.id = id;
        return this;
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
    public ClubEntity setName(String name) {
        this.name = name;
        return this;
    }

}
