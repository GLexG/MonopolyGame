package com.gl100531d.monopolyapp.db;

import android.provider.BaseColumns;

/**
 * Created by Leon on 8/8/2016.
 */
public final class FeedReaderContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public FeedReaderContract() {}

    /* Inner class that defines the table contents */
    public static abstract class PlayerEntry implements BaseColumns {
        //ime tabele
        public static final String TABLE_NAME = "players";
        //ime kolona
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PLAYER_NAME = "playerName";
        public static final String COLUMN_NAME_IMAGE_ID = "image_id";

    }

    public static abstract class GameEntry implements BaseColumns {
        //ime tabele
        public static final String TABLE_NAME = "game_scores";
        //ime kolona
        public static final String COLUMN_NAME_TIME_STARTED = "player_id";
        public static final String COLUMN_NAME_TIME_FINISHED = "name";
        public static final String COLUMN_NAME_TIME_DURATION = "playerName";

        //ovde bi trebalo player1,player2,player3,player4
        //public static final String COLUMN_NAME_GAME_ID = "game_winner";

    }




}
