package com.marchesi.federico.lupusintabula;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by federico.marchesi on 22/12/2016.
 */

public class PlayerAdapter extends ArrayAdapter<String> {
    public PlayerAdapter(Context context, ArrayList<String> players) {
        super(context, 0, players);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        String mplayer = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.player_info, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.player_name);
        // Populate the data into the template view using the data object
        tvName.setText(mplayer);
        // Return the completed view to render on screen
        return convertView;
    }
}