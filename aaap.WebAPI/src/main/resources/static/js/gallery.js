
/* =========================================
   JUST FOR US - GALLERY PHOTO VIEWER
   ========================================= */

document.addEventListener("DOMContentLoaded", () => {

    const photoButtons = [
        ...document.querySelectorAll(".photo-button")
    ];

    const lightbox = document.getElementById("lightbox");
    const lightboxImage = document.getElementById("lightboxImage");
    const lightboxCaption = document.getElementById("lightboxCaption");

    const closeButton = document.getElementById("lightboxClose");
    const previousButton = document.getElementById("prevPhoto");
    const nextButton = document.getElementById("nextPhoto");

    // Stop safely if the gallery or viewer isn't present.
    if (
        !lightbox ||
        !lightboxImage ||
        !lightboxCaption ||
        !closeButton ||
        !previousButton ||
        !nextButton ||
        photoButtons.length === 0
    ) {
        return;
    }

    let currentIndex = 0;
    let previouslyFocusedElement = null;
    let touchStartX = 0;
    let touchStartY = 0;

    /* Display a photo by its index */

    function showPhoto(index) {
        currentIndex =
            (index + photoButtons.length) % photoButtons.length;

        const button = photoButtons[currentIndex];
        const image = button.querySelector("img");

        lightboxImage.src = button.dataset.full || image.src;
        lightboxImage.alt = image.alt || "A special memory";

        lightboxCaption.textContent =
            button.dataset.caption || "";

        const multiplePhotos = photoButtons.length > 1;

        previousButton.hidden = !multiplePhotos;
        nextButton.hidden = !multiplePhotos;
    }

    /* Open viewer */

    function openLightbox(index) {
        previouslyFocusedElement = document.activeElement;

        showPhoto(index);

        lightbox.classList.add("open");
        lightbox.setAttribute("aria-hidden", "false");

        document.body.classList.add("lightbox-open");

        closeButton.focus();
    }

    /* Close viewer */

    function closeLightbox() {
        if (!lightbox.classList.contains("open")) {
            return;
        }

        lightbox.classList.remove("open");
        lightbox.setAttribute("aria-hidden", "true");

        document.body.classList.remove("lightbox-open");

        lightboxImage.removeAttribute("src");

        if (previouslyFocusedElement) {
            previouslyFocusedElement.focus();
        }
    }

    /* Previous and next photos */

    function showPreviousPhoto() {
        showPhoto(currentIndex - 1);
    }

    function showNextPhoto() {
        showPhoto(currentIndex + 1);
    }

    /* Attach click handlers to photo cards */

    photoButtons.forEach((button, index) => {
        button.addEventListener("click", () => {
            openLightbox(index);
        });
    });

    closeButton.addEventListener("click", closeLightbox);

    previousButton.addEventListener("click", showPreviousPhoto);

    nextButton.addEventListener("click", showNextPhoto);

    /* Close when the dark background is clicked */

    lightbox.addEventListener("click", (event) => {
        if (event.target === lightbox) {
            closeLightbox();
        }
    });

    /* Keyboard controls */

    document.addEventListener("keydown", (event) => {
        if (!lightbox.classList.contains("open")) {
            return;
        }

        if (event.key === "Escape") {
            closeLightbox();
        } else if (event.key === "ArrowLeft") {
            showPreviousPhoto();
        } else if (event.key === "ArrowRight") {
            showNextPhoto();
        }
    });

    /* Touch gestures for phones */

    lightbox.addEventListener("touchstart", (event) => {
        const touch = event.changedTouches[0];

        touchStartX = touch.clientX;
        touchStartY = touch.clientY;
    }, { passive: true });

    lightbox.addEventListener("touchend", (event) => {
        if (!lightbox.classList.contains("open")) {
            return;
        }

        const touch = event.changedTouches[0];

        const deltaX = touch.clientX - touchStartX;
        const deltaY = touch.clientY - touchStartY;

        // Ignore vertical scrolling gestures.
        if (
            Math.abs(deltaX) < 50 ||
            Math.abs(deltaX) < Math.abs(deltaY) * 1.2
        ) {
            return;
        }

        if (deltaX < 0) {
            showNextPhoto();
        } else {
            showPreviousPhoto();
        }
    }, { passive: true });

});
