import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class GridGame extends Applet implements Runnable {

    private Thread ticker;
    private boolean running, terminate;
    
    private final int WINDOW_WIDTH = 500, WINDOW_HEIGHT = 500;
    
    private final long FPS = 1000 / 1000;
    
    private Grid grid;
    
    private final int GRID_SIZE = 20;
    private boolean first = true;
    
    private int[] colorCounts;
    private Player player;
    
    public void setRunning(boolean b) { this.running = b; }

    public void init() {
	this.resize(WINDOW_WIDTH, WINDOW_HEIGHT);

	grid = new Grid(30, 30, GRID_SIZE, GRID_SIZE);

	int[] probs = { 250, 250, 250, 250 };

	setPlayer(probs);
	grid.addPlayer(grid.getWidth() - 1, grid.getHeight() - 1, 1);
	grid.addPlayer(0, grid.getHeight() - 1, 2);
	grid.addPlayer(grid.getWidth() - 1, 0, 3);

	colorCounts = new int[4];

    }

    public void setPlayer(int[] probs) {
	grid.removeMain();
	player = new Player(grid, 0, 0, 0, probs);
	grid.addMain(player);
    }
    
    public Player getPlayer() { return this.player; }

    @Override
	public void run() {
	init();
	// TODO Auto-generated method stub
	while (running) {
	    
	    execute();
	    
	    repaint();

	    try {
		ticker.sleep(FPS);
	    } catch (Exception e) {

	    }
	}
	
    }
    
    public void execute() {

	if (!grid.isFull()) {
	    grid.update();
	} else {
	    gameEnd();
	}
    }
    
    public void sleep(long time) {
	try {
	    ticker.sleep(time);
	} catch (Exception e) {

	}
    }
    
    public boolean isRunning() { return this.running; }

    public void paint(Graphics g) {
	if (first) {
	    grid.drawCells(g);
	    first = !first;
	} else {
	    grid.drawCells(g);
	    grid.draw(g);
	}
    }
    
    public void start() {
	
	if(ticker == null || !ticker.isAlive()) {
	    ticker = new Thread(this);
	    ticker.setPriority(Thread.MIN_PRIORITY);
	    running = true;
	    ticker.start();
	}
    }
    
    public void stop() {
	running = false;
    }
    
    public Grid getGrid() { return this.grid; }
    
    public int[] getCounts() {
	return this.colorCounts;
    }
    
    public int[] gameEnd() {
	
	Cell[][] cells = grid.getCells();
	
	for(int x = 0; x < cells.length; x++) {
	    for(int y = 0; y < cells[0].length; y++) {
		
		int i = -1;
		
		Cell curCell = cells[x][y];
		Color cellColor = curCell.getColor();
		
		if (cellColor == Color.RED) {
		    i = 0;
		} else if(cellColor == Color.BLUE) {
		    i = 1;
		} else if(cellColor == Color.GREEN) {
		    i = 2;
		} else if(cellColor == Color.YELLOW) {
		    i = 3;
		}
		
		colorCounts[i]++;
		
		//System.out.println("Cell at: " + x + ", " + y + " is color " + i);
	    }
	}
	
	/*
	  for(int x = 0; x < colorCounts.length; x++) {
	  System.out.println("Color " + x + " has : " + colorCounts[x] + " cells");
	  }
	*/
	
	
	
	stop();
	
	return colorCounts;
    }
}
