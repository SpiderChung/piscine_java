package edu.school21.tanks.services;

import edu.school21.tanks.objects.TanksLogic;
import org.springframework.stereotype.Component;

@Component
public interface StatService {
    void saveStat(TanksLogic first, TanksLogic second);
    String send1(TanksLogic player);
    String send2(TanksLogic enemy);
}
