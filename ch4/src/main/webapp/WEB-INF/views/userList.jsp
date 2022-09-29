<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true"%>
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
<body>
<script>
	function registerAllSelect(){
	    data = {"name" : $('[name=keyword]').val(),
	    		"option" : $('[name=option]').val()}
	    
	    $.ajax({
				async: true,
				type: "post",
				cache: false,
				url: "/ch4/register/Allselect",
				data: data,
				dataType: "json",
				success: function(data, status) {
					$("#bodyUserList").empty();
					for(var i=0; i<data.length; i++){
						var html = "<tr>";
						html += "<td>" + data[i].id + "</td>";
						html += "<td>" + data[i].pwd + "</td>";
						html += "<td>" + data[i].name + "</td>";
						html += "<td>" + data[i].email + "</td>";
						html += "<td>" + data[i].birth + "</td>";
						html += "<td>" + data[i].sns + "</td>";
						html += "<td>" + data[i].reg_date + "</td>";
						html +="</tr>";
						$("#bodyUserList").append(html);
					}
				}
			});
	}
</script>
<div style="text-align:center">
    <div class="board-container">
        <div class="search-container">
            <select class="search-option" name="option">
                <option value="sname">이름</option>
                <option value="sbirth">생일</option>
            </select>
            <input type="text" name="keyword" class="search-input" type="text" placeholder="검색어를 입력해주세요">
            <button type="button" class="search-button" onclick="registerAllSelect();">검색</button>
        </div>

        <table>
            <tr>
                <th class="writer">아이디</th>
                <th class="writer">패스워드</th>
                <th class="writer">이름</th>
                <th class="writer">이메일</th>
                <th class="regdate">생일</th>
                <th class="writer">sns</th>
                <th class="regdate">등록날짜</th>
            </tr>
            <tbody id="bodyUserList">
            </tbody>
        </table>
    </div>
</div>
</body>
</html>