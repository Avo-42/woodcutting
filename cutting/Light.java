package cutting;

import java.awt.Color;

public class Light {
	private int r = 100;
	private int g = 100;
	private int b = 100;
	private Point light;
	
	//this is a test of git pull
	public Light(Point point) {
		light = point;
	}
	public Light(Point point, int r, int g, int b) {
		light = point;
		this.r=r;
		this.g=g;
		this.b=b;
	}

	public Point getPoint() {
		return light;
	}
	
	public void setPoint(Point point) {
		light = point;
	}
	
	public void setColor(int red, int green, int blue) {
		r = red;
		g = green;
		b = blue;
	}
	
	public Color getColor() {
		Color result = new Color(r,g,b);
		return result;
	}
	
	
}
