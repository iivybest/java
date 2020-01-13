package edu.hit.core.collections.collection.model.comparator;

import edu.hit.core.collections.collection.model.Person;

import java.util.Comparator;

/**
 * <p>PersonNameComparator</p>
 * <p>Description : </p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2015年7月15日 - 上午9:44:55
 */
public class PersonNameComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        int temp = o1.getName().compareTo(o2.getName());
        return temp == 0 ? o1.getAge() - o2.getAge() : temp;
    }

}
