document.addEventListener("DOMContentLoaded", () => {

    // 19 October 2022
    const startDate = new Date(2022, 9, 19, 0, 0, 0);

    const yearsElement = document.getElementById("years");
    const daysElement = document.getElementById("days");
    const hoursElement = document.getElementById("hours");
    const minutesElement = document.getElementById("minutes");
    const secondsElement = document.getElementById("seconds");

    function updateTimer() {
        const now = new Date();

        let years = now.getFullYear() - startDate.getFullYear();

        const anniversary = new Date(startDate);
        anniversary.setFullYear(startDate.getFullYear() + years);

        if (now < anniversary) {
            years--;
        }

        anniversary.setFullYear(startDate.getFullYear() + years);

        let remaining = now.getTime() - anniversary.getTime();

        const dayMs = 24 * 60 * 60 * 1000;
        const hourMs = 60 * 60 * 1000;
        const minuteMs = 60 * 1000;
        const secondMs = 1000;

        const days = Math.floor(remaining / dayMs);
        remaining %= dayMs;

        const hours = Math.floor(remaining / hourMs);
        remaining %= hourMs;

        const minutes = Math.floor(remaining / minuteMs);
        remaining %= minuteMs;

        const seconds = Math.floor(remaining / secondMs);

        yearsElement.textContent = years;
        daysElement.textContent = days;
        hoursElement.textContent = String(hours).padStart(2, "0");
        minutesElement.textContent = String(minutes).padStart(2, "0");
        secondsElement.textContent = String(seconds).padStart(2, "0");
    }

    updateTimer();
    setInterval(updateTimer, 1000);
});