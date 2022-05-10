package cpcs324_project_ptii;

import java.io.PrintWriter;

public class SingleSourceSPAlg extends ShortestPathAlgorithm {

    private Graph g;

    public void computeDijkstraAlg(Graph g,PrintWriter output) {
        // start time
        double StartTime = System.currentTimeMillis();
        Edge graph[][] = g.getAdjMatrix();// Get the adjacncy matrix of the graph
        int size = graph.length;// save length for frequent use
        final int INF = Integer.MAX_VALUE;//maximum value
        // Prepare the other material we need
        boolean[] isVisited = new boolean[size];
        int distance[] = new int[size];//to compare distance
        String[] path = new String[size];//path for garph with char labels of vertex
        String[] pathi = new String[size];////path for garph with int labels of vertex
        // Make all the vertices unvisited and distance as infinity
        for (int i = 0; i < size; i++) {
            isVisited[i] = false;
            distance[i] = INF;
        }
        distance[0] = 0;
        // Take the source as the first vertix 'A', and prepare the paths (char label)
        path[0] = "A --" + distance[0] + "--> ";
        // Take the source as the first vertix '0', and prepare the paths (int label)
        pathi[0] = "0 --" + distance[0] + "--> ";

        boolean isChar = g.isIsChar();//check if label is char or not to now wher to save path

        // --- Start Dijkstra ---
        for (int i = 0; i < size; i++) {
            //Find the minium distance among all the vertices
            int u = 0;
            int minDistance = INF;
            for (int j = 0; j < size; j++) {
                if (isVisited[j] != true && distance[j] < minDistance) {
                    minDistance = distance[j];
                    u = j; // Min vertix index
                }
            }

            // Set u as visited
            isVisited[u] = true;

            // Update the adjacent distances
            for (int v = 0; v < size; v++) {
                if (graph[u][v] == null) {
                    continue;
                }//if there is no edge just ignor
                if (isVisited[v] == false) {
                    if (distance[u] + graph[u][v].getWeight() < distance[v]) {
                        distance[v] = distance[u] + graph[u][v].getWeight();
                        // Update the path by adding the parent path plus the current vertix path
                        if (isChar == true) {
                            path[v] = path[u] + (char) (v + 65) + " --" + (distance[v] - distance[u]) + "--> ";
                        } else {
                            pathi[v] = pathi[u] + v + " --" + (distance[v] - distance[u]) + "--> ";
                        }
                    }
                }
            }
        }
        //finish time of the algorithm
        double FinishTime = System.currentTimeMillis();
        //print the total time consumed by the algorithm
        System.out.println("Total runtime of Dijkstra Algorithm: " + (FinishTime - StartTime) + " ms.");
        // Print all the distances with the pathes
        //if labels of vertex is char
        if (isChar == true) {
            output.println("\n*************************Dijkstra Algorithm****************************");
            output.println("All the shortest distances from vertex 'A' to other vertices in graph");
            for (int i = 1; i < size; i++) {
                output.println("  > Shortest Distance from 'A' to '" + (char) (i + 65) + "' is <<" + distance[i] + ">>, the Path: " + path[i] + " " );
            }
            output.println();
            output.println("Total runtime of Dijkstra Algorithm: " + (FinishTime - StartTime) + " ms.");
        }
        //the else is for the graph with int labels
        //used only to check is the algorithm work properly
        //if you want to see output of int labels graph just remove the (/**/) that surround the code
        /*else {//if labels are int
            output.println("All the shortest distances from vertex '0' to other vertices in graph");
            for (int i = 1; i < size; i++) {
                if (distance[i] == INF) {
                    continue;
                }
                output.println("  > Shortest Distance from '0' to '" + i + "' is " + distance[i] + ", the Path: " + pathi[i] + " " + i + " " + distance[i]);
            }
            output.println();
        }*/
    }

}
    