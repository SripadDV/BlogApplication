package com.exam.service;

import java.util.List;
import java.util.Set;


import com.exam.entity.User;
import com.exam.entity.UserRole;


public interface UserService {
	
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	//get user by username
	public User getUserByUsername(String username);
	
	//delete user by id
	public void deleteUser(Long Id);
	
	//get all users
	public List<User> getAllUsers();
	
	//update user by username
	public User updateUser(String username, User user);
	
}
