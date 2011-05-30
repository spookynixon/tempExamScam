/**
 * 
 */
package com.examscam.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * @author Steve
 * 
 */
@Entity
public class Snafu
{

	@Id
	@GeneratedValue
	private Long id;
	private String situation;


	public void setSituation(String situation)
	{
		this.situation = situation;
	}


	public String getSituation()
	{
		return situation;
	}


	public void setId(Long id)
	{
		this.id = id;
	}


	public Long getId()
	{
		return id;
	}

}
