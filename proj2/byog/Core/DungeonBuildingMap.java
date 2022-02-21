package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

public class DungeonBuildingMap {

    private class DPosition {
        private int pX;
        private int pY;

        private DPosition(int x, int y) {
            this.pX = x;
            this.pY = y;
        }
        private DPosition() {
            this.pX = 0;
            this.pY = 0;
        }

    }

    private int WIDTH;
    private int HEIGHT;
    private Random random;
    public TETile[][] world;

    public DungeonBuildingMap(int w, int h, int seed) {

        this.WIDTH = w;
        this.HEIGHT = h;
        this.random = new Random(seed);
        world = new TETile[WIDTH][HEIGHT];
        createMap();


    }


    private void createMap() {
        //TERenderer ter = new TERenderer();
        //ter.initialize(WIDTH, HEIGHT);
        //initial
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }


        //

        while (true) {
            DPosition start = new DPosition(random.nextInt(WIDTH), random.nextInt(HEIGHT));
            int w = random.nextInt(WIDTH/6) + 1;
            int h = random.nextInt(HEIGHT/6) + 1;

            if (addRoom(start, w, h, random.nextInt(4) + 1)) {
                break;
            }
        }



        for(int i = 0; i < 1000; i++) {

            //System.out.println("Iteration : " + i);
            //choose start wall
            DPosition p = getStartWall();

            double type = random.nextDouble();
            int direction = random.nextInt(4) + 1;
            //choose feature
            //build room/ hallway - continue if no available space
            if (type >= 0.35) {

                int w = random.nextInt(WIDTH/6) + 1;
                int h = random.nextInt(HEIGHT/6) + 1;

                addRoom(p, w, h, direction);

            }
            else {
                int l = random.nextInt(HEIGHT) + 1;
                addHallWay(p, l, direction);
            }

            /*
            try { Thread.sleep ( 10 ) ;
            } catch (InterruptedException ie){}
            ter.renderFrame(world);
            */
        }
        addWall();
        //ter.renderFrame(world);


    }


    private DPosition getStartWall() {

        while (true) {

            DPosition p = new DPosition(random.nextInt(WIDTH - 1),random.nextInt(HEIGHT - 1));
            if (checkStartWall(p)) {
                return p;
            }
        }


    }


    private boolean checkStartWall(DPosition p) {
        int pX = p.pX;
        int pY = p.pY;

        if(checkFeature(pX, pY, Tileset.NOTHING)) {
            if (      checkFeature(pX, pY + 1, Tileset.FLOOR)
                    ||checkFeature(pX + 1, pY, Tileset.FLOOR)
                    ||checkFeature(pX, pY - 1, Tileset.FLOOR)
                    ||checkFeature(pX - 1, pY , Tileset.FLOOR)) {
                return true;

            }
        }

        return false;
    }
    private boolean checkFeature(int pX, int pY, TETile feature) {


        if(pX < 0 || pX >= WIDTH || pY < 0 || pY >= HEIGHT) return false;
        if(world[pX][pY] == feature) return true;
        return false;
    }


    private boolean addRoom(DPosition start, int w, int h, int direction) {
        int pX = start.pX;
        int pY = start.pY;
        switch (direction) {
            
            case 1: {
                //check enough space
                for (int i = pX - w/2 -1; i <= pX + (w - w/2); i++) {
                    if (i < 0 || i >= WIDTH) return false;
                    for (int j = pY; j <= pY + h + 1; j++) {
                        if (j < 0 || j >= HEIGHT) return false;
                        if (world[i][j] != Tileset.NOTHING) return false;
                    }

                }

                for (int i = pX - w/2; i <= pX + (w - w/2) - 1; i++) {
                    for (int j = pY + 1; j <= pY + h; j++) {
                        drawCeil(i, j, Tileset.FLOOR);
                    }
                }
                //drawCeil(pX, pY, Tileset.UNLOCKED_DOOR);
                drawCeil(pX, pY, Tileset.FLOOR);
                break;
            }
            case 2: {
                //check enough space
                for (int i = pX; i <= pX + w + 1; i++) {
                    if (i < 0 || i >= WIDTH) return false;
                    for (int j = pY - h/2 - 1; j <= pY + (h - h/2); j++) {
                        if (j < 0 || j >= HEIGHT) return false;
                        if (world[i][j] != Tileset.NOTHING) return false;
                    }

                }

                for (int i = pX + 1; i <= pX + w; i++) {
                    for (int j = pY - h/2; j <= pY + (h - h/2) - 1; j++) {
                        drawCeil(i, j, Tileset.FLOOR);
                    }
                }
                //drawCeil(pX, pY, Tileset.UNLOCKED_DOOR);
                drawCeil(pX, pY, Tileset.FLOOR);
                break;
            }
            case 3: {
                //check enough space
                for (int i = pX - w/2 -1; i <= pX + (w - w/2); i++) {
                    if (i < 0 || i >= WIDTH) return false;
                    for (int j = pY - h - 1; j <= pY; j++) {
                        if (j < 0 || j >= HEIGHT) return false;
                        if (world[i][j] != Tileset.NOTHING) return false;
                    }

                }

                for (int i = pX - w/2; i <= pX + (w - w/2) - 1; i++) {
                    for (int j = pY - h; j <= pY - 1; j++) {
                        drawCeil(i, j, Tileset.FLOOR);
                    }
                }
                //drawCeil(pX, pY, Tileset.UNLOCKED_DOOR);
                drawCeil(pX, pY, Tileset.FLOOR);
                break;
            }
            case 4: {
                //check enough space
                for (int i = pX - w - 1; i <= pX; i++) {
                    if (i < 0 || i >= WIDTH) return false;
                    for (int j = pY - h/2 - 1; j <= pY + (h - h/2); j++) {
                        if (j < 0 || j >= HEIGHT) return false;
                        if (world[i][j] != Tileset.NOTHING) return false;
                    }

                }

                for (int i = pX - w; i <= pX - 1; i++) {
                    for (int j = pY - h/2; j <= pY + (h - h/2) - 1; j++) {
                        drawCeil(i, j, Tileset.FLOOR);
                    }
                }
                //drawCeil(pX, pY, Tileset.UNLOCKED_DOOR);
                drawCeil(pX, pY, Tileset.FLOOR);
                break;
            }


            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }
        return true;


    }



    private boolean addHallWay(DPosition start, int l, int direction) {

        if (direction == 1 || direction == 3) {
            return addRoom(start, 1, l, direction);
        }
        if (direction == 2 || direction == 4) {
            return addRoom(start, l, 1, direction);
        }
        return false;
    }

    private void addWall() {

        for(int i = 0; i < WIDTH; i++) {
            for(int j = 0; j < HEIGHT; j++){
                if(world[i][j] == Tileset.NOTHING) {
                    if(checkAddWall(new DPosition(i,j))) {
                        drawCeil(i, j,Tileset.WALL);
                    }

                }
            }
        }

        while (true) {
            int w = random.nextInt(WIDTH);
            int h = random.nextInt(HEIGHT);
            if (checkFeature(w, h, Tileset.WALL)) {
                drawCeil(w, h, Tileset.LOCKED_DOOR);
                break;
            }
        }

    }

    private boolean checkAddWall(DPosition p) {
        int pX = p.pX;
        int pY = p.pY;

        if(checkFeature(pX, pY, Tileset.NOTHING)) {
            if (      checkFeature(pX, pY + 1, Tileset.FLOOR)
                    ||checkFeature(pX + 1, pY, Tileset.FLOOR)
                    ||checkFeature(pX, pY - 1, Tileset.FLOOR)
                    ||checkFeature(pX - 1, pY , Tileset.FLOOR)
                    ||checkFeature(pX - 1, pY - 1 , Tileset.FLOOR)
                    ||checkFeature(pX - 1, pY + 1 , Tileset.FLOOR)
                    ||checkFeature(pX + 1, pY + 1 , Tileset.FLOOR)
                    ||checkFeature(pX + 1, pY - 1 , Tileset.FLOOR)
            ) {
                return true;
            }
            if (      checkFeature(pX, pY + 1, Tileset.UNLOCKED_DOOR)
                    ||checkFeature(pX + 1, pY, Tileset.UNLOCKED_DOOR)
                    ||checkFeature(pX, pY - 1, Tileset.UNLOCKED_DOOR)
                    ||checkFeature(pX - 1, pY , Tileset.UNLOCKED_DOOR)
                    ||checkFeature(pX - 1, pY - 1 , Tileset.UNLOCKED_DOOR)
                    ||checkFeature(pX - 1, pY + 1 , Tileset.UNLOCKED_DOOR)
                    ||checkFeature(pX + 1, pY + 1 , Tileset.UNLOCKED_DOOR)
                    ||checkFeature(pX + 1, pY - 1 , Tileset.UNLOCKED_DOOR)
            ) {
                return true;
            }
        }

        return false;
    }

    private void drawCeil(int x, int y, TETile type) {
        world[x][y] = type;
    }
}
