package com.example.administrator.slidingdemo;

/**
 * Created by Administrator on 2018/12/24.
 */

public class CardBean {

    private int pic;
    private String name;
    private String ballYear;
    private String team;

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public String getBallYear() {
        return ballYear;
    }

    public void setBallYear(String ballYear) {
        this.ballYear = ballYear;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "CardBean{" +
                "pic=" + pic +
                ", title='" + name + '\'' +
                ", ballYear='" + ballYear + '\'' +
                ", team='" + team + '\'' +
                '}';
    }
}
