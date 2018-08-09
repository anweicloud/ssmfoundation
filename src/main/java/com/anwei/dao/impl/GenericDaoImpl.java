package com.anwei.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.anwei.dao.GenericDao;
import com.anwei.entity.PageList;

@SuppressWarnings("unchecked")
@Repository("genericDao")
public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	@Autowired
	private SessionFactory sessionFactory;

	Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public PK save(T newInstance) {
		return (PK) getSession().save(newInstance);
	}

	@Override
	public void update(T transientObject) {
		getSession().update(transientObject);
	}

	@Override
	public void delete(T persistentObject) {
		getSession().delete(persistentObject);
	}

	@Override
	public void saveOrUpdate(T object) {
		getSession().saveOrUpdate(object);
	}

	@Override
	public T get(Class<T> clazz, Serializable id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public T load(Class<T> clazz, Serializable id) {
		return (T) getSession().load(clazz, id);
	}

	@Override
	public T findObject(String hql, Object... objects) {
		List<T> list = findList(hql, objects);
		return (list.size() > 0) ? list.get(0) : null;
	}

	@Override
	public T findObjectBySQL(Class<T> cls, String sql, Object... objects) {
		List<T> list = findListBySQL(cls, sql, objects);
		return (list.size() > 0) ? list.get(0) : null;
	}

	@Override
	public List<Map<String, Object>> findColumnsBySQL(String sql, Object... objects) {
		Query query = getSession().createSQLQuery(sql);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		setParameter(query, objects);
		return query.list();
	}

	@Override
	public List<T> findList(String hql, Object... objects) {
		Query query = getSession().createQuery(hql);
		setParameter(query, objects);
		return query.list();
	}

	@Override
	public List<T> findList(Class<T> cls) {
		String hql = "FROM " + cls.getName();
		return findList(hql);
	}

	@Override
	public List<T> findListBySQL(Class<T> cls, String sql, Object... objects) {
		Query query = getSession().createSQLQuery(sql).addEntity(cls);
		setParameter(query, objects);
		return query.list();
	}

	@Override
	public int executeSQL(String sql, Object... objects) {
		Query query = getSession().createSQLQuery(sql);
		setParameter(query, objects);
		return query.executeUpdate();
	}

	@Override
	public int count(String hql, Object... objects) {
		Query query = getSession().createQuery(hql);
		setParameter(query, objects);
		ScrollableResults sr = query.scroll();
		sr.last();
		return (sr.getRowNumber() == -1) ? 0 : sr.getRowNumber() + 1;
	}

	@Override
	public int countBySQL(String sql, Object... objects) {
		Query query = getSession().createSQLQuery(sql);
		setParameter(query, objects);
		Object rs = query.uniqueResult();
		return (rs == null)? 0 : Integer.parseInt(rs.toString());
	}

	@Override
	public PageList<T> findPageList(String hql, int page, int rows, Object... objects) {
		Query query = getSession().createQuery(hql);
		setParameter(query, objects);
		return findPageList(query, page, rows);
	}

	PageList<T> findPageList(Query query, int page, int rows) {
		ScrollableResults sr = query.scroll();
		sr.last();
		int count = (sr.getRowNumber() == -1) ? 0 : sr.getRowNumber() + 1;
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		return new PageList<T>(page, rows, count, query.list());
	}

	void setParameter(Query query, Object... objects) {
		for (int i = 0, len = objects.length; i < len; i++) {
			query.setParameter(i, objects[i]);
		}
	}

}
