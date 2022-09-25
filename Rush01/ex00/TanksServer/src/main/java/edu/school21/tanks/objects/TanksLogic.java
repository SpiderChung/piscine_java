package edu.school21.tanks.objects;


public class TanksLogic {
    private int id;
    private int health;
    private float x;
    private float y;
    private long shots;
    private short hits;

    public TanksLogic(int id, float x, float y){
        this.id = id;
        this.health = 100;
        this.y = y;
        this. x = x;
        this.hits = 0;
        this.shots = 0;
    }

    public void moveLeft(){
        if (this.id == 1 && x > 50f)
            this.x += -4f;
        else if (x > 50f)
            this.x += -4f;
    }

    public void moveRight(){
        if (this.id == 1 && x < 900f)
            this.x += 4f;
        else if (x < 900f)
            this.x += 4f;
    }

    public void shot(){
        this.shots++;
    }

    public void hitEnemy(){
        this.hits++;
    }

    public boolean hitPlayer(){
        this.health -= 5;
        if (this.health <= 0)
            return true;
        return false;
    }

    public int getHealth() {
        return health;
    }

    public float getX() {
        return x;
    }

    public long getShots() {
        return shots;
    }

    public int getHits() {
        return hits;
    }

    public int getId() {
        return id;
    }

    public float getY() {
        return y;
    }
}
