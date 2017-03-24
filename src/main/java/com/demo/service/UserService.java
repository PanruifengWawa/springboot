package com.demo.service;

import org.springframework.data.domain.Page;

import com.demo.models.User;
import com.demo.utils.DataWrapper;

public interface UserService {
	DataWrapper<Void> login(String userName,String password);
	DataWrapper<Void> register(User user);
	Page<User> getUserList(String name);
}
