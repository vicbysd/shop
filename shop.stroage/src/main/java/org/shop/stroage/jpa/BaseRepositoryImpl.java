package org.shop.stroage.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import org.shop.exception.RepositoryException;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * 基础持久化接口实现
 * @author VIC
 *
 * @param <T>
 * @param <ID>
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

	// 构造函数
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
	  super(domainClass, entityManager);
	}
	  
	// 构造函数
	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
	 super(entityInformation, entityManager);
	}

	@Override
	public T saveEntity(T entity) throws RepositoryException {
		if(null == entity){
			throw new RepositoryException();
		}
		return save(entity);
	}

	@Override
	public Collection<T> saveEntity(Collection<T> entitys) throws RepositoryException {
		if(null == entitys || entitys.size() <= 0){
			throw new RepositoryException();
		}
		return save(entitys);
	}

	@Override
	public void deleteEntity(ID id) throws RepositoryException {
		if(null == id){
			throw new RepositoryException();
		}
		delete(id);
	}

	@Override
	public void deleteEntity(T entity) throws RepositoryException {
		if(null == entity){
			throw new RepositoryException();
		}
		delete(entity);
	}

	@Override
	public void deleteEntity(Collection<T> entitys) throws RepositoryException {
		if(null == entitys || entitys.size() <= 0){
			throw new RepositoryException();
		}
		delete(entitys);
	}

	@Override
	public void deleteAllEntity() throws RepositoryException {
		deleteAll();
	}

	@Override
	public T findById(ID id) throws RepositoryException {
		if(null == id){
			throw new RepositoryException();
		}
		return findById(id);
	}

	@Override
	public boolean exists(Id id) throws RepositoryException {
		if(null == id){
			throw new RepositoryException();
		}
		return exists(id);
	}

	@Override
	public Collection<T> findAllEntity() throws RepositoryException {
		return findAll();
	}

	@Override
	public Collection<T> findAllEntity(Collection<ID> ids) throws RepositoryException {
		if(null == ids || ids.size() <= 0){
			throw new RepositoryException();
		}
		return findAll(ids);
	}

	@Override
	public long countEntity() throws RepositoryException {
		return count();
	}

	@Override
	public Collection<T> sortFindEntity(String directionStr, String... properties) throws RepositoryException {
		if(null == directionStr){
			directionStr = "asc";
		}
		if(null == properties || properties.length <= 0){
			throw new RepositoryException();
		}
		// 创建排序规则对象:升序或降序
		Direction direction = Direction.valueOf(directionStr);
		// 创建排序对象:properties参与排序的属性
		Sort sort = new Sort(direction, properties);
		return findAll(sort);
	}

	@Override
	public Collection<T> pagingFindEntity(T entity, int page, int size) throws RepositoryException {
		if(null == entity){
			throw new RepositoryException();
		}
		// 创建分页对象
		Pageable pageable = new PageRequest(page, size);
		// 创建查询条件对象
		Example<T> example = Example.of(entity);
		// 执行分页查询
		Page<T> pages = findAll(example, pageable);
		// 返回分页查询结果
		return pages.getContent();
	}

	@Override
	public List<T> findByPropertys(T entity) throws RepositoryException {
		if(null == entity){
			throw new RepositoryException();
		}
		Example<T> example = Example.of(entity);
		return findAll(example);
	}
	
	@Override
	public T findByProperty(T entity) throws RepositoryException {
		if(null == entity){
			throw new RepositoryException();
		}
		List<T> ts = findByPropertys(entity);
		return ts.get(0);
	}
}
