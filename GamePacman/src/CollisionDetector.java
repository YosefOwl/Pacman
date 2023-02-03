import org.apache.commons.lang3.tuple.Pair;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollisionDetector {
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

    public List<Pair<ICollisional,ICollisional>> DetectCollisions(List<DynamicCharacter> dynamicCharacters) {

        var collisions = dynamicCharacters
                .stream()
                .flatMap(c ->
                        Stream.of(
                                DetectCollisionInCurrentPosition(c),
                                DetectCollisionFromAbove(c),
                                DetectCollisionFromBelow(c),
                                DetectCollisionFromRight(c),
                                DetectCollisionFromLeft(c)
                                )
                        )
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return collisions;
    }

    private List<Pair<ICollisional,ICollisional>> DetectCollisionInCurrentPosition(DynamicCharacter character) {

        return DetectCollisionFrom(character,GameConsts.STOP);
    }

    private List<Pair<ICollisional,ICollisional>> DetectCollisionFromAbove(DynamicCharacter character) {
        return DetectCollisionFrom(character,GameConsts.UP);
    }

    private List<Pair<ICollisional,ICollisional>> DetectCollisionFromBelow(DynamicCharacter character) {
        return DetectCollisionFrom(character,GameConsts.DOWN);
    }

    private List<Pair<ICollisional,ICollisional>> DetectCollisionFromRight(DynamicCharacter character) {
        return DetectCollisionFrom(character,GameConsts.RIGHT);
    }

    private List<Pair<ICollisional,ICollisional>> DetectCollisionFromLeft(DynamicCharacter character) {
        return DetectCollisionFrom(character,GameConsts.LEFT);
    }



    private List<Pair<ICollisional,ICollisional>> DetectCollisionFrom(DynamicCharacter character,int direction) {

        Point pCharacter = getMazePointByCharacterPosition(character);

        Point pOther = getNeighborsFrom(pCharacter,direction);

        if (!isPointInBounds(pOther)) {
            return new ArrayList<>();
        }

        var collisions = getCollisionalsInPosition(pOther)
                .stream()
                .filter(c -> !c.equals(character))
                .collect(Collectors.toList());

        if(collisions.isEmpty()){
            return new ArrayList<>();
        }

        var bounds = character.getCollider().getBounds();

        List<Line2D> characterBoundsLines = splitBoundsToLines(bounds);

        var characterCollisions = new ArrayList<Pair<ICollisional,ICollisional>>();

        collisions.forEach( c -> {
            List<Line2D> otherBoundsLines = splitBoundsToLines(c.getCollider().getBounds());

            Pair<Line2D, Line2D> intersectionLines = findIntersectionBetweenLines(characterBoundsLines, otherBoundsLines);

            if (intersectionLines != null){
                characterCollisions.add(Pair.of(character, c));
            }
        });

        return characterCollisions;
    }

    private static Pair<Line2D, Line2D> findIntersectionBetweenLines(List<Line2D> boundsLines, List<Line2D> otherBoundsLines) {
        for(int i = 0; i < boundsLines.size() ; i++){
            for(int j = 0; j < otherBoundsLines.size() ; j++){
                if (boundsLines.get(i).intersectsLine(otherBoundsLines.get(j)))
                {
                    return Pair.of(boundsLines.get(i), otherBoundsLines.get(j));
                }
            }
        }
        return null;
    }

    private static boolean isPointInBounds(Point point) {
        return !( point.y > GameConsts.MAZE_COL - 1 || point.y < 0 ||
                point.x > GameConsts.MAZE_ROW - 1 || point.x < 0 );
    }

    private Point getNeighborsFrom(Point pCharacter, int direction) {
        switch (direction){
            case GameConsts.UP:
                return new Point(pCharacter.x-1,pCharacter.y);
            case GameConsts.DOWN:
                return new Point(pCharacter.x+1,pCharacter.y);
            case GameConsts.RIGHT:
                return new Point(pCharacter.x ,pCharacter.y + 1);
            case GameConsts.LEFT:
                return new Point(pCharacter.x,pCharacter.y - 1);
            default:
                return new Point(pCharacter.x,pCharacter.y);
        }
    }

    private List<Line2D> splitBoundsToLines(Rectangle bounds) {
        List<Line2D> lines = new ArrayList<>();

        Point upperLeft = new Point((int)bounds.getMinX(),(int)bounds.getMinY());
        Point upperRight = new Point((int)bounds.getMaxX(),(int)bounds.getMinY());
        var upperLine = new Line2D.Double(upperLeft,upperRight);

        Point bottomRight = new Point((int)bounds.getMaxX(),(int)bounds.getMaxY());
        var rightLine = new Line2D.Double(upperRight,bottomRight);

        Point bottomLeft = new Point((int)bounds.getMinX(),(int)bounds.getMaxY());
        var bottomLine = new Line2D.Double(bottomRight, bottomLeft);

        var leftLine = new Line2D.Double(bottomLeft, upperLeft);

        lines.add(upperLine);
        lines.add(rightLine);
        lines.add(bottomLine);
        lines.add(leftLine);

        return lines;

    }

    private List<ICollisional> getCollisionalsInPosition(Point pCharacter) {
        return maze.getMazeDataAtPosition(pCharacter).getCollisional()
                .stream()
                .filter(c -> c.getCharacter().isActive)
                .collect(Collectors.toList());
    }

    private Point getMazePointByCharacterPosition(Character character){
        double col = character.getPosition().x / GameConsts.BLOCK_WIDTH;
        double row = character.getPosition().y / GameConsts.BLOCK_HEIGHT;

        return new Point((int)Math.round(row),(int)Math.round(col));
    }

}
