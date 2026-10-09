
package com.aaap.Model.Models;

import java.util.HashSet;
import java.util.Set;

public class KissGame {

    private int playerX = 0;
    private int playerY = 0;

    private final int targetX = 4;
    private final int targetY = 4;

    private final Set<String> collectedHearts = new HashSet<>();

    // All three hearts are inside the 5 x 5 board.
    private final int[][] heartPositions = {
            {1, 1},
            {3, 2},
            {1, 3}
    };

    private boolean finished;

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public int getTargetX() {
        return targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public int getScore() {
        return collectedHearts.size();
    }

    public int getCollectedHeartCount() {
        return collectedHearts.size();
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean hasHeartAt(int x, int y) {
        for (int i = 0; i < heartPositions.length; i++) {
            if (heartPositions[i][0] == x
                    && heartPositions[i][1] == y) {
                return !collectedHearts.contains(String.valueOf(i));
            }
        }

        return false;
    }

    public void move(String direction) {
        if (finished) {
            return;
        }

        switch (direction) {
            case "UP" -> playerY--;
            case "DOWN" -> playerY++;
            case "LEFT" -> playerX--;
            case "RIGHT" -> playerX++;
            default -> {
                return;
            }
        }

        // Keep the player inside the 5 x 5 board.
        playerX = Math.max(0, Math.min(4, playerX));
        playerY = Math.max(0, Math.min(4, playerY));

        collectHeart();
        checkWin();
    }

    private void collectHeart() {
        for (int i = 0; i < heartPositions.length; i++) {
            if (heartPositions[i][0] == playerX
                    && heartPositions[i][1] == playerY) {
                collectedHearts.add(String.valueOf(i));
            }
        }
    }

    private void checkWin() {
        if (collectedHearts.size() == heartPositions.length
                && playerX == targetX
                && playerY == targetY) {
            finished = true;
        }
    }

    public void reset() {
        playerX = 0;
        playerY = 0;
        collectedHearts.clear();
        finished = false;
    }
}
