package org.shop.stroage.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import net.sf.json.JSONArray;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import java.util.Collection;
import java.util.List;

/**
 *
 * Mongo base dao internal
 * Created by VIC on 2016-05-10.
 */
public class MongoBaseDaoImpl implements MongoBaseDao {

    private MongoTemplate mongoTemplate;


    public MongoBaseDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 根据主键id返回对象
     *
     * @param id
     *            唯一标识
     * @return T 对象
     */
    public <T> T findById(Class<T> entityClass, String id) {
        return this.mongoTemplate.findById(id, entityClass);
    }

    /**
     * 根据类获取全部的对象列表
     *
     * @param entityClass
     *            返回类型
     * @return List<T> 返回对象列表
     */
    public <T> List<T> findAll(Class<T> entityClass) {
        return this.mongoTemplate.findAll(entityClass);
    }

    public <T> List<T> findQuery(Class<T> entityClass,Query query){
        return this.mongoTemplate.find(query, entityClass);
    }

    public JSONArray findByKey(String key, String value, String collectionName) {

        DBCollection dbCollection = this.mongoTemplate.getCollection(collectionName);
        DBObject dbObject = new BasicDBObject(key, value);
        DBCursor dbCursor = dbCollection.find(dbObject);
        DBObject findResult = null;
        while (dbCursor.hasNext()){
            findResult = dbCursor.next();
        }
        return null != findResult ? JSONArray.fromObject(findResult.get(collectionName)) : new JSONArray();
    }

    public <T> T findByAny(Class<T> entityClass,Query query){
        return this.mongoTemplate.findOne(query, entityClass);
    }

    /**
     * 删除一个对象
     *
     * @param obj
     *            要删除的Mongo对象
     */
    public void remove(Object obj) {
        this.mongoTemplate.remove(obj);
    }

    /**
     * 添加对象
     *
     * @param obj
     *            要添加的Mongo对象
     */
    public void add(Object obj) {
        this.mongoTemplate.insert(obj);

    }

    /**
     * 修改对象
     *
     * @param obj
     *            要修改的Mongo对象
     */
    public void saveOrUpdate(Object obj) {
        this.mongoTemplate.save(obj);
    }

    /**
     *
     * @param entityClass
     *            查询对象
     * @param query
     *            查询条件
     * @return
     */
    public <T> Long count(Class<T> entityClass, Query query) {
        return this.mongoTemplate.count(query, entityClass);
    }

    /**
     * 批量插入
     * @param entityClass 对象类
     * @param collection  要插入的对象集合
     */
    public <T> void addCollection(Class<T> entityClass, Collection<T> collection){
        this.mongoTemplate.insert(collection, entityClass);
    }
}
