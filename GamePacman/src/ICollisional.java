import java.awt.*;
public interface ICollisional {
    void onCollisionEnter(ICollisional other);
    Character getCharacter();
    Point getPosition();
    Shape getCollider();

}
