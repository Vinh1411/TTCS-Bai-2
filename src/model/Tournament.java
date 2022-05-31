package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Tournament {

    private int id;
    private String name;
    private int year;
    private Date timeHeld;
    private String address;
    private String des;
    private ArrayList<Round> listRound;

    public Tournament() {
        timeHeld = new Date();
        listRound = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getTimeHeld() {
        return timeHeld;
    }

    public void setTimeHeld(Date timeHeld) {
        this.timeHeld = timeHeld;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public ArrayList<Round> getListRound() {
        return listRound;
    }

    public void setListRound(ArrayList<Round> listRound) {
        this.listRound = listRound;
    }

    //Kiểm tra xem liệu giải đấu đã kết thúc chưa
    public String trangThai() {
        if (this.listRound.size() == 11) {
            for (Round i : listRound) {
                if (i.getStt() == 11) {
                    if (i.checkEnd() == false) {
                        return "Dang dien ra";
                    }
                }
            }
            return "Da ket thuc";
        } else {
            return "Dang dien ra";
        }
    }

    //true la vong dau hien tai da ket thuc
    //false la vong dau hien tai chua ket thuc
    public boolean statusRound() {
        int roundIndex = this.listRound.size();
        if (listRound.get(roundIndex - 1).checkEnd() == true) {
            return true;
        }
        return false;
    }

    //Kiểm tra xem hiện tại cho đến vòng này thì 2 người này đã từng gặp nhau chưa
    public boolean checkMatchedTournament(Player player1, Player player2) {
        for (int i = 0; i < listRound.size(); i++) {
            if (listRound.get(i).checkMatchRound(player1, player2) == true) {
                return true;
            }
        }
        return false;
    }

    //Lấy tổng điểm của người chơi qua các vòng đấu đến hiện tại
    public void setTongDiem(Player player) {
        if (listRound.size() == 0) {
            player.setTongDiem(0);
        } else {
            float tongDiem = (float) 0.0;
            for (Round i : listRound) {
                if (i.checkEnd() == true) {
                    for (Match match : i.getListMatch()) {
                        if (player.getChessPlayer().getId() == match.getPlayer1().getChessPlayer().getId()) {
                            if (match.getResult() == 0) {
                                tongDiem += 1;
                            } else {
                                if (match.getResult() == 1) {
                                    tongDiem += 0.5;
                                }
                            }
                        }
                        if (player.getChessPlayer().getId() == match.getPlayer2().getChessPlayer().getId()) {
                            if (match.getResult() == 2) {
                                tongDiem += 1;
                            } else {
                                if (match.getResult() == 1) {
                                    tongDiem += 0.5;
                                }
                            }
                        }
                    }
                }
            }
            player.setTongDiem(tongDiem);
        }
    }

    //Lấy tổng điểm của người chơi cho đến roundSTT
    public void setTongDiem(Player player, int roundSTT) {
        if (listRound.size() == 0) {
            player.setTongDiem(0);
        } else {
            float tongDiem = (float) 0.0;
            for (Round i : listRound.subList(0, roundSTT)) {
                if (i.checkEnd() == true) {
                    for (Match match : i.getListMatch()) {
                        if (player.getChessPlayer().getId() == match.getPlayer1().getChessPlayer().getId()) {
                            if (match.getResult() == 0) {
                                tongDiem += 1;
                            } else {
                                if (match.getResult() == 1) {
                                    tongDiem += 0.5;
                                }
                            }
                        }
                        if (player.getChessPlayer().getId() == match.getPlayer2().getChessPlayer().getId()) {
                            if (match.getResult() == 2) {
                                tongDiem += 1;
                            } else {
                                if (match.getResult() == 1) {
                                    tongDiem += 0.5;
                                }
                            }
                        }
                    }
                }
            }
            player.setTongDiem(tongDiem);
        }
    }

    //Xếp lịch cho giải đấu
    public void startGame(ArrayList<Player> listPlayer) {
        Collections.sort(listPlayer);
        Round round = new Round();
        round.setStt(listRound.size() + 1);
        for (int i = 0; i < listPlayer.size() - 1; i++) {
            //Kiểm tra xem vòng đáu đã kết thức chưa
            if (round.checkMatchRound(listPlayer.get(i)) == false) {
                for (int j = i + 1; j < listPlayer.size(); j++) {
                    //Nếu 2 người này chưa từng gặp nhau
                    if (this.checkMatchedTournament(listPlayer.get(i), listPlayer.get(j)) == false) {
                        if (round.checkMatchRound(listPlayer.get(j)) == false) {
                            //Tạo một trận đấu cho 2 người này
                            Match match = new Match();
                            match.setEloPlayer1(listPlayer.get(i).getChessPlayer().getElo());
                            match.setEloPlayer2(listPlayer.get(j).getChessPlayer().getElo());
                            match.setResult(-1);
                            match.setPlayer1(listPlayer.get(i));
                            match.setPlayer2(listPlayer.get(j));
                            round.getListMatch().add(match);
                            break;
                        }
                    }
                }
            }
        }
        this.listRound.add(round);
    }
}
