package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;



/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final long SEED = 2873547;
    private static final Random RANDOM = new Random(SEED);

    /**
     * random choose an item
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(4);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.MOUNTAIN;
            default: return Tileset.NOTHING;
        }
    }


    /**
     * draw one hexagon given specific item and size
     * @param sideLen
     * @param item
     * @param world
     * @param pX
     * @param pY
     */
    public static void addHexagon(int sideLen,TETile item, TETile[][] world, int pX, int pY) {
        if(item == null) {
            item = randomTile();
        }

        for(int i = 0; i < sideLen; i++) {
            addHexagonHelp(sideLen + 2 * i, item, world, pX - i, pY + i);
        }

        for(int i = sideLen - 1; i >=0; i--) {
            addHexagonHelp(sideLen + 2 * i, item, world, pX - i, pY + 2 * sideLen - i - 1);
        }
    }

    /**
     * draw one row with specific size and position x ,y
     */
    private static void addHexagonHelp(int size, TETile item, TETile[][] world, int x, int y) {

        for(int i = 0; i < size; i++) {

            world[x+i][y] = item;

        }

    }

    /**
     * draw one line of Hexagon given initial position x,y and num
     * @param num
     * @param size
     * @param item
     * @param world
     * @param x
     * @param y
     */
    private static void addMultiHexagon(int num, int size, TETile item, TETile[][] world, int x, int y) {

        for(int i = 0; i < num; i++) {
            int[] p = getRPosition(size, i, x, y);
            addHexagon(size, item, world, p[0], p[1]);
        }

    }

    /**
     * get the position of start item given the offset number
     * only used for each line
     * @param size
     * @param num
     * @param pX
     * @param pY
     * @return
     */
    private static int[] getRPosition(int size, int num, int pX, int pY) {
        int[] finalP = new int[2];

        finalP[0] = pX + num * ((size - 1) * 2 + 1);
        finalP[1] = pY - num * size;
        return finalP;
    }


    private static int[] getLPosition(int size, int side, int num, int pX, int pY) {
        int[] finalP = new int[2];

        if( num <= side - 1) {
            finalP[0] = pX - num * ((size - 1) * 2 + 1);
            finalP[1] = pY - num * size;

        }

        else {
            finalP[0] = pX - (side - 1) * ((size - 1) * 2 + 1);
            finalP[1] = pY -  (2 * num - side + 1) * size;
        }

        return finalP;
    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        // fills in a block 14 tiles wide by 4 tiles tall
        //addHexagon(5,Tileset.FLOWER,world,10,20);
        int num = 3;
        for(int i = 0; i < 2 * num - 1; i++) {
            int[] p = getLPosition(3,num,i,20,30);
            if(i <= num - 1) {
                addMultiHexagon(num + i,3,null, world, p[0], p[1]);
            }
            else {
                addMultiHexagon(3 * num - i - 2,3,null, world, p[0], p[1]);
            }



        }
        //addMultiHexagon(3,4,null,world, 50,70);
        //draw hexagons equal

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
