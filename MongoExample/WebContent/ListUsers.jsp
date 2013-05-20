<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="userbase.data.*,java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
img {
   width: 100px;
   max-height: 100%;
}
div.header {
	background-color: #FFA500;
	text-align: center;
	margin-bottom: 0px;
}

div.content {
	margin: 0px;
	background-color: #FFD700;
	padding-left: 10px;
	padding-right: 10px;
	padding-top: 5px;
	padding-bottom: 5px;
}

td.text {
	vertical-align: top;
	padding-left: 5px;
}
td.photo {
	vertical-align: top;
	padding: 0px;
	margin: 0px;
}
td.link {
	text-align: center;
	vertical-align: top;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Database</title>
</head>
<body>
	<div id="userForm" style="width: 940px;">
		<div class="header">
			<h2 style="margin-bottom: 0px;">User Database</h2>
		</div>
		<div id="userData" class="content">
			<table border="1">
				<tr>
				    <th style="width: 100px;">Photo</th>
					<th style="width: 150px;">User name</th>			
					<th style="width: 150px;">First name</th>
					<th style="width: 150px;">Last name</th>
					<th style="width: 150px;">@Email</th>
					<th style="padding-left: 10px; padding-right: 10px;">Edit</th>
					<th style="padding-left: 10px; padding-right: 10px;">Remove</th>
					<td style="padding-left: 10px; padding-right: 10px;"><a href="/UpsertUser.jsp">Add</a></td>
				</tr>
				<% List<UserProfile> users = UserProfileDAO.getInstance().getAllUsers();
				   for (UserProfile user: users) {
				%>
				<tr>
					<td class="photo"><% if (user.getPhoto() != null) {%>
					    <img alt="" src="/UserPhoto?user=<%=user.getUserName()%>">
					    <%} else {%>
					    <img alt="" src="/Unknown-person.gif">
					    <%} %>
					</td>
					<td class="text"><%=user.getUserName()%></td>				
					<td class="text"><%=user.getFirstName()%></td>
					<td class="text"><%=user.getLastName()%></td>
					<td class="text"><%=user.getEmail()%></td>
					<td class="link"><a href="/UpsertUser.jsp?user=<%=user.getUserName()%>">Edit</a></td>
					<td class="link"><a href="/RemoveUser?user=<%=user.getUserName()%>">Remove</a></td>
				</tr>
				<%}%>
			</table>
		</div>
	</div>
</body>
</html>