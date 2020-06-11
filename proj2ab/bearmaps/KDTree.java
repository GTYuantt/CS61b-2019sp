package bearmaps;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class KDTree implements PointSet{
    private Node root;
    private static final boolean START_DIMENSION = true;

    private class Node {
        private Point point;
        private Node left;
        private Node right;
        private boolean dimension; // true means x-dimension and false means y-dimension.

        public Node(Point point, boolean dimension){
            this.point = point;
            this.dimension = dimension;
            left = null;
            right = null;
        }
    }
    // You can assume points has at least size 1.
    public KDTree(List<Point> points){
        Collections.shuffle(points);
        for (Point p:points){
            root = insert(root, p, START_DIMENSION);
        }
    }

    private Node insert(Node n, Point p, boolean dimension){
        if(n == null){
            return new Node(p, dimension);
        }
        if(p.equals(n.point)){
            return n;
        }
        if(n.dimension == true){
            double cmp = Double.compare(n.point.getX(),p.getX());
            if(cmp > 0){
                n.left = insert(n.left, p, !dimension);
            }
            else if(cmp <= 0){
                n.right = insert(n.right, p, !dimension);
            }
        } else {
            double cmp = Double.compare(n.point.getY(),p.getY());
            if(cmp > 0){
                n.left = insert(n.left, p, !dimension);
            }else if(cmp <= 0){
                n.right = insert(n.right, p, !dimension);
            }
        }
        return n;

    }



    // Returns the closest point to the inputted coordinates.
    // This should take O(logN) time on average, where N is the number of points.
    public Point nearest(double x, double y){
        Point goal = new Point(x,y);
        Point curNearest = root.point;
        curNearest = nearest(root, goal, curNearest);
        return curNearest;
    }

    private Point nearest(Node n, Point goal, Point curNearest){
        if(n == null){
            return curNearest;
        }
        if(Point.distance(n.point,goal)<Point.distance(curNearest,goal)){
            curNearest = n.point;
        }

        boolean dimensionOfNode = n.dimension;
        Node goodSide;
        Node badSide;
        if(dimensionOfNode == true){
            double cmp = Double.compare(n.point.getX(),goal.getX());
            if(cmp >= 0){
                goodSide = n.left;
                badSide = n.right;
            }
            else {
                goodSide = n.right;
                badSide = n.left;
            }
        }
        else {
            double cmp = Double.compare(n.point.getY(),goal.getY());
            if(cmp >= 0){
                goodSide = n.left;
                badSide = n.right;
            }
            else {
                goodSide = n.right;
                badSide = n.left;
            }
        }

        curNearest = nearest(goodSide, goal, curNearest);

        if (isWorthyBadSide(goal,curNearest,n)){
            curNearest = nearest(badSide, goal, curNearest);
        }

        return curNearest;
    }

    private boolean isWorthyBadSide(Point goal, Point curNearest, Node n){
        boolean dimensionOfNode = n.dimension;
        if (dimensionOfNode == true){
            if (Math.pow(n.point.getX() - goal.getX(),2) < Point.distance(curNearest,goal)){
                return true;
            }
            else return false;
        } else {
            if (Math.pow(n.point.getY() - goal.getY(),2) < Point.distance(curNearest,goal)){
                return true;
            }
            else return false;
        }
    }
}
