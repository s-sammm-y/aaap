console.log("memories.js loaded");

document.querySelectorAll(".memory").forEach(memory => {

    memory.addEventListener("click", async function () {

        console.log("Memory clicked!");

        const id = this.dataset.id;

        console.log("Memory ID:", id);

        try {

            const response = await fetch(`/memories/${id}`);

            console.log("API response:", response);

            if (!response.ok) {
                console.error("Memory not found");
                return;
            }

            const memoryData = await response.json();

            console.log("Memory data:", memoryData);

            document.getElementById("letterTitle").textContent =
                memoryData.title;

            document.getElementById("letterDate").textContent =
                memoryData.date;

            document.getElementById("letterText").textContent =
                memoryData.text;

            document.getElementById("letterModal")
                .classList.add("show");

        } catch (error) {

            console.error("Error loading memory:", error);

        }
    });
});


function closeLetter() {

    document.getElementById("letterModal")
        .classList.remove("show");
}


document.getElementById("letterModal")
    .addEventListener("click", function (event) {

        if (event.target === this) {
            closeLetter();
        }

    });


document.addEventListener("keydown", function (event) {

    if (event.key === "Escape") {
        closeLetter();
    }

});