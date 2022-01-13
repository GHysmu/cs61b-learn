import java.util.ArrayList;
import java.util.*;
public class NBody {
    /**
     * this class is to run the actual simulation( graph)
     */
    public static double readRadius(String filename) {
        In in = new In(filename);
        int numPlanet = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int numPlanet = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[numPlanet];
        for (int i = 0; i < numPlanet; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();

            String f = in.readString();

            planets[i] = (new Planet(xP, yP, xV, yV, m, f));


        }
        return planets;


    }

    public static void main(String[] args) {
        /**
         * this function is to draw the universe in its
         * starting position
         */

        //read existing data
        String TStr = args[0];
        String dtStr = args[1];
        String filename = args[2];

        double T = Double.parseDouble(TStr);
        double dt = Double.parseDouble(dtStr);
        double t = 0;
        int i = 0;

        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        //draw background
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);


        for (t = 0; t < T; t += dt) {
            //calculate x,y force
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            //update & draw
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg", 2 * radius, 2 * radius);
            for (i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
                planets[i].draw();

            }
            StdDraw.show();
            StdDraw.pause(10);


        }


    }
}
