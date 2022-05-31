package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.ChessPlayer;

public class ChessPlayerDAO extends DAO {

    public ChessPlayerDAO() {
        super();
    }

    public boolean save(ChessPlayer chessPlayer) {
        String sql = "INSERT INTO tblChessPlayer(name, year, nationality, elo, des) VALUES(?,?,?,?,?)";
        boolean test = false;
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, chessPlayer.getName());
            ps.setInt(2, chessPlayer.getYear());
            ps.setString(3, chessPlayer.getNationality());
            ps.setFloat(4, chessPlayer.getElo());
            ps.setString(5, chessPlayer.getDes());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                chessPlayer.setId(generatedKeys.getInt(1));
                test = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }

    public ArrayList<ChessPlayer> findByKey(String key) {
        ArrayList<ChessPlayer> list = new ArrayList();
        String sql = "SELECT * FROM tblChessPlayer WHERE name LIKE ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChessPlayer cp = new ChessPlayer();
                cp.setId(rs.getInt("id"));
                cp.setName(rs.getString("name"));
                cp.setYear(rs.getInt("year"));
                cp.setNationality(rs.getString("nationality"));
                cp.setElo(rs.getFloat("elo"));
                cp.setDes(rs.getString("des"));
                list.add(cp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ChessPlayer findById(int id) {
        ChessPlayer cp = new ChessPlayer();
        String sql = "SELECT * FROM tblChessPlayer WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cp.setId(rs.getInt("id"));
                cp.setName(rs.getString("name"));
                cp.setYear(rs.getInt("year"));
                cp.setNationality(rs.getString("nationality"));
                cp.setElo(rs.getFloat("elo"));
                cp.setDes(rs.getString("des"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cp;
    }
    
    public boolean update(ChessPlayer cp) {
        String sql = "UPDATE tblChessPlayer SET name=?, year=?, nationality=?, elo=?, des=? WHERE id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cp.getName());
            ps.setInt(2, cp.getYear());
            ps.setString(3, cp.getNationality());
            ps.setFloat(4, cp.getElo());
            ps.setString(5, cp.getDes());
            ps.setInt(6, cp.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
