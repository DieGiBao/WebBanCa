<%@ page pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/css/main.css">
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/static/semantic.css">

<script
	src="${pageContext.servletContext.contextPath}/static/jquery.min.js"></script>

</head>
<body>
<div class="ui teal attached stackable inverted  menu">
  <div class="ui container">
    <a class="item" href="${pageContext.servletContext.contextPath}/home.htm">
      <i class="home icon"></i> Trang chủ
    </a>
    <a class="item">
      <i class="tty icon"></i> Tư vấn
    </a>
    <div class="ui simple dropdown item">
      Thêm
      <i class="dropdown icon"></i>
      <div class="menu" >
        <a class="item"><i class="question circle outline icon"></i> Câu hỏi thường gặp</a>
        <a class="item"><i class="money bill alternate outline icon"></i> Bán chạy </a>
        <a class="item"><i class="info circle icon"></i> Về trang web </a>
      </div>
    </div>
    <div class="left item">
      <div class="ui input"><input type="text" placeholder="Tìm kiếm ..."></div>
    </div>
    <c:if test="${user==null }">
    <form:form class="ui form"  action = "${pageContext.servletContext.contextPath}/login/loginPage.htm" >
      <a class="right item">
      <Button class="ui teal inverted button" ><i class="sign-in icon"></i> Đăng nhập </Button>
    	</a>
    </form:form>
    </c:if>
    <c:if test="${user!=null }">
      <a class="right item"> <i class="address book icon"></i> Xin chào ${user.tenKhach}
    	</a>
    	<form:form class="ui form"  action = "${pageContext.servletContext.contextPath}/login/doSignOut.htm" >
    	<a class="right item">
      	<Button class="ui teal inverted button" ><i class="sign-out icon"></i> Đăng xuất</Button>
    	</a>
    </form:form>
    </c:if>
  </div>
</div>
	
	<div class="parallax"		style="background-image: url('${pageContext.servletContext.contextPath}/static/images/main.jpg')"></div>
	<form:form class="ui form"  action = "${pageContext.servletContext.contextPath}/cart/xemGioHang.htm" >
	<button class="myCart" style="border: none" ><i class="shopping cart icon " ></i><div class="floating ui red label">${hoaDon.chiTietHoaDons.size()}</div></button>
	</form:form>
	<div class="ui container" > 
		<div class="myDivider"></div>
		<div class="ui grid">		
			<c:forEach var="p" items="${ca}">
			<form action="${pageContext.servletContext.contextPath}/cart/addCaVoHoaDon/${p.maCa}.htm" method="POST">
			  <div class="four wide column">
			  		<div class="ui special cards">
					  <div class="card">
					    <div class="blurring dimmable image">
					      <div class="ui inverted dimmer">
					        <div class="content">
					          <div class="center">													            
								<div class="ui vertical animated button" tabindex="0">
								 <div class="hidden content">
								 	<button type="submit" class="ui button"  style="position: relative;  transform: translateX(-22%)"> ${p.donGiaXuat}$ </Button>
										</div>
  									<div class="visible content">
								    <i class="shop icon "></i>
								  </div>
								</div>
					          </div>
					        </div>
					      </div>
			      		<img  class="ui fluid image" src ="${pageContext.servletContext.contextPath}/files/${p.anh}" >
					    </div>
					    <div class="content">
					      <a class="header teal">${p.loaiCa}</a>
							<div class="ui input">
  								<input name="soLuong" type="number" min="0" max="${p.soLuongConLai}" required >
							</div>
					    </div>
					  </div>
					</div>
			  </div>
			  </form>
			 </c:forEach> 
		</div>	 
	</div>
			 <div class="parallax"></div>
	<div class="ui inverted vertical footer segment">
    <div class="ui container">
      <div class="ui stackable inverted divided equal height stackable grid">
        <div class="three wide column">
          <h4 class="ui inverted header">About</h4>
          <div class="ui inverted link list">
            <a class="item">Sitemap</a>
            <a class="item">Contact Us</a>
            <a class="item">Religious Ceremonies</a>
            <a class="item">Gazebo Plans</a>
          </div>
        </div>
        <div class="three wide column">
          <h4 class="ui inverted header">Services</h4>
          <div class="ui inverted link list">
            <a class="item">Banana Pre-Order</a>
            <a class="item">DNA FAQ</a>
            <a class="item">How To Access</a>
            <a class="item">Favorite X-Men</a>
          </div>
        </div>
        <div class="seven wide column">
          <h4 class="ui inverted header">ABC</h4>
          <p>Extra space for a call to action inside the footer that could help re-engage users.</p>
        </div>
      </div>
    </div>
  </div>
	
	<script
		
		src="${pageContext.servletContext.contextPath}/static/semantic.js"></script>
	<script >
	$('.special.cards .image').dimmer({
		  on: 'hover'
	});
	</script>
</body>
</html>