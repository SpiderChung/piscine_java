package rush01.objects;

import java.util.List;

public class Player {
    public Tank tank;
    public List<bulletLogic> bullets;

    @Override
    public String toString() {
        return "Player{" +
                "tank=" + tank +
                ", bullets=" + bullets +
                '}';
    }
}

