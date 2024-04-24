// Function to toggle the visibility of the login popup
function toggleLoginPopup() {
  document.getElementById('loginPopup').classList.toggle('active');
}

// Attaching event listener to the user icon
document.getElementById('userIcon').addEventListener('click', function (event) {
  event.preventDefault(); // Prevents the default anchor action
  toggleLoginPopup();
});

// Attaching event listener to the close button
document.getElementById('closePopup').addEventListener('click', function () {
  toggleLoginPopup();
});