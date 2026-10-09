document.addEventListener("submit", async function (event) {
    const form = event.target;

    if (!form.matches("#gameContent form")) {
        return;
    }

    event.preventDefault();

    const currentContent = document.getElementById("gameContent");
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
        const parsed = new DOMParser().parseFromString(html, "text/html");
        const updatedContent = parsed.getElementById("gameContent");

        if (!updatedContent) {
            throw new Error("Updated game content was not returned.");
        }

        currentContent.replaceWith(updatedContent);

    } catch (error) {
        console.error(error);

        buttons.forEach(button => button.disabled = false);

        alert("Couldn't update the game. Please try again!");
    }
});