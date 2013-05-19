<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="userbase.data.*"%>
<!DOCTYPE html>    
<html>
<head>
<style type="text/css">

  div.header {
    background-color:#FFA500;
    text-align: center;
    margin-bottom: 0px;
  }
    div.content {
    margin: 0px;
    background-color:#FFD700;
    padding-left: 10px;
    padding-right: 10px;
    padding-top: 5px;
    padding-bottom: 5px;
  }
  input {
    min-width:95%;
    width: 95%; 
    padding-bottom: 4px;
  }
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
<%
   String user = request.getParameter("user");
   UserProfile userProfile = null;
   if (user != null) {
	   userProfile = UserProfileDAO.getInstance().getUserByName(user);
   }
   if (userProfile != null) {
%> Edit <%= userProfile.getUserName() %> profile
<% } else {
%>
   Add new user profile
<% 	
   }
%>
</title>
  <script type="text/javascript">
        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    document.getElementById('photoPreview').setAttribute('src', e.target.result);
                };

                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>
</head>
<body>
  <form action="UserUpsertServlet" method="post" name="uploadForm" enctype="multipart/form-data">
  <div id="userForm" style="width:400px;">
  <div class="header">
  <h2 style="margin-bottom:0px;">
<%
   if (userProfile != null) {
%> Edit <%= userProfile.getUserName() %> profile
<% } else {
%>
   Add new user profile<% 	
   }
%></h2>
  </div>
  <div id="userData" class="content">
    <div >
    <div><label for="user">User name:</label></div>
    <div><input id="user" type="text" name="<%= UserProfileDAO.USER_NAME %>" required="required"
    <% if (userProfile != null)  {%>
       readonly="readonly" value="<%=  userProfile.getUserName()%>"    
    <% }%>></div>
    </div>
    <div >
    <div><label for="email">Email:</label></div>
    <div><input id="email" type="text" name="<%= UserProfileDAO.EMAIL %>"
    <% if (userProfile != null && userProfile.getEmail()!=null)  {%>
       value="<%=  userProfile.getEmail()%>"    
    <% }%>
    ></div>
    </div>
    <div >
    <div><label for="firstname">First name:</label></div> 
    <div><input id="firstname" type="text" name="<%= UserProfileDAO.FIRSTNAME %>"
    <% if (userProfile != null && userProfile.getFirstName()!=null)  {%>
       value="<%=  userProfile.getFirstName()%>"    
    <% }%>
    ></div>
    </div>
    <div >
    <div><label for="lastname">Last name:</label></div> 
    <div><input id="lastname" type="text" name="<%= UserProfileDAO.LASTNAME %>"
    <% if (userProfile != null && userProfile.getLastName()!=null)  {%>
       value="<%=  userProfile.getLastName()%>"    
    <% }%>
    ></div>
    </div>
 
    <div >
    <div><label for="photo">Photo:</label> </div> 
    <div><input id="photo" type="file" name="<%= UserProfileDAO.PHOTO %>" onchange="readURL(this);"  accept="image/*" size="38"></div>
    </div>
    <div  style="width: 95%">
    <input id="photoPreview" type="image" style="border: solid; padding: 10px; margin-top: 10px;" name="image" alt=""
    <% if (userProfile != null && userProfile.getPhoto()!=null)  {%>
       src="/UserPhoto?user=<%=userProfile.getUserName()%>"    
    <% } else {%>
       src="/Unknown-person.gif"
    <% } %>
    >
    </div>
    <div>
    <input name="submit" type="Submit" value="Submit">
    </div>  
    <div>
    <INPUT Type="button" VALUE="Back" onClick="history.go(-1);return true;">
    </div>
  </div>
  </div>
  </form>
  
</body>
</html>