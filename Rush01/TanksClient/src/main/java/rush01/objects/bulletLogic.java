package rush01.objects;

import javafx.scene.image.Image;

public class bulletLogic extends AbstractObject {
    public static Image PLAYER_BULLET = new Image(PATH_IMAGE_PLAYER_BULLET);
    public static Image ENEMY_BULLET = new Image(PATH_IMAGE_ENEMY_BULLET);
    public static int imageWidth = (int) ENEMY_BULLET.getWidth();
    public static int imageHeight = (int) ENEMY_BULLET.getHeight();

    public bulletLogic(int x, int y) {
        super(x, y);
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
