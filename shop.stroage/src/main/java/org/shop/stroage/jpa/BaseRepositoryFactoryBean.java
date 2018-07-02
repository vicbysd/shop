package org.shop.stroage.jpa;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * 
 * 自定义基础持久类工厂
 * 该工厂主要用于将jpa默认Repository替换为自定义的Repository
 * 
 * @author VIC
 *
 * @param <R>
 * @param <T>
 * @param <ID>
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<T, I>, T, I extends Serializable> extends JpaRepositoryFactoryBean<R, T, I> {

	public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
		super(repositoryInterface);
	}

	@SuppressWarnings("rawtypes")
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new BaseRepositoryFactory(entityManager);
	}

	private static class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

		private EntityManager entityManager;
		
		public BaseRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}

		@SuppressWarnings({ "unused", "unchecked" })
		protected Object getTargetRepository(RepositoryMetadata metadata) {
			return new BaseRepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), entityManager);
		}
		
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return BaseRepositoryImpl.class;
		}
	}

}
