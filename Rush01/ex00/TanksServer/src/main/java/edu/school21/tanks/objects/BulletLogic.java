package edu.school21.tanks.objects;

public class BulletLogic {
    private TanksLogic send;
    private TanksLogic enemy;
    private float x;
    private float y;
    private float step;

    public BulletLogic(TanksLogic send, TanksLogic enemy){
        this.send = send;
        this.enemy = enemy;
        this.x = send.getX();
        this.y = send.getY();
        if (send.getId() == 1)
            this.step = 2f;
        else
            this.step = -2f;
    }

    public boolean fly(){
        if (Math.abs(enemy.getX() - x) < 43f && Math.abs(enemy.getY() - y) < 32f)
            return true;
        this.y += this.step;
        if (Math.abs(enemy.getX() - x) < 43f && Math.abs(enemy.getY() - y) < 32f)
            return true;
        return false;
    }

    public TanksLogic getSend() {
        return send;
    }

    public TanksLogic getEnemy() {
        return enemy;
    }

    public void setEnemy(TanksLogic enemy) {
        this.enemy = enemy;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
