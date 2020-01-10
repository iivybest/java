package edu.hit.core.collections.collection.model;

import edu.hit.core.collections.collection.model.comparator.PersonAgeComparator;

import java.io.Serializable;
import java.util.Comparator;

/**
 * <p>Person</p>
 * <p>Description : </p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2015年7月13日 - 下午6:51:55
 */
public class Person implements Comparable<Person>, Serializable {
    private static final long serialVersionUID = 4225406109244384917L;

    private String name;
    private int age;

    private Comparator<Person> comparator;

    public Person() {
        super();
        this.comparator = new PersonAgeComparator();
    }

    public Person(String name, int age) {
        this();
        this.name = name;
        this.age = age;
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
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        int hash = this.name.hashCode() + this.age * 25;
//		System.out.println("--------> " + hash);
        return hash;
//		return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        boolean equal = false;
        if (obj instanceof Person) {
            Person temp = (Person) obj;
            equal = this.name.equals(temp.getName()) && this.age == temp.getAge();
        }
        return equal;
    }

    @Override
    public String toString() {
        return "[name : " + this.name + ", " + "age : " + this.age + "]";
    }

    @Override
    public int compareTo(Person o) {
        return this.comparator.compare(this, o);
    }

}
