package com.fastcampus.ch4.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fastcampus.ch4.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    SqlSession session;

    String namespace="UserMapper.";

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace+"deleteAll");
    }

    @Override
    public int deleteUser(String id) throws Exception {
       return session.delete(namespace+"deleteUser", id);
    }

    @Override
    public User selectUser(String id) throws Exception {
        return session.selectOne(namespace+"selectUser", id);
    }
    
    @Override
    public List<User> selectAll(HashMap<String, String> map) throws Exception {
        return session.selectList(namespace+"selectAll", map);
    }

    // �궗�슜�옄 �젙蹂대�� user_info �뀒�씠釉붿뿉 ���옣�븯�뒗 硫붿꽌�뱶
    @Override
    public int insertUser(User user) throws Exception {
        return session.insert(namespace+"insertUser", user);
    }

    @Override
    public int updateUser(User user) throws Exception {
        return session.update(namespace+"updateUser", user);
    }

    @Override
    public int countUser(String name) throws Exception {
        return session.selectOne(namespace+"countUser", name);
    }
}