package com.fastcampus.ch4.dao;

import com.fastcampus.ch4.domain.new_Info;
import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;
import com.fastcampus.ch4.domain.User;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class new_InfoDaoImpl implements new_InfoDao {
    @Autowired
    SqlSession session;

    String namespace="new_InfoMapper.";

    @Override
    public List<new_Info> selectAll_new() throws Exception {
        return session.selectList(namespace+"selectAll_new");
    }
    
    @Override
    public List<new_Info> selectsearchAll_new(HashMap<String, String> map) throws Exception {
        return session.selectList(namespace+"selectsearchAll_new", map);
    }
    
//    @Override
//    public List<new_Info> searchSelectPage_new(SearchCondition sc) throws Exception {
//        return session.selectList(namespace+"searchSelectPage_new", sc);
//    }
}
