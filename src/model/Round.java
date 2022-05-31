package model;

import java.util.ArrayList;

public class Round {

    protected int id;
    protected int stt;
    protected String des;
    protected ArrayList<Match> listMatch;

    public Round() {
        listMatch = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public ArrayList<Match> getListMatch() {
        return listMatch;
    }

    public void setListMatch(ArrayList<Match> listMatch) {
        this.listMatch = listMatch;
    }

    public boolean checkEnd() {
        for (Match i : this.listMatch) {
            if (i.getResult() == -1) {
                return false;
            }
        }
        return true;
    }
    //Xem trong round nay 2 nguoi nay da gap nhau chua
    public boolean checkMatchRound(Player player1, Player player2){
        for(Match i:this.getListMatch()){
            if(i.getPlayer1().getId()==player1.getId()){
                if(i.getPlayer2().getId()==player2.getId()){
                    return true;
                }
            }
            else{
                if(i.getPlayer1().getId()==player2.getId() && i.getPlayer2().getId()==player1.getId()){
                    return true;
                }
            }
        }
        return false;
    }
    //Xem trong round nay nguoi nay da dau chua
    public boolean checkMatchRound(Player player){
        for(Match i:this.getListMatch()){
            if(i.getPlayer1().getId()==player.getId()){
                return true;
            }
            else{
                if(i.getPlayer2().getId()==player.getId()){
                    return true;
                }
            }
        }
        return false;
    }
    //Danh sách trận đấu cần cập nhập kết quả
    public ArrayList <Match> listMatchNeedUpdate(){
        ArrayList<Match> list=new ArrayList();
        for(Match i:this.listMatch){
            if(i.getResult()==-1){
                list.add(i);
            }
        }
        return list;
    }
    
    //Lấy Id trận đấu mà người chơi này tham gia trong vòng đấu này
    public int idMatchForRound(int idPlayer){
        for(Match i:this.listMatch){
            if(i.getPlayer1().getId()==idPlayer){
                return i.getId();
            }
            if(i.getPlayer2().getId()==idPlayer){
                return i.getId();
            }
        }
        return 0;
    }
}
