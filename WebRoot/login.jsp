<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="ie6 ielt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="ie7 ielt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="ie8"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--> <html lang="en"> <!--<![endif]-->

<head>
<base href="<%=basePath%>">

<title>用戶登錄</title>

<link rel="stylesheet" type="text/css" href="css/loginstyle2.css" />
</head>

<body>
<div class="container">
	<section id="content">
			<s:form action="login" namespace="/user" theme="simple" id="signupForm">
				<h1>Login Form</h1>
				<div>
					<s:textfield id="t1" name="user.account" size="20" maxlength="20" />
           		</div>	 
           		<div>
       				<s:password id="t2"  name="user.password" size="20" maxlength="20" />	 
           		</div>
				<div>
					<input type="submit" value="登入" />
				
				</div>
				<br/>
				<br/>
					<p>
						${message}
					</p>
					
			</s:form>
			<div class="button">
			<a href="#">NUSPOS</a>
		</div><!-- button -->
	</section><!-- content -->
</div>

</body>
</html>
