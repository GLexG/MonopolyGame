package com.gl100531d.monopolyapp.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.gl100531d.monopolyapp.Controller.AddPlayersActivity;
import com.gl100531d.monopolyapp.Controller.AddedPlayersActivity;
import com.gl100531d.monopolyapp.Model.Player;
import com.gl100531d.monopolyapp.R;
import com.gl100531d.monopolyapp.db.FeedReaderContract.*;
import com.gl100531d.monopolyapp.db.FeedReaderDbHelper;


/**
 * Created by Leon on 8/3/2016.
 */
public class AddNewPlayerDialogFragment extends DialogFragment {

    private String vrednost;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because it's going in the dialog layout
        final View view = inflater.inflate(R.layout.add_new_player_dialog_view, null);
        builder.setView(view);


        final int imageId = getArguments().getInt("imageId");
        final String name = getArguments().getString("name");



        // Add action buttons
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditText new_player = (EditText) view.findViewById(R.id.new_player);
                String newPlayer = new_player.getText().toString().trim();

/*                Player current = new Player();
                current.name = name;
                //current.imageId = imageId;
                current.playerName = newPlayer;*/



                Intent intent = new Intent(view.getContext(), AddPlayersActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("imageId", imageId);
                intent.putExtra("playerName", newPlayer);
                startActivity(intent);


            }
        });

        builder.setNegativeButton("Cancel", null);

        return builder.create();
    }



}
