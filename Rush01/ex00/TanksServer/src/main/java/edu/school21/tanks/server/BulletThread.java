package edu.school21.tanks.server;

import edu.school21.tanks.objects.BulletLogic;

import java.util.LinkedList;

public class BulletThread extends Thread{
    private BulletLogic bullet;
    private LinkedList<BulletLogic> bulletList;

    public BulletThread(BulletLogic bullet, LinkedList<BulletLogic> bulletList){
        this.bullet = bullet;
        this.bulletList = bulletList;
    }

    @Override
    public void run() {
        while(bullet.getX() < 1000f && bullet.getX() > 10f && bullet.getY() > 10f && bullet.getY() < 1000f){
            if (bullet.fly()) {
                bullet.getSend().hitEnemy();
                bullet.getEnemy().hitPlayer();
                break;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
}
