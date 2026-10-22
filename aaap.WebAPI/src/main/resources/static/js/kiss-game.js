
document.addEventListener("submit", async function (event) {
    const form = event.target;

    if (!form.matches("#gameContent form")) {
        return;
    }

    event.preventDefault();

    const currentContent = document.getElementById("gameContent");

    if (!currentContent) {
        return;
    }

    const buttons = currentContent.querySelectorAll("button");

    buttons.forEach(button => button.disabled = true);

    try {
        const response = await fetch(form.action, {
            method: "POST",
            body: new URLSearchParams(new FormData(form)),
            headers: {
                "X-Requested-With": "XMLHttpRequest"
            }
        });

        if (!response.ok) {
            throw new Error("Game update failed: " + response.status);
        }

        const html = await response.text();

        const parsed = new DOMParser().parseFromString(
            html,
            "text/html"
        );

        const updatedContent = parsed.getElementById("gameContent");

        if (!updatedContent) {
            throw new Error("Updated game content was not returned.");
        }

        // Detect a new win, rather than replaying the animation
        // after every subsequent AJAX update.
        const justWon =
            !currentContent.querySelector(".victory-screen") &&
            updatedContent.querySelector(".victory-screen") !== null;

        // Replace only the game section.
        currentContent.replaceWith(updatedContent);

        // Scroll to the celebration when the player wins.
        if (justWon) {
            requestAnimationFrame(() => {
                updatedContent
                    .querySelector(".victory-screen")
                    ?.scrollIntoView({
                        behavior: "smooth",
                        block: "center"
                    });
            });
        }

    } catch (error) {
        console.error(error);

        // Re-enable the original buttons if the request fails.
        buttons.forEach(button => button.disabled = false);

        alert("Couldn't update the game. Please try again!");
    }
});


// Reveal the secret love letter.
document.addEventListener("click", function (event) {
    const button = event.target.closest("#revealReward");

    if (!button) {
        return;
    }

    const reward = document.getElementById("loveReward");

    if (!reward || !reward.hidden) {
        return;
    }

    reward.hidden = false;

    button.textContent = "A Little Gift From My Heart ❤️";
    button.disabled = true;

    reward.scrollIntoView({
        behavior: "smooth",
        block: "nearest"
    });
});
