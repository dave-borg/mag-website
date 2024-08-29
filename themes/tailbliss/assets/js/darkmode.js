// var themeToggleDarkIcon = document.getElementById('theme-toggle-dark-icon');
// var themeToggleLightIcon = document.getElementById('theme-toggle-light-icon');

// // Always set the light theme by default
// themeToggleDarkIcon.classList.add('hidden');
// themeToggleLightIcon.classList.remove('hidden');
document.documentElement.classList.remove('dark');
localStorage.setItem('color-theme', 'light');

// Optionally, you can disable the theme toggle button to prevent user interaction
// var themeToggleBtn = document.getElementById('theme-toggle');
// themeToggleBtn.disabled = true;