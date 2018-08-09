package com.anwei.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.anwei.entity.PageList;

/**
 * E - Element (在集合中使用，因为集合中存放的是元素) 
 * T - Type（Java 类） 
 * K - Key（键） 
 * V - Value（值） 
 * N - Number（数值类型）
 * ? - 表示不确定的java类型 S、U、V - 2nd、3rd、4th types
 * 
 * @author Anwei
 * @date 2017年11月22日
 * @param <T>
 * @param <PK>
 */
public interface GenericDao<T, PK extends Serializable> {
	/** Persist the newInstance object into database */
	PK save(T newInstance);

	/** Save changes made to a persistent object. */
	void update(T transientObject);

	/**
	 * 
	 * @param object
	 */
	void saveOrUpdate(T object);

	/** Remove an object from persistent storage in the database */
	void delete(T persistentObject);

	/** Return a persistent instance or null */
	T get(Class<T> clazz, Serializable id);

	/** Return the persistent instance or proxy */
	T load(Class<T> clazz, Serializable id);

	/**
	 * 根据HQL条件查询对象
	 * 
	 * @param hql
	 * @param objects
	 * @return
	 */
	T findObject(String hql, Object... objects);

	/**
	 * 根据SQL条件查询对象
	 * 
	 * @param cls
	 * @param sql
	 * @param objects
	 * @return
	 */
	T findObjectBySQL(Class<T> cls, String sql, Object... objects);

	/**
	 * 根据SQL条件查询列，配合BeanUtil.map2Bean() 与 BeanUtil.bean2Map()
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	List<Map<String, Object>> findColumnsBySQL(String sql, Object... objects);

	/**
	 * 根据HQL条件查询多个记录
	 * 
	 * @param hql
	 * @param objects
	 * @return
	 */
	List<T> findList(String hql, Object... objects);

	/**
	 * 查询某个类对应表记录
	 * 
	 * @param cls
	 * @return
	 */
	List<T> findList(Class<T> cls);

	/**
	 * 根据SQL条件查询对象列表
	 * 
	 * @param cls
	 * @param sql
	 * @param objects
	 * @return
	 */
	List<T> findListBySQL(Class<T> cls, String sql, Object... objects);

	/**
	 * 执行SQL，返回被影响行数。不能执行查询语句
	 * 
	 * @param sql
	 * @param objects
	 * @return The number of entities updated or deleted.
	 */
	int executeSQL(String sql, Object... objects);

	/**
	 * HQL统计记录数
	 * 
	 * @param hql
	 * @param objects
	 * @return
	 */
	int count(String hql, Object... objects);

	/**
	 * SQL统计记录数
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	int countBySQL(String sql, Object... objects);

	/**
	 * 分页查询
	 * 
	 * @param hql
	 * @param page
	 * @param rows
	 * @param objects
	 * @return
	 */
	PageList<T> findPageList(String hql, int page, int rows, Object... objects);

}