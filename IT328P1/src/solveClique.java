import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class solveClique {
	public static void main(String[] args){
	/*Student A will write a method that can find an k-clique, if such clique exists, for any given
	undirected graph and integer k >= 1.*/
	
	/*java solveClique graphs16.txt
should print the results in the following format.
* Max Cliques in graphs in graphsDense.txt
(|V|,|E|) Cliques (size, ms used)
G1 ( 2, 0) {0} (size=1, 0 ms)
G2 ( 2, 1) {0,1} (size=2, 0 ms)
G3 ( 4, 4) {1,2,3} (size=3, 1 ms)
............
G60 (60,1403) {11,13,19,20,21,26,32,37,38,43,44,47,48,51,56,59} (size=16, 10487 ms)
***
*/
	
//R: is possibly a clique
//P:holds vertices adjacent to every vertex currently in R, non/few /all when added to R makes it maximal
//X: contains nodes already in some clique or processed ( to remove duplicate cliques)
		int edges = 0;
		int totaledges = 0; //(number of 1's - number of vertices) /2
		int maxedges = 0;
		int vertices = 0;
		int graphnumber = 1;
	Scanner sc2 = null;{
	try{
		sc2 = new Scanner(new File("graphs18.txt"));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	while(sc2.hasNextLine()){
		Scanner vertexCount = new Scanner(sc2.nextLine());
		vertices = Integer.parseInt(vertexCount.next());
		vertexCount.close();
		totaledges = 0;
		maxedges = 0;
		for(int i = 0; i < vertices; i++)
		{
		Scanner s2 = new Scanner(sc2.nextLine());
		edges = 0;
		while (s2.hasNext()){
			int s = Integer.parseInt(s2.next());
			if(s == 1)
			{
				edges++;
				totaledges++;
			}
		}
		if(edges > maxedges)
		{
			maxedges = edges;
		}
		s2.close();
	}
		totaledges = ((totaledges-vertices)/2);
		System.out.println("G" + graphnumber + " (" + vertices + ", "+totaledges +") " );
		graphnumber++;
	/*List<V> P= new ArrayList<V>();
	
	for(int i=0; i < vertices; i++)
	{
		for(int j=0; j < vertices; j++)
		{
			if(line.next = 1)
			{
				edges++;
			}
		}
		if(edges > maxedges)
		{
			maxedges = edges;
		}
	}*/
}
	}
}
}