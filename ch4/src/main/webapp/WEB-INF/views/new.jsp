<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: "Noto Sans KR", sans-serif;
        }
        a {
            text-decoration: none;
            color: black;
        }
        button,
        input {
            border: none;
            outline: none;
        }
        .board-container {
            width: 60%;
            height: 1200px;
            margin: 0 auto;
            /* border: 1px solid black; */
        }
        .search-container {
            background-color: rgb(253, 253, 250);
            width: 100%;
            height: 110px;
            border: 1px solid #ddd;
            margin-top : 10px;
            margin-bottom: 30px;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .search-form {
            height: 37px;
            display: flex;
        }
        .search-option {
            width: 100px;
            height: 100%;
            outline: none;
            margin-right: 5px;
            border: 1px solid #ccc;
            color: gray;
        }
        .search-option > option {
            text-align: center;
        }
        .search-input {
            color: gray;
            background-color: white;
            border: 1px solid #ccc;
            height: 100%;
            width: 300px;
            font-size: 15px;
            padding: 5px 7px;
        }
        .search-input::placeholder {
            color: gray;
        }
        .search-button {
            /* 메뉴바의 검색 버튼 아이콘  */
            width: 20%;
            height: 100%;
            background-color: rgb(22, 22, 22);
            color: rgb(209, 209, 209);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 15px;
        }
        .search-button:hover {
            color: rgb(165, 165, 165);
        }
        table {
            border-collapse: collapse;
            width: 100%;
            border-top: 2px solid rgb(39, 39, 39);
        }
        tr:nth-child(even) {
            background-color: #f0f0f070;
        }
        th,
        td {
            width:300px;
            text-align: center;
            padding: 10px 12px;
            border-bottom: 1px solid #ddd;
        }
        td {
            color: rgb(53, 53, 53);
        }
        .no      { width:150px;}
        .title   { width:50%;  }
        td.title   { text-align: left;  }
        td.writer  { text-align: left;  }
        td.viewcnt { text-align: right; }
        td.title:hover {
            text-decoration: underline;
        }
        .paging {
            color: black;
            width: 100%;
            align-items: center;
        }
        .page {
            color: black;
            padding: 6px;
            margin-right: 10px;
        }
        .paging-active {
            background-color: rgb(216, 216, 216);
            border-radius: 5px;
            color: rgb(24, 24, 24);
        }
        .paging-container {
            width:100%;
            height: 70px;
            display: flex;
            margin-top: 50px;
            margin : auto;
        }
        .btn-write {
            background-color: rgb(236, 236, 236); /* Blue background */
            border: none; /* Remove borders */
            color: black; /* White text */
            padding: 6px 12px; /* Some padding */
            font-size: 16px; /* Set a font size */
            cursor: pointer; /* Mouse pointer on hover */
            border-radius: 5px;
            margin-left: 30px;
        }
        .btn-write:hover {
            text-decoration: underline;
        }
    </style>
</head>
<script>
	$(document).ready(function(){
 		fn_selectlist();
 		tempSearch();
	});

	
	function fn_selectlist() {
		data = {"name" : "dtdt", "gender" : "dfdf"}
		
	    $.ajax({
			async: true,
			type: "post",
			url: "/ch4/new_Info/new",
			data: data,
			dataType: "json",
			success: function(data, status) {
				$("#inforTB").empty();
				$("#inforTB2").empty();

				for(var i=0; i<data.length; i++){
					var html = "<tr>";
					html += "<td>" + data[i].name + "</td>";
					html += "<td>" + data[i].old + "</td>";
					html += "<td>" + data[i].gender + "</td>";
					html +="</tr>";
					$("#inforTB").append(html);
					$("#inforTB2").append(html);
				}
			}
		})
	}
	
	function SearchSelect(){
	    data = {"name" : $('[name=keyword]').val(),
	    		"option" : $('[name=option]').val()}
	    
	    $.ajax({
				async: true,
				type: "post",
				cache: false,
				url: "/ch4/new_Info/search",
				data: data,
				dataType: "json",
				success: function(data, status) {
					$("#inforTB").empty();

					for(var i=0; i<data.length; i++){
						var html = "<tr>";
						html += "<td>" + data[i].name + "</td>";
						html += "<td>" + data[i].old + "</td>";
						html += "<td>" + data[i].gender + "</td>";
						html +="</tr>";
						$("#inforTB").append(html);
					}
				}
			});
	}
	
	function formSearch() {
		data = $("#what").serialize();
		alert(data);
		
	    $.ajax({
			async: true,
			type: "post",
			cache: false,
			url: "/ch4/new_Info/form",
			data: data,
			dataType: "json",
			success: function(data, status) {
				$("#inforTB2").empty();

				for(var i=0; i<data.length; i++){
					var html = "<tr>";
					html += "<td>" + data[i].name + "</td>";
					html += "<td>" + data[i].old + "</td>";
					html += "<td>" + data[i].gender + "</td>";
					html +="</tr>";
					$("#inforTB2").append(html);
				}
			}
		});
	}
</script>


<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fa fa-search"></i></a></li>
    </ul>
</div>

<h3> AJAX 이용하기 </h3>

<div class="search-container">
	<select class="search-option" name="option">
	    <option value="nname">이름</option>
	    <option value="nold">나이</option>
	    <option value="ngender">성별</option>
	</select>
	<input type="text" name="keyword" class="search-input" type="text" placeholder="검색어를 입력해주세요">
	<button type="button" class="search-button" onclick="SearchSelect();">검색</button>
</div>
<table>
	<colgroup>
		<col width = "10%">
		<col width = "30%">
		<col width = "20%">
	</colgroup>
	<thead>
		<th>이름</th>
		<th>나이</th>
		<th>성별</th>
	</thead>
	<tbody id = "inforTB">
	</tbody>
</table>

<br><hr>
<h3> 페이지에서 값 넘기기</h3>
<table>
	<colgroup>
		<col width = "10%">
		<col width = "30%">
		<col width = "20%">
	</colgroup>
	<thead>
		<th>이름</th>
		<th>나이</th>
		<th>성별</th>
	</thead>
	<tbody>
		<c:if test="${infolist==null}" >
		<tr>
            <td>dfd</td>
            <td>dd</td>
            <td>dd</td>
        </tr>
		</c:if>
		<c:forEach var="new_Info" items="${infolist}">
                <tr>
                    <td>${new_Info.name}</td>
                    <td>${new_Info.old}</td>
                    <td>${new_Info.gender}</td>
                </tr>
 		</c:forEach>
	</tbody>
</table>

<br><hr>
<h3> serialize 이용하기</h3>
<div class="search-container">
	<form id  = "what" >
		<select class="search-option" name = "option2">
		    <option value="nname">이름</option>
		    <option value="nold">나이</option>
		    <option value="ngender">성별</option>
		</select>
		<input type="text" name = "keyword2" class="search-input" placeholder="검색어를 입력해주세요">
		<button type="button" class="search-button" onclick="formSearch();">검색</button>
	</form>
</div>
<table>
	<colgroup>
		<col width = "10%">
		<col width = "30%">
		<col width = "20%">
	</colgroup>
	<thead>
		<th>이름</th>
		<th>나이</th>
		<th>성별</th>
	</thead>
	<tbody id = "inforTB2">
	</tbody>
</table>

<h3> JQuery TABLE</h3>

<!-- jQuery TABLE -->
<script src="http://ajax.microsoft.com/ajax/jquery.templates/beta1/jquery.tmpl.min.js"></script>
<script type="text/javascript">
	function tempSearch() {

	    $.ajax({
			async: true,
			type: "post",
			cache: false,
			url: "/ch4/new_Info/new",
			data: data,
			dataType: "json",
			success: function(data, status) {
				$("#inforTB3").empty();
				var tmpl = $("#listTemplate").tmpl(data).appendTo("#inforTB3");
				
			}
		});
	}
</script>

<script id="listTemplate" type="text/x-jquery-templ"> 
	<tr>
		<td>\${name}</td>
		<td>\${old}</td>
		<td>\${gender}</td>
	</tr>
</script>

<table>
	<colgroup>
		<col width = "10%">
		<col width = "30%">
		<col width = "20%">
	</colgroup>
	<thead>
		<th>이름</th>
		<th>나이</th>
		<th>성별</th>
	</thead>
	<tbody id = "inforTB3">
	</tbody>
</table>

</body>
</html>