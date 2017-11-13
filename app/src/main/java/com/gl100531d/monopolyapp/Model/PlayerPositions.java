package com.gl100531d.monopolyapp.Model;

/**
 * Created by Leon on 9/5/2016.
 */
public class PlayerPositions {

    private int leftMargin;
    private int topMargin;

    public PlayerPositions(int leftMargin, int topMargin) {
        this.leftMargin = leftMargin;
        this.topMargin = topMargin;
    }

    public int getLeftMargin() {
        return leftMargin;
    }

    public void setLeftMargin(int leftMargin) {
        this.leftMargin = leftMargin;
    }

    public int getTopMargin() {
        return topMargin;
    }

    public void setTopMargin(int topMargin) {
        this.topMargin = topMargin;
    }
}
