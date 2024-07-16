package foundation.dataStructures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//广度优先
public class BFS {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        /*v1.edges = new ArrayList<>();
        v1.edges.add(new Edge(v3,9));
        v1.edges.add(new Edge(v2,7));
        v1.edges.add(new Edge(v6,14));*/
        v1.edges = getList(new Edge(v3,9),new Edge(v2,7),new Edge(v6,14));
        v3.edges = getList(new Edge(v4,2),new Edge(v6,11));
        v4.edges = getList(new Edge(v5,6));
        v2.edges = getList(new Edge(v4,15));
        v6.edges = getList(new Edge(v5,9));
        v5.edges = getList();
        bfs(v1);
    }

    public static void bfs(Vertex v){
        LinkedList<Vertex> queue = new LinkedList<>();
        v.visited = true;
        queue.offer(v);
        while (!queue.isEmpty()){
            Vertex pop = queue.pop();
            System.out.println(pop.name);
            for (Edge edge : pop.edges){
                if(!edge.linked.visited){
                    edge.linked.visited = true;
                    queue.offer(edge.linked);
                }
            }
        }
    }






    private static <E>List<E> getList(E... edges){
        List<E> list = new ArrayList<>();
        for (E edge : edges){
            list.add(edge);
        }
        return list;
    }
}
