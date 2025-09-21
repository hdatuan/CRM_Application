<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html lang="vi">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login Interface</title>
    <link rel="stylesheet" href="css/login.css" />
  </head>
  <body>
    <div class="login-container">
      <div class="login-form">
        <h1>Đăng nhập</h1>

        <form action="login" method= "post" >
          <div class="form-group">
            <input type="email" name="email" placeholder="Email" required />
          </div>

          <div class="form-group">
            <input type="password" name="password" placeholder="........" required />
          </div>
		
		  <input type="checkbox" name="remember" /> Nhớ mật khẩu <br /> <br>
          <button type="submit" class="signin-btn">Đăng nhập</button>
        </form>
      </div>

      <div class="welcome-section">
        <h2>CRM quản lý doanh nghiệp</h2>
        <p>Quản lý nhân viên, công việc và dự án một cách hiệu quả</p>
      </div>
    </div>

  </body>
</html>