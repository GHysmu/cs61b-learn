package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.text.Position;

public class TestDungeonMap {

    public static void main(String[] args) {
        int WIDTH = 80;
        int HEIGHT = 40;
        int seed = 389479;

        Main test = new Main();
        String[] argsTest = new String[] {"23433"};
        // draws the world to the screen

        //DungeonBuildingMap map = new DungeonBuildingMap(WIDTH, HEIGHT, seed);
        test.main(argsTest);


    }



}
