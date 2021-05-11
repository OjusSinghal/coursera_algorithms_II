import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class WordNet
{
	private final int n;
	private final ArrayList<ArrayList<String>> V;
	private final TreeSet<String> allNouns;
	private final Digraph DAG;
	private final SAP shortestPath;
	
	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms)
	{
		if (synsets == null || hypernyms == null) throw new IllegalArgumentException();
		V = new ArrayList<>();
		allNouns = new TreeSet<>();
		In in = new In(synsets);
		
		while (in.hasNextLine())
		{
			String line = in.readLine();
			String[] data = line.split(",");
			V.add(new ArrayList<>(Arrays.asList(data[1].split(" "))));
			allNouns.addAll(V.get(V.size() - 1));
		}
		n = V.size();
		in.close();
		this.DAG = new Digraph(n);
		
		in = new In(hypernyms);
		while (in.hasNextLine())
		{
			String line = in.readLine();
			String[] data = line.split(",");
			for (int i = 1; i < data.length; i++)
				DAG.addEdge(Integer.parseInt(data[0]), Integer.parseInt(data[i]));
		}
		
		in.close();
		if (hasCycle()) throw new IllegalArgumentException("Graph contains a cycle.");
		if (!isRooted()) throw new IllegalArgumentException("Graph is not single-rooted");
		this.shortestPath = new SAP(DAG);
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
		Iterable<Integer> v = getIndices(nounA);
		Iterable<Integer> w = getIndices(nounB);
		return shortestPath.length(v, w);
	}
	
	
	// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB in a
	// shortest ancestral path (defined below)
	public String sap(String nounA, String nounB)
	{
		Iterable<Integer> v = getIndices(nounA);
		Iterable<Integer> w = getIndices(nounB);
		
		int x = shortestPath.ancestor(v, w);
		StringBuilder s = new StringBuilder();
		
		for (String i : V.get(x))
			s.append(i).append(" ");
		
		return s.substring(0, s.length() - 1);
	}
	
	private Iterable<Integer> getIndices(String noun)
	{
		if (noun == null) throw new IllegalArgumentException();
		
		ArrayList<Integer> indices = new ArrayList<>();
		for (int i = 0; i < n; i++)
		{
			for (String s : V.get(i))
				if (s.equals(noun)) indices.add(i);
		}
		
		if (indices.isEmpty()) throw new IllegalArgumentException();
		return indices;
	}
	
	
	private boolean isRooted()
	{
		boolean rootFound = false;
		for (int i = 0; i < n; i++)
		{
			if (DAG.outdegree(i) == 0)
			{
				if (rootFound) return false;
				rootFound = true;
			}
		}
		return rootFound;
	}
	
	private boolean hasCycle()
	{
		boolean[] recStack = new boolean[n];
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++)
			if (!visited[i] && hasCycle(recStack, visited, i)) return true;
		
		return false;
	}
	
	private boolean hasCycle(boolean[] recStack, boolean[] visited, int v)
	{
		if (recStack[v] && visited[v]) return true;
		if (visited[v]) return false;
		recStack[v] = true;
		visited[v] = true;
		
		for (int i : DAG.adj(v))
			if (hasCycle(recStack, visited, i)) return true;
		
		recStack[v] = false;
		return false;
	}
	
	// do unit testing of this class
	public static void main(String[] args)
	{
	}
}