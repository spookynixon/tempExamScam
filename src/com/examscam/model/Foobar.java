/**
 * 
 */
package com.examscam.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;


/**
 * Stores state across two tables with the same primary key used for each section
 * Note @ Ëmbedded and @ Embeddable are used to do the reverse two objects stored in one table 
 * 
 * @author Steve
 * 
 */
@Entity
@Table(name = "foo")
@SecondaryTable(name = "bar")
public class Foobar
{

	@Id
	@GeneratedValue
	private Long id;

	private String fooName;

	@Column(table = "bar")
	private String barCode;


	public void setId(Long id)
	{
		this.id = id;
	}


	public Long getId()
	{
		return id;
	}


	public void setFooName(String fooName)
	{
		this.fooName = fooName;
	}


	public String getFooName()
	{
		return fooName;
	}


	public void setBarCode(String barCode)
	{
		this.barCode = barCode;
	}


	public String getBarCode()
	{
		return barCode;
	}

}
