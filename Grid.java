import java.awt.Color;
import java.awt.Graphics;
import java.util.Vector;

public class Grid {

    private int width, height;
    
    private int x, y;
    
    private Cell[][] cells;
    private boolean done;
    
    Vector<Walker> walkers = new Vector<Walker>();
    
    public Grid(int x, int y, int width, int height) {
	this.width = width;
	this.height = height;
	
	this.x = x;
	this.y = y;
	
	this.done = false;
	
	cells = new Cell[width][height];
	
	for(int i = 0; i < width; i++) {
	    for(int j = 0; j < height; j++) {
		cells[i][j] = new Cell(x + Cell.SQUARE_SIZE * i,
				       y + Cell.SQUARE_SIZE * j);
	    }
	}
    }
    
    public Cell[][] getCells() { return this.cells; }
    
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    
    public boolean isFull() { return this.done; }
    
    public void addPlayer(int x, int y, int id) {
	Walker w = new Walker(this, x, y, id);
	walkers.add(w);
    }
    
    public void addMain(Player p) {
	//Player p = new Player(this, x, y, id, probs);
	walkers.add(p);
    }
    
    public void removeMain() {
	for(int i = 0; i < walkers.size(); i++) {
	    Walker w = walkers.elementAt(i);
	    if(w instanceof Player) {
		walkers.remove(w);
	    }
	}
    }
    
    public void update() {
	for(Walker w: walkers) {
	    w.move();
	}
	
	done = true;
	
	for(Cell[] cellCol: cells) {
	    for(Cell c: cellCol) {
		if(c.getColor() == Color.WHITE) {
		    done = false;
		}
	    }
	}
	
	
    }
    
    public void drawCells(Graphics g) {
	for(int x = 0; x < cells.length; x++ ) {
	    for(int y = 0; y < cells[0].length; y++) {
		Cell c = cells[x][y];
		c.draw(g);
	    }
	}
    }
    
    public void draw(Graphics g) {
	for(Walker w: walkers) {
	    w.draw(g);
	}
    }
}
