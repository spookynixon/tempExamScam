/**
 * 
 */
package com.examscam.dao;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.examscam.model.User;


/**
 * Coded as per hiberbook
 * Didn't bother with catching to return false at this level
 * 
 * @author Steve
 * 
 */
public class HibernateUserDAO extends BaseDAO implements UserDAO
{

	@Override
	public User create(User user)
	{
		if (user.getId() != null && user.getId() != 0)
		{
			// hmm - as book insists, but should be exception, arguable rolled up into update (persist)
			return null;
		}
		else
		{
			user.setLastAccessTime(new Date());
			user.setRegistrationDate(new GregorianCalendar());
			user.setVerified(false);
			super.persist(user);
		}
		return user;

	}


	@Override
	public boolean update(User user)
	{
		if (user.getId() == null || user.getId() == 0)
		{
			return false;
		}
		else
		{
			super.persist(user);
			return true;
		}
	}


	@Override
	public boolean delete(User user)
	{
		super.delete(user);
		// book wants password set here
		return true;
	}


	@Override
	public User findById(long id)
	{
		return super.findById(User.class, id);
	}


	@Override
	public List<User> findByExample(User user, boolean fuzzy)
	{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<User> findAll()
	{
		return super.findAll(User.class);
	}

}
