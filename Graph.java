import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
        // graph[1].add(new Edge(1,0));
        graph[1].add(new Edge(1,3));
        // graph[2].add(new Edge(2,0));
        graph[2].add(new Edge(2,4));
        // graph[3].add(new Edge(3,1));
        // graph[3].add(new Edge(3,4));
        graph[3].add(new Edge(3,5));
          graph[4].add(new Edge(4,3));
        // graph[4].add(new Edge(4,2));
        //  graph[4].add(new Edge(4,5));
        // graph[5].add(new Edge(5,3));
          graph[5].add(new Edge(5,4));
        graph[5].add(new Edge(5,6));
        // graph[6].add(new Edge(6,5));

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
            boolean isCyclicGrpah = false;
            for(int i=0;i<V;i++){
            if( visited[i]==false && checkIsCyclicGraphDirtected(graph, visited, i )){
                isCyclicGrpah = true;
                break;
            }
            }
            System.out.print(isCyclicGrpah);

        
    } 
}