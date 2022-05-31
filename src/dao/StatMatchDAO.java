package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Player;
import model.StatMatch;

public class StatMatchDAO extends DAO{
    public StatMatchDAO(){
        super();
    }
    public StatMatch getById(int id){
        StatMatch sm=new StatMatch();
        String sql = "SELECT * FROM tblMatch WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sm.setId(rs.getInt("id"));
                sm.setResult(rs.getInt("result"));
                sm.setEloPlayer1(rs.getFloat("eloPlayer1"));
                sm.setEloPlayer2(rs.getFloat("eloPlayer2"));
                PlayerDAO playerDAO=new PlayerDAO();
                Player player1=playerDAO.findById(rs.getInt("playerId1"));
                Player player2=playerDAO.findById(rs.getInt("playerId2"));
                player1.getChessPlayer().setElo(sm.getEloPlayer1());
                player2.getChessPlayer().setElo(sm.getEloPlayer2());
                sm.setPlayer1(player1);
                sm.setPlayer2(player2);
                if(sm.getResult()==0){
                    sm.getPlayer1().getChessPlayer().updateElo(1, sm.getEloPlayer2());
                    sm.getPlayer2().getChessPlayer().updateElo(0, sm.getEloPlayer1());
                    sm.setEloChange1(-sm.getEloPlayer1()+sm.getPlayer1().getChessPlayer().getElo());
                    sm.setEloChange2(-sm.getEloPlayer2()+sm.getPlayer2().getChessPlayer().getElo());
                }
                else{
                    if(sm.getResult()==1){
                        sm.getPlayer1().getChessPlayer().updateElo((float) 0.5, sm.getEloPlayer2());
                        sm.getPlayer2().getChessPlayer().updateElo((float) 0.5, sm.getEloPlayer1());
                        sm.setEloChange1(-sm.getEloPlayer1()+sm.getPlayer1().getChessPlayer().getElo());
                        sm.setEloChange2(-sm.getEloPlayer2()+sm.getPlayer2().getChessPlayer().getElo());
                    }
                    else{
                        sm.getPlayer1().getChessPlayer().updateElo(0, sm.getEloPlayer2());
                        sm.getPlayer2().getChessPlayer().updateElo(1, sm.getEloPlayer1());
                        sm.setEloChange1(-sm.getEloPlayer1()+sm.getPlayer1().getChessPlayer().getElo());
                        sm.setEloChange2(-sm.getEloPlayer2()+sm.getPlayer2().getChessPlayer().getElo());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sm;
    }
}
