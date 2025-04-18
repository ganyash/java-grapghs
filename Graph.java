import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Graph{

    static class Edge  implements  Comparable<Edge>{
        int src;
        int dest;
        int weight;
        public Edge(int src, int dest, int weight){
            this.src = src;
            this.dest= dest;
            this.weight= weight;
        }
        @Override
        public int compareTo (Edge p2){
          return this.weight-p2.weight;
        }
    }

    static class Pair implements  Comparable<Pair>{
        int node;
        int dist;
        public Pair(int node, int dist){
            this.node= node;
            this.dist= dist;
        }
        @Override
        public int compareTo (Pair p2){
          return this.dist-p2.dist;
        }
    }
    public static  void createGraph(ArrayList<Edge> graph[], int V){
        for(int i=0;i<V;i++){
            graph[i]= new ArrayList<>();
        }
        graph[0].add(new Edge(0,1,1));
        graph[0].add(new Edge(0,2,2));
        graph[1].add(new Edge(1,0,1));
        graph[1].add(new Edge(1,3,0));
        graph[2].add(new Edge(2,0,2));
        graph[2].add(new Edge(2,4,1));
        graph[3].add(new Edge(3,1,0));
        graph[3].add(new Edge(3,4, 3));
        graph[3].add(new Edge(3,5,5));
        graph[4].add(new Edge(4,3,3));
        graph[4].add(new Edge(4,2,1));
        graph[4].add(new Edge(4,5,4));
        graph[5].add(new Edge(5,3,5));
        graph[5].add(new Edge(5,4,4));
        graph[5].add(new Edge(5,6,3));
        graph[6].add(new Edge(6,5,3));

        // graph[0].add(new Edge(0, 1, 5));
        // graph[1].add(new Edge(1, 2, -2));
        // graph[1].add(new Edge(1, 5, -3));
        // graph[2].add(new Edge(2, 4, 3));
        // graph[3].add(new Edge(3, 2, 6));
        // graph[3].add(new Edge(3, 4, -2));
        // graph[5].add(new Edge(5, 3, 1));


        // graph[0].add(new Edge(0,1,1));
        // graph[0].add(new Edge(0,2,2));
        // graph[1].add(new Edge(1,3,0));
        // graph[2].add(new Edge(2,4,-1));
        // graph[3].add(new Edge(3,5,5));
        // graph[4].add(new Edge(4,3,-3));
        // graph[4].add(new Edge(4,5,4));
        // graph[5].add(new Edge(5,6,3));
    }
    public static void bfs(ArrayList<Edge> graph[], boolean visited[], int curr){
        Queue<Integer> q= new LinkedList<>();
        q.add(curr);
        while (!q.isEmpty()) {
            int ele= q.remove();
            if(visited[ele]==false){
                visited[ele] = true;
                System.out.print(ele+" ");
                for(int i=0;i<graph[ele].size();i++){
                    Edge e = graph[ele].get(i);
                    if(visited[e.dest]==false){
                        q.add(e.dest);
                    }
                }
            }
        }
    }

    public static void dfs(ArrayList<Edge> graph[], boolean visited[], int curr){
            System.out.println(curr+" ");
            visited[curr] = true;
            for(int i=0;i<graph[curr].size();i++){
                Edge e = graph[curr].get(i);
                if(visited[e.dest]==false)
                    dfs(graph,visited, e.dest);
            }
        

    }

    public static void printAllPaths (ArrayList<Edge> graph[],boolean visited[], int curr, int target, String path){
        if(curr==target){
            System.out.println(path);
            return;
        }
        visited[curr] = true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(visited[e.dest]==false){
                printAllPaths(graph,visited, e.dest,target, path+e.dest);
              
            }
        }
        visited[curr] =false;

    }

    public static boolean checkIsCyclicGraphUndirected(ArrayList<Edge> graph[], boolean visited[], int curr, int parent){
        visited[curr] = true;
        System.out.println("curr "+curr+" parent "+parent);
        for(int i=0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(visited[e.dest]&&e.dest!=parent){
                return true;
            }
            if(visited[e.dest]==false){
                if(checkIsCyclicGraphUndirected(graph, visited, e.dest, curr)){
                    return true;
                }
            }
        }
        System.out.println("curr after "+curr+" parent after "+parent);
        return false;
    }


    public static boolean checkIsCyclicGraphDirtected(ArrayList<Edge> graph[], boolean visited[], int curr){
        visited[curr] =true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(visited[e.dest]==false){
                if(checkIsCyclicGraphDirtected(graph, visited, e.dest)){
                    return true;
                }
            }
            if(visited[e.dest]){
                return true;
            }
        }       
        visited[curr]= false;
        return false;
    }
    
    public static void topologicalOrder(ArrayList<Edge> graph[], boolean visited[], int curr, Stack<Integer> recursion){

        visited[curr] = true;
        for(int i=0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);
            if(visited[e.dest]==false){
                topologicalOrder(graph, visited, e.dest, recursion);
            }
        }
        recursion.push(curr);
    }

    public static void getShortestPathUsingDijikstra(ArrayList<Edge> graph[], int curr, int distance[], boolean visited[] ){
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(int i=0;i<distance.length;i++){
            distance[i] = Integer.MAX_VALUE;
        }
        pq.add(new Pair(curr, 0));
        distance[curr] =0;
        while (!pq.isEmpty()) {

            Pair p = pq.remove();
            if(visited[p.node]==false){
                visited[p.node] = true; 
                int node = p.node;
                int dist = p.dist;
                for(int i=0;i<graph[node].size();i++){
                    Edge e = graph[node].get(i);
                    if((dist+e.weight)<distance[e.dest]){
                        distance[e.dest] = dist+e.weight;
                    }
                    pq.add(new Pair(e.dest, distance[e.dest]));
                }
            }
            
        }
    

    }

    public static void getShortestPathUsingBellman(ArrayList<Edge> graph[], int curr, int distance[]){
        for(int i=0;i<distance.length;i++){
            distance[i] = i!=curr? Integer.MAX_VALUE:0;
        }
        for(int i=0;i<graph.length-1;i++){
            for(int j=0;j<graph.length;j++){
                for(int k=0;k<graph[j].size();k++){
                    Edge e= graph[j].get(k);
                    int weight= e.weight;
                    int srcDistance=distance[e.src];
                    if(srcDistance!=Integer.MAX_VALUE &&(srcDistance+weight)<distance[e.dest]){
                        distance[e.dest]=srcDistance+weight;
                    }
                }
            }
        }
    }

    public static void MSTUsingPrims(ArrayList<Edge> graph[], boolean visited[],ArrayList <Edge> MSTGraph[]){
        int MSTTotalWeight = 0;
        int src =0;
        PriorityQueue<Edge> pq= new PriorityQueue<>();
       
        for(int i=0;i<graph.length;i++){
            MSTGraph[i]= new ArrayList<>();
        }

        pq.add(new Edge(0, 0, 0));
        while(!pq.isEmpty()){
            Edge node1 = pq.remove();
            if(visited[node1.dest]==false){
                System.out.println("node1 "+node1.src+" "+node1.dest+" "+node1.weight);
                MSTTotalWeight+=node1.weight;
                visited[node1.dest] = true;
                if(node1.dest!=src){
                    MSTGraph[node1.src].add(new Edge(node1.src, node1.dest,node1.weight));
                    MSTGraph[node1.dest].add(new Edge(node1.dest, node1.src,node1.weight));
                }

                for(int i=0;i<graph[node1.dest].size();i++){
                    Edge node2 = graph[node1.dest].get(i);
                    pq.add(new Edge(node2.src, node2.dest, node2.weight));
                }
            }
        }
        System.out.println("MSTTotalWeight "+ MSTTotalWeight);
        
    }
    
    public static void main(String[] args) {
        
        int V=7;
        ArrayList <Edge> graph[]= new ArrayList[V];
        boolean visited[] = new boolean[V];

        // create graph
        createGraph(graph,V);

       // print graph
            // for(int i=0;i<V;i++){
            //     for(int j=0;j<graph[i].size();j++){
            //         Edge e = graph[i].get(j);
            //         System.out.println("SRC "+ e.src+ " DEST "+e.dest);
            //     }
            // }

        //bfs
            // for(int i=0;i<V;i++){
            //     if(visited[i]==false)
            //         bfs(graph, visited,i);
            // }

        //dfs
            // for(int i=0;i<V;i++){
            //     if(visited[i]==false)
            //         dfs(graph, visited,i);
            // }

        //print path from src to target
            // int src=0;
            // int target=5;
            // String path="0";

            // printAllPaths(graph, visited, src, target, path);

        // detect cycle in undirected graph
            // int parent =-1;
            // boolean isCyclicGrpah = false;
            // for(int i=0;i<V;i++){
            // if( visited[i]==false && checkIsCyclicGraphUndirected(graph, visited, i,parent )){
            //     isCyclicGrpah = true;
            //     break;
            // }
            // }
            // System.out.print(isCyclicGrpah);

        // detect cycle in directed graph
            // boolean isCyclicGrpah = false;
            // for(int i=0;i<V;i++){
            // if( visited[i]==false && checkIsCyclicGraphDirtected(graph, visited, i )){
            //     isCyclicGrpah = true;
            //     break;
            // }
            // }
            // System.out.print("isCyclicGrpah "+isCyclicGrpah+" ");

        // topologiocal order print( should be DAG must)
            // if(!isCyclicGrpah){
            //     Stack<Integer> recursion= new Stack<>();
            //     for(int i=0;i<V;i++){
            //         if(visited[i]==false){
            //             topologicalOrder(graph,  visited,i, recursion);
            //         }
            //     }

            // while(!recursion.isEmpty()){
            //     System.out.print(recursion.pop()+" ");
            // }
            // }
        
        // dijikstra Algo
            // int src = 0;
            // int distance[]= new int[V];
            // getShortestPathUsingDijikstra(graph, src, distance, visited);
            // for(int i=0;i<distance.length;i++){
            //      System.out.print(distance[i]+" ");
            // }

        //bellman ford algo
            // int src =0;
            // int distance[]= new int[V];
            // getShortestPathUsingBellman(graph, src, distance);
            // for(int i=0;i<distance.length;i++){
            //      System.out.print(distance[i]+" ");
            // }

        // prims algo MST
            ArrayList <Edge> MSTGraph[]= new ArrayList[V];
            MSTUsingPrims(graph , visited, MSTGraph);
             for(int i=0;i<V;i++){
                for(int j=0;j<MSTGraph[i].size();j++){
                    Edge e = MSTGraph[i].get(j);
                    System.out.println("SRC "+ e.src+ " DEST "+e.dest+" Weight "+e.weight);
                }
            }

        
    } 
}