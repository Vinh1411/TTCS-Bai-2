package model;

public class Player implements Comparable<Player> {

    protected int id;
    protected Tournament tournament;
    protected ChessPlayer chessPlayer;
    protected float tongDiem;
    public Player() {
        this.tournament = new Tournament();
        this.chessPlayer = new ChessPlayer();
        tongDiem = (float) 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTongDiem(float tongDiem) {
        this.tongDiem = tongDiem;
    }
    
    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public ChessPlayer getChessPlayer() {
        return chessPlayer;
    }

    public float getTongDiem() {
        return tongDiem;
    }

    public void setChessPlayer(ChessPlayer chessPlayer) {
        this.chessPlayer = chessPlayer;
    }

    //So sánh 2 người chơi với nhau
    @Override
    public int compareTo(Player o) {
        if (tongDiem < o.tongDiem) {
            return 1;
        } else {
            if (tongDiem == o.tongDiem) {
                if (this.getChessPlayer().getElo() < o.getChessPlayer().getElo()) {
                    return 1;
                } else {
                    if (this.getChessPlayer().getElo() == o.getChessPlayer().getElo()) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            }
            else{
                return -1;
            }
        }
    }
}
