import java.util.Random;

public class Learning {

    public static void main(String[] args) {
	
	//simOneAlg();

	int numGens = 10;
	int numSims = 5;
	
	try {
	    numGens = Integer.parseInt(args[0]);
	    numSims = Integer.parseInt(args[1]);
	} catch (Exception e) {}

	learn(numGens, numSims);

    }
    
    public static void learn(int numGens, int numSims) {
	
	Random r = new Random();
	
	ScoreCard[] scores = new ScoreCard[1000];
	
	// Number of generations that creatures will have to evolve
	int NUM_GENS = numGens;
	
	// Number of trials each creature gets to perform
	int NUM_SIMS = numSims;;
	
	int[] genScores = new int[NUM_GENS];
	
	for (int i = 0; i < scores.length; i++) {
	    // Prob right
	    int p1 = r.nextInt(260);
	    // int p1 = 255;
	    // Prob left
	    int p2 = r.nextInt(250);
	    // int p2 = 245;
	    // Prob down
	    int p3 = r.nextInt(260);
	    // int p3 = 255;
	    // Prob up
	    // int p4 = 245;
	    int p4 = 1000 - p3 - p2 - p1;

	    int[] probs = { p1, p2, p3, p4 };
	    
	    ScoreCard card = new ScoreCard(probs, 0);
	    
	    scores[i] = card;
	}
	
	
	for (int x = 0; x < NUM_GENS; x++) {
	    for (int i = 0; i < scores.length; i++) {
		
		ScoreCard card = scores[i];
		int total = 0;
		
		for (int j = 0; j < NUM_SIMS; j++) {
		    GridGame game = new GridGame();
		    game.init();

		    game.setPlayer(card.getProbs());

		    while (!game.getGrid().isFull()) {
			game.execute();
		    }

		    int[] tempCount = game.gameEnd();

		    total += tempCount[0];

		}
		card.setPerf(total / NUM_SIMS);

		scores[i] = card;
	    }

	    System.out.println("Generation: " + x);
	    System.out.println();
	    
	    int topScore = newGeneration(scores);
	    genScores[x] = topScore;
	}
	
	for(int i: genScores) {
	    System.out.println(i);
	}
    }
    
    public static int newGeneration(ScoreCard[] scores) {
	
	int LEARNING_RATE = 2;
	
	Random r = new Random();
	
	for(int x = 0; x < scores.length; x++) {
	    for(int y = 0; y < scores.length - 1; y++) {
		if(scores[y].getPerf() < scores[y+1].getPerf()) {
		    ScoreCard temp = scores[y];
		    scores[y] = scores[y+1];
		    scores[y+1] = temp;
		}
	    }
	}
	
	System.out.println("Best score was: " 
			   + scores[0].getPerf() + " cells" + " with probs: ");
	
	for(int i: scores[0].getProbs()) {
	    System.out.print(i + ", ");
	}
	System.out.println();
	
	System.out.println("Median score was: " 
			   + scores[scores.length / 2].getPerf() + " cells");
	
	for(int i: scores[scores.length / 2].getProbs()) {
	    System.out.print(i + ", ");
	}
	System.out.println();
	
	System.out.println();
	System.out.println("------------------------");
	System.out.println();
	
	for(int x = 0; x < scores.length / 2 + 1; x++) {
	    ScoreCard card = scores[x];
	    
	    int[] playProbs = card.getProbs();
	    
	    while (playProbs[3] <= 0) {
		
		for(int i = 0; i < playProbs.length; i++) {
		    int delta = r.nextInt(LEARNING_RATE) - LEARNING_RATE / 2;
		    if(playProbs[i] + delta < 1000 && playProbs[i] + delta > 0) {
			playProbs[i] += delta;
		    }
		}
	    }
	    
	    card.setProbs(playProbs);
	}
	
	for (int x = scores.length / 2 - 1; x < scores.length; x++) {
	    
	    int p1 = 0;
	    int p2 = 0;
	    int p3 = 0;
	    int p4 = 0;
	    
	    do {
		// Prob right
		p1 = r.nextInt(1000);
		
		// Prob left
		p2 = r.nextInt(1000);
		
		// Prob down
		p3 = r.nextInt(1000);
		
		// Prob up
		p4 = 1000 - p3 - p2 - p1;
		
	    } while(p4 <= 0);
	    

	    int[] probs = { p1, p2, p3, p4 };

	    ScoreCard card = new ScoreCard(probs, 0);

	    scores[x] = card;
	}
	
	return scores[0].getPerf();
    }
    
    public static void simOneAlg() {
	final int NUM_SIMS = 1000;
	
	Random r = new Random();
	
	int[] ranks = new int[NUM_SIMS];
	
	for (int a = 0; a < NUM_SIMS; a++) {
	    GridGame game = new GridGame();
	    game.init();
	    
	    // Prob right
	    int p1 = 250;
	    //int p1 = 255;
	    // Prob left
	    int p2 = 250;
	    //int p2 = 245;
	    // Prob down 
	    int p3 = 250;
	    //int p3 = 255;
	    // Prob up
	    //int p4 = 245;
	    int p4 = 1000 - p3 - p2 - p1;
	    
	    int[] probs = {p1, p2, p3, p4};
	    
	    game.setPlayer(probs);
	    
	    while(!game.getGrid().isFull()) {
		game.execute();
	    }
	    
	    int[] tempCount = game.gameEnd();
	    //System.out.println(a + ": " + tempCount[0]);
	    ranks[a] = tempCount[0];
	}
	
	int total = 0;
	
	for(int i: ranks) {
	    total += i;
	}
	
	System.out.println("On average, Red will have: " + total / NUM_SIMS + " cells");

    }
}
