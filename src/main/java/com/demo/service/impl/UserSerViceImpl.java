package com.demo.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.demo.dao.UserDao;
import com.demo.enums.ErrorCodeEnum;
import com.demo.models.User;
import com.demo.service.UserService;
import com.demo.utils.DataWrapper;
import com.demo.utils.MD5Util;
import com.demo.utils.SessionManager;

@Service("userService")
public class UserSerViceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
    private EntityManager em;
	

	@Override
	public DataWrapper<Void> login(String userName, String password) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User user = userDao.findByUserName(userName);
		if (user != null) {
			if (user.getPassword().equals(MD5Util.getMD5String(password))) {
				SessionManager.removeSessionByUserId(user.getId());
				String token = SessionManager.newSession(user);
				dataWrapper.setToken(token);
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> register(User user) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		if (user.getUserName() == null || user.getUserName().trim().length() == 0 || user.getPassword() == null || user.getPassword().equals("")) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		} else {
			user.setId(null);
			user.setRegisterDate(new Date());
			user.setPassword(MD5Util.getMD5String(user.getPassword()));
//			if (!userDao.addUser(user)) {
//				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
//			}
		}
		return dataWrapper;
	}

	@Override
	public Page<User> getUserList(String token) {
		// TODO Auto-generated method stub
		
		
		Sort sort = new Sort(Sort.Direction.ASC, "id");
	    Pageable pageable = new PageRequest(0, 5, sort);
		Page<User> page = userDao.findList(token, pageable);
		
		
		Query nativeQuery = em.createNativeQuery("select u.id as id,u.name as name,u.user_name as userName from t_user u where u.id=6");
		
		nativeQuery.unwrap(SQLQuery.class)
			.addScalar("id",StandardBasicTypes.LONG)
			.addScalar("name",StandardBasicTypes.STRING)
			.addScalar("userName",StandardBasicTypes.STRING)
			.setResultTransformer(Transformers.aliasToBean(User.class));
		@SuppressWarnings({ "unchecked", "unchecked" })
		List<User> resultList = nativeQuery.getResultList(); 
		for(User u : resultList) {
			System.out.println(u.getId() + "," + u.getName() + "," + u.getUserName());
		}
		return page;
	}

}
