package com.demo.dao;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demo.models.User;


@Transactional 
public interface UserDao extends JpaRepository<User, Long> {
	User findByUserName(String userName);
	
	@Query("select u from User u where u.name like %:name")
    public Page<User> findList( @Param("name") String name,Pageable pageable);
	
}
