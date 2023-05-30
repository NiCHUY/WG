<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>What fact is it?</title>
</head>
<link rel="stylesheet" href="css/fact.css">
<html lang="en">
<meta charset="UTF-8">
<title>What Flag is it?</title>
<body>
<div class="form-container">
    <h1>Guess Fact</h1></div>
${fact}
<form action="country" method="post">
    <input type="radio" name="option" value="1" checked> ${o1} <br>
    <input type="radio" name="option" value="2"> ${o2} <br>
    <input type="radio" name="option" value="3"> ${o3} <br>
    <input type="radio" name="option" value="4"> ${o4} <br>
    <input type="submit" value="Submit"/>
</form>
<div class="footer"><h2>
    <a href="login.html">Exit</a>
</h2></div>
</body>
</html>