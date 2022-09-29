package com.fastcampus.ch4.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fastcampus.ch4.dao.UserDao;
import com.fastcampus.ch4.domain.User;
import com.fastcampus.ch4.domain.new_Info;
import com.fastcampus.ch4.service.RegisterService;
import com.fastcampus.ch4.service.new_InfoService;

@Controller // ctrl+shift+o 자동 import
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	UserDao userDao;
	@Autowired
	RegisterService registerService;
    @Autowired
    new_InfoService new_infoservice;
	
	@GetMapping("/add") // 페이지를 호출할 때
	public String register() {
		return "registerForm"; // WEB-INF/views/registerForm.jsp
	}
	
	@PostMapping("/add") // 페이지에 정보를 받아올 때
	public String save(HttpServletRequest request, HttpServletResponse respone, Model m) throws Exception {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String birth = request.getParameter("birth");
		String sns = request.getParameter("sns");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(sdf.parse(birth).getTime());
		User user = new User(id, pwd, name, email, date, sns);

		int updateResult = registerService.userInsert(user);
		
		if(updateResult ==1) {
			User resultUser = userDao.selectUser(id);
			m.addAttribute(resultUser);
		} else {m.addAttribute(user);}
		
		User resultUser = userDao.selectUser(id);
		m.addAttribute(resultUser);

		// 2. DB에 신규회원 정보를 저장 
		return "registerInfo";
	}
	
	@GetMapping("/update")
	public String registerUpdate() {
		return "registerResult";
	}
	
	@PostMapping("/update")
	public String registerUpdateSave(HttpServletRequest request, HttpServletResponse respone, Model m, HttpSession session) throws Exception {
		String id = (String)session.getAttribute("id");
		
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String birth = request.getParameter("birth");
		String sns = request.getParameter("sns");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(sdf.parse(birth).getTime());
		User user = new User(id, pwd, name, email, date, sns);

		int updateResult = registerService.userUpdate(user);
		
		if(updateResult ==1) {
			User resultUser = userDao.selectUser(id);
			m.addAttribute("user", resultUser);
		} else {m.addAttribute("user", user);}

		// 2. DB에 신규회원 정보를 저장 
		return "userResultJson";
	}
	
	@GetMapping("/select")
	public String registerSelect() {
		return "registerSelect";
	}
	
	@PostMapping("/select")
	public String registerSelectSave(HttpServletRequest request, HttpServletResponse respone, Model m, HttpSession session) throws Exception {
		String id = request.getParameter("id");
		
		if(id != null) {
			User selectUser = registerService.userSelect(id);
			if(selectUser != null) {
				m.addAttribute("user", selectUser);
				return "userSelectJson";
			}
		}
		m.addAttribute("msg", "fail");
		return "userUpdateJson";
	}
	
	@GetMapping("/Allselect")
	public String registerAllSelect() {
		return "userList";
	}
	
	@PostMapping("/Allselect")
	public String registerAllSelectSave(HttpServletRequest request, HttpServletResponse respone, Model m, HttpSession session) throws Exception {
		String option = request.getParameter("option");
		String keyword = request.getParameter("name");
		if(keyword != null && option != null) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("option", option);
			map.put("keyword", keyword);
			List<User> userList = registerService.getList(map);
			if(userList != null && userList.size() > 0) {
				m.addAttribute("userList", userList);
				return "userListResultJson";
			}
		}
		
        return "userListResultJson";
	}

	private boolean isValid(User user) {
		return true;
	}
}