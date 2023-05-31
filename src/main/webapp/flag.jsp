<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>What flag is it?</title>
</head>
<link rel="stylesheet" href="css/flag.css">
<html lang="en">
<meta charset="UTF-8">
<title>What Flag is it?</title>
<body>
<img src="${img}" alt="error">
<div class="form-container">
    <h1><font size="10" color="Black" face="Arial">Guess Flag</font></h1></div>
<form action="flag" method="post">
    <input type="radio" name="option" value="1" checked> <font size="6" color="Black" face="Arial">${o1}</font> <br>
    <input type="radio" name="option" value="2"> <font size="6" color="Black" face="Arial">${o2}</font> <br>
    <input type="radio" name="option" value="3"> <font size="6" color="Black" face="Arial">${o3}</font> <br>
    <input type="radio" name="option" value="4"> <font size="6" color="Black" face="Arial">${o4}</font> <br>
    <input type="submit" value="Submit"/>
</form>
<div class="footer"><h2>
    <a href="login.html">Exit</a>
</h2></div>
</body>
</html>