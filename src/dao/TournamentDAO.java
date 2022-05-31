package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Match;
import model.Round;
import model.Tournament;

public class TournamentDAO extends DAO {

    public TournamentDAO() {
        super();
    }

    public ArrayList<Tournament> findAll() {
        ArrayList<Tournament> list = new ArrayList();
        String sql = "SELECT * FROM tblTournament";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Tournament tournament = new Tournament();
                tournament.setId(rs.getInt("id"));
                tournament.setName(rs.getString("name"));
                tournament.setYear(rs.getInt("year"));
                tournament.setTimeHeld(rs.getDate("timeHeld"));
                tournament.setAddress(rs.getString("address"));
                tournament.setDes(rs.getString("des"));
                list.add(tournament);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //Lấy toàn bộ thông tin về giải đấu, các vòng đấu trong giải, các trận đấu, thông tin người chơi
    public Tournament get(int id){
        Tournament tournament = new Tournament();
        String sql = "SELECT * FROM tblTournament WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tournament.setId(rs.getInt("id"));
                tournament.setName(rs.getString("name"));
                tournament.setYear(rs.getInt("year"));
                tournament.setTimeHeld(rs.getDate("timeHeld"));
                tournament.setAddress(rs.getString("address"));
                tournament.setDes(rs.getString("des"));
                RoundDAO roundDAO=new RoundDAO();
                tournament.setListRound(roundDAO.findByTournament(id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tournament;
    }
    
    public boolean save(Tournament tournament) throws SQLException {
        String sql = "INSERT INTO tblTournament(name, year, timeHeld, address, des) VALUES(?,?,?,?,?)";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean test = false;
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, tournament.getName());
            ps.setInt(2, tournament.getYear());
            ps.setDate(3, new java.sql.Date(tournament.getTimeHeld().getTime()));
            ps.setString(4, tournament.getAddress());
            ps.setString(5, tournament.getDes());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                tournament.setId(generatedKeys.getInt(1));
                test = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }
    
    //Lưu thông tin về round mới của giải đấu vào hệ thống
    public boolean saveRound(Tournament tournament){
        String sqlAddRound = "INSERT INTO tblRound (stt, tournamentId) VALUES(?, ?)";
        String sqlAddMatch = "INSERT INTO tblMatch(result, eloPlayer1, eloPlayer2, playerId1, playerId2, roundId) VALUES(?, ?, ?, ?, ?, ?)";
        boolean result = true;
        try {
            PreparedStatement ps = con.prepareStatement(sqlAddRound, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, tournament.getListRound().size());
            ps.setInt(2, tournament.getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                tournament.getListRound().get(tournament.getListRound().size()-1).setId(generatedKeys.getInt(1));
                for (Match i : tournament.getListRound().get(tournament.getListRound().size()-1).getListMatch()) {
                    ps = con.prepareStatement(sqlAddMatch, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, i.getResult());
                    ps.setFloat(2, i.getEloPlayer1());
                    ps.setFloat(3, i.getEloPlayer2());
                    ps.setInt(4, i.getPlayer1().getId());
                    ps.setInt(5, i.getPlayer2().getId());
                    ps.setInt(6, tournament.getListRound().get(tournament.getListRound().size()-1).getId());
                    ps.executeUpdate();
                    generatedKeys = ps.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        i.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (Exception e1) {
            result = false;
            e1.printStackTrace();
        }
        return result;
    }
}
