package com.marchesi.federico.lupusintabula;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String PLAYER_ARRAY = "players";
    public static final String NO_OF_ACTIVE_PLAYER = "no_of_active_player";
    private static final String NO_OF_PLAYER = "no_of_player";
    private static final String PLAYER_NAME = "player_name";
    private static final String PLAYER_IS_SELECTED = "player_is_selected";
    protected static ArrayList<PlayerClass> playerNames = new ArrayList<>();
    PlayerAdapter playersAdapter;
    private EditText nameEditText;
    private Button addButton;

    private static boolean savePrefs(Context mContext) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor mEdit1 = sp.edit();
        mEdit1.clear();
    /* sKey is an array */
        mEdit1.putInt(NO_OF_PLAYER, playerNames.size());

        for (int i = 0; i < playerNames.size(); i++) {
            mEdit1.putString(PLAYER_NAME + i, playerNames.get(i).getPlayerName());
            mEdit1.putBoolean(PLAYER_IS_SELECTED + i, playerNames.get(i).getIsPlayerSelected());
        }
        return mEdit1.commit();
    }

    public static void loadPrefs(Context mContext) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(mContext);
        playerNames.clear();
        int size = sp.getInt(NO_OF_PLAYER, 0);
        String playerName;
        boolean isPlayerSelected;

        for (int i = 0; i < size; i++) {
            playerName = sp.getString(PLAYER_NAME + i, null);
            isPlayerSelected = sp.getBoolean(PLAYER_IS_SELECTED + i, false);
            playerNames.add(new PlayerClass(playerName, isPlayerSelected));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addButton = (Button) findViewById(R.id.add_player_button);

        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String name = nameEditText.getText().toString();
                TextView hintTV = (TextView) findViewById(R.id.name_exists);
                boolean isExisting = contains(playerNames, name);

                hintTV.setVisibility(isExisting ? View.VISIBLE : View.GONE);

                addButton.setEnabled(!isExisting);
            }
        });


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName;

                playerName = nameEditText.getText().toString();
                if (TextUtils.isEmpty(playerName)) {
                    return;
                }
                if (addPlayer(playerName))
                    nameEditText.setText(null);
            }
        });

        Button nextButton = (Button) findViewById(R.id.button_next);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RuleSetup.class);
//                intent.putExtra(PLAYER_ARRAY, playerNames);
                int count = 0;
                for (int i = 0; i < playerNames.size(); i++) {
                    if (playerNames.get(i).getIsPlayerSelected()) count++;
                }
                intent.putExtra(NO_OF_ACTIVE_PLAYER, count);
                startActivity(intent);
            }
        });

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        savePrefs(this);
    }

    private void init() {

        nameEditText = (EditText) findViewById(R.id.add_player_text);

        playersAdapter = new PlayerAdapter(this, playerNames);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(playersAdapter);
        loadPrefs(this);
        playersAdapter.notifyDataSetChanged();
        addRoles();
    }

    private void addRoles() {

    }

    private boolean addPlayer(String playerName) {
        if (contains(playerNames, playerName)) {
            return false;
        }
        PlayerClass temp = new PlayerClass(playerName, true);

        playerNames.add(temp);
        playersAdapter.notifyDataSetChanged();
        return true;
    }

    private boolean contains(ArrayList<PlayerClass> players, String name) {
        for (PlayerClass item : players) {
            if (item.getPlayerName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }


}
