/**
 * <p>Test</p>
 *
 * @author miao.xl
 * @date 2016年2月22日-下午12:35:51
 * @version 1.0
 */
package edu.hit.adv.generic.dao;

import edu.hit.adv.generic.dao.dao.StudentDao;
import edu.hit.adv.generic.dao.dao.impl.StudentDaoImpl;
import edu.hit.adv.generic.dao.domain.Student;
import org.junit.Before;
import org.junit.Test;


/**
 * <p>Test</p>
 *
 *
 * @author miao.xl
 * @date 2016年2月22日-下午12:35:51
 * @version 1.0
 *
 */
public class StudentDaoTest {
    private StudentDao dao;

    @Before
    public void setup() {
        this.dao = new StudentDaoImpl();
    }

    @Test
    public void testAdd() {
        Student s = new Student();
        this.dao.add(s);
    }


}
