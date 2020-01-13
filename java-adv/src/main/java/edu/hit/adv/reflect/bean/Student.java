package edu.hit.adv.reflect.bean;

/**
 * <p>Student</p>
 * <p>Description:</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2014年8月7日 上午10:16:20
 */
public class Student extends people implements SmartAwareAble {
    public String name;
    private String grade;
    private String address;
    private subClass sub = new subClass();
    private subClass2 sub2 = new subClass2();

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
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public class subClass {
        public subClass() {
            System.out.println("this is a public subClass in student");
        }
    }

    private class subClass2 {
        public subClass2() {
            System.out.println("this is a private subClass in student");
        }
    }

}
