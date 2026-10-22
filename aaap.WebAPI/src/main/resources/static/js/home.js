
document.addEventListener("DOMContentLoaded", () => {

    // The day we first met: 19 October 2022.
    // Month is zero-based in JavaScript, so October = 9.
    const startDate = new Date(2022, 9, 19, 0, 0, 0);

    const yearsElement = document.getElementById("years");
    const daysElement = document.getElementById("days");
    const hoursElement = document.getElementById("hours");
    const minutesElement = document.getElementById("minutes");
    const secondsElement = document.getElementById("seconds");

    // Run only on pages containing the relationship timer.
    if (
        !yearsElement ||
        !daysElement ||
        !hoursElement ||
        !minutesElement ||
        !secondsElement
    ) {
        return;
    }

    function updateRelationshipTimer() {

        const now = new Date();

        if (now < startDate) {
            yearsElement.textContent = "0";
            daysElement.textContent = "0";
            hoursElement.textContent = "00";
            minutesElement.textContent = "00";
            secondsElement.textContent = "00";
            return;
        }

        // Calculate complete calendar years.
        let years = now.getFullYear() - startDate.getFullYear();

        const anniversary = new Date(startDate);
        anniversary.setFullYear(startDate.getFullYear() + years);

        if (now < anniversary) {
            years--;
        }

        // Find the most recent anniversary.
        anniversary.setFullYear(startDate.getFullYear() + years);

        // Calculate the remaining time after complete years.
        let remainingMilliseconds = now - anniversary;

        const millisecondsPerSecond = 1000;
        const millisecondsPerMinute = 60 * millisecondsPerSecond;
        const millisecondsPerHour = 60 * millisecondsPerMinute;
        const millisecondsPerDay = 24 * millisecondsPerHour;

        const days = Math.floor(
            remainingMilliseconds / millisecondsPerDay
        );

        remainingMilliseconds %= millisecondsPerDay;

        const hours = Math.floor(
            remainingMilliseconds / millisecondsPerHour
        );

        remainingMilliseconds %= millisecondsPerHour;

        const minutes = Math.floor(
            remainingMilliseconds / millisecondsPerMinute
        );

        remainingMilliseconds %= millisecondsPerMinute;

        const seconds = Math.floor(
            remainingMilliseconds / millisecondsPerSecond
        );

        // Update the page.
        yearsElement.textContent = years;
        daysElement.textContent = days;
        hoursElement.textContent = String(hours).padStart(2, "0");
        minutesElement.textContent = String(minutes).padStart(2, "0");
        secondsElement.textContent = String(seconds).padStart(2, "0");
    }

    // Display the timer immediately, then update every second.
    updateRelationshipTimer();
    setInterval(updateRelationshipTimer, 1000);

});
