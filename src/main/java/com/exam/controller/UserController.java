package com.exam.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("Normal");
		
		Set<UserRole> userRolesSet = new HashSet<UserRole>();
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		userRolesSet.add(userRole);
		
		User createdUser = this.userService.createUser(user, userRolesSet);
		
		return createdUser;
	}
	
	@GetMapping("/username/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUserByUsername(username);
	}
	
	@PutMapping("/username/{username}")
	public void updateUser(@PathVariable("username") String username, @RequestBody User user) {
		//return this.userService.updateUser(username, user);
	}
	
	@GetMapping("/")
	public List<User> getAllUsers() {
		return this.userService.getAllUsers();
	}
	
	@DeleteMapping("/id/{id}")
	public void deleteUser(@PathVariable("id") Long Id) {
		this.userService.deleteUser(Id);
	}
	
}
