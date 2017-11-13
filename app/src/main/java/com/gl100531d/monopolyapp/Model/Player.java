package com.gl100531d.monopolyapp.Model;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Leon on 8/4/2016.
 */
public class Player {

    public int playerId;
    public String name;
    public int imageId;

    public ImageView playerImage;

    public String playerName;

    int[] playerPositions;

    public Player(int playerId, String name, int imageId, String playerName, int[] playerPositions) {
        this.playerId = playerId;
        this.name = name;
        this.imageId = imageId;
        this.playerName = playerName;
        this.playerPositions = playerPositions;
    }

    public Player(Context context, int playerId, String name, int imageId, int playerImageID, String playerName) {
        this.playerId = playerId;
        this.name = name;
        this.imageId = imageId;
        //ikonica igraca mala na tabli
        playerImage = new ImageView(context);
        playerImage.setImageResource(playerImageID);

        this.playerName = playerName;

    }

    public Player(String name, int imageId, String playerName) {
        this.name = name;
        this.imageId = imageId;
        this.playerName = playerName;
    }

    public Player(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }


    public Player() {}

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int[] getPlayerPositions() {
        return playerPositions;
    }

    public void setPlayerPositions(int[] playerPositions) {
        this.playerPositions = playerPositions;
    }

/*    public ImageView getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(ImageView playerImage) {
        this.playerImage = playerImage;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
