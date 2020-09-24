package Model;

import java.util.ArrayList;

import static Model.Constants.IMAGE_SIZE;

/**
 * MapBuilder based on the Factory Design Pattern
 *
 * @author Nicolas Drapier
 * @version 1.0
 */
public class MapBuilder {

    /**
     * Default constructor.
     */
    public MapBuilder() {
    }

    /**
     * BuildMap function.
     *
     * @param map String map
     * @return ArrayList of GameObject
     * @see GameObject
     */
    public ArrayList<GameObject> buildMap(String map) {

        //Split map to get the number of columns
        String[] strings = map.split("\\$");
        int mapSize = Integer.parseInt(strings[0]);

        //Split each section of small section corresponding to objects
        String[] objects = strings[1].split("%");

        ArrayList<GameObject> gameObjectArrayList = new ArrayList<>();

        int x = 0, y = 0;
        for (String o : objects) {
            // Get string length used to get
            // the number of objects to instantiate
            int length = o.length();
            int n;
            char c;
            // if greater or equal than 2
            // it's because we have this form :
            // "21-" or "9O" for example
            if (length >= 2) {
                //store the number for the for loop beside
                n = Integer.parseInt(o.substring(0, length - 1));
                c = o.charAt(length - 1);
            } else {
                n = 1;
                c = o.charAt(0);
            }

            // instantiate all object
            for (int z = 0; z < n; z++) {
                GameObject gameObject = null;
                try {
                    gameObject = getObject(c, x, y);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //check if there is an object
                // add to list
                if (gameObject != null) {
                    gameObjectArrayList.add(gameObject);
                    if (gameObject instanceof Player player) {
                        player.animate();
                    }

                }
                // new coordinate
                x += IMAGE_SIZE;

                // if x%0, change line and put x to 0
                if (x % mapSize == 0) {
                    x = 0;
                    y += IMAGE_SIZE;
                }
            }
        }
        return gameObjectArrayList;
    }

    /**
     * Get the right object with the character.
     * Instantiate new object with Position.
     *
     * @param c Character c :
     *          <ul>
     *              <li>O is rounded Bone</li>
     *              <li>| is vertical Bone</li>
     *              <li>- is horizontal Bone</li>
     *              <li>X is Purse</li>
     *              <li>G is Gate</li>
     *              <li>P is Player</li>
     *              <lI>' ' is void</lI>
     *          </ul>
     *          --------------
     * @param x x coordinate
     * @param y y coordinate
     * @return GameObject type.
     * @throws IllegalStateException If the character does not exist
     * @see Position
     * @see Bone
     * @see Player
     * @see Gate
     * @see Purse
     * @see GameObject
     */
    private GameObject getObject(char c, int x, int y) {
        Position position = new Position(x, y);
        return switch (c) {
            case 'O' -> new Bone(position, (byte) 1);
            case '|' -> new Bone(position, (byte) 2);
            case '-' -> new Bone(position, (byte) 3);
            case 'P' -> new Player(position, (byte) 1);
            case 'G' -> new Gate(position);
            case 'X' -> new Purse(position);
            case ' ' -> null;
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }

}
