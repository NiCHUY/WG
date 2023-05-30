document.addEventListener("DOMContentLoaded", function () {
    // Get the username from sessionStorage
    var username = sessionStorage.getItem("username");
    // Update the welcome message
    var welcomeElement = document.querySelector("h1");
    if (welcomeElement && username) {
        welcomeElement.textContent = "Welcome home, " + username;
    }
});