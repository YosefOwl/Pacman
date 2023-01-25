import java.awt.*;
public interface ICollisional {
    public void onCollisionEnter(ICollisional other);
    public Character getCharacter();
    public Shape getCollider();
    public Point getPosition();
}
