import com.google.inject.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CollisionDetector {
    //private Collection<ICollisional> collisionalObjects;
    private Maze maze;

    public CollisionDetector(Maze maze){
        this.maze = maze;
    }

    public void ExecuteOnCollisionEnters(Collection<Pair<ICollisional,ICollisional>> inCollisionObjects){
        inCollisionObjects.forEach(pair ->{
            pair.getLeft().onCollisionEnter(pair.getRight());
            pair.getRight().onCollisionEnter(pair.getLeft());
        });
    }

    public List<Pair<ICollisional,ICollisional>> DetectCollisions() {
        var mazeData = maze.getMap();

        var inCollision =
                (Arrays.stream(mazeData))
                        .map(col -> Arrays.stream(col)
                                .filter(data -> data.getCharacters().size() > 1)
                                .map(data -> Pair.of(
                                        (ICollisional) data.getCharacters().get(0),
                                        (ICollisional) data.getCharacters().get(1)))
                                .collect(Collectors.toList())
                        )
                        .flatMap(p -> p.stream());

        return inCollision.toList();
    }
}
