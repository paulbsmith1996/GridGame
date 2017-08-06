public class ScoreCard {
    
    private int[] probs;
    private int perf;
    
    public ScoreCard(int[] probs, int perf) {
	this.probs = probs;
	this.perf = perf;
    }
    
    public void setProbs (int[] probs) { this.probs = probs; }
    public int[] getProbs() { return this.probs; }
    
    public void setPerf (int perf) { this.perf = perf; }
    public int getPerf() { return this.perf; }
}
