package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.ChessPlayer;
import model.Player;
import model.Tournament;

public class PlayerDAO extends DAO {
    public PlayerDAO(){
        super();
    }
    public ArrayList<Player> findAll(){
        ArrayList <Player> list=new ArrayList();
        String sql = "SELECT * FROM tblPlayer";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Player p = new Player();
                p.setId(rs.getInt("id"));
                ChessPlayer cp=new ChessPlayer();
                cp.setId(rs.getInt("chessPlayerId"));
                Tournament tournament=new Tournament();
                tournament.setId(rs.getInt("tournamentId"));
                p.setChessPlayer(cp);
                p.setTournament(tournament);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Player findById(int id){
        Player player=new Player();
        String sql = "SELECT * FROM tblPlayer WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                player.setId(rs.getInt("id"));
                ChessPlayerDAO chessPlayerDAO=new ChessPlayerDAO();
                ChessPlayer cp=chessPlayerDAO.findById(rs.getInt("chessPlayerId"));
                player.setChessPlayer(cp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return player;
    }
    
    //Lấy toàn bộ người chơi trong giải đấu dựa theo id của giải đấu
    public ArrayList<Player> findByTournament(int id){
        ArrayList <Player> list=new ArrayList();
        String sql = "SELECT * FROM tblPlayer WHERE tournamentId=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Player p = new Player();
                p.setId(rs.getInt("id"));
                Tournament tournament=new Tournament();
                tournament.setId(rs.getInt("tournamentId"));
                ChessPlayerDAO cpDAO=new ChessPlayerDAO();
                p.setChessPlayer(cpDAO.findById(rs.getInt("chessPlayerId")));
                p.setTournament(tournament);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean save(Player player){
        String sql = "INSERT INTO tblPlayer (tournamentId, chessPlayerId) VALUES (?, ?)";
        boolean rs=false;
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, player.getTournament().getId());
            ps.setInt(2, player.getChessPlayer().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                player.setId(generatedKeys.getInt(1));
            }
            rs=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}
