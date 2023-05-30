<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>What Flag is it?</title>
    <link rel="stylesheet" href="css/country.css">
</head>
<body>
<div class="container">
    <div class="image-container">
        <img src="${img}" alt="error">
    </div>
    <div class="info-container">
        <p>${fact}</p>
        <p>Area: ${area} km^2</p>
        <p>Population: ${population} residents.</p>
        <p>Continent: ${continent}</p>
    </div>
    <div class="form-container">
        <h1>Guess Flag</h1>
    </div>
    <form action="country" method="post" class="guess-form">
        <div class="form-input">
            <label for="answer">Answer:</label>
            <input type="text" id="answer" name="answer" required placeholder="Enter your answer">
        </div>
        <input type="submit" value="Submit" class="submit-button">
    </form>
</div>
<div class="footer"><h2>
    <a href="login.html">Exit</a>
</h2></div>
</body>
</html>
