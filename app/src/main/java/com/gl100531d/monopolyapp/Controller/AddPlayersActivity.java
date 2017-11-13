package com.gl100531d.monopolyapp.Controller;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.gl100531d.monopolyapp.Adapters.MyCustomAdapter;
import com.gl100531d.monopolyapp.Fragments.AddNewPlayerDialogFragment;
import com.gl100531d.monopolyapp.Model.Player;
import com.gl100531d.monopolyapp.R;
import com.gl100531d.monopolyapp.db.FeedReaderDbHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.R.attr.data;

import com.gl100531d.monopolyapp.db.FeedReaderContract.*;
import com.gl100531d.monopolyapp.db.FeedReaderDbHelper;

/**
 * Created by Leon on 8/2/2016.
 */



public class AddPlayersActivity  extends AppCompatActivity {
    private static final String SHARED_PREFS_FILE = "shared_file";
    private static final String TASKS = "task";
    RecyclerView recyclerView;

    MyCustomAdapter adapter;


    public Player player;
    public ArrayList<Player> allPlayers;
    public ArrayList<Player> allPlayers2;

    public ArrayList<String> player_names_list;
    public ArrayList<Integer> image_id_list;

    public String pom_name;
    public String pom_playerName;
    public int pom_id;


    //player za delitovanje iz prosleg view-a da ne bi mogli istog 2 puta da selektuju
    public String del_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.added_players_game);


/*        if(savedInstanceState == null){
            allPlayers = new ArrayList<>();
            player_names_list = new ArrayList<>();
            image_id_list = new ArrayList<>();
        }*/


        //deletePlayers(this);


        Intent intent = getIntent();
        int id = intent.getIntExtra("imageId",-1);
        String name = intent.getStringExtra("name");
        String playerName = intent.getStringExtra("playerName");

        if (allPlayers == null){
            allPlayers = new ArrayList<>();
        }

        if (name != null){


            //PRVO UPISUJEM NOVO................................................................
            /***************************** UPISUJEM U BAZU **************************/
            FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
            SQLiteDatabase db = feedReaderDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(PlayerEntry.COLUMN_NAME_NAME, name); //
            values.put(PlayerEntry.COLUMN_NAME_PLAYER_NAME, playerName); // Ime igraca
            values.put(PlayerEntry.COLUMN_NAME_IMAGE_ID, id); // Ime igraca

            // Inserting Row
            db.insert(PlayerEntry.TABLE_NAME, null, values);
            db.close(); // Closing database connection

            //PA CITAM POSLE...................................................................


            SQLiteDatabase db2 = feedReaderDbHelper.getReadableDatabase();

            String sql = "SELECT * FROM " + PlayerEntry.TABLE_NAME;
            Cursor cur = db2.rawQuery(sql,null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        pom_name =  cur.getString(1);
                        pom_playerName =  cur.getString(2);
                        pom_id = cur.getInt(3);


                        Player current = new Player();
                        current.name = pom_name;
                        current.playerName = pom_playerName;
                        current.imageId = pom_id;

/*                        switch (playerName){
                            case "player1":
                                current.playerImage = "player1_small"
                                break;
                            case "player2":
                                LinearLayoutManager mLinearLayoutManagerHorizontal = new LinearLayoutManager(this); // (Context context)
                                break;
                            case "player3":
                                LinearLayoutManager mLinearLayoutManagerHorizontal = new LinearLayoutManager(this); // (Context context)
                                break;
                            case "player4":
                                LinearLayoutManager mLinearLayoutManagerHorizontal = new LinearLayoutManager(this); // (Context context)
                                break;

                        }*/

                        allPlayers.add(current);

                    }while(cur.moveToNext());
                }
            }
            cur.close();
            db2.close();

            /*String[] projection = {PlayerEntry._ID, PlayerEntry.COLUMN_NAME_NAME, PlayerEntry.COLUMN_NAME_PLAYER_NAME, PlayerEntry.COLUMN_NAME_IMAGE_ID};
            String selection = PlayerEntry.COLUMN_NAME_WINNER + " != ?";
            //selection =>WHERE
            String[] selectionArgs = { "" };
            String sortOrder = PlayerEntry.COLUMN_NAME_STARTED + " DESC";
            Cursor cursor = db.query(PlayerEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);*/

        }


        /**********************JEAAAAAAAAAAAAAAA MADAFAKAAAAAAAAAAAAAAAAAAAAAAA******************/
/*        if (null == allPlayers) {
            allPlayers = new ArrayList<>();
        }*/

        // load tasks from preference
        /*SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);*/


/*        try {
            allPlayers = (ArrayList<Player>) ObjectSerializer.deserialize(prefs.getString(TASKS, ObjectSerializer.serialize(new ArrayList<Player>())));
        } catch (IOException e) {
            e.printStackTrace();
        }*/


/*        Player current = new Player();
        current.name= name;
        current.imageId = id;
        current.playerName = playerName;
        if (current.name != null) {
            allPlayers.add(current);
        }*/

/*        if (playerName!=null) {

            if (player_names_list == null){
                player_names_list = new ArrayList<>();
                image_id_list = new ArrayList<>();
            }

            player_names_list.add(playerName);
            image_id_list.add(id);

            for (int i = 0; i < image_id_list.size(); i++) {
                Player current = new Player();
                current.name = player_names_list.get(i);
                current.imageId = image_id_list.get(i);

                //allPlayers.add(current);
                addPlayer(current);
            }

        }*/








        //del_player = name;



/*        default_player = new Player();

        default_players = new ArrayList<>();

        final int[] images ={ R.drawable.player1,R.drawable.player2, R.drawable.player3 ,R.drawable.player4 };
        //final int[] images ={};
        //final String[] Categories = {};
        final String[] Categories = {"Player1","Player2","Player3","Player4"};

        for (int i = 0; i < images.length; i++) {

            Player current = new Player();
            current.name = Categories[i];
            current.imageId = images[i];

            default_players.add(current);
        }*/

/*        initPlayers();*/

        //vadim extra iz intenta

/*        String del_player = "prazno";
        del_player = intent.getStringExtra("del_player");*/

/*        Iterator<Player> i = allPlayers.iterator();
        while (i.hasNext()) {
            Player o = i.next();

            if(del_player == o.getName()) {
                //i.remove();
                allPlayers.remove(o);
            }
        }*/




        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        adapter = new MyCustomAdapter(this, allPlayers, 1);
        recyclerView.setAdapter(adapter);

        //tip layouta
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
/*        StaggeredGridLayoutManager mStaggeredHorizontalLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
        recyclerView.setLayoutManager(mStaggeredHorizontalLayoutManager);*/

/*        Button dugme1 = (Button) findViewById(R.id.dugme1);

        dugme1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AddNewPlayerDialogFragment names = new AddNewPlayerDialogFragment();
                names.setCancelable(false);
                names.show(getFragmentManager(), "Player names");


                adapter.notifyDataSetChanged();


            }

        });*/

    }

/*    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
*//*        savedInstanceState.putStringArrayList("player_names_list", player_names_list);
        savedInstanceState.putIntegerArrayList("image_id_list", image_id_list);*//*

        super.onSaveInstanceState(savedInstanceState);

        // etc.
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.

*//*        player_names_list = savedInstanceState.getStringArrayList("player_names_list");
        image_id_list = savedInstanceState.getIntegerArrayList("image_id_list");*//*

        String stateSaved = savedInstanceState.getString("save_state");
        if(stateSaved == null){
            Toast.makeText(AddPlayersActivity.this, "onRestore saved state =" + stateSaved, Toast.LENGTH_LONG).show();

        }

    }*/

    public void deletePlayers(AddPlayersActivity addPlayersActivity){
        // When I delete row in results table, all rows in other tables will be deleted automatically (ON DELETE CASCADE)
        FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
        SQLiteDatabase db = feedReaderDbHelper.getWritableDatabase();
        db.delete(PlayerEntry.TABLE_NAME, null, null);
        // Return to MainActivity
        finish();
    }

    public void addPlayer(Player p) {
        if (null == allPlayers) {
            allPlayers = new ArrayList<>();
        }

        allPlayers.add(p);
        // save the task list to preference
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        try {
            editor.putString(TASKS, ObjectSerializer.serialize(allPlayers));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();
    }





/*        @Override
    protected void onResume() {
        super.onResume();

*//*
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString("text", null);
        if (restoredText != null) {
            player_names_list = prefs.getStringArrayList("name", "No name defined");//"No name defined" is the default value.
            int image_id_list = prefs.getInt("idName", 0); //0 is the default value.
        }
*//*




    }

    @Override
    protected void onPause() {
        super.onPause();

*//*        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putStringArrayList("player_names_list", player_names_list);
        editor.getIntegerArrayList("image_id_list", image_id_list);
        editor.commit();*//*


    }*/

    public void imageClick(View view) {

        int velicina = allPlayers.size();
        if(velicina >3){
            Toast.makeText(this, "Maximalan broj igraca je " + velicina, Toast.LENGTH_LONG).show();
        }else {

            Intent intent = new Intent(view.getContext(), AddedPlayersActivity.class);
            //intent.putExtra("del_player", del_player);
            startActivity(intent);
        }

    }
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_list_look ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.linearViewHorizontal:
                LinearLayoutManager mLinearLayoutManagerHorizontal = new LinearLayoutManager(this); // (Context context)
                mLinearLayoutManagerHorizontal.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(mLinearLayoutManagerHorizontal);
                break;

            case R.id.linearViewVertical:
                LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this); // (Context context)
                mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(mLinearLayoutManagerVertical);
                break;
            case R.id.gridView:
                GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 4); // (Context context, int spanCount)
                recyclerView.setLayoutManager(mGridLayoutManager);
                break;
            case R.id.staggeredViewHorizontal:
                StaggeredGridLayoutManager mStaggeredHorizontalLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
                recyclerView.setLayoutManager(mStaggeredHorizontalLayoutManager);
                break;
            case R.id.staggeredViewVertical:
                StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
                recyclerView.setLayoutManager(mStaggeredVerticalLayoutManager);
                break;

            case R.id.startGame:
                Intent intent = new Intent(this, BoardActivity.class);
                startActivity(intent);
                break;

            case R.id.deletePlayers:
                deletePlayers(this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
