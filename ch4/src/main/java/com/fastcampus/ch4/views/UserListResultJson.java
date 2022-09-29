package com.fastcampus.ch4.views;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.servlet.view.AbstractView;

import com.fastcampus.ch4.domain.User;

public class UserListResultJson extends AbstractView {

	SimpleDateFormat	DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<User> userList = (List<User>) model.get("userList");
		
		JSONArray jsonArray = new JSONArray();
		
		for(User user : userList) {
			JSONObject jsonObject = new JSONObject(user);
			if(user.getBirth() != null) {				
				String birth = DATE_FORMAT.format(user.getBirth());
				jsonObject.put("birth", birth);
			}else {
				jsonObject.put("birth", "");
			}
			if(user.getReg_date() != null) {
				String reg_date = DATE_FORMAT.format(user.getReg_date());
				jsonObject.put("reg_date", reg_date);
			}else {
				jsonObject.put("reg_date", "");
			}
			jsonArray.put(jsonObject);
		}
		
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().println(jsonArray.toString());
	}
}
