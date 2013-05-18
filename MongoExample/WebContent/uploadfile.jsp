<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
function addTag() {
	alert('Hello world!');
}
</script>
</head>
<body>
 <form action="UploadServlet" method="post" name="uploadForm" enctype="multipart/form-data">
  <p>
   <input name="uploadfile" type="file" size="50">
  </p>

  <p> 
  First name: <input type="text" name="firstname"><br>
  Last name: <input type="text" name="lastname"></p>
  <div id="tags">
  <button type="button" 
  onclick="addTag()">Add tag!</button>
  Tag1: <input type="text" name="tag[]"><br>
  Tag2: <input type="text" name="tag[]"><br>
  </div>
  <input name="submit" type="submit" value="Submit">
 </form>
</body>
</html>