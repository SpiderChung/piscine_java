package edu.school21.tanks.services;

import edu.school21.tanks.models.DataStat;
import edu.school21.tanks.objects.TanksLogic;
import edu.school21.tanks.repositories.Statrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatServiceImpl implements StatService{
    @Autowired
    private Statrepository statrepository;
    @Override
    public void saveStat(TanksLogic first, TanksLogic second) {
        DataStat stat = new DataStat((int)first.getShots(), (int)first.getHits(), (int)(first.getShots() - first.getHits()),
                (int)second.getShots(), (int)second.getHits(), (int)(second.getShots() - second.getHits()));
        statrepository.save(stat);
    }

    @Override
    public String send2(TanksLogic enemy) {
        return "ENEMY: Shots: " + enemy.getShots() + " Hits:  "+ enemy.getHits() + " Mises: " + (enemy.getShots() - enemy.getHits());
    }

    @Override
    public String send1(TanksLogic player) {
        return "YOU: Shots: " + player.getShots() + " Hits: " + player.getHits() + " Mises: " + (player.getShots() - player.getHits());
    }
}
