package com.examscam.runners;

import static com.examscam.runners.HibernateUtil.beginTransaction;
import static com.examscam.runners.HibernateUtil.commitTransaction;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.examscam.model.User;


/**
 * Contains HQL query usage examples. Also: "from User as u GROUP BY u.id HAVING u.id > 4" "from User as u ORDER BY u.id ASC"
 * "update User set password = :pwd  where password=:pwd2"
 * 
 * @author Steve
 * 
 */
public class CrudRunner
{

	private static Logger logger = LoggerFactory.getLogger(CrudRunner.class);


	public static void main(String[] args)
	{
		Session session = beginTransaction();

		// List<User> userList = findVerifiedUsers(session);
		// User mjUser = findSpecificUser(session, "mj");
		List<User> userList = findAllPaged(session);

		// User u = create(session);
		// retrieve(session);
		// User u2 = userByLoginName(session, u.getLoginName());

		// retrieveFromId(1, session);
		// updateAll(session);

		// deleteAll(session);

		commitTransaction();
	}


	// CRUD

	private static User create(Session session)
	{
		User u = new User();
		u.setLoginName("ohhaithere" + new Random().nextInt());
		u.setPassword("pword");
		u.setEmailAddress("a@b.com");
		u.setLastAccessTime(new Date());
		u.setRegistrationDate(new GregorianCalendar());
		u.setEncryptedPassword("Mr Namoko");
		session.save(u);
		return u;
	}


	private static void updateAll(Session session)
	{
		List<User> userList = retrieve(session);
		for (User user : userList)
		{
			user.setPassword("passworda");
			user.setLoginName("Mr Namoko");
			user.setEncryptedPassword("Mr Namoko");
			session.update(user);
		}
	}


	@SuppressWarnings("unchecked")
	private static void deleteAll(Session session)
	{
		Query query = session.createQuery("from User");

		List<User> userList = query.list();
		for (User user : userList)
		{
			session.delete(user);
		}
	}


	@SuppressWarnings("unchecked")
	private static List<User> retrieve(Session session)
	{
		// Select * from
		Query query = session.createQuery("from User");
		List<User> allUsers = query.list();

		logger.info("Retrieved {} users.", allUsers.size());

		return allUsers;
	}


	private static User retrieveFromId(int id, Session session)
	{

		Query query = session.createQuery("from User where id = :id");
		query.setInteger("id", id);
		User myUser = (User) query.uniqueResult();

		logger.info("Retrieved {} user.", myUser.getPassword());
		return myUser;
	}


	// queries
	private static User userByLoginName(Session session, String loginName)
	{
		Query query = session.getNamedQuery("user.findByLoginName");
		query.setParameter("name", loginName);
		return (User) query.uniqueResult();
	}


	// GET/LOAD

	private static User callGet(Session session)
	{
		return (User) session.get(User.class, new Long(10));
	}


	private static User callLoad(Session session)
	{
		User u = (User) session.load(User.class, Long.valueOf(11));
		System.out.print(u.getPassword()); // note load only hits db when accessing values
		return u;
	}


	// CRITERIA

	private static List<User> findVerifiedUsers(Session session)
	{
		// Cleaner but brittle, relies on 'verified' consistency:
		// Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("verified", true));

		// Using an example class to search on:
		User user = new User();
		user.setVerified(true);
		Example exampleUser = Example.create(user);
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(exampleUser);

		return criteria.list();
	}


	private static User findSpecificUser(Session session, String loginName)
	{
		// as above
		Criteria criteria = session.createCriteria(User.class)
				.add(Restrictions.eq("loginName", "mj"));

		return (User) criteria.uniqueResult();
	}


	private static List<User> matchSearchTerm(Session session)
	{
		User user = new User();
		user.setEmailAddress(".com");
		Example exampleUser = Example.create(user);
		exampleUser.enableLike(MatchMode.END);
		exampleUser.ignoreCase();
		Criteria criteria = session.createCriteria(User.class).add(exampleUser);

		// Criteria criteria = session.createCriteria(User.class)
		// .add(Restrictions.like("emailAddress", ".com", MatchMode.END));

		return criteria.list();
	}


	private static List<User> findAllPaged(Session session)
	{
		Criteria criteria = session.createCriteria(User.class)
				.addOrder(Order.asc("loginName"))
				.setFirstResult(2)
				.setMaxResults(2);

		return criteria.list();
	}
}
