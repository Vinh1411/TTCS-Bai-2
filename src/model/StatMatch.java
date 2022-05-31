package model;

public class StatMatch extends Match{
    private float eloChange1;
    private float eloChange2;
    public StatMatch(){
        super();
    }

    public float getEloChange1() {
        return eloChange1;
    }

    public void setEloChange1(float eloChange1) {
        this.eloChange1 = eloChange1;
    }

    public float getEloChange2() {
        return eloChange2;
    }

    public void setEloChange2(float eloChange2) {
        this.eloChange2 = eloChange2;
    }
    
}
