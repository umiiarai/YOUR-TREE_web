package com.fastcampus.ch4.views;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.web.servlet.view.AbstractView;

import com.fastcampus.ch4.domain.User;

public class UserSelectJson extends AbstractView {

	SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) model.get("user");
		
		JSONObject jsonObject = new JSONObject(user);
		
		String birth = DATE_TIME_FORMAT.format(user.getBirth()); // 포조객체 값 가져올 때 user.get-()
		jsonObject.put("birth", birth);
		
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
		response.getWriter().println(jsonObject.toString());
	}
}
