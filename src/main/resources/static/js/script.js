// Script for slider om homepage
let currentSlide = 0;

function moveSlide(direction) {
    const slides = document.querySelector('.destination-slider .slides');
    const slideCount = slides.children.length;
    const slideWidth = slides.children[0].offsetWidth;
    currentSlide += direction;

    if (currentSlide < 0) {
        currentSlide = slideCount - 1;
    } else if (currentSlide >= slideCount) {
        currentSlide = 0;
    }

    slides.style.transform = `translateX(-${currentSlide * slideWidth}px)`;
}

function autoSlide() {
    moveSlide(1);
}

document.addEventListener('DOMContentLoaded', (event) => {
    setInterval(autoSlide, 5000); // Slide every 3 seconds
});