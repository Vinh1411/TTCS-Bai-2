package model;

public class Match {
    protected int id;
    protected int result;
    protected float eloPlayer1;
    protected float eloPlayer2;
    protected Player player1;
    protected Player player2;
    public Match(){
        //result = -1 tuc la tran dau dang dien ra
        //result = 0 tuc la player 1 win.
        //result = 1 tuc la hoa nhau.
        //result = 2 tuc la player 2 win
        this.result=-1;
        player1=new Player();
        player2=new Player();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public float getEloPlayer1() {
        return eloPlayer1;
    }

    public void setEloPlayer1(float eloPlayer1) {
        this.eloPlayer1 = eloPlayer1;
    }

    public float getEloPlayer2() {
        return eloPlayer2;
    }

    public void setEloPlayer2(float eloPlayer2) {
        this.eloPlayer2 = eloPlayer2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
    
}
