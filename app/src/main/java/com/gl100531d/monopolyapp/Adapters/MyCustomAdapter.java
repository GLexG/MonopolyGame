package com.gl100531d.monopolyapp.Adapters;


import android.app.FragmentManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.gl100531d.monopolyapp.Animation.AnimationUtil;
import com.gl100531d.monopolyapp.Controller.AddPlayersActivity;
import com.gl100531d.monopolyapp.Controller.AddedPlayersActivity;
import com.gl100531d.monopolyapp.Fragments.AddNewPlayerDialogFragment;
import com.gl100531d.monopolyapp.Model.Player;
import com.gl100531d.monopolyapp.R;
import com.gl100531d.monopolyapp.db.FeedReaderContract.*;
import com.gl100531d.monopolyapp.db.FeedReaderDbHelper;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Leon on 8/3/2016.
 */
//linkovanja adaptera sa recycler view-om!!!!!!!!!!!!!!!!!!!
public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {

    int flag;
    int pozadina;

    Context context;
    ArrayList<Player> data;
    LayoutInflater inflater;

    //deo za button
    public String pom="";


    //DEO ZA ANIMACIJU
    int previousPosition = 0;

    //konstruktor za data klasu
    public MyCustomAdapter(Context context, ArrayList<Player> data, @Nullable int flag) {
        this.context = context;
        this.data = data;
        this.flag = flag;
        inflater = LayoutInflater.from(context);

/*        //hocu nekako da ispisem celu listu
        for (Iterator<Player> iter = data.iterator(); iter.hasNext(); ) {
            Player info = iter.next();
            String s = info.getName();

            pom+= " + " + s;
        }*/
    }

    @Override
    public MyCustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        //ovo je izgled svakog layout-a i to prosledjujemo MyViewHolder-u
        //View view = inflater.inflate(R.layout.list_item_row,parent,false);
        if (flag == 1){
            pozadina = R.layout.added_players_game_row;
        }else{
            pozadina = R.layout.add_players_game_row;
        }

        View view = inflater.inflate(pozadina,parent,false);
        MyViewHolder holder = new MyViewHolder(view);






        /** ---------------------------MOJE VNUGOOOOOOOOOOOOOOOOOOOOOOOOOOOOO -------------------- */

        return holder;
    }

    //onBindViewHolder se zove za svaki row i tu setujemo textView i imageView
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int position) {


        myViewHolder.textView.setText(data.get(position).name);
        myViewHolder.imageView.setImageResource(data.get(position).imageId);

        if(flag ==1 ){
            myViewHolder.textView2.setText(data.get(position).playerName);
        }

        //myViewHolder.butyon

        /**********************DEO ZA ANIMACIJU************************/
        //ako je true idemo na DOLE
        if(position > previousPosition){
            AnimationUtil.animate(myViewHolder, true);

        }else{  //idemo na GORE
            AnimationUtil.animate(myViewHolder, false);
        }
        previousPosition = position;

        /**********************Za ADDOVANJE podataka************************/
        final int currentPosition = position;
        final Player infoData = data.get(position);




        /**********************DEO ZA on click************************/
        //obican click
        /***** OVDE PRVO na Obican Click prikazujem Dijalog i prosledjujem ime u dijalog i posle u
         * dijalogu pravim novi Intent gde mi prikazuje dodate igrace
         */
        myViewHolder.imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "onClick Called on position" + position, Toast.LENGTH_SHORT).show();

                //IZABEREM IGRACA I DODAM IME
                AddNewPlayerDialogFragment addNew = new AddNewPlayerDialogFragment();

                FragmentManager manager = ((AddedPlayersActivity) context).getFragmentManager();

                Bundle args = new Bundle();
                //da li sam pokupio sa dobre pozicije?>??
                args.putInt("imageId", infoData.getImageId());
                args.putString("name", infoData.getName());

                addNew.setArguments(args);

                addNew.setCancelable(false);
                addNew.show(manager, "Player names");

/*                Intent intent = new Intent(context, AddedPlayersActivity.class);
                intent.putExtra("imageId", infoData.getimageId());
                intent.putExtra("name", infoData.getName());
                context.startActivity(intent);*/


                //notifyDataSetChanged();


                //addovanje itema
                //addItem(currentPosition, infoData);
            }
        });
        //long click
        myViewHolder.imageView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "on_LONG_Click Called on position" + position, Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, "Informacije" + infoData.getImageId() + infoData.getName(), Toast.LENGTH_SHORT).show();

                //vracamo true da ne bi prvo vratio long pa short call
                removeItem(infoData);

                if (flag == 1){
                    //izbrisi iz baze
                    FeedReaderDbHelper feedReaderDbHelper = new FeedReaderDbHelper(context);
                    SQLiteDatabase db = feedReaderDbHelper.getWritableDatabase();
                    db.delete(PlayerEntry.TABLE_NAME,
                            PlayerEntry.COLUMN_NAME_PLAYER_NAME + " = ? ",
                            new String[]{infoData.playerName});
                    db.close();

                    Toast.makeText(context, "Izbrisan igrac" + infoData.playerName, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "on_LONG_Click Called on position" + pom, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        //HOCU KADA KLIKNEM BUTTON DA RADI NESTO



    }
    /**********************Za ADDOVANJE podataka************************/
    private void addItem(int position, Player infoData) {
        //setujemo data i notificiramo adapter da smo napravili promenu
        data.add(position, infoData);
        notifyItemInserted(position);
    }

    private void removeItem(Player infoData) {

        int currPosition = data.indexOf(infoData);
        data.remove(currPosition);
        notifyItemRemoved(currPosition);

        //Toast.makeText(context, " Izbrisana macka id" + infoData.imageId, Toast.LENGTH_SHORT).show();
    }

    //kazemo velicinu liste
    @Override
    public int getItemCount() {
        return data.size();
    }

    //inicijalizujemo textView i imageView
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView textView2;
        ImageView imageView;

        public MyViewHolder(View itemView){
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.txv_row);
            if(flag == 1) {
                textView2 = (TextView) itemView.findViewById(R.id.txv_row2);
            }
            imageView = (ImageView) itemView.findViewById(R.id.img_row);
        }
    }
}
