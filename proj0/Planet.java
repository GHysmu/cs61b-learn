import java.util.Objects;

public class Planet {
    /**
     * this class is to create a planet with basic feature
     */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet P){
        xxPos = P.xxPos;
        yyPos = P.yyPos;
        xxVel = P.xxVel;
        yyVel = P.yyVel;
        mass = P.mass;
        imgFileName = P.imgFileName;
    }
    //@Override
    public boolean equals(Planet P) {
        if (this == P) return true;
        if (P == null || getClass() != P.getClass()) return false;
        //Planet planet = (Planet) P;
        return Double.compare(P.xxPos, xxPos) == 0 && Double.compare(P.yyPos, yyPos) == 0 && Double.compare(P.xxVel, xxVel) == 0 && Double.compare(P.yyVel, yyVel) == 0 && Double.compare(P.mass, mass) == 0 && imgFileName.equals(P.imgFileName);
    }

    public double calcDistance(Planet P){
        double distance;
        distance = Math.sqrt((xxPos-P.xxPos)*(xxPos-P.xxPos)+(yyPos-P.yyPos)*(yyPos-P.yyPos));
        return distance;
    }

    public double calcForceExertedBy(Planet P){
        double Force;
        double G = 6.67e-11;
        Force = G*mass*P.mass/(calcDistance(P)*calcDistance(P));
        return Force;
    }
    public double calcForceExertedByX(Planet P){
        double ForceX;
        ForceX = calcForceExertedBy(P)*(P.xxPos-xxPos)/calcDistance(P);
        return ForceX;
    }
    public double calcForceExertedByY(Planet P){
        double ForceY;
        ForceY = calcForceExertedBy(P)*(P.yyPos-yyPos)/calcDistance(P);
        return ForceY;
    }
    public double calcNetForceExertedByX(Planet[] Planets){
        int num = Planets.length;
        int NetForceX = 0;
        for(int i =0;i<num;i++){
            if(this.equals(Planets[i])){continue;}
            NetForceX += calcForceExertedByX(Planets[i]);
        }
        return NetForceX;

    }

    public double calcNetForceExertedByY(Planet[] Planets){
        int num = Planets.length;
        int NetForceY = 0;
        for(int i =0;i<num;i++){
            if(this.equals(Planets[i])){continue;}
            NetForceY += calcForceExertedByY(Planets[i]);
        }
        return NetForceY;

    }
    public void update(double dt,double fx,double fy){
        //small period of time - simulate
        //xxPos = xxVel*dt + 0.5*(fx/mass)*dt*dt;
        //yyPos = yyVel*dt + 0.5*(fy/mass)*dt*dt;

        xxVel = xxVel + (fx/mass)*dt;
        yyVel = yyVel + (fy/mass)*dt;

        xxPos = xxVel*dt + xxPos;
        yyPos = yyVel*dt + yyPos;
    }
    public void draw()
    {
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);

    }


}
