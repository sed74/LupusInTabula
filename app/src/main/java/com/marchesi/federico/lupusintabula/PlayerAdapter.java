package com.marchesi.federico.lupusintabula;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by federico.marchesi on 22/12/2016.
 */

public class PlayerAdapter extends ArrayAdapter<PlayerClass> {

    private ArrayList<PlayerClass> mPlayers;

    public PlayerAdapter(Context context, ArrayList<PlayerClass> players) {
        super(context, 0, players);
        mPlayers = players;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.player_info, parent, false);
        }

        // Get the data item for this position
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

        // Populate the data into the template view using the data object
        selectPlayerCheckBox.setText(mPlayer.getPlayerName());

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