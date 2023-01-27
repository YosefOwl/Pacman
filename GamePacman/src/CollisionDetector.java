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
        var mazeData = maze.getMap();

        var collisions = dynamicCharacters
                .stream()
                .flatMap(c ->
                        Stream.of(
                                DetectCollisionInCurrentPosition(c),
                                DetectCollisionFromAbove(c),
                                DetectCollisionFromBelow(c),
                                DetectCollisionFromRight(c),
                                DetectCollisionFromLeft(c)
                                ).filter(o -> o != null)
                        )
                .collect(Collectors.toList());

        return collisions;
    }

    private Pair<ICollisional,ICollisional> DetectCollisionInCurrentPosition(DynamicCharacter character) {

        Point pCharacter = getMazePointByCharacterPosition(character);

        var collisions = getCollisionalsInPosition(pCharacter)
                .stream()
                .filter(c -> !c.getCharacter().equals(character))
                .collect(Collectors.toList());

        if(collisions.isEmpty()){
            return null;
        }

        var bounds = character.getCollider().getBounds();

        List<Line2D> boundsLines = splitBoundsToLines(bounds);

        List<Line2D> otherBoundsLines = splitBoundsToLines(collisions.get(0).getCollider().getBounds());

        Pair<Line2D, Line2D> intersectionLines = findIntersectionBetweenLines(boundsLines, otherBoundsLines);
        if (intersectionLines != null){
            return Pair.of(character, collisions.get(0));
        }

        return null;
    }

    private Pair<ICollisional,ICollisional> DetectCollisionFromAbove(DynamicCharacter character) {
        return DetectCollisionFrom(character,GameConsts.UP);
    }

    private Pair<ICollisional,ICollisional> DetectCollisionFromBelow(DynamicCharacter character) {
        return DetectCollisionFrom(character,GameConsts.DOWN);
    }

    private Pair<ICollisional,ICollisional> DetectCollisionFromRight(DynamicCharacter character) {
        return DetectCollisionFrom(character,GameConsts.RIGHT);
    }

    private Pair<ICollisional,ICollisional> DetectCollisionFromLeft(DynamicCharacter character) {
        return DetectCollisionFrom(character,GameConsts.LEFT);
    }



    private Pair<ICollisional,ICollisional> DetectCollisionFrom(DynamicCharacter character,int direction) {

        Point pCharacter = getMazePointByCharacterPosition(character);

        Point pOther = getNeighborsFrom(pCharacter,direction);

        if (!isPointInBounds(pOther)) {
            return null;
        }

        var collisions = getCollisionalsInPosition(pOther);

        if(collisions.isEmpty()){
            return null;
        }

        var bounds = character.getCollider().getBounds();

        List<Line2D> boundsLines = splitBoundsToLines(bounds);

        List<Line2D> otherBoundsLines = splitBoundsToLines(collisions.get(0).getCollider().getBounds());

        Pair<Line2D, Line2D> intersectionLines = findIntersectionBetweenLines(boundsLines, otherBoundsLines);
        if (intersectionLines != null){
             return Pair.of(character, collisions.get(0));
        }

        return null;
    }

    private static Pair<Line2D, Line2D> findIntersectionBetweenLines(List<Line2D> boundsLines, List<Line2D> otherBoundsLines) {
        for(int i = 1; i < boundsLines.size() ; i++){
            for(int j = 1; j < otherBoundsLines.size() ; j++){
                if (boundsLines.get(i).intersectsLine(otherBoundsLines.get(j)))
                {
                    return Pair.of(boundsLines.get(i), otherBoundsLines.get(j));
                }
            }
        }
        return null;
    }

    private static boolean isPointInBounds(Point pOther) {
        return !(pOther.y > GameConsts.MAZE_COL ||
                pOther.x > GameConsts.MAZE_ROW ||
                pOther.y < 0 ||
                pOther.x < 0);
    }

    private Point getNeighborsFrom(Point pCharacter, int direction) {
        switch (direction){
            case GameConsts.UP:
                return new Point(pCharacter.x-1,pCharacter.y);
            case GameConsts.DOWN:
                return new Point(pCharacter.x+1,pCharacter.y);
            case GameConsts.RIGHT:
                return new Point(pCharacter.x ,pCharacter.y + 1 );
            case GameConsts.LEFT:
                return new Point(pCharacter.x,pCharacter.y - 1);
            default:
                return null;
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
        var bottomLine = new Line2D.Double(bottomLeft,bottomRight);

        var leftLine = new Line2D.Double(bottomLeft,upperLeft);

        lines.add(upperLine);
        lines.add(rightLine);
        lines.add(bottomLine);
        lines.add(leftLine);

        return lines;

    }

    private List<ICollisional> getCollisionalsInPosition(Point pCharacter) {
        return maze.getMazeDataAtPosition(pCharacter).getCollisionals()
                .stream()
                .filter(c -> c.getCharacter().isActive)
                .collect(Collectors.toList());
    }

    private Point getMazePointByCharacterPosition(Character character){
        double col = character.getPosition().x / ((GameConsts.BLOCK_WIDTH));
        double row = character.getPosition().y / ((GameConsts.BLOCK_HEIGHT));

        return new Point((int)Math.round(row),(int)Math.round(col));
    }

}
