package com.marchesi.federico.lupusintabula;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by federico.marchesi on 22/12/2016.
 */

public class PlayerAdapter extends ArrayAdapter<PlayerClass> {

    ArrayList<PlayerClass> mPlayers;

    public PlayerAdapter(Context context, ArrayList<PlayerClass> players) {
        super(context, 0, players);
        mPlayers = players;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // Get the data item for this position


        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.player_info, parent, false);
        }

        PlayerClass mPlayer = getItem(position);
        CheckBox selectPlayerCheckBox = (CheckBox) convertView.findViewById(R.id.cbx_player);

        selectPlayerCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPlayers.get(position).setIsPlayerSelected(isChecked);

            }
        });

        selectPlayerCheckBox.setChecked(mPlayer.getIsPlayerSelected());

        //Handle buttons and add onClickListeners
        ImageView deleteBtn = (ImageView) convertView.findViewById(R.id.delete_player_button);

        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                mPlayers.remove(position);
                notifyDataSetChanged();
            }
        });


        // Lookup view for data population
//        TextView tvName = (TextView) convertView.findViewById(R.id.player_name);
        CheckBox tvName = (CheckBox) convertView.findViewById(R.id.cbx_player);
        // Populate the data into the template view using the data object
        tvName.setText(mPlayer.getPlayerName());

        CheckBox playerCheckBox = (CheckBox) convertView.findViewById(R.id.cbx_player);

        // Return the completed view to render on screen
        return convertView;
    }

    private boolean canStartGame() {
        if (mPlayers == null) {
            return false;
        }
        for (PlayerClass item : mPlayers) {
            if (item.getIsPlayerSelected()) {
                return true;
            }
        }
        return false;
    }

}