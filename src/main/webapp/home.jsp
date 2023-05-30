<!DOCTYPE html>
<link rel="stylesheet" href="css/home.css">
<html lang="en">
<head>
    <title>Home</title>
</head>
<body>
<script src="js/home.js"></script>
<div id="profile-picture"></div>
<h1> Welcome home, ${username} </h1>
<svg class="game-link1">
    <a href="">
        <rect height="200" width="400"></rect>
        <image href="image/comparison-removebg-preview.png" width="250" height="250" alt="game 1" x="30"
               y="-35"></image>
    </a>
</svg>
<svg class="game-link2">
    <a href="">
        <rect height="200" width="400"></rect>
        <image href="image/image-removebg-preview.png" width="180" height="180" alt="game 2" x="60" y="-2"></image>
    </a>
</svg>
<svg class="game-link3">
    <a href="fact">
        <rect height="200" width="400"></rect>
        <image href="image/question-removebg-preview.png" width="90" height="200" alt="game 3" x="120" y="-20"></image>
    </a>
</svg>
<svg class="game-link4">
    <a href="country">
        <rect height="200" width="400"></rect>
        <image href="image/flag-removebg-preview.png" width="150" height="150" alt="game 4" x="100" y="2"></image>
    </a>
</svg>
<svg class="country">
    <a href="#">
        <rect height="600" width="600"></rect>
        <text class="average-score-text" x="200" y="300">Average Score 1: ${res1}</text>
        <text class="average-score-text" x="200" y="300">Average Score 2: ${res2}</text>
        <text class="average-score-text" x="200" y="300">Average Score 3: ${res3}</text>
        <text class="average-score-text" x="200" y="300">Average Score 4: ${res4}</text>
    </a>
</svg>
<div class="footer"><h2>
    <a href="login.html">Exit</a>
</h2></div>

</body>
</html>
