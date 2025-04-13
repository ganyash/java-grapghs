import java.util.ArrayList;

public class Graph{

    static class Edge{
        int src;
        int dest;
        public Edge(int src, int dest){
            this.src = src;
            this.dest= dest;
        }
    }
    public static  void createGraph(ArrayList<Edge> graph[], int V){
        for(int i=0;i<V;i++){
            graph[i]= new ArrayList<>();
        }
        graph[0].add(new Edge(0,1));
        graph[0].add(new Edge(0,2));
        graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,3));
        graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,4));
        graph[3].add(new Edge(3,1));
        graph[3].add(new Edge(3,4));
        graph[3].add(new Edge(3,5));
        graph[4].add(new Edge(4,3));
        graph[4].add(new Edge(4,2));
        graph[4].add(new Edge(4,5));
        graph[5].add(new Edge(5,3));
        graph[5].add(new Edge(5,4));
        graph[5].add(new Edge(5,6));
        graph[6].add(new Edge(6,5));
    }
    public static void main(String[] args) {
        
        int V=7;
        ArrayList <Edge> graph[]= new ArrayList[V];
        // create graph
        createGraph(graph,V);

        //print graph
        for(int i=0;i<V;i++){
            for(int j=0;j<graph[i].size();j++){
                Edge e = graph[i].get(j);
                System.out.println("SRC "+ e.src+ " DEST "+e.dest);
            }
        }
    } 
}