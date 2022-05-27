<%--
  Created by IntelliJ IDEA.
  User: Pavlo
  Date: 2022-05-25
  Time: 오후 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>사물함 생성</title>
    <style>

        * {
            box-sizing: border-box;
            padding: 0;
            margin: 0;
        }

        .container{
            width: 500px;
            height: 380px;
            background-color: rgb(221, 221, 221);
            margin: 0 auto;
        }

        h1,h3{
            width: auto;
            text-align: center;
            padding: 20px 0px;
        }

        .container .itembox{
            width: 400px;
            margin: 0 50px;
            padding: 20px 50px;
            border: 1px solid rgb(141, 141, 141);
        }

        .item1,.item2,.myButton{
            height: 50px;
            text-align: center;

        }

    </style>
</head>
<body>
<form class="container" action="/mng/mngLock/create" method="post">
    <h1>사물함 생성</h1>
    <h3>사물함 정보를 입력하시오</h3>
    <div class="itembox">
        <div class="item1">
            기관명
            <select name="facility">
                <option>소공 체육센터</option>
                <option>소공 종합운동장</option>
                <option>객소공 종합운동장</option>
                <option>소공 스포츠센터</option>
                <option>객소공 스포츠센터</option>
            </select><br>
        </div>
        <div class="item2">사물함 비용 : <input type="number" name="cost"></div>
        <div class="myButton">
            <input type="submit" value="생성" name = "action" />
            <input type="submit" value="돌아가기" name = "action" />
        </div>
    </div>
</form>

</body>
</html>
