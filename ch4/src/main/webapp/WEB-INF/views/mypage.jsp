<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.net.URLDecoder"%>
<c:set var="loginId"
	value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}" />
<c:set var="loginOutLink"
	value="${loginId=='' ? '/login/login' : '/login/logout'}" />
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="<c:url value='/css/mypage.css'/>">
</head>
<body>
	<div id="menu">
		<ul>
			<li id="logo"><img src="http://localhost:8080/ch4/image/peach.png" alt="이미지" width="50px">
			</li>
			<li id="logo">YOUR TREE</li>
			<li><a href="<c:url value='/'/>">Home</a></li>
			<li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
			<li><a href="<c:url value='/register/add'/>">Sign in</a></li>
			<li><a href="<c:url value='/register/update'/>">update</a></li>
			<li><a href="<c:url value='/register/select'/>">select</a></li>
			<li><a href="<c:url value='/register/Allselect'/>">Allselect</a></li>
			<li><a href="<c:url value='/new_Info/new'/>">NEW</a></li>
			<li><a href="<c:url value='/board/list'/>">Board</a></li>
			<li><a href="<c:url value='/login/mypage'/>">MYPAGE</a></li>
			<li><a href=""><i class="fa fa-search"></i></a></li>
		</ul>
	</div>
	<form>
		<table>
				<br> <br>
				<tr>
					<th colspan="2"><div class="title" id="title">마이 페이지</div></th>
				</tr>
				<tr>
					<td><label for="">아이디</label></td>
					<td><input class="input-field" type="text" id="id"></td>
					<br>
					<br>
				</tr>
				<tr>
					<td><label for="">비밀번호</label></td>
					<td><input class="input-field" type="text" id="pwd" readonly></td>
					<br>
					<br>
				</tr>
				<tr>
					<td><label for="">이름</label></td>
					<td><input class="input-field" type="text" id="name" readonly></td>
					<br>
					<br>
				</tr>
				<tr>
					<td><label for="">이메일</label></td>
					<td><input class="input-field" type="text" id="email" readonly></td>
					<br>
					<br>
				<tr>
				<tr>
					<td><label for="">생일</label></td>
					<td><input class="input-field" type="text" id="birth" readonly></td>
					<br>
					<br>
				</tr>
				<tr>
					<td><label for="">SNS</label></td>
					<td><input class="input-field" type="text" id="sns" readonly></td>
					<br>
					<br>
				</tr>
				<tr>
					<br>
					<td><button type="button" onclick="registerSelect();">
							<a href="<c:url value='/register/update'/>">수정하기</a>
						</button></td>
					<td><button type="button" onclick="registerSelect();">탈퇴하기</button>
					</td>
				</tr>
		</table>
		<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
		<script>
       function setMessage(msg, element){
            document.getElementById("msg").innerHTML = '<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>';
            if(element) {
                element.select();
            }
       }
       
       function registerSelect(){
           data = {"id" : $('[id=id]').val()} // 아이디를 넣음 => ajax으로 이동
           
           $.ajax({
			    async: true,
			    type: "post",
			    cache: false,
			    url: "/ch4/login/mypage",
			    data: data,
			    dataType: "json",
			    success: function(data, status) {
			    	if(data.msg != null) {
			    		alert(data.msg);
			    	} else {
			    		$('#pwd').val(data.pwd);
			    		$('#name').val(data.name);
			    		$('#email').val(data.email);
			    		$('#birth').val(data.birth);
			    		$('#sns').val(data.sns);
			    	}
			    }
   			});
      	}
   </script>
	</form>
</body>
</html>