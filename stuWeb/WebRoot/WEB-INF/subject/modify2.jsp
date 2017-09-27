<%@ page language="java" import="java.util.*,entity.Student"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'add.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css"
	href="bootstrap/css/bootstrap.min.css">

<style>
#main {
	width: 700px;
	margin: 10px auto;
}

.form {
	width: 260px;
}

.form label {
	float: left;
	width: 60px;
}

.form .value {
	float: left;
	width: 200px;
}

.form div {
	clear: both;
	margin-top: 30px;
	overflow: hidden;
}

.btn {
	clear: both;
	text-align: center;
}

#save {
	clear: both;
	text-align: center;
	overflow: hidden;
}

#save input {
	
}

.form span {
	margin: 0 10px;
	font-size: 14px;
	font-weight: bold;
}

#main ul {
	overflow: hidden
}

#main ul li {
	list-style: none;
	float: left;
	margin-left: 60px;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$("#save input").click(function() {

			var subs = "";
			$(".form").each(function(index, element) {

				subs += $(this).find("[name=id]").val() + ",";

				subs += $(this).find("[name=name]").val() + ";";

			})
			subs = subs.substring(0, subs.length - 1);
			location.href = "sub?type=modify2&subs=" + subs;
		})

	})
</script>

</head>

<body>

	<div id="main">
		<ul>
			<c:forEach items="${subs}" var="sub">
				<li>
					<form class="form" action="sub" method="post">
						<input type="hidden" name="id" value="${sub.id }" />
						<div>
							<label>姓名</label><input type="text" name="name"
								class="form-control value" placeholder="请输入姓名"
								value="${sub.name}" />
						</div>

						<div class="btn">
							<input type="submit" value="保存" class="btn btn-danger" />
						</div>
					</form>
				</li>
			</c:forEach>
		</ul>
		<div id="save">
			<input type="button" value="保存" class="btn btn-lg btn-primary" />
		</div>

	</div>


</body>
</html>
