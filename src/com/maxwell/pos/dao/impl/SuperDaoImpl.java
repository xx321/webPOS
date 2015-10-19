package com.maxwell.pos.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.*;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.maxwell.pos.dao.SuperDao;

public class SuperDaoImpl<T, PK extends Serializable> extends
		HibernateDaoSupport implements SuperDao<T, PK> {

	private Class<T> clazz;

	@SuppressWarnings("unchecked")
	public SuperDaoImpl() {
		clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Resource(name = "sf")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	public T findById(PK id) {
		T result = (T) this.getHibernateTemplate().get(clazz, id);
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return this.getHibernateTemplate().loadAll(clazz);
	}

	public T save(T entity) {
		this.getHibernateTemplate().save(entity);
		return entity;
	}

	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	public Object queryObject(final String hql, final List<Object> params) {

		return this.getHibernateTemplate().execute(new HibernateCallback() {

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i < params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}
				return query.uniqueResult();
			}
		});
	}

	public List<?> find(final String hql, final List<Object> params,
			final Integer rows, final Integer page) {
		
		return (List<?>) getHibernateTemplate().execute(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				if (params != null) {
					for (int i = 0; i< params.size(); i++) {
						query.setParameter(i, params.get(i));
					}
				}
				if(rows !=null && page !=null) {
					query.setMaxResults(rows);
					query.setFirstResult((page - 1) * rows);
				}
				return query.list();
			}
		});
	}
	
	//增加排序功能 : 添加 order by 'xxx' (desc OR asc) 語句 
	public void addOrderBy(StringBuffer sb, String sort, String order) {
		if (sort != null && order != null) {
			sb.append(" order by ");
			sb.append(sort);
			sb.append(" ");
			sb.append(order);
		}
	}
}
