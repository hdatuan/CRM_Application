<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login Interface</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
  </head>
  <body>
    <div class="login-container">
      <div class="login-form">
        <h1>Đăng nhập</h1>

        <form action="login" method= "post" >
          <div class="form-group">
            <input type="email" name="email" 
            			placeholder="Email" 
            			value="${not empty email ? email : '' }"
            			required />
          </div>

          <div class="form-group">
            <input type="password" name="password" placeholder="........" required />
          </div>
		
		  <input type="checkbox" name="remember" /> Nhớ mật khẩu <br />
		  	<c:if test="${not empty loginResult}">
    				<div style="color:red">${loginResult}</div>
			</c:if>
		  
          <button type="submit" class="signin-btn">Đăng nhập</button>
        </form>
        
      </div>

      <div class="welcome-section">
        <h2>CRM quản lý doanh nghiệp</h2>
        <p>Quản lý nhân viên, công việc và dự án một cách hiệu quả</p>
      </div>
    </div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  </body>
</html>