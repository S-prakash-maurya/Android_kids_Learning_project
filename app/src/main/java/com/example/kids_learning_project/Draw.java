package com.example.kids_learning_project;

import android.graphics.Path;

public class Draw {

    public int color;
    public int strokeWidth;
    public Path path;

    public Draw(int color, int strokeWidth, Path path) {

        this.color = color;
        this.strokeWidth = strokeWidth;
        this.path = path;

    }

}