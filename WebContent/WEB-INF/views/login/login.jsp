<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
	<style type="text/css">
*[id$=errors]{
color:red;
font-style: italic;
}
</style>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/semantic.css">
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/css/main.css">
</head>
<body>
<div class="myContainer">
<c:if test = "${message != null}">
         <div class="ui negative message">
  <i class="close icon"></i>
  <div class="header">
       ${message}
  </div>
  <p>Đã có lỗi xảy ra
</p></div>
      </c:if>
<form:form class="ui form"  action = "${pageContext.servletContext.contextPath}/login/doLogin.htm" method = "POST" 
modelAttribute="user">
	<h2 class="ui teal image header">
				<i class="icons"> <i class="user icon"></i>
				</i>
				<div class="content">Log-in to your account</div>
			</h2>
  <div class="field">
    <label>Email</label>
    <form:input path="email" /><form:errors path = "email"/>
  </div>
  <div class="field">
    <label>Password</label>
    <form:input type="password" path="matKhau" name="mật khẩu"/><form:errors path = "matKhau"/>
  </div>
 	 <br><button class="ui green inverted button" type="submit">Đăng nhập</button>
</form:form>
<a href="${pageContext.servletContext.contextPath}/login/signup.htm"><Button class="ui red inverted button"  > Tạo tài khoản</Button></a>

</div>

</body>
</html>