package edu.hit.adv.generic.dao.dao;

import edu.hit.adv.generic.dao.GenericDao;
import edu.hit.adv.generic.dao.domain.Student;

import java.util.List;


/**
 * <p>StudentDao</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年2月22日-下午12:32:05
 */
public interface StudentDao extends GenericDao {
    List<Student> findPage(Student s, int start, int end);
}
