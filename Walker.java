import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Walker {

    protected Grid grid;
    protected int x, y;
    private int id;
    
    protected Cell[][] cells;
    protected Cell cell;
    
    protected Color color;
    
    private Random r = new Random();
    
    public Walker(Grid grid, int x, int y, int id) {
	this.grid = grid;
	
	this.x = x;
	this.y = y;
	this.id = id;
	
	defColor();
	
	this.cells = grid.getCells();
	this.cell = cells[x][y];
	
	cell.setWalker(this);
	cell.setColor(color);
    }
    
    public void defColor() {
	if(id == 0) {
	    setColor(Color.RED);
	} else if (id == 1) {
	    setColor(Color.BLUE);
	} else if(id == 2) {
	    setColor(Color.GREEN);
	} else {
	    setColor(Color.YELLOW);
	}
    }
    
    public void setX(int x) { this.x = x; }
    public int getX() { return this.x; }
    
    public void setY(int y) { this.y = y; }
    public int getY() { return this.y; }
    
    public int getID() { return this.id; }
    
    public void setColor(Color c) { this.color = c; }
    public Color getColor() { return this.color; }
    
    public void move() {

	int dir = r.nextInt(4);
	
	if(dir == 0 && x + 1 < cells.length) {
	    // Move right
	    x++;
	} else if(dir == 1 && x - 1 >= 0) {
	    // Move left
	    x--;
	} else if(dir == 2 && y + 1 < cells[0].length) {
	    // Move down
	    y++;
	} else if(dir == 3 && y - 1 >= 0) {
	    // Move up
	    y--;
	} else {
	    move();
	}
	
	cell = cells[x][y];
	cell.setColor(color);
	cell.setWalker(this);
    }
    
    public void draw(Graphics g) {
	g.setColor(color);
	g.fillOval(cell.getX(), cell.getY(), 
		   Cell.SQUARE_SIZE, Cell.SQUARE_SIZE);
	cell.setColor(color);
	
	cell.draw(g);
	
	g.setColor(Color.BLACK);
	g.drawOval(cell.getX(), cell.getY(), 
		   Cell.SQUARE_SIZE, Cell.SQUARE_SIZE);
    }
}
