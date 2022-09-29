package com.fastcampus.ch4.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fastcampus.ch4.dao.UserDao;
import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;
import com.fastcampus.ch4.domain.User;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired // 생성자 역할
	UserDao userDao;
	
	@Override // RegisterService 오버라이드
	public int userUpdate(User user) throws Exception {
		return userDao.updateUser(user);
	}
	
	@Override
	public int userInsert(User user) throws Exception {
		return userDao.insertUser(user);
	}
	
	@Override 
	public User userSelect(String id) throws Exception {
		return userDao.selectUser(id);
	}
	
	@Override
    public List<User> getList(HashMap<String, String> map) throws Exception {
        return userDao.selectAll(map);
    }
	
	@Override
    public int countUser(String name) throws Exception {
		return userDao.countUser(name);
	}

	@Override
	public int getSearchResultCnt(User user) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
