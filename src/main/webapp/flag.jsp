<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" href="css/main.css">
<html lang="en">
    <meta charset="UTF-8">
    <title>What Flag is it?</title>
<body>
<img src = "${img}" alt = "error">
<div class="form-container">
    <h1>Guess Flag</h1></div>
<form action="flag" method="post">
    <input type="radio" name="option" value="1" checked> ${o1} <br>
    <input type="radio" name="option" value="2"> ${o2} <br>
    <input type="radio" name="option" value="3"> ${o3} <br>
    <input type="radio" name="option" value="4"> ${o4} <br>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
