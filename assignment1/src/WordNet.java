import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class WordNet
{
	private int n;
	private ArrayList<ArrayList<String>> V;
	private TreeSet<String> allNouns;
	private Digraph DAG;
	
	
	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms)
	{
		// ===================================================
		// Implement throw exception when the input to the
		// constructor does not correspond to a rooted DAG.
		// (check for cycles and also for existence of a root)
		// ===================================================
		
		if (synsets == null || hypernyms == null) throw new IllegalArgumentException();
		
		V = new ArrayList<ArrayList<String>>();
		allNouns = new TreeSet<>();
		
		In in = new In(synsets);
		
		n = 0;
		while (in.hasNextLine())
		{
			String line = in.readLine();
			String[] data = line.split(",");
			V.add(new ArrayList<String>());
			
			ArrayList<String> temp = new ArrayList<>(Arrays.asList(data[1].split(" ")));
			
			V.set(n, temp);
			
			for (String s : temp) allNouns.add(s);
			
			n++;
		}
		
		in.close();
		
		DAG = new Digraph(n);
		
		in = new In(hypernyms);
		
		while (in.hasNextLine())
		{
			String line = in.readLine();
			String[] data = line.split(",");
			
			for (int i = 1; i < data.length; i++)
				DAG.addEdge(Integer.parseInt(data[0]), Integer.parseInt(data[i]));
		}
		in.close();
	}
	
	// returns all WordNet nouns
	public Iterable<String> nouns()
	{
		return allNouns;
	}
	
	// is the word a WordNet noun?
	public boolean isNoun(String word)
	{
		if (word == null) throw new IllegalArgumentException();
		return allNouns.contains(word);
	}
	
	// distance between nounA and nounB
	public int distance(String nounA, String nounB)
	{
		// ===================================================
		// Complexity aim: linear in size of digraph
		// ===================================================
		checkValidNoun(nounA, nounB);
		
		return 0;
	}
	
	
	// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB in a
	// shortest ancestral path (defined below)
	public String sap(String nounA, String nounB)
	{
		// =========================================================================================
		// Complexity aim: linear in size of digraph
		// =========================================================================================
		checkValidNoun(nounA, nounB);
		
		return "";
	}
	
	
	private void checkValidNoun(String nounA, String nounB)
	{
		if (nounA == null || nounB == null) throw new IllegalArgumentException();
		if (!allNouns.contains(nounA) || !allNouns.contains(nounB))
		{ throw new IllegalArgumentException(); }
	}
	
	
	// do unit testing of this class
	public static void main(String[] args)
	{
	}
}