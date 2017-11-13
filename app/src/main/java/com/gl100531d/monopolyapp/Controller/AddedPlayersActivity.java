package com.gl100531d.monopolyapp.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.gl100531d.monopolyapp.Adapters.MyCustomAdapter;
import com.gl100531d.monopolyapp.Fragments.AddNewPlayerDialogFragment;
import com.gl100531d.monopolyapp.Model.Player;
import com.gl100531d.monopolyapp.R;

import java.util.ArrayList;

/**
 * Created by Leon on 8/2/2016.
 */



public class AddedPlayersActivity  extends AppCompatActivity {
    RecyclerView recyclerView2;

    MyCustomAdapter adapter2;

    public Player default_player;
    public ArrayList<Player> default_players;

    public String del_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_players_game);



        if (default_players == null) {
            initPlayers();
        }

/*        final int[] images ={};
        final String[] Categories = {};*/

/*        //vadim extra iz intenta
        Intent intent = getIntent();
        int id = intent.getIntExtra("imageId",-1);
        String name = intent.getStringExtra("name");
        String playerName = intent.getStringExtra("playerName");*/


/*        Player current = new Player();
        current.name= name;
        current.imageId = id;
        current.playerName = playerName;
        default_players.add(current);

        del_player = name;*/

/*        for (int i = 0; i < images.length; i++) {

            Player current = new Player();
            current.name = Categories[i];
            current.imageId = images[i];

            players2.add(current);
        }*/

        recyclerView2 = (RecyclerView) findViewById(R.id.recycleView);

        //1 mi je flag da renderujem drugi view
        adapter2 = new MyCustomAdapter(this, default_players, 0);
        recyclerView2.setAdapter(adapter2);
        //tip layouta
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        /*GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 4); // (Context context, int spanCount)
        recyclerView2.setLayoutManager(mGridLayoutManager);*/

/*        Button dugme1 = (Button) findViewById(R.id.dugme1);

        dugme1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AddNewPlayerDialogFragment names = new AddNewPlayerDialogFragment();
                names.setCancelable(false);
                //names.show(getFragmentManager(), "Player names");


                adapter2.notifyDataSetChanged();


            }

        });*/

    }
/*    public void imageClick(View view) {

        Intent intent = new Intent(view.getContext(), AddPlayersActivity.class);
        intent.putExtra("del_player", del_player);
        startActivity(intent);

    }*/

    public void initPlayers(){
        default_player = new Player();
        default_players = new ArrayList<>();

        final int[] images ={ R.drawable.player1,R.drawable.player2, R.drawable.player3 ,R.drawable.player4 };
        //final int[] images ={};
        //final String[] Categories = {};
        final String[] Categories = {"Tinky Winky","Dipsy","Laa-Laa","Po"};

        for (int i = 0; i < images.length; i++) {

            Player current = new Player();
            current.name = Categories[i];
            current.imageId = images[i];

            default_players.add(current);
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
                recyclerView2.setLayoutManager(mLinearLayoutManagerHorizontal);
                break;

            case R.id.linearViewVertical:
                LinearLayoutManager mLinearLayoutManagerVertical = new LinearLayoutManager(this); // (Context context)
                mLinearLayoutManagerVertical.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView2.setLayoutManager(mLinearLayoutManagerVertical);
                break;
            case R.id.gridView:
                GridLayoutManager mGridLayoutManager = new GridLayoutManager(this, 4); // (Context context, int spanCount)
                recyclerView2.setLayoutManager(mGridLayoutManager);
                break;
            case R.id.staggeredViewHorizontal:
                StaggeredGridLayoutManager mStaggeredHorizontalLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL); // (int spanCount, int orientation)
                recyclerView2.setLayoutManager(mStaggeredHorizontalLayoutManager);
                break;
            case R.id.staggeredViewVertical:
                StaggeredGridLayoutManager mStaggeredVerticalLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL); // (int spanCount, int orientation)
                recyclerView2.setLayoutManager(mStaggeredVerticalLayoutManager);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
