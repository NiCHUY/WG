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
${fact} <br>
Area: ${area} km^2<br>
Population: ${population} residents.<br>
Continent: ${continent} <br>
<div class="form-container">
    <h1>Guess Flag</h1></div>
<form action="country" method="post">
    <div class="form-input">
        <label for="answer"></label><input type="text" id="answer" name="answer" required placeholder="Answer">
    </div>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
