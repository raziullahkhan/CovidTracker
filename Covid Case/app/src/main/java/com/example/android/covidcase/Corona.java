package com.example.android.covidcase;

import java.io.Serializable;

public class Corona implements Serializable {
    private long total;
    private long death;
    private long recovered;
    private long active;
    private long totalToday;
    private long deathToday;
    private long recoveredToday;
    private String country;

    public Corona(long total, long death, long recovered, long active, long totalToday, long deathToday, long recoveredToday) {
        this.total = total;
        this.death = death;
        this.recovered = recovered;
        this.active = active;
        this.totalToday = totalToday;
        this.deathToday = deathToday;
        this.recoveredToday = recoveredToday;
    }

    public Corona(long total, long death, long recovered, long active, long totalToday, long deathToday, long recoveredToday, String country) {
        this.total = total;
        this.death = death;
        this.recovered = recovered;
        this.active = active;
        this.totalToday = totalToday;
        this.deathToday = deathToday;
        this.recoveredToday = recoveredToday;
        this.country = country;
    }

    public long getTotal() {
        return total;
    }

    public long getDeath() {
        return death;
    }

    public long getRecovered() {
        return recovered;
    }

    public long getActive() {
        return active;
    }

    public long getTotalToday() {
        return totalToday;
    }

    public long getDeathToday() {
        return deathToday;
    }

    public long getRecoveredToday() {
        return recoveredToday;
    }

    public String getCountry() {
        return country;
    }
}
