package com.my.task.artists;

import java.io.Serializable;

public class Cover implements Serializable {
    private String big;
    private String small;

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }
}
