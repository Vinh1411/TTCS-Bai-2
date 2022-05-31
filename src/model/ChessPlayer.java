package model;

import java.math.*;

public class ChessPlayer {
    private int id;
    private String name;
    private int year;
    private String nationality;
    private float elo;
    private String des;
    public ChessPlayer(){
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public float getElo() {
        return elo;
    }

    public void setElo(float elo) {
        this.elo = elo;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
    
    //Cập nhập điểm Elo sau trận đấu
    public void updateElo(float diemMatch, float eloPlayer2){
        float q1=(float) Math.pow(10, this.elo/400);
        float q2=(float) Math.pow(10, eloPlayer2/400);
        float e=q1/(q1+q2);
        if(elo>=2400){
            this.elo=this.elo+10*(diemMatch-e);
        }
        else{
            if(elo>2000){
                this.elo=this.elo+15*(diemMatch-e);
            }
            else{
                if(elo>1600){
                    this.elo=this.elo+20*(diemMatch-e);
                }
                else{
                    this.elo=this.elo+25*(diemMatch-e);
                }
            }
        }
    }
}
