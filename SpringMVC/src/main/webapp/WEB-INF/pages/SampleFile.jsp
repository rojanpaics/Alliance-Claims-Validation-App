<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>This is a sample page by emerson</h1>
	<h2>This is sample subtitle</h2>
	
	<form action="/sample/testdata" method="POST">
		<label>Name</label>
		<input name="name"/><br/>
		<label>Age</label>
		<input name="age"/><br/>
		<label>Zodiac Sign</label>
		<input name="zodiac"/>
		<button type="submit">SUBMIT</button>
	</form>
</body>
</html>