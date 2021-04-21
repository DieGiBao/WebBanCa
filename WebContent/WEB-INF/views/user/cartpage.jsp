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
	
	<div class="parallax" style="background-image: url('${pageContext.servletContext.contextPath}/static/images/main.jpg')"></div>
	<div class="pusher">
      <div class="main-content">
        <div class="ui grid stackable padded">
          <div class="column">
            <table class="ui celled padded table">
  				<thead>
    			<tr>
					    <th>Loài cá </th>
					    <th>Hình</th>
					    <th>Số lượng</th>
					    <th>Đơn giá</th>
					    <th>Thành tiền</th>
			  </tr></thead>
			  <tbody>			  
			<c:forEach var="p" items="${chiTietHoaDon}">
			    <tr>
			      <td class="single line">
			      ${p.ca.loaiCa}
			      </td>
			      <td>
			      <img  class="ui fluid image" src ="${pageContext.servletContext.contextPath}/files/${p.ca.anh}" >
			      </td>
			      <td>
			      ${p.soLuong}
			      </td>
			      <td>
			      ${p.donGia}
			      </td>
			      <td>
			      ${p.thanhTien}
			      </td>
			      <td>
			      <a href="${pageContext.servletContext.contextPath}/cart/xoa/${p.maChiTietHoaDon}.htm"><Button class="ui red inverted button"  > Xóa</Button></a>
			      </td>
			      </tr>
			    </c:forEach>
			  </tbody>
			  <tfoot>	    
			    <a href="${pageContext.servletContext.contextPath}/cart/thanhToan/${tongTien}.htm"><Button class="ui green inverted button"  > Thanh toán</Button></a>				
				<a class="right item"> <i class="money bill alternate outline icon"></i>${tongTien} Tổng tiền </a>
			  </tfoot>
			</table>			
          </div>
        </div>
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