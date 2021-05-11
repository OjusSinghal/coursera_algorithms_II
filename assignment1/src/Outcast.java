import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast
{
	private final WordNet wn;
	
	// constructor takes a WordNet object
	public Outcast(WordNet wordnet)
	{
		if (wordnet == null) throw new IllegalArgumentException();
		this.wn = wordnet;
	}
	
	
	// given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns)
	{
		int[] distances = new int[nouns.length];
		for (int i = 0; i < nouns.length; i++)
		{
			for (String noun : nouns)
			{
				int x = wn.distance(nouns[i], noun);
				if (x > 0) distances[i] += x;
			}
		}
		
		int out = 0, maxDist = 0;
		
		for (int i = 0; i < distances.length; i++)
		{
			if (distances[i] >= maxDist)
			{
				out = i;
				maxDist = distances[i];
			}
		}
		return nouns[out];
	}
	
	public static void main(String[] args)
	{
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++)
		{
			In in = new In(args[t]);
			String[] nouns = in.readAllStrings();
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}
}