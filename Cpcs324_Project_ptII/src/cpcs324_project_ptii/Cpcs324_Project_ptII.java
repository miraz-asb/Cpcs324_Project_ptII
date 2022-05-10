package cpcs324_project_ptii;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Cpcs324_Project_ptII {

    public static void main(String[] args) throws FileNotFoundException {
        //TODO code application logic here
        File ReqOneFile = new File("output.txt");
        PrintWriter output = new PrintWriter(ReqOneFile);
        Scanner input = new Scanner(System.in);
        int algonum = -1;
        int n = 0, m = 0;

        while (algonum != 1 && algonum != 2 ) {
            System.out.println("******** Comparison of two of the Shortest Path Algorithm algorithms ********");
            System.out.println("1-Execute Requirement 1");
            System.out.println("2-Execute Dijkstra Algorithm + Floyd Warshall Algorithm");
            System.out.print("Enter your choice: ");
            algonum = input.nextInt();

            if (algonum != 1 && algonum != 2) {
                System.out.println("****Wrong input!****");
            }
        }
        if (algonum == 1) {
            File file = new File("R1.txt");
            Graph g = new Graph();
            Graph done = g.readGraphFromFile(file);
            done.printCharGraph(output);
            SingleSourceSPAlg SSA = new SingleSourceSPAlg();
            SSA.computeDijkstraAlg(done, output);
            AllSourceSPAlg ASA = new AllSourceSPAlg();
            ASA.computeFloydWarshalAlg(done, output);
            output.close();
        } else if (algonum == 2) {
            System.out.println("\nChoose from the following cases: ");
            System.out.println("1- n=3000 , m=15000");
            System.out.println("2- n=5000 , m=25000");
            System.out.println("3- n=7000 , m=35000");
            System.out.println("4- n=9000 , m=45000");
            System.out.println("5- n=11000 , m=55000");
            
            System.out.print("\nEnter your choice: ");
            int incase = input.nextInt();
            while (incase < 1 || incase > 5) {
                System.out.println("****Wrong input!****");
                System.out.print("Enter your choice: ");
                incase = input.nextInt();
            }
            switch (incase) {
                case 1: {
                    n = 3000;
                    m = 15000;
                }
                break;
                case 2: {
                    n = 5000;
                    m = 25000;
                }
                break;
                case 3: {
                    n = 7000;
                    m = 35000;
                }
                break;
                case 4: {
                    n = 9000;
                    m = 45000;
                }
                break;
                case 5: {
                    n = 11000;
                    m = 55000;
                }
                break;
            }
            System.out.println("\t\t\tGraph size is n = "+n+" m = "+m);
            for (int i = 0; i < 5; i++) {
                System.out.println("************************************ Trail " + (1 + i) + " ************************************");
                Graph g = new Graph();
                Graph done = g.makeGraph(n, m);
                //done.printGraph(output);//remove dash to see the graph
                SingleSourceSPAlg SSA = new SingleSourceSPAlg();
                SSA.computeDijkstraAlg(done, output);
                AllSourceSPAlg ASA = new AllSourceSPAlg();
                ASA.computeFloydWarshalAlg(done, output);
            }
        }
    }
}
