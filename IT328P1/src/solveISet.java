import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * solveISet.java
 * find max independent set of each graph in the input fileName
 * Usage: java solveISet <filename>
 *
 * @author jbschmi3
 */

public class solveISet {
    public static int[][] graph;
    public static int vertices;
    public static int graphnumber;
    public static int edges;
    public static int row;
    public static long duration;

    public solveISet() {
        graph = new int[60][60]; //max size of the graph
        vertices = 0;
        graphnumber = 0;
        edges = 0;
        row = 0;
        duration = 0;
    }

    public ArrayList<Integer> findMaxISet(ArrayList<Integer> clique, int row, int d) {
        //start of time
        long start = System.currentTimeMillis();

        ArrayList<Integer> tempClique = new ArrayList<Integer>();
        ArrayList<Integer> maxClique = new ArrayList<Integer>();
        maxClique = clique;

        for (int i = row; i < d; i++) {
            boolean isClique = true;
            for (int j = 0; j < clique.size(); j++)
                if (graph[clique.get(j)][i] != 1) isClique = false;

            if (isClique) {
                //find maxClique
                ArrayList<Integer> thisClique = new ArrayList<Integer>(clique);
                thisClique.add(i);
                tempClique = findMaxISet(thisClique, i + 1, vertices);

                //set maxClique
                if (tempClique.size() > maxClique.size())
                    maxClique = tempClique;
            }
        }
        //end of time
        long end = System.currentTimeMillis();
        duration = end - start;

        return maxClique;
    }

    public static void main(String[] args) {
        String fileName = "";

        //check for file
        if (args.length != 1) {
            System.out.println("Usage: java solveISet <filename>");
        } else {
            fileName = args[0];
        }

        //print out header
        System.out.println("* Max Independent Sets in graphs in " + fileName + " :   (reduced to K-Clique) *");
        System.out.println("    (|V|, |E|) Independent Set (size, ms used)");

        //solve
        solveISet iSet = new solveISet();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line = null;
            while ((line = br.readLine()) != null) {
                vertices = Integer.parseInt(line);

                if (vertices != 0) {
                    graphnumber++;

                    for (int i = 0; i < vertices; i++) {
                        for (int j = 0; j < vertices; j++) {
                            char c = (char) br.read();
                            int v = Character.getNumericValue(c);
                            graph[i][j] = v;

                            //find the number of edges
                            edges = 0;
                            for (int m = 0; m < vertices; m++) {
                                for (int n = i; n < vertices; n++) {
                                    if (graph[m][n] == 1)
                                        edges++;
                                }
                            }

                            if (i != j) {
                                if (graph[i][j] == 1) {
                                    graph[i][j] = 0;
                                } else {
                                    graph[i][j] = 1;
                                }
                            }
                            br.read();
                        }
                        br.read();
                        br.read();
                    }
                    ArrayList<Integer> iList = new ArrayList<Integer>();
                    iList = iSet.findMaxISet(iList, row, vertices);

                    //print out results
                    System.out.println("G" + graphnumber + " (" + vertices + ", " + edges + ") "
                            + iList.toString() + " (size=" + iList.size() + ", " + duration + " ms)");
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found.  Usage: java solveISet <filename>");
        } catch (IOException e) {
            System.out.println("File Read Error.");
        }
    }
}
