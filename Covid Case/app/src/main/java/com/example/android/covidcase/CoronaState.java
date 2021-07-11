package com.example.android.covidcase;

import java.io.Serializable;
import java.util.ArrayList;

public class CoronaState implements Serializable {
    private String state;
    private long total;
    private long totalToday;
    private long death;
    private long deathToday;
    private long recovered;
    private long recoveredToday;
    private long active;

    public CoronaState(String state, long total, long totalToday, long death, long deathToday, long recovered, long recoveredToday, long active) {
        this.state = state;
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
