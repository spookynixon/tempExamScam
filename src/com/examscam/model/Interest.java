package com.examscam.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Compound primary key example
 * 
 * @author Steve
 * 
 */
@Entity
@Table(name = "interest")
public class Interest
{

	//Note can override column names in pk here
	//@AttributeOverride(name="userId", column=@Column(name="user_id")
	@EmbeddedId
	private InterestPK id;
	private double rate;


	public Interest(long userId, long bankId, double rate)
	{
		this.id = new InterestPK(userId, bankId);
		this.rate = rate;
	}


	public Long getUserId()
	{
		return id.getUserId();
	}


	public Long getBankId()
	{
		return id.getBankId();
	}


	public void setRate(double rate)
	{
		this.rate = rate;
	}


	public double getRate()
	{
		return rate;
	}

	@Embeddable
	private static class InterestPK implements Serializable
	{

		private static final long serialVersionUID = 1L;
		@Column(name="user_id")
		private Long userId;
		@Column(name="bank_id")
		private Long bankId;


		public InterestPK(Long userId, Long bankId)
		{
			this.userId = userId;
			this.bankId = bankId;
		}


		public Long getUserId()
		{
			return userId;
		}


		public Long getBankId()
		{
			return bankId;
		}


		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + ((bankId == null) ? 0 : bankId.hashCode());
			result = prime * result + ((userId == null) ? 0 : userId.hashCode());
			return result;
		}


		@Override
		public boolean equals(Object obj)
		{
			if (this == obj) return true;
			if (obj == null) return false;
			if (getClass() != obj.getClass()) return false;
			InterestPK other = (InterestPK) obj;
			if (bankId == null)
			{
				if (other.bankId != null) return false;
			}
			else
				if (!bankId.equals(other.bankId)) return false;
			if (userId == null)
			{
				if (other.userId != null) return false;
			}
			else
				if (!userId.equals(other.userId)) return false;
			return true;
		}

	}

}
