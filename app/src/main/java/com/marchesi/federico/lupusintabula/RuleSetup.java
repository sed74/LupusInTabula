package com.marchesi.federico.lupusintabula;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RuleSetup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_setup);

        Intent intent = getIntent();

        TextView headerTextView = (TextView) findViewById(R.id.no_of_players);
        int noOfPlayer = intent.getIntExtra(MainActivity.NO_OF_ACTIVE_PLAYER, 0);

        headerTextView.setText(getString(R.string.no_of_player_title, noOfPlayer));


    }
}
