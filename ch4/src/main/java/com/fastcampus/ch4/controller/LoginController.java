package com.fastcampus.ch4.controller;

import java.net.URLEncoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fastcampus.ch4.dao.*;
import com.fastcampus.ch4.domain.*;
import com.fastcampus.ch4.service.RegisterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserDao userDao;
    RegisterService registerservice;

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 1. 세션을 종료
        session.invalidate();
        // 2. 홈으로 이동
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(String id, String pwd, String toURL, boolean rememberId,
                        HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 1. id와 pwd를 확인
        if(!loginCheck(id, pwd)) {
            // 2-1   일치하지 않으면, loginForm으로 이동
            String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");

            return "redirect:/login/login?msg="+msg;
        }
        // 2-2. id와 pwd가 일치하면,
        //  세션 객체를 얻어오기
        HttpSession session = request.getSession();
        //  세션 객체에 id를 저장
        session.setAttribute("id", id);

        if(rememberId) {
            //     1. 쿠키를 생성
            Cookie cookie = new Cookie("id", id); // ctrl+shift+o 자동 import
//		       2. 응답에 저장
            response.addCookie(cookie);
        } else {
            // 1. 쿠키를 삭제
            Cookie cookie = new Cookie("id", id); // ctrl+shift+o 자동 import
            cookie.setMaxAge(0); // 쿠키를 삭제
//		       2. 응답에 저장
            response.addCookie(cookie);
        }
//		       3. 홈으로 이동
        toURL = toURL==null || toURL.equals("") ? "/" : toURL;

        return "redirect:"+toURL;
    }

    private boolean loginCheck(String id, String pwd) {
        User user = null;

        try {
            user = userDao.selectUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return user!=null && user.getPwd().equals(pwd);
    }
    
    @GetMapping("/mypage")
    public String mypage(Model m, SearchCondition sc, HttpServletRequest request) {
        //if(!loginCheck(request))
        //    return "redirect:/login/login?toURL="+request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동

        return "mypage"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }
    // 로그인 확인
    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서(false는 session이 없어도 새로 생성하지 않는다. 반환값 null)
        HttpSession session = request.getSession(false);
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session!=null && session.getAttribute("id")!=null;
    }

	/*
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
	}*/
    
    @PostMapping("/mypage")
    public String mypage(HttpServletRequest request, HttpServletResponse response,  Model m, HttpSession session) throws Exception {
    	String id = request.getParamer("id");
    	if(id != null) {
			User selectUser = registerservice.userSelect(id);
			if(selectUser != null) {
				m.addAttribute("user", selectUser);
				return "userSelectJson";
			}
		}
		m.addAttribute("msg", "fail");
		return "userUpdateJson";
    }
}