package com.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.annotation.CheckUser;
import com.demo.enums.UserType;
import com.demo.models.User;
import com.demo.service.UserService;
import com.demo.utils.DataWrapper;

import net.wimpi.telnetd.io.terminal.xterm;

@Controller
@RequestMapping("/api/user")
public class HelloController {
	@Autowired
	UserService userService;
	@RequestMapping("login")
	@ResponseBody
    public DataWrapper<Void> login(
    		@RequestParam(value = "userName", required = true) String userName,
    		@RequestParam(value = "password",required = true) String password
    		){
        return userService.login(userName, password);
    }
	
	@RequestMapping("/getList")
	@ResponseBody
    public Page<User> getList(
    		@RequestParam(value = "name", required = true) String name
    		){
        return userService.getUserList(name);
    }
	
	@RequestMapping("/exception")
	@ResponseBody
    public Page<User> exception(
    		@RequestParam(value = "name", required = true) String name
    		){
		int xterm = 1/0;
        return userService.getUserList(name);
    }
	
	@CheckUser(type=UserType.Admin)
	@RequestMapping("/aop")
	@ResponseBody
    public Page<User> aop(
    		HttpServletRequest request,HttpServletResponse response,
    		@RequestParam(value = "name", required = true) String name
    		){
        return userService.getUserList(name);
    }
}
