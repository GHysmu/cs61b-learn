public class TestPlanet {


    public static void main(String[] args){
        Planet p1 = new Planet(0,0,0,0,3,null);
        Planet p2 = new Planet(2,3,1,1,40,null);

        System.out.println("this is a test, output is the force between two planet");
        System.out.println(p1.calcForceExertedBy(p2));


    }
}
