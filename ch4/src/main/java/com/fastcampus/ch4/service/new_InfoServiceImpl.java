package com.fastcampus.ch4.service;

import com.fastcampus.ch4.dao.new_InfoDao;
import com.fastcampus.ch4.domain.new_Info;
import com.fastcampus.ch4.domain.BoardDto;
import com.fastcampus.ch4.domain.SearchCondition;
import com.fastcampus.ch4.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class new_InfoServiceImpl implements new_InfoService {
    @Autowired
    new_InfoDao new_infodao;

    @Override
    public List<new_Info> getList() throws Exception {
        return new_infodao.selectAll_new();
    }
    
    @Override
    public List<new_Info> getsearchList(HashMap<String, String> map) throws Exception {
        return new_infodao.selectsearchAll_new(map);
    }
    
//    @Override
//    public List<new_Info> getSearchResultPage(SearchCondition sc) throws Exception {
//        return new_infodao.searchSelectPage_new(sc);
//    }
}
