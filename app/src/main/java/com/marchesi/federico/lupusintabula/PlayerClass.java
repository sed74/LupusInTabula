package com.marchesi.federico.lupusintabula;


/**
 * Created by federico.marchesi on 23/12/2016.
 */

class PlayerClass {
    private String mPlayerName;
    private boolean mIsPlayerSelected;
    private Role mRole;


    PlayerClass(String playerName, boolean isPlayerSelected){
        mIsPlayerSelected = isPlayerSelected;
        mPlayerName = playerName;
    }
    public PlayerClass(String playerName, boolean isPlayerSelected, Role role){
        mIsPlayerSelected = isPlayerSelected;
        mPlayerName = playerName;
        mRole = role;
    }

    String getPlayerName(){return mPlayerName;}

    boolean getIsPlayerSelected(){return mIsPlayerSelected;}

    public Role getRole() {return mRole;}

    void setIsPlayerSelected(boolean isSelected) {mIsPlayerSelected = isSelected;}


}
