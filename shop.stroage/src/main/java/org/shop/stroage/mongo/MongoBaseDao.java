package org.shop.stroage.mongo;

import net.sf.json.JSONArray;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;

/**
 * Created by VIC on 2016-05-11.
 */
public interface MongoBaseDao {

    <T> T findById(Class<T> entityClass, String id);

    <T> List<T> findQuery(Class<T> entityClass, Query query);

    JSONArray findByKey(String key,String value,String collectionName);

    <T> List<T> findAll(Class<T> entityClass);

    <T> T findByAny(Class<T> entityClass, Query query);

    void remove(Object obj);

    void add(Object obj);

    void saveOrUpdate(Object obj);

}
