<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page import="java.net.URLDecoder"%>
<c:set var="loginId"
	value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}" />
<c:set var="loginOutLink"
	value="${loginId=='' ? '/login/login' : '/login/logout'}" />
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}" />
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>YOUR TREE</title>
<link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
<link rel="stylesheet" href="<c:url value='/css/main.css'/>">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
</head>
<body>
	<div id="menu">
		<ul>
			<li id="logo"><img src="image/peach.png" alt="이미지" width="50px">
			</li>
			<li id="appname">YOUR TREE</li>
			<li><a href="<c:url value='/'/>">Home</a></li>
			<li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
			<li><a href="<c:url value='/register/add'/>">Sign in</a></li>
			<li><a href="<c:url value='/register/update'/>">update</a></li>
			<li><a href="<c:url value='/register/select'/>">select</a></li>
			<li><a href="<c:url value='/register/Allselect'/>">Allselect</a></li>
			<li><a href="<c:url value='/new_Info/new'/>">NEW</a></li>
			<li><a href="<c:url value='/board/list'/>">Board</a></li>
			<li><a href="<c:url value='/board/lovelist'/>">BookMark</a></li>
			<li><a href="<c:url value='/login/mypage'/>">MYPAGE</a></li>
			<li><a href=""><i class="fa fa-search"></i></a></li>
		</ul>
	</div>
	<div id="container">
		<h1>YOUR TREE</h1>
		<h5>YOUR TREE 앱 연동 웹 사이트</h5>
		<hr>
		<br> <br>
		<div class="conimg img1">
			<h3>1. 기존 앱과 연동</h3>
			<h4>앱에서 사진 연동(사진 보내기, 사진 불러오기)</h4>
			<div style="text-align: center;">
				<img src="image/p1.PNG" width="800px">
			</div>
			<br> <br>
		</div>
		<div class="conimg img2">
			<h3>2. 깔끔한 문서 제작</h3>
			<h4>사진 보정 기능 제공(사진 보정 및 필터 적용)</h4>
			<div style="text-align: center;">
				<img src="image/p2.png" width="800px">
			</div>
			<br> <br>
		</div>
		<div class="conimg img3">
			<h3>3. 소통 기능</h3>
			<h4>커뮤니티 게시판을 통한 소통</h4>
			<div style="text-align: center;">
				<img src="image/p3.PNG" width="800px">
			</div>
			<br> <br>
		</div>
		<div class="conimg img4">
			<h3>4. 게시판 사용자화</h3>
			<h4>게시글 즐겨찾기 기능 제공</h4>
			<div style="text-align: center;">
				<img src="image/p4.PNG" width="800px">
			</div>
			<br> <br>
		</div>
	</div>
</body>
</html>