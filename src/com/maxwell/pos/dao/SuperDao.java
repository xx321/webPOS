package com.maxwell.pos.dao;

import java.io.Serializable;
import java.util.List;

public interface SuperDao<T, PK extends Serializable> {
	public T findById(PK id);
	
	public List<T> findAll();
	
	public T save(T entity);
	
	public void update(T entity);
	
	public void delete(T entity);
	
	public List<?> find(String hql, List<Object> params,
			 Integer rows, Integer page);
}
