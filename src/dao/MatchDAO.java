package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Match;

public class MatchDAO extends DAO{
    public ArrayList <Match> findByRound(int idRound){
        String sql = "SELECT * FROM tblMatch WHERE roundId = ?";
        ArrayList <Match> list=new ArrayList();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idRound);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Match match=new Match();
                match.setId(rs.getInt("id"));
                match.setResult(rs.getInt("result"));
                match.setEloPlayer1(rs.getFloat("eloPlayer1"));
                match.setEloPlayer2(rs.getFloat("eloPlayer2"));
                PlayerDAO playerDAO=new PlayerDAO();
                match.setPlayer1(playerDAO.findById(rs.getInt("playerId1")));
                match.setPlayer2(playerDAO.findById(rs.getInt("playerId2")));
                list.add(match);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Match getEloById(int id){
        String sql = "SELECT eloPlayer1, eloPlayer2 FROM tblMatch WHERE id = ?";
        Match match=new Match();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                match.setId(rs.getInt("id"));
                match.setEloPlayer1(rs.getFloat("eloPlayer1"));
                match.setEloPlayer2(rs.getFloat("eloPlayer2"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return match;
    }
    
    public boolean update(Match match) {
        String sql = "UPDATE tblMatch SET result=? WHERE id=?";
        String sqlPlayer = "UPDATE tblChessPlayer SET elo=? WHERE id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, match.getResult());
            ps.setInt(2, match.getId());
            ps.executeUpdate();
            
            ps=con.prepareStatement(sqlPlayer);
            ps.setFloat(1, match.getPlayer1().getChessPlayer().getElo());
            ps.setInt(2, match.getPlayer1().getChessPlayer().getId());
            ps.executeUpdate();
            
            ps=con.prepareStatement(sqlPlayer);
            ps.setFloat(1, match.getPlayer2().getChessPlayer().getElo());
            ps.setInt(2, match.getPlayer2().getChessPlayer().getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
