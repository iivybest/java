/**
 * <p>StudentDaoImpl</p>
 *
 * @author miao.xl
 * @date 2016年2月22日-下午12:34:23
 * @version 1.0
 */
package edu.hit.adv.generic.dao.dao.impl;

import edu.hit.adv.generic.dao.BaseDao;
import edu.hit.adv.generic.dao.dao.StudentDao;
import edu.hit.adv.generic.dao.domain.Student;

import java.util.List;


/**
 * <p>StudentDaoImpl</p>
 *
 *
 * @author miao.xl
 * @date 2016年2月22日-下午12:34:23
 * @version 1.0
 *
 */
public class StudentDaoImpl extends BaseDao implements StudentDao {

    @Override
    public List<Student> findPage(Student s, int start, int end) {
        return null;
    }

}
