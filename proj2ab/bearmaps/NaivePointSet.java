package bearmaps;

import java.util.ArrayList;
import java.util.List;

public class NaivePointSet implements PointSet{

    private List<Point> points;
    // You can assume points has at least size 1.
    public NaivePointSet(List<Point> points){
        this.points = points;
    }

    // Returns the closest point to the inputted coordinates. This should take θ(N) time where N is the number of points.
    @Override
    public Point nearest(double x, double y) {
        Point input = new Point(x, y );
        Point nearest = points.get(0);
        for (Point p:points){
            if(Point.distance(p, input) < Point.distance(nearest, input)){
                nearest = p;
            }
        }
        return nearest;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        List<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);

        NaivePointSet nn = new NaivePointSet(points);
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        ret.getX(); // evaluates to 3.3
        ret.getY(); // evaluates to 4.4
        System.out.println("the nearest point to the given coordinate is ("+ret.getX()+", "+ret.getY()+").");
    }
}
