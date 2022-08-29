package com.exam.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.repository.RoleRepo;
import com.exam.repository.UserRepo;
import com.exam.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User local = this.userRepo.findByUsername(user.getUsername());
		if(local!=null) {
			System.out.println("Username already exists !! ");
			throw new Exception("User already present!!");
		}
		else {
			for(UserRole ur:userRoles) {
				this.roleRepo.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local = this.userRepo.save(user);
		}
		
		return local;
	}

	@Override
	public User getUserByUsername(String username) {
		return this.userRepo.findByUsername(username);
	}

	@Override
	public void deleteUser(Long Id) {
		this.userRepo.deleteById(Id);
		
	}

	@Override
	public List<User> getAllUsers() {
		return this.userRepo.findAll();
	}

	@Override
	public User updateUser(String username, User user) {
		User local = this.userRepo.findByUsername(username);
		if(user.getFirstName() != null) { local.setFirstName(user.getFirstName()); }
		if(user.getLastName() != null) { local.setLastName(user.getLastName()); }
		if(user.getEmail() != null) { local.setEmail(user.getEmail()); }
		if(user.getPassword() != null) { local.setPassword(user.getPassword()); }
		if(user.getPhone() != null) { local.setPhone(user.getPhone()); }
		if(user.getProfile() != null) { local.setProfile(user.getProfile()); }
		if(user.isEnabled() != local.isEnabled()) { local.setEnabled(user.isEnabled()); }
		User updatedUser = this.userRepo.save(local);
		return updatedUser;
	}

}
