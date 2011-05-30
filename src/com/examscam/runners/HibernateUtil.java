package com.examscam.runners;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.examscam.dao.HibernateUserDAO;
import com.examscam.dao.UserDAO;
import com.examscam.model.Foobar;
import com.examscam.model.Interest;
import com.examscam.model.Snafu;
import com.examscam.model.User;


/**
 * @ Basic is an implied annotation replaced by using @ Column
 * @ Lob used if persisting database Blob or Clob types
 * 
 * Use load when you know an instance exists in the DB, otherwise get.
 * Load only hits db when accessing values, alleviating load.
 * 
 * save or saveOrUpdate is unnecessary if we're committing a transaction anyway, this persists attached objects,
 * provided the flush mode is set to do so (manual, auto, commit, always)
 * 'evict' detaches an object from a transaction
 * 
 * In HQL, move the HQL statements as named queries into an annotated class that is part of the AnnotationConfiguration object.
 * e.g. see @ NamedQuery in the User class
 * 
 * Compound primary keys (see Interest class for example)
 * Key exists as @ Embeddable class containing key fields
 * Reference to PK requires @ EmbeddedId
 * Embedded class must be serializable and override hashcode and equals
 * Only main class needs to be added to hibernate configuration
 * 
 * @ Inheritance default is Single Table, keep child and parent types in same table
 * DTYPE is used to differentiate these when searching (e.g. query on children only in Person table)
 * 
 * TODO - OneToOne, code these
 * 
 * 
 * 
 * @author Steve
 */
public class HibernateUtil
{

	private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);


	public static void main(String[] args)
	{
		try
		{
			recreateDatabase();
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static SessionFactory sessionFactory;


	public static Session getSession()
	{
		if (sessionFactory == null)
		{
			sessionFactory = getInitializedConfiguration().buildSessionFactory();
		}
		return sessionFactory.getCurrentSession();
	}


	public static void closeSession()
	{
		getSession().close();
	}


	public static Session beginTransaction()
	{
		Session session = getSession();
		session.beginTransaction();
		return session;
	}


	public static void commitTransaction()
	{
		getSession().getTransaction().commit();
	}


	public static void rollbackTransaction()
	{
		getSession().getTransaction().rollback();
	}


	public static Configuration getInitializedConfiguration()
	{
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(User.class);
		cfg.addAnnotatedClass(Snafu.class);
		cfg.addAnnotatedClass(Foobar.class);
		cfg.addAnnotatedClass(Interest.class);
		cfg.configure();
		return cfg;
	}


	public static void recreateDatabase() throws Exception
	{
		Configuration cfg = getInitializedConfiguration();
		new SchemaExport(cfg).create(true, true);

		try
		{
			HibernateUtil.beginTransaction();
			Snafu snafu = new Snafu();
			snafu.setSituation("normal");
			getSession().saveOrUpdate(snafu);
			HibernateUtil.commitTransaction();

			HibernateUtil.beginTransaction();
			Foobar foobar = new Foobar();
			foobar.setBarCode("barcodehere");
			foobar.setFooName("foonamehere");
			getSession().saveOrUpdate(foobar);
			HibernateUtil.commitTransaction();

			HibernateUtil.beginTransaction();
			Interest interest = new Interest(2, 2, 18.5);
			getSession().saveOrUpdate(interest);
			HibernateUtil.commitTransaction();

			HibernateUtil.beginTransaction();
			UserDAO userDAO = new HibernateUserDAO();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

			{
				User u = new User();
				u.setLoginName("mj");
				u.setPassword("abc123");
				u.setEmailAddress("mj@mcnz.com");
				u.setVerified(false);
				u.setLastAccessTime(new java.util.Date());
				u.setRegistrationDate(new GregorianCalendar(2006, 01, 01));
				userDAO.create(u);
			}
			{
				User u = new User();
				u.setLoginName("mario");
				u.setPassword("pass");
				u.setEmailAddress("mario@scja.ca");
				u.setVerified(true);
				u.setLastAccessTime(sdf.parse("2008-1-1"));
				u.setRegistrationDate(new GregorianCalendar(2007, 01, 01));
				userDAO.create(u);
			}
			{
				User u = new User();
				u.setLoginName("sk8trgrl");
				u.setPassword("password");
				u.setEmailAddress("avril@scja.com");
				u.setVerified(false);
				u.setLastAccessTime(new java.util.Date());
				u.setRegistrationDate(new GregorianCalendar(2008, 01, 01));
				userDAO.create(u);
			}
			{
				User u = new User();
				u.setLoginName("ridley");
				u.setPassword("mypassword");
				u.setEmailAddress("getbent@scja.ca");
				u.setVerified(true);
				u.setLastAccessTime(new java.util.Date());
				u.setLastAccessTime(sdf.parse("2006-10-5"));
				u.setRegistrationDate(new GregorianCalendar(2006, 5, 11));
				userDAO.create(u);
			}

			{
				User u = new User();
				u.setLoginName("kerri");
				u.setPassword("pwd");
				u.setEmailAddress("sheehan@princessjava.com");
				u.setVerified(false);
				u.setLastAccessTime(sdf.parse("2008-02-25"));
				u.setRegistrationDate(new GregorianCalendar(2007, 12, 12));
				userDAO.create(u);
			}

			{
				User u = new User();
				u.setLoginName("astra");
				u.setPassword("pwd");
				u.setEmailAddress("rabbit@princessjava.com");
				u.setVerified(false);
				u.setLastAccessTime(new java.util.Date());
				u.setRegistrationDate(new GregorianCalendar());
				userDAO.create(u);
			}

			{
				User u = new User();
				u.setLoginName("cameron");
				u.setPassword("90210");
				u.setEmailAddress("me@scwcd.com");
				u.setVerified(true);
				u.setLastAccessTime(sdf.parse("2008-09-15"));
				u.setRegistrationDate(new GregorianCalendar(2008, 8, 12));
				userDAO.create(u);
			}
			{
				User u = new User();
				u.setLoginName("stephen");
				u.setPassword("low");
				u.setEmailAddress("stanley@pulpjava.com");
				u.setVerified(false);
				u.setLastAccessTime(sdf.parse("2008-02-25"));
				u.setRegistrationDate(new GregorianCalendar(2008, 02, 15));
				userDAO.create(u);
			}
			{
				User u = new User();
				u.setLoginName("che");
				u.setPassword("password");
				u.setEmailAddress("ernesto@pulpjava.com");
				u.setVerified(true);
				u.setLastAccessTime(sdf.parse("1999-07-26"));
				u.setRegistrationDate(new GregorianCalendar(1999, 3, 9));
				userDAO.create(u);
			}
			{
				User u = new User();
				u.setLoginName("remy");
				u.setPassword("password");
				u.setEmailAddress("rabbit@scja.com");
				u.setVerified(false);
				u.setLastAccessTime(new java.util.Date());
				u.setRegistrationDate(new GregorianCalendar(2007, 05, 21));
				userDAO.create(u);
			}
			HibernateUtil.commitTransaction();
		}
		catch (HibernateException e)
		{
			logger.error("Failed to insert users", e);
		}
	}

}