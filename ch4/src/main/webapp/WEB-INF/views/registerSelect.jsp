<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page import="java.net.URLDecoder"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <style>
        * { box-sizing:border-box; }
        form {
            width:400px;
            height:600px;
            display : flex;
            flex-direction: column;
            align-items:center;
            position : absolute;
            top:50%;
            left:50%;
            transform: translate(-50%, -50%) ;
            border: 1px solid rgb(89,117,196);
            border-radius: 10px;
        }
        .input-field {
            width: 300px;
            height: 40px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
            padding: 0 10px;
            margin-bottom: 10px;
        }
        
        label {
            width:300px;
            height:30px;
            margin-top :4px;
        }
        button {
            background-color: rgb(89,117,196);
            color : white;
            width:300px;
            height:50px;
            font-size: 17px;
            border : none;
            border-radius: 5px;
            margin : 20px 0 30px 0;
        }
        .title {
            font-size : 50px;
            margin: 40px 0 30px 0;
        }
        .msg {
            height: 30px;
            text-align:center;
            font-size:16px;
            color:red;
            margin-bottom: 20px;
        }
        
        .sns-chk {
            margin-top : 5px; 
        }
    </style>
    <title>회원 조회</title>
</head>
<body>
   <div>
    <div class="title">회원 조회</div>
    
    <label for="">아이디</label>
    <input class="input-field" type="text" id="id">
    <button type="button" onclick="registerSelect();">회원 조회</button> <br>
    <label for="">비밀번호</label>
    <input class="input-field" type="text" id="pwd" readonly><br>
    <label for="">이름</label>
    <input class="input-field" type="text" id="name" readonly><br>
    <label for="">이메일</label>
    <input class="input-field" type="text" id="email" readonly> <br>
    <label for="">생일</label>
    <input class="input-field" type="text" id="birth" readonly><br>
    <label for="">sns</label>
    <input class="input-field" type="text" id="sns" readonly>
   </div> 
   
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
			    url: "/ch4/register/select",
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
</body>
</html>