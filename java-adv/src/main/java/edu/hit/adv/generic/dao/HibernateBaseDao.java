package edu.hit.adv.generic.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>HibernateBaseDao</p>
 *
 * @author ivybest ivybestdev@163.com
 * @version 1.0
 * @date 2016年2月22日-下午12:28:55
 */
public class HibernateBaseDao implements GenericDao {

    @Override
    public <T> void add(T t) {
        System.out.println("add operation hibernate implemention...");
    }

    @Override
    public <T> void update(T t) {
        // TODO Auto-generated method stub

    }

    @Override
    public <T> T findById(Class<T> t, Serializable id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> void delete(Class<T> t, Serializable id) {
        // TODO Auto-generated method stub

    }

    @Override
    public <T> List<T> find(Class<T> t, Map<String, Object> params) {
        // TODO Auto-generated method stub
        return null;
    }


}
