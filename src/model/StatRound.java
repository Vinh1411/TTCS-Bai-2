package model;

import java.util.ArrayList;

public class StatRound extends Round{
    private ArrayList<StatPlayer> listStatPlayer;
    public StatRound(){
        super();
        this.listStatPlayer=new ArrayList();
    }

    public ArrayList<StatPlayer> getListStatPlayer() {
        return listStatPlayer;
    }

    public void setListStatPlayer(ArrayList<StatPlayer> listStatPlayer) {
        this.listStatPlayer = listStatPlayer;
    }
    
}
