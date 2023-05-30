function validateForm() {
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;

    if (password !== confirmPassword) {
        alert("Passwords do not match!");
        return false;
    } else {
        var username = document.getElementById("username").value;
        localStorage.setItem("username", username);
        sessionStorage.setItem("username", username);
        localStorage.setItem('key', JSON)
        return true;
    }
}