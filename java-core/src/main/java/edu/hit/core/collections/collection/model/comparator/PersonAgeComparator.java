package edu.hit.core.collections.collection.model.comparator;

import edu.hit.core.collections.collection.model.Person;

import java.util.Comparator;

/**
 * <p>PersonAgeComparator</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年7月15日 - 上午9:44:55
 */
public class PersonAgeComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        int age1 = o1.getAge(), age2 = o2.getAge();
        // 顺序排列，逆序排列反之
//		if(age1 > age2) return 1;
//		else if (age1 < age2) return -1;
//		return 0;

        // 策略二
//		return age1 - age2;

        // 策略三，岁数相同时，按name排序，否则会被集合当成同一对象
        int temp = age1 - age2;
        return temp == 0 ? o1.getName().compareTo(o2.getName()) : temp;

    }

}
