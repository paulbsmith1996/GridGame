import java.util.Random;

public class Player extends Walker {
    
    Random r = new Random();
    private int[] probs;
    private int performance;
    
    public Player(Grid grid, int x, int y, int id, int[] probs) {
	super(grid, x, y, id);
	this.probs = probs;
	this.performance = 0;
    }
    
    public int[] getProbs() { return this.probs; }
    public void setProbs(int[] probs) { this.probs = probs; }
    
    public void setPerformance(int p) { this.performance = p; }
    public int getPerformance() { return this.performance; }
    
    @Override
	public void move() {

	int dir = r.nextInt(1000);
	
	int inc1 = probs[0];
	int inc2 = inc1 + probs[1];
	int inc3 = inc2 + probs[2];

	if (dir <= inc1 && x + 1 < cells.length) {
	    // Move right
	    x++;
	} else if (dir > inc1 && dir <= inc2 && x - 1 >= 0) {
	    // Move left
	    x--;
	} else if (dir > inc2 && dir <= inc3 && y + 1 < cells[0].length) {
	    // Move down
	    y++;
	} else if (dir > inc3 && y - 1 >= 0) {
	    // Move up
	    y--;
	} else {
	    move();
	}

	cell = cells[x][y];
	cell.setColor(color);
	cell.setWalker(this);
    }

}
