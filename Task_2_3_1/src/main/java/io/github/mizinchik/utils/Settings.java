package io.github.mizinchik.utils;

/**
 * Record for storing the level settings.
 *
 * @param rows on the field
 * @param columns on the field
 * @param competitors AI
 * @param goal score to win
 * @param time between two frames
 * @param walls on the field
 */
public record Settings(int rows, int columns, int competitors, int goal, int time, int[][] walls) {
}
