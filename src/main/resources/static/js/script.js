let currentSlide = 0;

function moveSlide(direction) {
    const slides = document.querySelector('.destination-slider .slides');
    const slideCount = slides.children.length;
    const visibleSlides = 3;
    const slideWidth = slides.children[0].offsetWidth * visibleSlides;

    currentSlide += direction;

    if (currentSlide < 0) {
        currentSlide = Math.floor((slideCount - 1) / visibleSlides) * visibleSlides;
    } else if (currentSlide >= slideCount) {
        currentSlide = 0;
    }

    slides.style.transform = `translateX(-${currentSlide * slideWidth / visibleSlides}px)`;
}

function autoSlide() {
    moveSlide(1);
}

document.addEventListener('DOMContentLoaded', (event) => {
    setInterval(autoSlide, 5000);
});
