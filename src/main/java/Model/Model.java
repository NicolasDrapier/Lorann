package Model;

import java.util.ArrayList;

import static Model.Constants.IMAGE_SIZE;

/**
 * Model Class.
 * The most important class of the game.
 * Use Constant Class to get ImageSize.
 *
 * @author Nicolas Drapier
 * @version 1.0
 * @see Constants
 */
public class Model {

    private final ArrayList<Player> players;
    private ArrayList<GameObject> gameObjects;

    /**
     * Default constructor. Instantiate GameObject list and PLayer list.
     */
    public Model() {
        this.gameObjects = new ArrayList<>();
        this.players = new ArrayList<>();
    }

    /**
     * Void builMap function. Use to create Map with MapBuilder class.
     * <br><br>
     * Form map: 23$O%21-%O%|%X%20 %2|%20 %X%2|%21 %2|%13 %P%7 %2|%21 %G%|%21 %|%O%21-%O
     * <br>
     * First number is the length map. Here is 23.
     * $ character is used to separate this number with the rest of the map
     * <ul>
     *     <li>O is rounded Bone</li>
     *     <li>| is vertical Bone</li>
     *     <li>- is horizontal Bone</li>
     *     <li>X is Purse</li>
     *     <li>G is Gate</li>
     *     <li>P is Player</li>
     *     <lI>' ' is void</lI>
     * </ul>
     * <p>
     *     Position of each object is calculated with the number before symbol.
     *     For example : "21-" represents 21 vertical Bones.
     *     If there is nothing before, that corresponds to 1 object.
     *     Each section is separated with % symbol.
     * </p>
     *
     * @param map String corresponding to the map
     * @see MapBuilder
     * @see Bone
     * @see Player
     * @see Gate
     * @see Purse
     */
    public void buildMap(String map) {
        MapBuilder mapBuilder = new MapBuilder();
        this.gameObjects = mapBuilder.buildMap(map);
        Object[] objects = gameObjects.stream().filter(e -> e instanceof Player).toArray();
        for (Object o : objects) {
            this.players.add((Player) o);
        }
    }

    /**
     * Moves player method.
     * Checks is the block place in the new position is traversable, like purses.
     * Checks if it stills purses in the game. If not, opens the door.
     *
     * @param direction Direction.
     * @see Purse
     * @see GameObject
     */
    public void movePlayer(Direction direction) {
        Player player = getPlayer();
        player.setLastDirection(direction);
        int[] ints = getXandY(direction);

        int finalX = ints[0];
        int finalY = ints[1];
        Object[] objects = gameObjects.stream().filter(
                e -> (e.getPosition().getX() == (player.getPosition().getX() + finalX) &&
                        e.getPosition().getY() == (player.getPosition().getY() + finalY))).toArray();

        // No object at the new position
        // Or it's an opened door
        // Move on
        if (objects.length == 0 || (objects[0] instanceof Gate gate && gate.isOpen())) {
            player.move(finalX, finalY);
        }

        // Else if it's an Item
        // Move and collect
        // Add score to player score
        // Remove item from the game object list
        else if (objects[0] instanceof Item item) {
            player.move(finalX, finalY);
            player.takeItem(item.getWeight());
            gameObjects.remove(item);

            // If there is no purses
            // Get the door, open it.
            if (checkIfStillPurses()) {
                Object[] objects1 = gameObjects.stream().filter(e -> e instanceof Gate).toArray();
                ((Gate) objects1[0]).setOpen(true);
            }
        }
    }

    /**
     * Launch spell.
     * Chek if player has already launched his spell.
     *
     * @see Spell
     * @see Player
     */
    public void launchSpell() {
        Player player = getPlayer();
        if (!player.isHasLaunchSpell()) {

            //Set property to true because player launched spell
            player.setHasLaunchSpell(true);

            //Instantiate new Spell with las player direction
            Direction lastDirection = player.getLastDirection();
            Spell spell = getPlayer().instantiateSpell(lastDirection);

            //Add to GameObjects list
            gameObjects.add(spell);
            spell.animate();

            int[] ints = getXandY(lastDirection);
            int x = ints[0], y = ints[1];

            // Thread to move spell
            // Maybe not the right place for this code
            Thread thread = new Thread() {
                @Override
                public void run() {
                    super.run();
                    for (int i = 0; i < 5; i++) {
                        spell.move(x, y);
                        try {
                            sleep(75);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            thread.start();
//            gameObjects.remove(spell);
//            player.setHasLaunchSpell(false);

        }
    }


    /**
     * Get X and Y coordinate with an array form
     *
     * @param direction Direction to calculate new x an y
     * @return Return an int array with x and y.
     */
    private int[] getXandY(Direction direction) {
        int x = 0;
        int y = 0;

        switch (direction) {
            case UP -> y = -IMAGE_SIZE;
            case DOWN -> y = IMAGE_SIZE;
            case LEFT -> x = -IMAGE_SIZE;
            case RIGHT -> x = IMAGE_SIZE;
        }

        return new int[]{x, y};
    }

    /**
     * Check if there are purses in GameObject list.
     *
     * @return True if it still purses.
     * @see Purse
     */
    private boolean checkIfStillPurses() {
        Object[] objects = gameObjects.stream().filter(e -> e instanceof Purse).toArray();
        return objects.length == 0;
    }

    /**
     * Get all GameObjects.
     * Used by the View to render Objects
     *
     * @return ArrayList of GameObject
     * @see GameObject
     * @see View.GamePanel
     */
    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * Get Player in the list of GameObject.
     * Used to move player.
     * Maybe not the right way.
     *
     * @return Player instance.
     * @see Player
     */
    public Player getPlayer() {
        Object[] objects = gameObjects.stream().filter(e -> e instanceof Player).toArray();
        return (Player) objects[0];
    }

}
