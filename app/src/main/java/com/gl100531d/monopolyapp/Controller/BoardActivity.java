package com.gl100531d.monopolyapp.Controller;

import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.FloatMath;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gl100531d.monopolyapp.Animation.ShakeDetector;
import com.gl100531d.monopolyapp.Fragments.AddNewPlayerDialogFragment;
import com.gl100531d.monopolyapp.Model.Player;
import com.gl100531d.monopolyapp.Model.PlayerPositions;
import com.gl100531d.monopolyapp.R;
import com.gl100531d.monopolyapp.db.FeedReaderContract;
import com.gl100531d.monopolyapp.db.FeedReaderDbHelper;

import java.util.ArrayList;

/**
 * Created by Leon on 8/7/2016.
 */
public class BoardActivity extends AppCompatActivity{

    // The following are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    public static RelativeLayout relativeLayout;

    private ArrayList<Player> allPlayers;
    private PlayerPositions position;

    int[][] figurePositions;

    private void otvoriBazu(){
        FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(this);
        SQLiteDatabase db = feedReaderDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.PlayerEntry.COLUMN_NAME_NAME, name); //
        values.put(FeedReaderContract.PlayerEntry.COLUMN_NAME_PLAYER_NAME, playerName); // Ime igraca
        values.put(FeedReaderContract.PlayerEntry.COLUMN_NAME_IMAGE_ID, id); // Ime igraca

        // Inserting Row
        db.insert(FeedReaderContract.PlayerEntry.TABLE_NAME, null, values);
        db.close(); // Closing database connection

        //PA CITAM POSLE...................................................................

        //init Players
        SQLiteDatabase db2 = feedReaderDbHelper.getReadableDatabase();

        String sql = "SELECT * FROM " + FeedReaderContract.PlayerEntry.TABLE_NAME;
        Cursor cur = db2.rawQuery(sql,null);
        if(cur != null){
            if(cur.moveToFirst()){
                do{
                    String pom_name =  cur.getString(1);
                    String pom_playerName =  cur.getString(2);
                    int pom_id = cur.getInt(3);

                    Player current = new Player();
                    current.name = pom_name;
                    current.playerName = pom_playerName;
                    current.imageId = pom_id;

                    public Player(Context context, int playerId, String name, int imageId, int playerImageID, String playerName)

                    switch (pom_playerName){
                            case "player1":
                                current.playerImage = new ImageView(this);
                                current.playerImage.setImageResource(R.drawable.player1_small);
                                break;
                            case "player2":
                                current.playerImage = new ImageView(this);
                                current.playerImage.setImageResource(R.drawable.player2_small);
                                break;
                            case "player3":
                                current.playerImage = new ImageView(this);
                                current.playerImage.setImageResource(R.drawable.player3_small);
                                break;
                            case "player4":
                                current.playerImage = new ImageView(this);
                                current.playerImage.setImageResource(R.drawable.player4_small);
                                break;

                        }


                    allPlayers.add(current);

                }while(cur.moveToNext());
            }
        }
        cur.close();
        db2.close();
    }
    private void inicijalizacija() {
        otvoriBazu();

        players = new ArrayList<>();

        players.add(pl);






    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board_game);

        inicijalizacija();

        //=============================================================================
        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
				/*
				 * The following method, "handleShakeEvent(count):" is a stub //
				 * method you would use to setup whatever you want done once the
				 * device has been shook.
				 */
                handleShakeEvent(count);
            }
        });
        //=============================================================================


        RelativeLayout board_layout = (RelativeLayout) findViewById(R.id.game_layout);


        //dodavanje slicke
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);

        //delim ekran na 13 i setujem margine
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

/*        int visina = (int)( metrics.widthPixels / 8 - 200 );
        int sirina = (int)( metrics.heightPixels /13 -60 );*/



        int visina = (int)( metrics.widthPixels / 8 -200 );
        int sirina = (int)( metrics.heightPixels /13 - 60 +240);

        /*layoutParams.rightMargin =  metrics.widthPixels /13 *3;
        layoutParams.bottomMargin = metrics.heightPixels /8 * 3;
*/
        //layoutParams.rightMargin =  metrics.widthPixels/2 ;
        //layoutParams.bottomMargin = metrics.widthPixels/2 ;

        layoutParams.leftMargin =  sirina;
        layoutParams.topMargin = visina ;

/*        layoutParams.leftMargin = 500;
        layoutParams.topMargin = 50;*/


        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.player1);

        players
        board_layout.addView(imageView,layoutParams);
        /*this.addContentView(imageView, layoutParams);*/




/*        figurePositions = new int[10][2];
        figurePositions[0][0]= metrics.widthPixels /13;
        figurePositions[0][1]= metrics.widthPixels /13 +50;

        figurePositions[1][0]= 12;
        figurePositions[1][1]= 12;

        figurePositions[2][0]= 12;
        figurePositions[2][1]= 12;

        figurePositions[3][0]= 12;
        figurePositions[3][1]= 12;

        figurePositions[4][0]= 12;
        figurePositions[4][1]= 12;

        figurePositions[5][0]= 12;
        figurePositions[5][1]= 12;*/


        //initBoardPlayers();

    }
    public void handleShakeEvent(int count){
        Toast.makeText(this,"Mobilni protresen"+ count, Toast.LENGTH_SHORT).show();

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        movePlayer(layoutParams,0,5);

        //PAZITI I DA BAZA TREBA DA SE UPDATE POSLE SVAKOG POTEZA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    }

    private void movePlayer(final RelativeLayout.LayoutParams layoutParams, int start, final int end){
        int pomeraj;
        int vremePomeraja = 300;

        pomeraj = end - start;

        for(int i  = start+1, j = 0 ; j< pomeraj; i++, j++){


            Handler handler = new Handler();

            //drnda nesto kad se stavi i
            final int pom = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    movePlayerIcon(layoutParams, pom);
                    //ovde odraditi ako se prodje kroz polje 0 tj start da se dobije 200$
                    /*if (finalI == GO_FIELD)
                        *//* Rule: Each time a player's token lands on or passes over GO,
                        whether by throwing the dice or drawing a card,
                        the Banker pays him/her a $200 salary. *//*
                        players[currentPlayer].giveMoney(GO_SALARY);
                    if (finalI == destination)
                        // Perform action
                        // Proveri na kom je polju i odradi odredjenu akciju
                        action(params);*/

                }
            }, vremePomeraja * (j + 1));

        }



    }

    private void movePlayerIcon(RelativeLayout.LayoutParams params, int position) {
        params.leftMargin = 500;
        params.topMargin = 500;
        players[currentPlayer].getPawn().setLayoutParams(params);
    }

/*    @Override
    public void onSensorChanged(SensorEvent event) {


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }*/

    public void settingsClicked(View view) {
        AddNewPlayerDialogFragment addNew = new AddNewPlayerDialogFragment();

        FragmentManager manager = ((AddedPlayersActivity) context).getFragmentManager();

        Bundle args = new Bundle();
        //da li sam pokupio sa dobre pozicije?>??
        args.putInt("imageId", 1);
        args.putString("name", "nesto2");

        addNew.setArguments(args);

        addNew.setCancelable(false);
        addNew.show(manager, "Player names");
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }



/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_board ,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.endGame:
                Toast.makeText(this, "Maximalan broj igraca je " , Toast.LENGTH_LONG).show();
                break;
            case R.id.startGame:
                Toast.makeText(this, "Maximalan broj igraca je " , Toast.LENGTH_LONG).show();
                break;


        }

        return super.onOptionsItemSelected(item);
    }*/
}
