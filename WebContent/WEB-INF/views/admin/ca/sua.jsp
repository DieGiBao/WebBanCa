<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Admin</title>

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.css"
      integrity="sha256-9mbkOfVho3ZPXfM7W8sV2SndrGDuh7wuyLjtsWeTI1Q="
      crossorigin="anonymous"
    />

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css"
      integrity="sha256-+N4/V/SbAFiW1MPBCXnfnP9QSN3+Keu+NlB+0ev/YKQ="
      crossorigin="anonymous"
    />
	<style>
	@import url("https://fonts.googleapis.com/css?family=Open+Sans:400,700&display=swap");
:root {
  --tablet: 768px;
  --smallMonitor: 992px;
  --largeMonitor: 1200px;
  --font-family: 'Open Sans', sans-serif;
}

body {
  font-family: var(--font-family) !important;
}

body ::-webkit-scrollbar {
  width: 6px;
}

.ui.vertical.menu.sidebar-menu {
  margin-top: 40px !important;
  max-height: calc(100% - 40px) !important;
  height: calc(100% - 40px) !important;
}

.ui.vertical.menu.sidebar-menu .item i.icon {
  float: left;
  margin: 0em 0.5em 0em 0em;
}

.main-content {
  margin-top: 40px;
}

@media (min-width: 768px) {
  .ui.vertical.menu.sidebar-menu {
    visibility: visible;
    -webkit-transform: translate3d(0, 0, 0);
            transform: translate3d(0, 0, 0);
    width: 15rem !important;
  }
  .main-content {
    margin-left: 15rem;
  }
  .sidebar-menu-toggler {
    display: none !important;
  }
}
	</style>
	
	
<title>Trang Chủ</title>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<base href="${pageContext.servletContext.contextPath}/">
  </head>

  <body>
    <!-- sidebar -->
    <div class="ui sidebar inverted vertical menu sidebar-menu" id="sidebar">
      
      <div class="item">
        <div class="header">
          Administration
        </div>
        <div class="menu">
          <a class = "item" href="${pageContext.servletContext.contextPath}/admin/hoaDon.htm"> Hóa đơn </a> 
		<a class = "item" href="${pageContext.servletContext.contextPath}/admin/chiTietHoaDon.htm"> Chi tiết hóa đơn </a> 
		<a class = "item" href="${pageContext.servletContext.contextPath}/admin/user.htm"> User </a> 
		<a class = "item" href="${pageContext.servletContext.contextPath}/admin/ca.htm"> Cá </a>
        </div>
      </div>        
    </div>

    <!-- sidebar -->
    <!-- top nav -->

    <nav class="ui top fixed inverted menu">
      <div class="left menu">
        <a href="#" class="sidebar-menu-toggler item" data-target="#sidebar">
          <i class="sidebar icon"></i>
        </a>
        <a href="${pageContext.servletContext.contextPath}/admin/index.htm" class="header item">
          Trang chủ
        </a>
      </div>

      <div class="right menu">
       <form:form class="ui form"  action = "${pageContext.servletContext.contextPath}/login/doSignOut.htm" >
    		<a class="right item">
      			<Button class="ui button" ><i class="sign-out icon"></i> Đăng xuất</Button>
    		</a>
    	</form:form>
      </div>
    </nav>

    <!-- top nav -->

    <div class="pusher">
      <div class="main-content">
        <div class="ui grid stackable padded">
          <div class="column">
          	<form:form action="${pageContext.servletContext.contextPath}/admin/ca/sua.htm" modelAttribute="ca" enctype="multipart/form-data">
            <table class="ui celled padded table">            
					
  				<thead>
    				<tr>
					    <th>Mã cá</th>
					    <th>Loại cá</th>
					    <th>Đơn giá nhập</th>
					    <th>Đơn giá xuất</th>
					    <th>Ảnh</th>
					    <th>Ghi chú</th>
					    <th>Kích cỡ</th>
					    <th>Màu sắc</th>
					    <th>Số lượng còn lại</th>
					    <th>Số lượng đã bán</th>
			  </tr>
			  </thead>
			  <tbody>			  
			    <tr>
			      <td class="single line">	
			      <div class="ui input">
  					<form:input path="maCa" readonly="true" style="max-width: 40px"/>
					</div>     
			      </td>
			      <td>
			      <div class="ui input" style="max-width: 150px">
					<form:input path="loaiCa" />
					</div><form:errors path = "loaiCa"/> 
			      </td>
			      <td>
			      <div class="ui input" style="max-width: 100px">
					<form:input path="donGiaNhap" type="number" min= "0" /></div><form:errors path = "donGiaNhap"/> 
			      </td>
			      <td>
			      <div class="ui input" style="max-width: 100px">
					<form:input path="donGiaXuat" type="number" min ="0"/></div><form:errors path = "donGiaXuat"/> 
			      </td>
			      <td>			      
					<input name="photo" type="file" /><form:errors path = "anh"/> 
			      </td>
			      <td>
			      <div class="ui input" style="max-width: 100px">
					<form:input path="ghiChu"  /></div>
			      </td>
			      <td>
			      <div class="ui input" style="max-width: 50px">
					<form:input path="kichCo" type="number" min ="0" /></div><form:errors path = "kichCo"/> 
			      </td>
			      <td>
			      <div class="ui input" style="max-width: 100px">
					<form:input path="mauSac" /></div><form:errors path = "mauSac"/> 
			      </td>
			      <td>
			      <div class="ui input" style="max-width: 80px">
					<form:input path="soLuongConLai" type="number" min = "0"/></div><form:errors path = "soLuongConLai"/> 
			      </td>
			      <td>
			      <div class="ui input" style="max-width: 80px" >
					<form:input path="soLuongDaBan" type ="number" min= "0"/></div><form:errors path = "soLuongDaBan"/> 
			      </td>			      
			      	<button class="ui blue inverted button" > Sửa</button>
			      <td>
			      </td>
			      </tr>
			  </tbody>
			  <tfoot>		        
			  </tfoot>
			</table>
			</form:form>
          </div>
        </div>
      </div>
    </div>

    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"
      integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.4.1/semantic.min.js"
      integrity="sha256-t8GepnyPmw9t+foMh3mKNvcorqNHamSKtKRxxpUEgFI="
      crossorigin="anonymous"
    ></script>
  </body>
</html>
