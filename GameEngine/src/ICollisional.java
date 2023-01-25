import java.awt.*;
public interface ICollisional {
    public void OnCollisionEnter(ICollisional other);
    public Shape GetCollider();
    public Point GetPosition();
}
