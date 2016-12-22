package com.marchesi.federico.lupusintabula;

import android.content.DialogInterface;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected ArrayList<String> playerNames = new ArrayList<>();

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

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void init(){
        itemsAdapter =
                new PlayerAdapter(this, playerNames);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

    }
    private void addPlayer(String playerName){
        playerNames.add(playerName);
        itemsAdapter.notifyDataSetChanged();
    }

}
