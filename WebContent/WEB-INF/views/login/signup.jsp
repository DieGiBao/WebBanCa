<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath}/">
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
<form:form class="ui form"  action = "${pageContext.servletContext.contextPath}/login/dosignup.htm" method = "POST" 
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
    <form:input type="password" path="matKhau" /><form:errors path = "matKhau"/>
  </div>
  <div class="field">
    <label>Xác nhận</label>
    <input  name="xacnhan" type = "password" />
  </div>
  <div class="field">
    <label>Tên khách</label>
    <form:input path="tenKhach" /><form:errors path = "tenKhach"/>
  </div>
  <div class="field">
    <label>Địa chỉ</label>
    <form:input path="diaChi" /><form:errors path = "diaChi"/>
  </div>
  <div class="field">
    <label>Điện thoại</label>
    <form:input type="number" min="0" path="dienThoai" /><form:errors path = "dienThoai"/>
  </div>
 	 <br><button class="ui green inverted button" type="submit" >Đăng kí</button>
</form:form>

</div>

</body>
</html>