package io.github.mizinchik.view;

import javafx.scene.canvas.Canvas;

/**
 * Resizable canvas such that it could change
 * its dimensions when its parent does so.
 *
 * @author MIZINCHIK
 */
public class ResizableCanvas extends Canvas {
    /**
     * The way it's written makes it resizable.
     *
     * @return always true
     */
    @Override
    public boolean isResizable() {
        return true;
    }
}