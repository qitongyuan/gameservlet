<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
  String message=(String)request.getAttribute("message");
if(message!=null){
	out.write("<font color='red'>"+message+"</font>");
}
%>
<%
Integer times=(Integer) request.getAttribute("times");
if(times!=null){
	out.write(",你还有"+(5-times)+"次机会！");
}
%>
<form action="/Gameprocedures/GuessServlet" method="post">
请输入30以下的整数:<input type="text" name="luckNo" /><br/>
<% if(times!=null){ %>
<input type="hidden" name="times" value="<%=times%>"/>
<%} %>
<input type="submit" value="开始游戏"/>


</form>

</body>
</html>