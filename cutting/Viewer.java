package cutting;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import cutting.Shape.Plane;

import javax.swing.JComponent;

public class Viewer extends JComponent implements MouseMotionListener, MouseListener {

	private Creation creation;
	private double theta = Math.PI/6;
	private double tilt = Math.PI/6;
	private double viewerDistance = 20.0;
	private Point origin = new Point(0, 0, 0);
	
	private Point makePointFromPerspective(Point p) {
		Point thetaRotated = p.getRotatedZ(origin, theta);
		return thetaRotated.getRotatedY(origin, tilt);
	}
	
	public Viewer(Creation creation) {
		//setFocusable(true);
		this.creation = creation;
		addMouseMotionListener(this);
		addMouseListener(this);
	}
	
	private int initialX = -1;
	private int initialY = -1;
	private double initialTheta;
	private double initialTilt;
		
	public void mouseDragged(MouseEvent e) {
		if (initialX == -1) {
			System.out.println("Bye");
			return;
		}
		int dx = e.getX() - initialX;
		int dy = e.getY() - initialY;
		
		// System.out.printf("dx: %d dy: %d\n", dx, dy);
		theta = initialTheta + ((double)dx) / 200;
		tilt = initialTilt + ((double)dy) / 200;
		System.out.printf("theta: %f tilt: %f\n", theta, tilt);
		repaint();
	}
	
	public void mouseMoved(MouseEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {	}

	@Override
	public void mouseEntered(MouseEvent arg0) {	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse Pressed");
		initialX = e.getX();
		initialY = e.getY();
		initialTheta = theta;
		initialTilt = tilt;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		System.out.println("Mouse Released");
		initialX = -1;
		initialY = -1;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	    g2.setRenderingHints(rh);
		Map<Shape, Double> zDistances = new HashMap<>();
		List<Shape> shapes = new ArrayList<>();
		for(int i=0; i<creation.getShapeCount(); i++) {
			Shape shape = creation.getShape(i);
			shapes.add(shape);
			Point center = shape.makeCenter();
			Point perspectiveCenter = makePointFromPerspective(center);
			zDistances.put(shape,  perspectiveCenter.getX());
		}
		shapes.sort((Shape s1, Shape s2)->zDistances.get(s2).compareTo(zDistances.get(s1)));
		
		int centerX = getSize().width/2;
		int centerY = getSize().height/2;
		for(int j=0; j<shapes.size(); j++) {
			Shape shape = shapes.get(j);
			int pointCount = shape.pointsSize();
			int[] xs = new int[pointCount];
			int[] ys = new int[pointCount];
			for(int i=0; i<pointCount; i++) {
				Point p = shape.getPoint(i);
				Point perspectiveP = makePointFromPerspective(p);
				double dist = perspectiveP.getX()+viewerDistance;
				double x = 400 * perspectiveP.getY() / dist;
				double y = -400 * perspectiveP.getZ() / dist;
//				System.out.printf("(%.2f, %.2f) ", x , y);
				xs[i] = (int)(x + centerX);
				ys[i] = (int)(y + centerY);
			}
			double ratio = ((double)j) / shapes.size();
//			g2.setColor(new Color(200, 200, 20));
			Color color = new Color(100, 100, (int)(255.0 * ratio));
			g2.setColor(creation.getColor(shape, color));
			g2.fillPolygon(xs, ys, pointCount);
		}
	}

	private static void addCloseListener(JFrame frame){
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				e.getWindow().dispose();
				System.exit(0);
			}
		});
	}
	
	private static Creation makeCreation() {
		Creation creation = new Creation();
		creation.addLight(new Light(new Point(20,10,10)));
		creation.addLight(new Light(new Point(-20,10,10), 120, 200, 100));
		Parallelogram p = new Parallelogram((new Edge (new Point(-1,1,0), new Point(-1,-1,0))), new Point(2,0,0));
//		creation.addShape(p);
		creation.makeRotatedCreation(p, 1, (new Point(0,0,2)), Plane.XY);
//		creation.makeRotatedCube(new Point(0,0,0), 4, 45, Plane.XY);
//		creation.makeParallelepiped(new Edge(new Point (-1,-1,0), new Point (-3,1,0)), new Point (2,0,0), new Point(0,0,3));
//		Square square = new Square(new Point(0, 0, 0), 2, Shape.Plane.XY);
//		System.out.println(square);
//		creation.addShape(square);
//		creation.makeRotatedCreation(square, 1, (new Point(0,0,2)), Plane.XY);
//		creation.makeCube(new Point(0, 0, -2), 4, Shape.Plane.XY);
//		creation.addShape(new Square(new Point(0,0,-2), 8, Shape.Plane.XY));
//		creation.makeCube(new Point(0, 0, -4), 3, Shape.Plane.XY);
		return creation;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Creation Viewer");
		addCloseListener(frame);
		Creation creation = makeCreation();
		Viewer viewer = new Viewer(creation);
		viewer.setPreferredSize(new Dimension(400, 400));
		frame.getContentPane().add(viewer);
		frame.pack();
		frame.setVisible(true);
	}
}
