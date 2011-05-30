/**
 * 
 */
package com.examscam.dao;

import java.util.List;

import com.examscam.model.User;


/**
 * @author Steve
 */
public interface UserDAO
{

	public User create(User user);


	public boolean update(User user);


	public boolean delete(User user);


	public User findById(long id);


	public List<User> findByExample(User user, boolean fuzzy);


	public List<User> findAll();

}
