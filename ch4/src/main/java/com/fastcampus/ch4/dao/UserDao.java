package com.fastcampus.ch4.dao;

import java.util.HashMap;
import java.util.List;

import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;
import com.fastcampus.ch4.domain.User;

public interface UserDao {
    int deleteAll() throws Exception;

    int deleteUser(String id) throws Exception;

    User selectUser(String id) throws Exception;
    
    List<User> selectAll(HashMap<String, String> map) throws Exception;

    int insertUser(User user) throws Exception;

    int updateUser(User user) throws Exception;

    int countUser(String name) throws Exception;
}
