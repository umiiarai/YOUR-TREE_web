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
    <title>회원 수정</title>
</head>
<body>
   <div>
    <div class="title">회원 수정</div>
    <div id="msg" class="msg"><form:errors path="id"/></div> 
    <label for="">비밀번호</label>
    <input class="input-field" type="text" name="pwd" placeholder="8~12자리의 영대소문자와 숫자 조합">
    <label for="">이름</label>
    <input class="input-field" type="text" name="name" placeholder="홍길동">
    <label for="">이메일</label>
    <input class="input-field" type="text" name="email" placeholder="example@fastcampus.co.kr"> 
    <label for="">생일</label>
    <input class="input-field" type="text" name="birth" placeholder="2020-12-31">
    <div class="sns-chk">
        <label><input type="checkbox" name="sns" value="facebook"/>페이스북</label>
        <label><input type="checkbox" name="sns" value="kakaotalk"/>카카오톡</label>
        <label><input type="checkbox" name="sns" value="instagram"/>인스타그램</label>
    </div>
    <button type="button" onclick="registerUpdate();">회원 수정</button>
   </div> 
   
   <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
   <script>
       function setMessage(msg, element){
            document.getElementById("msg").innerHTML = '<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>';
            if(element) {
                element.select();
            }
       }
       
       function registerUpdate(){
           data = {
             "pwd" : $('[name=pwd]').val(),
             "name" : $('[name=name]').val(),
             "email" : $('[name=email]').val(),
             "birth" : $('[name=birth]').val(),
             "sns" : $('[name=sns]').val()
           }
           
           $.ajax({
    async: true,
    type: "post",
    cache: false,
    url: "/ch4/register/update",
    data: data,
    dataType: "json",
    success: function(data, status) {
     alert("id : " + data.id + "\npwd : " + data.pwd + "\nname : " + data.name + "\nbirth : " + data.birth + "\nsns : " + data.sns);
    }
   });
      }
   </script>
</body>
</html>