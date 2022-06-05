<%--
  Created by IntelliJ IDEA.
  User: Pavlo
  Date: 2022-05-25
  Time: 오전 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>사물함 관리</title>
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

      h1{
        width: auto;
        text-align: center;
        padding: 20px 0px;

      }

    </style>
  </head>
  <body>
  <form action="/mng/mngLock">
    <div class = "container">
      <a href="/mng/mngLock/create"><h1>사물함 생성</h1></a> <br>
      <a href="/mng/mngLock/register"><h1>사물함 신청</h1></a>
    </div>
  </form>
  </body>
</html>
