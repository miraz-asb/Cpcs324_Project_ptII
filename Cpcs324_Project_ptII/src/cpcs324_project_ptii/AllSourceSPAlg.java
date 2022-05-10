package cpcs324_project_ptii;

import java.io.PrintWriter;
import java.util.*;

public class AllSourceSPAlg extends ShortestPathAlgorithm {

    private Graph g;
    final static int INF = 99999;

    /**
     *
     */
    public AllSourceSPAlg() {
    }

    /**
     * compute Floyd & Warshall Algorithm
     *
     * @param g
     */
    public void computeFloydWarshalAlg(Graph g,PrintWriter output) {
        // start time
        double StartTime = System.currentTimeMillis();
        int verticesNo = g.getVertices().size();//save number of Vertices
        int dist[][] = new int[verticesNo][verticesNo];// array to save distance
        int i, j, k;//variable of the loops

        //loop to copy weight to dist a
        //if no edge it will be set to INF
        for (i = 0; i < verticesNo; i++) {
            for (j = 0; j < verticesNo; j++) {
                if (g.getAdjMatrix()[i][j] == null) {
                    dist[i][j] = INF;//set to infinity
                } else {
                    dist[i][j] = g.getAdjMatrix()[i][j].getWeight();//save weight
                }
            }
        }

        output.println("\n*************************Floyd Warshall Algorithm****************************");
        //loop to compute shortest path
        for (k = 0; k < verticesNo; k++) {
            // Pick all vertices as source one by one
            for (i = 0; i < verticesNo; i++) {
                // Pick all vertices as destination for the
                // above picked source
                for (j = 0; j < verticesNo; j++) {
                    // If vertex k is on the shortest path from
                    // i to j, then update the value of dist[i][j]
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
            //to see output of int labels graph remove (/**/)that surround the esle statment 
            if (g.isIsChar() == true) {
            output.println("\n******************************************" + (k + 1) + "******************************************");
            // Print the shortest distance matrix
            //ethir by char version or int version
                printCharGraph(dist,output);
            } /*else {
                System.out.println("\n******************************************" + (k + 1) + "******************************************");
                printGraph(dist);
            }*/
        }
        //finish time of the algorithm
        double FinishTime = System.currentTimeMillis();
        //print the total time consumed by the algorithm
        System.out.println("Total runtime of Floyd Warshall Algorithm: " + (FinishTime - StartTime) + " ms.");
        // Print the shortest distance matrix
        //ethir by char version or int version
        if (g.isIsChar() == true) {
            output.println("\n******************************************" + "Final result" + "******************************************");
            printCharGraph(dist,output);
            output.println("Total runtime of Floyd Warshall Algorithm: " + (FinishTime - StartTime) + " ms.");
        } /*else {
            System.out.println("\n******************************************" + "Final result" + "******************************************");
            printGraph(dist);
        }*/
    }

    /**
     * print graph with char label
     *
     * @param graph
     */
    public void printCharGraph(int graph[][],PrintWriter output) {
        //get the graph size 
        int verticesNo = graph.length;
        //print as example
        //  A   	B   	C   	D   	E   	F   	G   	H   	I   	J   	 
        output.print("   ");
        for (int i = 0; i < verticesNo; i++) {
            output.printf("%-4s\t", (char) (i + 65));
        }
        output.println();

        //print as example
        //A| ∞	8 	11	15	11	5 	13	16	19	7 	
        for (int i = 0; i < verticesNo; i++) {
            output.print((char) (i + 65) + "| ");
            for (int j = 0; j < verticesNo; j++) {
                if (graph[i][j] == INF) {
                    output.print("∞\t");
                } else {
                    output.printf("%-2d\t", graph[i][j]);
                }
            }
            output.println();
        }
        output.println("\n");
    }

    /**
     * print graph with int label
     *
     * @param graph
     */
    public void printGraph(int graph[][],PrintWriter output) {
        //get the graph size 
        int verticesNo = graph.length;
        //print as example
        //   0   	1   	2   	3   	4   	
        output.print("   ");
        for (int i = 0; i < verticesNo; i++) {
            output.printf("%-4d\t", i);
        }
        output.println();
        //print as example
        //0| 0	0	18	3 	0	
        for (int i = 0; i < verticesNo; i++) {
            output.print(i + "| ");
            for (int j = 0; j < verticesNo; j++) {
                if (graph[i][j] == INF) {
                    output.print("∞\t");
                } else {
                    output.printf("%-2d\t", graph[i][j]);
                }
            }
            output.println();
        }
        output.println("\n");
    }
}
