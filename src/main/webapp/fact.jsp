<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>What fact is it?</title>
</head>
<link rel="stylesheet" href="css/fact.css">
<html lang="en">
<meta charset="UTF-8">
<title>What Country is it?</title>
<body>
<div class="form-container">
    <h1><font size="8" color="Black" face="Arial">Guess Fact</font></h1></div>
<font size="8" color="Black" face="Arial">${fact}</font>
<form action="fact" method="post">
    <input type="radio" name="option1" value="1" checked> <font size="5" color="Black" face="Arial">${o1}</font> <br>
    <input type="radio" name="option1" value="2"> <font size="5" color="Black" face="Arial">${o2}</font> <br>
    <input type="radio" name="option1" value="3"> <font size="5" color="Black" face="Arial">${o3}</font> <br>
    <input type="radio" name="option1" value="4"> <font size="5" color="Black" face="Arial">${o4}</font> <br>
    <input type="submit" value="Submit"/>
</form>
<div class="footer"><h2>
    <a href="login.html">Exit</a>
</h2></div>
</body>
</html>