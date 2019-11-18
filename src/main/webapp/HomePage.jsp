<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel = "stylesheet" type="text/css" href= "EmployeeReimbursement.css">
</head>
<body>
<script src = "EmployeeReimbursement.js"></script>
<div class = "flex-container">
Sign In
<br>
<br>
<!--  <form name = "loginForm" onsubmit="login(event)"> -->
<form name = "loginForm" action="/Project1/users" method="get">

Username:<br>
<input type="text" id="username"  placeholder="username">
  <br><br>
Password:<br>
  <input type="password" id="password" placeholder="password">
  <br><br>
  <input type="submit" value="Submit">
</form>
</div>
</body>
</html>