package cutting;

import java.lang.Math;

public class Ngon extends Shape{

    public Ngon(int pointNum, double sidelength, Point firstPoint,Plane plane){
        super(pointNum, pointNum);
        makeNgon(pointNum, sidelength, firstPoint, plane);
    }

    public void makeNgon(int pointNum, double radius, Point center, Plane plane) {
        double turnAngle = getNgonAngle(pointNum);
        Point p1 = plane.getPerpendicular1();
        Point p2 = plane.getPerpendicular2();
        for(int i = 0; i < pointNum; i++){
            double c = Math.cos(turnAngle*i);
            double s = Math.sin(turnAngle*i);
            Point temp = center.getTranslation(p1.getScaled(c*radius));
            temp = temp.getTranslation(p2.getScaled(s*radius));
            this.setPoint(i, temp);
        }
        for(int i = 1; i < pointNum; i++){
            Edge edge = new Edge(this.getPoint(i-1), this.getPoint(i%pointNum));
            this.setEdge(edge, i-1);
        }
    }

//        supposed to make a polygon with pointNum points with a side length of
//    public void makeNgon(int pointNum, double sideLength, Point firstPoint, Plane plane){
//        double turnAngle = getNgonAngle(pointNum);
//        this.setPoint(0,firstPoint);
//        if(plane == Plane.XY){
//            Point one = firstPoint.getTranslation(new Point(sideLength,0,0));
//            Point two = firstPoint.getTranslation(new Point(sideLength, sideLength, 0));
//            Point three = firstPoint.getTranslation(new Point(0,sideLength,0));
//            Point[] points = new Point[4];
//            points[0]=firstPoint;
//            points[1]=one;
//            points[2]=two;
//            points[3]=three;
//            Shape temp = makeShape(points,4);
//            Point center = temp.makeCenter();
//            this.setPoint(1,Point.getTranslation(firstPoint,sideLength,0.0,0.0));
//            this.setEdge(new Edge(this.getPoint(0), this.getPoint(1)), 0);
//            for(int i = 2; i < pointNum; i++){
//                this.setPoint(i, this.getPoint(i-1).getRotatedZ(center, turnAngle));
//                this.setEdge(new Edge(this.getPoint(i-1), this.getPoint(i%pointNum)), i);
//            }
//        }else if(plane == Plane.YZ){
//            Point one = firstPoint.getTranslation(new Point(0,sideLength,0));
//            Point two = firstPoint.getTranslation(new Point(0, sideLength, sideLength));
//            Point three = firstPoint.getTranslation(new Point(0,0,sideLength));
//            Point[] points = new Point[4];
//            points[0]=firstPoint;
//            points[1]=one;
//            points[2]=two;
//            points[3]=three;
//            Shape temp = makeShape(points,4);
//            Point center = temp.makeCenter();
//            this.setPoint(1,Point.getTranslation(firstPoint,0,sideLength,0.0));
//            this.setEdge(new Edge(this.getPoint(0), this.getPoint(1)), 1);
//            for(int i = 2; i < pointNum; i++){
//                this.setPoint(i, this.getPoint(i-1).getRotatedX(center, turnAngle));
//                this.setEdge(new Edge(this.getPoint(i-1), this.getPoint(i%pointNum)), i);
//            }
//        }else{
//            Point one = firstPoint.getTranslation(new Point(sideLength,0,0));
//            Point two = firstPoint.getTranslation(new Point(sideLength, 0, sideLength));
//            Point three = firstPoint.getTranslation(new Point(0,0,sideLength));
//            Point[] points = new Point[4];
//            points[0]=firstPoint;
//            points[1]=one;
//            points[2]=two;
//            points[3]=three;
//            Shape temp = makeShape(points,4);
//            Point center = temp.makeCenter();
//            this.setPoint(1,Point.getTranslation(firstPoint,0,0,sideLength));
//            this.setEdge(new Edge(this.getPoint(0), this.getPoint(1)), 1);
//            for(int i = 2; i < pointNum; i++){
//                this.setPoint(i, this.getPoint(i-1).getRotatedY(center, turnAngle));
//                this.setEdge(new Edge(this.getPoint(i-1), this.getPoint(i%pointNum)), i);
//            }
//        }
//    }

    private double getNgonAngle(int pointNum){
        return (2*Math.PI/pointNum);
    }


}
