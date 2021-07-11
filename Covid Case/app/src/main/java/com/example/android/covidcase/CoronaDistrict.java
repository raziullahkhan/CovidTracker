package com.example.android.covidcase;

import java.io.Serializable;

public class CoronaDistrict implements Serializable {
    private String state;
    private String district;
    private long total;
    private long totalToday;
    private long death;
    private long deathToday;
    private long recovered;
    private long recoveredToday;
    private long active;

    public CoronaDistrict(String state, String district, long total, long totalToday, long death, long deathToday, long recovered, long recoveredToday, long active) {
        this.state = state;
        this.district = district;
        this.total = total;
        this.totalToday = totalToday;
        this.death = death;
        this.deathToday = deathToday;
        this.recovered = recovered;
        this.recoveredToday = recoveredToday;
        this.active = active;
    }

    public String getState() {
        return state;
    }

    public String getDistrict() {
        return district;
    }

    public long getTotal() {
        return total;
    }

    public long getTotalToday() {
        return totalToday;
    }

    public long getDeath() {
        return death;
    }

    public long getDeathToday() {
        return deathToday;
    }

    public long getRecovered() {
        return recovered;
    }

    public long getRecoveredToday() {
        return recoveredToday;
    }

    public long getActive() {
        return active;
    }
}
