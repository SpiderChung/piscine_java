package edu.school21.tanks.models;

public class DataStat {
    private int firstShots;
    private int firstHits;
    private int firstMiss;
    private int secondShots;
    private int secondHits;
    private int secondMiss;

    public DataStat(int shots, int hits, int miss, int secondShots, int secondHits, int secondMiss){
        this.firstShots = shots;
        this.firstHits = hits;
        this.firstMiss = miss;
        this.secondShots = secondShots;
        this.secondHits = secondHits;
        this.secondMiss = secondMiss;
    }


    public int getFirstShots() {
        return firstShots;
    }

    public void setFirstShots(int firstShots) {
        this.firstShots = firstShots;
    }

    public int getFirstHits() {
        return firstHits;
    }

    public void setFirstHits(int firstHits) {
        this.firstHits = firstHits;
    }

    public int getFirstMiss() {
        return firstMiss;
    }

    public void setFirstMiss(int firstMiss) {
        this.firstMiss = firstMiss;
    }

    public int getSecondShots() {
        return secondShots;
    }

    public void setSecondShots(int secondShots) {
        this.secondShots = secondShots;
    }

    public int getSecondHits() {
        return secondHits;
    }

    public void setSecondHits(int secondHits) {
        this.secondHits = secondHits;
    }

    public int getSecondMiss() {
        return secondMiss;
    }

    public void setSecondMiss(int secondMiss) {
        this.secondMiss = secondMiss;
    }
}
