/**
 * 
 */
package com.examscam.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;

import com.examscam.runners.HibernateUtil;


/**
 * @author Steve
 */
public abstract class BaseDAO
{

	protected Session getSession()
	{
		return HibernateUtil.getSession();
	}


	protected void persist(Object o)
	{
		getSession().saveOrUpdate(o);
	}


	protected void delete(Object o)
	{
		getSession().delete(o);
	}


	@SuppressWarnings("unchecked")
	protected <T> T findById(Class<T> c, long id)
	{
		return (T) getSession().get(c, id);
	}


	@SuppressWarnings("unchecked")
	protected <T> List<T> findAll(Class<T> c)
	{
		return getSession().createCriteria(c).list();
	}


	@SuppressWarnings("unchecked")
	protected <T> List<T> findByExample(T t, boolean fuzzy)
	{

		Criteria criteria = getSession().createCriteria(t.getClass());
		Example e = Example.create(t);

		if (fuzzy)
		{
			e.enableLike(MatchMode.ANYWHERE);
			e.ignoreCase();
			e.excludeZeroes();
		}

		criteria.add(e);
		return criteria.list();
	}

}
