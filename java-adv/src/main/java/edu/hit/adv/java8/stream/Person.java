package edu.hit.adv.java8.stream;

import java.io.Serializable;

/**
 * @ClassName: Person
 * @author: ivybest imiaodev@163.com
 * @date: 2019年3月15日 下午1:21:32
 * : TODO(用一句话描述该文件做什么)
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 6434517118255361620L;

    private String name;
    private String addr;

    public static Person newInstance() {
        Person person = new Person();
//		System.out.println("====> person-" + person.hashCode() );
        return person;
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
    public Person setName(String name) {
        this.name = name;
        return this;
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
    public Person setAddr(String addr) {
        this.addr = addr;
        return this;
    }


}
