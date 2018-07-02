package org.shop.stroage.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.shop.exception.RepositoryException;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 基础持久化接口
 * 
 * @NoRepositoryBean 该注解标注该接口不是Repository
 * 
 * @author VIC
 *
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	/**
	 * 保存或修改对象,执行该方法会先查询对象然后在判断是保存还是更新
	 * 1. 保存：如果数据库中没有改对象则保存
	 * 2. 修改： 如果数据库中有该对象则执行更新
	 * @param entity
	 * @return
	 */
	T saveEntity(T entity) throws RepositoryException;
	
	/**
	 * 保存或修改一组对象
	 * @param entitys
	 * @return
	 */
	Collection<T> saveEntity(Collection<T> entitys) throws RepositoryException;
	
	/**
	 * 根据ID删除一个实体对象
	 * @param id
	 */
	void deleteEntity(ID id) throws RepositoryException;
	
	/**
	 * 删除一个实体对象
	 * @param entity
	 */
	void deleteEntity(T entity) throws RepositoryException;
	
	/**
	 * 删除一组实体对象
	 * @param entitys
	 */
	void deleteEntity(Collection<T> entitys) throws RepositoryException;
	
	/**
	 * 输出所有实体对象
	 */
	void deleteAllEntity() throws RepositoryException;
	
	/**
	 * 根据ID查询一个实体对象
	 * @param id
	 * @return
	 */
	T findById(ID id) throws RepositoryException;
	
	/**
	 * 根据ID检查实体对象是否存在
	 * @param id
	 * @return
	 */
	boolean exists(Id id) throws RepositoryException;
	
	/**
	 * 查询所有实体对象
	 * @return
	 */
	Collection<T> findAllEntity() throws RepositoryException;
	
	/**
	 * 根据一组ID查询实体对象
	 * @param ids
	 * @return
	 */
	Collection<T> findAllEntity(Collection<ID> ids) throws RepositoryException;
	
	/**
	 * 查询所有实体对象数量
	 * @return
	 */
	long countEntity() throws RepositoryException;
	
	/**
	 * 排序查询对象
	 * @param directionStr 升序或降序
	 * @param properties 参与排序的属性
	 * @return
	 */
	Collection<T> sortFindEntity(String directionStr,String...properties) throws RepositoryException;
	
	/**
	 * 带条件分页查询对象
	 * @param t
	 * @param page
	 * @param size
	 * @return
	 */
	Collection<T> pagingFindEntity(T t,int page, int size) throws RepositoryException;
	
	/**
	 * 根据对象属性查询一组对象
	 * @param entity
	 * @return
	 */
	List<T> findByPropertys(T entity) throws RepositoryException;
	
	/**
	 * 根据对象属性查询对象
	 * @param entity
	 * @return
	 */
	T findByProperty(T entity) throws RepositoryException;
	
}
