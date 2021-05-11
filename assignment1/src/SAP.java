import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP
{
	//	private myBFS bfs;
	private final Digraph G;
	private final int n;
	
	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G)
	{
		if (G == null) throw new IllegalArgumentException();
		//		this.bfs = new myBFS(G);
		this.G = new Digraph(G);
		n = G.V();
	}
	
	
	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w)
	{
		checkVerticesRange(v, w);
		BreadthFirstDirectedPaths vLengths = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths wLengths = new BreadthFirstDirectedPaths(G, w);
		
		int min = n + 1;
		
		for (int i = 0; i < n; i++)
		{
			if (vLengths.hasPathTo(i) && wLengths.hasPathTo(i))
			{ min = Math.min(min, vLengths.distTo(i) + wLengths.distTo(i)); }
		}
		
		return min == n + 1 ? -1 : min;
	}
	
	// a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such
	// path
	public int ancestor(int v, int w)
	{
		checkVerticesRange(v, w);
		BreadthFirstDirectedPaths vLengths = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths wLengths = new BreadthFirstDirectedPaths(G, w);
		
		int min = n + 1, lca = -1;
		
		for (int i = 0; i < n; i++)
		{
			if (vLengths.hasPathTo(i) && wLengths.hasPathTo(i))
			{
				int len = vLengths.distTo(i) + wLengths.distTo(i);
				if (min > len)
				{
					min = len;
					lca = i;
				}
			}
		}
		
		return lca;
	}
	
	// length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no
	// such path
	public int length(Iterable<Integer> v, Iterable<Integer> w)
	{
		checkVerticesRange(v, w);
		if (!v.iterator().hasNext() || !w.iterator().hasNext()) return -1;
		BreadthFirstDirectedPaths vLengths = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths wLengths = new BreadthFirstDirectedPaths(G, w);
		
		int min = n + 1;
		
		for (int i = 0; i < n; i++)
		{
			if (vLengths.hasPathTo(i) && wLengths.hasPathTo(i))
			{ min = Math.min(min, vLengths.distTo(i) + wLengths.distTo(i)); }
		}
		
		return min == n + 1 ? -1 : min;
	}
	
	
	// a common ancestor that participates in shortest ancestral path; -1 if no such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
	{
		checkVerticesRange(v, w);
		if (!v.iterator().hasNext() || !w.iterator().hasNext()) return -1;
		BreadthFirstDirectedPaths vLengths = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths wLengths = new BreadthFirstDirectedPaths(G, w);
		
		int min = n + 1, lca = -1;
		
		for (int i = 0; i < n; i++)
		{
			if (vLengths.hasPathTo(i) && wLengths.hasPathTo(i))
			{
				int len = vLengths.distTo(i) + wLengths.distTo(i);
				if (len < min)
				{
					min = len;
					lca = i;
				}
			}
		}
		
		return lca;
	}
	
	private void checkVerticesRange(int v, int w)
	{
		if (v < 0 || w < 0 || v >= n || w >= n) throw new IllegalArgumentException();
	}
	
	private void checkVerticesRange(Iterable<Integer> v, Iterable<Integer> w)
	{
		if (v == null || w == null) throw new IllegalArgumentException();
		for (Integer i : v)
			if (i == null || i < 0 || i >= n) throw new IllegalArgumentException();
		for (Integer i : w)
			if (i == null || i < 0 || i >= n) throw new IllegalArgumentException();
	}
	
	// do unit testing of this class
	public static void main(String[] args)
	{
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		while (!StdIn.isEmpty())
		{
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
	}
}
