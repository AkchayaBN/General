function evaluation(event) {
    event.preventDefault();
    
    const username = document.getElementById("name").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirm_Password").value;
    
    if (username.trim() === "") {
        alert("Please enter a username");
        return;
    }
    
    if (password === "") {
        alert("Please enter a password");
        return;
    }
    
    if (password !== confirmPassword) {
        alert("Passwords do not match");
        return;
    }
    
    if (password.length < 6) {
        alert("Password must be at least 6 characters");
        return;
    }
    
    alert("Login successful! Welcome " + username);
}

document.querySelector("button").addEventListener("onclick", evaluation);