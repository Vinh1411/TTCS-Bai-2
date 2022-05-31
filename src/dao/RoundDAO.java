package dao;

import static dao.DAO.con;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Round;
import model.Tournament;

public class RoundDAO extends DAO{
    //Lấy toàn bộ các round cho đến hiện tại của 1 giải đấu dựa theo id của giải đấu
    public ArrayList <Round> findByTournament(int idTou){
        String sql = "SELECT * FROM tblRound WHERE tournamentId = ?";
        ArrayList <Round> list=new ArrayList();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idTou);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Round round=new Round();
                round.setId(rs.getInt("id"));
                round.setStt(rs.getInt("stt"));
                MatchDAO matchDAO=new MatchDAO();
                round.setListMatch(matchDAO.findByRound(rs.getInt("id")));
                list.add(round);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
