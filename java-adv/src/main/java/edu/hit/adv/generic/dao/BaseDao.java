package edu.hit.adv.generic.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>BaseDao</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年2月22日-下午12:26:52
 */
public class BaseDao implements GenericDao {
    private GenericDao dao;

    {
        this.dao = new HibernateBaseDao();
    }

    @Override
    public <T> void add(T t) {
        this.dao.add(t);
    }

    @Override
    public <T> void update(T t) {
        this.dao.update(t);
    }

    @Override
    public <T> T findById(Class<T> t, Serializable id) {
        return this.dao.findById(t, id);
    }

    @Override
    public <T> void delete(Class<T> t, Serializable id) {
        this.dao.delete(t, id);
    }

    @Override
    public <T> List<T> find(Class<T> t, Map<String, Object> params) {
        return this.dao.find(t, params);
    }

    // Spring inject GenericDao
    public void setGenericDao(GenericDao dao) {
        this.dao = dao;
    }

}
