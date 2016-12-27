package com.marchesi.federico.lupusintabula;

import java.util.ArrayList;

/**
 * Created by federico.marchesi on 23/12/2016.
 */

public class PlayerClass {
    private String mPlayerName;
    private boolean mIsPlayerSelected;
    private Role mRole;


    public PlayerClass(String playerName, boolean isPlayerSelected){
        mIsPlayerSelected = isPlayerSelected;
        mPlayerName = playerName;
    }
    public PlayerClass(String playerName, boolean isPlayerSelected, Role role){
        mIsPlayerSelected = isPlayerSelected;
        mPlayerName = playerName;
        mRole = role;
    }

    public String getPlayerName(){return mPlayerName;}

    public boolean getIsPlayerSelected(){return mIsPlayerSelected;}

    public Role getRole() {return mRole;}

    public void setIsPlayerSelected(boolean isSelected) {mIsPlayerSelected = isSelected;}


}
