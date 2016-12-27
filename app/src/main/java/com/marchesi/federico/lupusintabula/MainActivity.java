package com.marchesi.federico.lupusintabula;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected static ArrayList<PlayerClass> playerNames = new ArrayList<>();

    private static final String NO_OF_PLAYER = "no_of_player";
    private static final String PLAYER_NAME = "player_name";
    private static final String PLAYER_IS_SELECTED = "player_is_selected";

    PlayerAdapter itemsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        Button addButton;
        addButton = (Button) findViewById(R.id.add_player_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName;
                TextView nameTextView = (TextView) findViewById(R.id.add_player_text);
                playerName = nameTextView.getText().toString();
                addPlayer(playerName);
                nameTextView.setText(null);
            }
        });

        Button nextButton = (Button) findViewById(R.id.button_next);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(this, )
            }
        });

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveArray(this);
        Toast.makeText(this, "Login details are saved..", Toast.LENGTH_SHORT).show();
    }

    private void init(){
        itemsAdapter =
                new PlayerAdapter(this, playerNames);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
        loadArray(this);
        itemsAdapter.notifyDataSetChanged();
        addRoles();
    }

    private void addRoles() {

    }

    private void addPlayer(String playerName){
        playerNames.add(new PlayerClass(playerName, true));
        itemsAdapter.notifyDataSetChanged();
    }

    private static boolean saveArray(Context mContext)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor mEdit1 = sp.edit();
        mEdit1.clear();
    /* sKey is an array */
        mEdit1.putInt(NO_OF_PLAYER, playerNames.size());

        for(int i=0;i<playerNames.size();i++)
        {
//            mEdit1.remove(PLAYER_NAME + i);
            mEdit1.putString(PLAYER_NAME + i, playerNames.get(i).getPlayerName());

//            mEdit1.remove(PLAYER_IS_SELECTED + i);
            mEdit1.putBoolean(PLAYER_IS_SELECTED + i, playerNames.get(i).getIsPlayerSelected());
        }
        return mEdit1.commit();
    }

    public static void loadArray(Context mContext)
    {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        playerNames.clear();
        int size = sp.getInt(NO_OF_PLAYER, 0);
        String playerName;
        boolean isPlayerSelected=false;

        for(int i=0;i<size;i++)
        {
            playerName = sp.getString(PLAYER_NAME + i, null);
            isPlayerSelected = sp.getBoolean(PLAYER_IS_SELECTED + i, false);
            playerNames.add(new PlayerClass(playerName, isPlayerSelected));
        }

    }

}
