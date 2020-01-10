package edu.hit.adv.generic.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>GenericDao</p>
 *
 * @author miao.xl
 * @version 1.0
 * @date 2016年2月22日-下午12:22:50
 */
public interface GenericDao {

    public <T> void add(T t);

    public <T> void update(T t);

    public <T> T findById(Class<T> t, Serializable id);

    public <T> void delete(Class<T> t, Serializable id);

    public <T> List<T> find(Class<T> t, Map<String, Object> params);

}














