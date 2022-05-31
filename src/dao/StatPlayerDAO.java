package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.ChessPlayer;
import model.Match;
import model.Round;
import model.StatMatch;
import model.StatPlayer;
import model.Tournament;

public class StatPlayerDAO extends DAO{
    public StatPlayer getById(int id){
        StatPlayer sp=new StatPlayer();
        String sql = "SELECT * FROM tblPlayer WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sp.setId(rs.getInt("id"));
                ChessPlayerDAO chessPlayerDAO=new ChessPlayerDAO();
                ChessPlayer cp=chessPlayerDAO.findById(rs.getInt("chessPlayerId"));
                sp.setChessPlayer(cp);
                TournamentDAO tournamentDAO=new TournamentDAO();
                Tournament tournament=tournamentDAO.get(rs.getInt("tournamentId"));
                for(Round i:tournament.getListRound()){
                    if(i.checkEnd()==true){
                        for(Match j:i.getListMatch()){
                            if(j.getPlayer1().getId()==id){
                                StatMatchDAO smDAO=new StatMatchDAO();
                                StatMatch sm=smDAO.getById(j.getId());
                                sp.getListStatMatch().add(sm);
                            }
                            if(j.getPlayer2().getId()==id){
                                StatMatchDAO smDAO=new StatMatchDAO();
                                StatMatch sm=smDAO.getById(j.getId());
                                sp.getListStatMatch().add(sm);
                            }
                        }
                    }
                }
                if(sp.getListStatMatch().get(0).getPlayer1().getId()==id){
                    sp.setEloOld(sp.getListStatMatch().get(0).getEloPlayer1());
                }
                else{
                    sp.setEloOld(sp.getListStatMatch().get(0).getEloPlayer2());
                }
                int size=sp.getListStatMatch().size();
                if(size!=0){
                    if(sp.getListStatMatch().get(size-1).getPlayer1().getId()==id){
                        sp.setEloNew(sp.getListStatMatch().get(size-1).getPlayer1().getChessPlayer().getElo());
                    }
                    else{
                        sp.setEloNew(sp.getListStatMatch().get(size-1).getPlayer2().getChessPlayer().getElo());
                    }
                }
                else{
                    sp.setEloNew(sp.getEloOld());
                }
                sp.setEloChange(sp.getEloNew()-sp.getEloOld());
                
             } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }
}
