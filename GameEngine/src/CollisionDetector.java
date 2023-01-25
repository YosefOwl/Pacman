import com.google.inject.*;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.DataTruncation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CollisionDetector {
    private Collection<ICollisional> collisionalObjects;
    private Maze maze;

    @Inject
    public CollisionDetector(Maze maze){
        this.maze = maze;
    }

    public void ExecuteOnCollisionEnters(Collection<Pair<ICollisional,ICollisional>> inCollisionObjects){
        inCollisionObjects.forEach(pair ->{
            pair.getLeft().OnCollisionEnter(pair.getRight());
            pair.getRight().OnCollisionEnter(pair.getLeft());
        });
    }

    public List<Pair<ICollisional,ICollisional>> DetectCollisions() {
        var mazeData = maze.getMap();

        var inCollision =
                (Arrays.stream(mazeData))
                        .map(col -> Arrays.stream(col)
                                .filter(data -> data.getCharacters().size() > 0)
                                .map(data -> Pair.of(
                                        (ICollisional) data.characters.get(0),
                                        (ICollisional) data.characters.get(1)))
                                .collect(Collectors.toList())
                        )
                        .flatMap(p -> p.stream());

        return inCollision.toList();
    }

    @Inject
    public void initialize() {
        // Perform additional configuration or initialization
        Injector injector = Guice.createInjector();
        List<ICollisional> implementations = new ArrayList<>();

        for (Binding<ICollisional> binding :
                injector.findBindingsByType(TypeLiteral.get(ICollisional.class))) {
            implementations.add(injector.getInstance(binding.getKey()));
        }

        collisionalObjects = implementations;

    }
}
