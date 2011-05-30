/**
 * 
 */
package com.examscam.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * @author Steve
 * 
 */
@Entity
@Table(name = "user")
@NamedQuery(name="user.findByLoginName", query="from User where loginName = :name")
public class User
{

	@Id
	@GeneratedValue
	@Column(name = "id")
	public Long id;

	@Column(name = "login_name")
	private String loginName;

	@Column(name = "password")
	private String password;

	@Column(name = "email_address")
	private String emailAddress;

	@Column(name = "verified")
	private Boolean verified;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_access_time")
	private Date lastAccessTime;

	@Temporal(TemporalType.DATE)
	@Column(name = "registration_date")
	private Calendar registrationDate;

	@Transient
	private String encryptedPassword;


	public Long getId()
	{
		return id;
	}


	public void setId(Long id)
	{
		this.id = id;
	}


	public String getPassword()
	{
		return password;
	}


	public void setPassword(String password)
	{
		this.password = password;
	}


	public void setLoginName(String loginName)
	{
		this.loginName = loginName;
	}


	public String getLoginName()
	{
		return loginName;
	}


	public void setEncryptedPassword(String encryptedPassword)
	{
		this.encryptedPassword = encryptedPassword;
	}


	public String getEncryptedPassword()
	{
		return encryptedPassword;
	}


	public void setRegistrationDate(Calendar registrationDate)
	{
		this.registrationDate = registrationDate;
	}


	public Calendar getRegistrationDate()
	{
		return registrationDate;
	}


	public void setLastAccessTime(Date lastAccessTime)
	{
		this.lastAccessTime = lastAccessTime;
	}


	public Date getLastAccessTime()
	{
		return lastAccessTime;
	}


	public void setVerified(Boolean verified)
	{
		this.verified = verified;
	}


	public Boolean getVerified()
	{
		return verified;
	}


	public void setEmailAddress(String emailAddress)
	{
		this.emailAddress = emailAddress;
	}


	public String getEmailAddress()
	{
		return emailAddress;
	}

}
