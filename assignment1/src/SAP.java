import edu.princeton.cs.algs4.Digraph;

public class SAP
{
	private myBFS bfs;
	private Digraph G;
	private int n;
	
	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G)
	{
		if (G == null) throw new IllegalArgumentException();
		this.bfs = new myBFS(G);
		this.G = new Digraph(G);
		n = G.V();
	}
	
	
	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w)
	{
		checkVerticeRange(v, w);
		
		
		return -1;
	}
	
	// a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such
	// path
	public int ancestor(int v, int w)
	{
		checkVerticeRange(v, w);
		
		
		return -1;
	}
	
	// length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no
	// such path
	public int length(Iterable<Integer> v, Iterable<Integer> w)
	{
		return -1;
	}
	
	// a common ancestor that participates in shortest ancestral path; -1 if no such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
	{
		return -1;
	}
	
	
	private void checkVerticeRange(int v, int w)
	{
		if (v >= 0 && w >= 0 && v <= n && w <= n) return;
		throw new IllegalArgumentException();
	}
	
	// do unit testing of this class
	public static void main(String[] args)
	{
	
	}
}
