package com.fastcampus.ch4.service;

import java.util.HashMap;
import java.util.List;

import com.fastcampus.ch4.domain.User;

public interface RegisterService {
	
	User userSelect(String id) throws Exception;
	
	List<User> getList(HashMap<String, String> map) throws Exception;

	int userUpdate(User user) throws Exception;
	
	int userInsert(User user) throws Exception;

	int getSearchResultCnt(User user) throws Exception;
	
	public int countUser(String name) throws Exception;
}