package model;

import java.util.ArrayList;

public class StatPlayer extends Player{
    private float eloOld;
    private float eloNew;
    private float eloChange;
    private ArrayList<StatMatch> listStatMatch;
    public StatPlayer(){
        super();
        this.listStatMatch=new ArrayList();
    }

    public float getEloOld() {
        return eloOld;
    }

    public void setEloOld(float eloOld) {
        this.eloOld = eloOld;
    }

    public float getEloNew() {
        return eloNew;
    }

    public void setEloNew(float eloNew) {
        this.eloNew = eloNew;
    }

    public float getEloChange() {
        return eloChange;
    }

    public void setEloChange(float eloChange) {
        this.eloChange = eloChange;
    }

    public ArrayList<StatMatch> getListStatMatch() {
        return listStatMatch;
    }

    public void setListStatMatch(ArrayList<StatMatch> listStatMatch) {
        this.listStatMatch = listStatMatch;
    }

    public int compareTo(StatPlayer o){
        if(this.eloChange<o.getEloChange()){
            return -1;
        }
        else{
            if(this.eloChange==o.getEloChange()){
                if(this.eloNew<o.getEloNew()){
                    return -1;
                }
                else{
                    if(this.eloNew==o.getEloNew()){
                        return 0;
                    }
                    else{
                        return 1;
                    }
                }
            }
            return 1;
        }
    }
}
