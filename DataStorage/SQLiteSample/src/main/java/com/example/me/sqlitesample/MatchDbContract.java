package com.example.me.sqlitesample;

import android.provider.BaseColumns;

/**
 * Created by Administrator on 2015/12/28.
 */
public final class MatchDbContract {
    public MatchDbContract(){}
    public static final String DB_NAME="matchsdatabase";

    public static abstract class MatchsTable implements BaseColumns{
        public static final String TABLE_NAME="matchs";
        public static final String COLUMN_NAME_ENTRY_ID="matchid";
        public static final String COLUMN_NAME_MATCH_NAME="matchname";
    }
    public static abstract class PlayersTable implements BaseColumns{
        public static final String TABLE_NAME="players";
        public static final String COLUMN_NAME_ENTRY_ID="playerid";
        public static final String COLUMN_NAME_PLAYER_NAME="playername";
    }

    public static abstract class GamesTable implements BaseColumns{
        public static final String TABLE_NAME="games";
        public static final String COLUMN_NAME_ENTRY_ID="gameid";
        public static final String COLUMN_NAME_MATCH_ID="matchid";
        public static final String COLUMN_NAME_PLAYER1_ID="player1id";
        public static final String COLUMN_NAME_PLAYER2_ID="player2id";
    }

}
