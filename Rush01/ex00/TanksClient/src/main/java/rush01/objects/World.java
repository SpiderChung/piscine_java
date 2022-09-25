package rush01.objects;

import java.util.List;

public class World {
    public List<Player> players;

    @Override
    public String toString() {
        return "World{" +
                "players=" + players +
                '}';
    }
}
