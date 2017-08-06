import java.awt.Color;
import java.awt.Graphics;

public class Cell {

    private Color color;
    private Walker w;
    
    private int x, y;
    
    public static final int SQUARE_SIZE = 15;
    
    public Cell(int x, int y, Color color) {
	this.x = x;
	this.y = y;
	this.color = color;
    }
    
    public Cell(int x, int y) {
	this.x = x;
	this.y = y;
	this.color = Color.WHITE;
    }
    
    public int getX() { return this.x; }
    public int getY() { return this.y; }
    
    public void setColor(Color color) { this.color = color; }
    public Color getColor() { return this.color; }
    
    public void setWalker(Walker w) { this.w = w; }
    public Walker getWalker() { return this.w; }
    
    
    public void draw(Graphics g) {
	g.setColor(Color.BLACK);
	g.drawRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
	
	g.setColor(color);
	g.fillRect(x + 1, y + 1, SQUARE_SIZE - 1, SQUARE_SIZE - 1);
    }
}
