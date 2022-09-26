<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.tmd.dto.TestDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Hello World!</h1>
<form id="myForm" action="/reload" method="get">
	<p id="change" onClick="myChange()" style="cursor: pointer;">increase</p>
	<p>Number: <%= TestDTO.number %></p>
	<input type="hidden" name="contentValue" id="contentValue">
</form>
<script>

var contentValue = '${contentValue}';
if(contentValue !== "")
	document.getElementById('change').innerText = contentValue;

function myChange() {
	var p = document.getElementById('change');
	var form = document.getElementById('myForm');
	var content = document.getElementById('contentValue');
	if(p.innerText == "increase") {
		// p.innerText = "decrease";
		content.value = "decrease";
		form.submit();
	}
	else {
		// p.innerText = "increase";
		content.value = "increase";
		form.submit();
	}
}
</script>
</body>
</html>