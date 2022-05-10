package cpcs324_project_ptii;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Graph {

    private int verticesNo;//number of vertices of the graph
    private int edgeNo;//number of edges of the graph.
    private boolean isDigraph = false;//boolean that is set to true if the graph is directed graph
    private Edge adjMatrix[][];//adjacency matrix of the graph
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();//list of vertices of a graph.
    private boolean isChar = false;
    

    public Graph() {
    }

    public Graph(int verticesNo, int edgeNo) {
        this.verticesNo = verticesNo;
        this.edgeNo = 0;
        adjMatrix = new Edge[verticesNo][verticesNo];//initialize matrix with number of vertices and edge
        //loop to initialize all index with null
        for (Edge[] adjMatrix1 : adjMatrix) {
            for (int j = 0; j < adjMatrix1.length; j++) {
                adjMatrix1[j] = null;
            }
        }
    }

    /**
     * add edge between v and u and increment edgeNo by 1 if directed and by 2
     * if undirected also use addVertLabelI in int version
     *
     * @param v
     * @param u
     * @param w
     * @return
     */
    public Edge addEdge(int v, int u, int w) {
        Edge e1, e2;//edge object to save value
        Vertex s = new Vertex(v);//first vertex
        addVertLabelI(s);//check if it is exist in vertices
        Vertex t = new Vertex(u);//seconde vertex
        addVertLabelI(t);//also check if exist in vertices
        e1 = new Edge(s, t, w);//create edge
        adjMatrix[v][u] = e1;//add to the adjacncy matrix
        edgeNo++;//increment number of edges
        //three step above for dircted graph 
        //if the graph undircted repeat same step but invert between s and t
        if (isDigraph == false) {
            e2 = new Edge(t, s, w);
            adjMatrix[u][v] = e2;
            edgeNo++;
        }
        return e1;//return the first egde created
    }

    /**
     * overload addEdge that add edge between v and u and increment edgeNo by 1
     * since directed graph also it use addVertLabelc in char version
     *
     * @param v
     * @param u
     * @param w
     * @return
     */
    public Edge addEdge(char v, char u, int w) {
        Edge e1, e2;//edge object to save value
        Vertex s = new Vertex(v);//first vertex
        addVertLabelc(s);//check if it is exist in vertices
        Vertex t = new Vertex(u);//seconde vertex
        addVertLabelc(t);//also check if exist in vertices
        e1 = new Edge(s, t, w);//create edge
        adjMatrix[s.getPosition()][t.getPosition()] = e1; //add to the adjacncy matrix (take position since v and u are char not int )
        edgeNo++;//increment number of edges
        //since this addEdge are only used with readFromFile function which read a directed graph no need to make edge between u and v
        /*if the graph undircted repeat same step but invert between s and t
        if (isDigraph == false && v != u) {
            e2 = new Edge(t, s, w);
            adjMatrix[t.getPosition()][s.getPosition()] = e2;
            edgeNo++;
        }*/
        return e1;//return the first egde created
    }

    /**
     * check if label exist in vertices or no char version
     *
     * @param v
     * @return
     */
    public boolean addVertLabelc(Vertex v) {
        //loop to see is the vertex in the vertices vector
        for (int i = 0; i < vertices.size(); i++) {
            //if yes return false(no added label to vertices)
            if (v.getLabel() == vertices.get(i).getLabel()) {
                return false;
            }
        }
        vertices.add(v);//otherwise add the vertex
        verticesNo++;//increment number of vertices
        return true;//return true since(vertex is added)
    }

    /**
     * check if label exist in vertices or no int version
     *
     * @param v
     * @return
     */
    public boolean addVertLabelI(Vertex v) {
        //loop to see is the vertex in the vertices vector
        for (int i = 0; i < vertices.size(); i++) {
            //if yes return false(no added label to vertices)
            if (v.getLabelI() == vertices.get(i).getLabelI()) {
                return false;
            }
        }
        vertices.add(v);//otherwise add the vertex
        verticesNo++;//increment number of vertices
        return true;//return true since(vertex is added)
    }

    /**
     * create graph with the verticesNo and edgeNo
     *
     * @param verticesNo
     * @param edgeNo
     * @return
     */
    public Graph makeGraph(int verticesNo, int edgeNo) {
        //variables
        //new graph object
        Graph g = new Graph(verticesNo, edgeNo);
        int randomv, randomu, w = (int) (Math.random() * 20 + 1), leftEdge = 0;//w weight, randomv random value for v(source) and randomu random value for u(destination)

        //loop to randomly connect two randomly verices with randomly edges
        for (int i = 0; i < verticesNo; i++) {

            //random vertices
            randomv = (int) (Math.random() * verticesNo);
            randomu = (int) (Math.random() * verticesNo);

            //check if element is not null (not empty) and if yes choose different vertices by looping again
            if (g.getAdjMatrix()[randomv][randomu] != null || randomv == randomu) {
                i--;
            }//if empty (null), connect it by addEdge function shown below
            else {
                //random waight 
                w = (int) (Math.random() * 20 + 1);
                // add edge between v and u 
                Edge e = g.addEdge(randomv, randomu, w);
                //set IsVisited to true to check connectivity
                g.getAdjMatrix()[randomv][randomu].getSource().setIsVisited(true);
                g.getAdjMatrix()[randomv][randomu].getTarget().setIsVisited(true);
            }
            //calculate remaining number of edge     
            leftEdge = edgeNo - g.edgeNo;

        }
        int isConnect = isConnected(vertices);// save value of is conected
        //if it is bigger than -1 it means a vertex was un Visited
        if (isConnect > -1) {
            //loop to find the un Visited vertex
            for (int i = 0; i < g.getAdjMatrix().length; i++) {
                //either i is the vertex
                if (i == isConnect) {
                    //if i is the vertex loop to find a empty place to make edge
                    for (int j = 0; j < g.getAdjMatrix()[i].length; j++) {
                        if (g.getAdjMatrix()[i][j] == null) {
                            g.getAdjMatrix()[i][j] = g.addEdge(i, j, w);
                            g.edgeNo++;
                        }
                    }
                } //or j is the vertex
                else {
                    //make the edge and break out of the for-loop
                    if (g.getAdjMatrix()[i][isConnect] == null) {
                        g.getAdjMatrix()[i][isConnect] = g.addEdge(i, isConnect, w);
                        g.edgeNo++;
                    }
                    break;
                }
            }
            //calculate remaining number of edge     
            leftEdge = edgeNo - g.edgeNo;
        }

        //loop again on leftover edges
        for (int i = 0; i < leftEdge; i++) {

            //random vertices
            randomv = (int) (Math.random() * verticesNo);
            randomu = (int) (Math.random() * verticesNo);
            //check if element is not null (not empty) and if yes choose different vertices by looping again
            if (g.getAdjMatrix()[randomv][randomu] != null || randomv == randomu) {
                i--;
            }//if empty (null), connect it by addEdge function shown below
            else {
                //random waight 
                w = (int) (Math.random() * 20 + 1);
                // add edge between v and u 
                g.addEdge(randomv, randomu, w);
                //set IsVisited to true to check connectivity 
                g.getAdjMatrix()[randomv][randomu].getSource().setIsVisited(true);
                g.getAdjMatrix()[randomv][randomu].getTarget().setIsVisited(true);
            }
        }
        return g;
    }

    /**
     * check is all vertices are IsVisited or not if not it will return the
     * vertex that is no Visited yet
     *
     * @param vertices
     * @return
     */
    public int isConnected(ArrayList<Vertex> vertices) {
        int count = 0;//to count all vertices
        boolean coneccted[] = new boolean[vertices.size()];//array to save Visited or not
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getIsVisited() == true) {
                coneccted[i] = true;
            }
        }
        //now after count reach the vertices number check if there any un Visited vertex
        if (vertices.size() == count) {
            for (int i = 0; i < vertices.size(); i++) {
                if (coneccted[i] == false) {
                    return i;//return the un Visited vertex
                }
            }
        }
        return -1;
    }

    /**
     * read information from file then create graph
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public Graph readGraphFromFile(File file) throws FileNotFoundException {
        //check if file exist 
        if (file.exists()) {
            //if yes
            Scanner input = new Scanner(file);//Scanner to read from file
            String check = input.next();//read (digraph) 
            int dORu = input.nextInt();//read 0 or 1
            int verticesN = input.nextInt();//read number of vertices
            int edgesN = input.nextInt();//read number of edges
            Graph g = new Graph(verticesN, edgesN);//creat new graph 
            //set isDigraph to true if and only if dORu = 1
            if (dORu == 1) {
                g.setIsDigraph(true);
            }
            //loop all over the file
            while (input.hasNextLine()) {
                char v = input.next().charAt(0);//read first label (vertex)
                char u = input.next().charAt(0);//read second lable (vertex)
                int w = input.nextInt();//read weight
                g.addEdge(v, u, w);//invoke addEdge
            }
            g.setIsChar(true);
            return g;
        } else {
            //if No print msg and return null
            System.out.println("File dose not exist");
            return null;
        }
    }

    /**
     * print graph with char label of the vertices
     */
    public void printCharGraph(PrintWriter output) {
        //get the graph size 
        int verticesNum = vertices.size();
        //print as example
        //  A   	B   	C   	D   	E   	F   	G   	H   	I   	J 
        output.println("*************************Adjacncy Matrix Represntation****************************");        
        output.print("   ");
        for (int i = 0; i < verticesNum; i++) {
            output.printf("%-4s\t", (char) (i + 65));
        }
        output.println();

        //print as example
        //A| ∞	8 	11	15	11	5 	13	16	19	7 	
        for (int i = 0; i < verticesNum; i++) {
            output.print((char) (i + 65) + "| ");
            for (int j = 0; j < verticesNum; j++) {
                if (adjMatrix[i][j] == null) {
                    output.print("0\t");
                } else {
                    output.printf("%-2d\t", adjMatrix[i][j].getWeight());
                }
            }
            output.println();
        }
        output.println("\n");
    }

    /**
     * print graph with int label of the vertices
     */
    public void printGraph(PrintWriter output) {
        //get the graph size 
        int verticesNum = vertices.size();
        //print as example
        //   0   	1   	2   	3   	4
        output.println("*************************Adjacncy Matrix Represntation****************************");   
        output.print("   ");
        for (int i = 0; i < verticesNum; i++) {
            output.printf("%-4d\t", i);
        }
        output.println();
        ////print as example
        //0| 0  8 	11	15	11	5 	13	16	19	7
        for (int i = 0; i < verticesNum; i++) {
            output.print(i + "| ");
            for (int j = 0; j < verticesNum; j++) {
                if (adjMatrix[i][j] == null) {
                    output.print("0\t");
                } else {
                    output.printf("%-2d\t", adjMatrix[i][j].getWeight());
                }
            }
            output.println();
        }
        output.println("\n");
    }

    /**
     * number of setter and getter for abstraction
     */
    public int getVerticesNo() {
        return verticesNo;
    }

    public void setVerticesNo(int verticesNo) {
        this.verticesNo = verticesNo;
    }

    public int getEdgeNo() {
        return edgeNo;
    }

    public void setEdgeNo(int edgeNo) {
        this.edgeNo = edgeNo;
    }

    public boolean getIsDigraph() {
        return isDigraph;
    }

    public void setIsDigraph(boolean isDigraph) {
        this.isDigraph = isDigraph;
    }

    public Edge[][] getAdjMatrix() {
        return adjMatrix;
    }

    public void setAdjMatrix(Edge[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertex> vertices) {
        this.vertices = vertices;
    }

    public boolean isIsChar() {
        return isChar;
    }

    public void setIsChar(boolean isChar) {
        this.isChar = isChar;
    }

}
