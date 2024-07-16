package foundation.dataStructures.graph;

import java.util.ArrayList;
import java.util.List;

//可处理负边  每次遍历所有边修改节点对应的距离
public class BellmanFord {
    public static void main(String[] args) {
        // 正常情况
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges = getList(new Edge(v3, 9), new Edge(v2, 7), new Edge(v6, 14));
        v2.edges = getList(new Edge(v4, 15));
        v3.edges = getList(new Edge(v4, 11), new Edge(v6, 2));
        v4.edges = getList(new Edge(v5, 6));
        v5.edges = getList();
        v6.edges = getList(new Edge(v5, 9));

        List<Vertex> graph = getList(v4, v5, v6, v1, v2, v3);

        // 负边情况
        /*Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = getList(new Edge(v2, 2), new Edge(v3, 1));
        v2.edges = getList(new Edge(v3, -2));
        v3.edges = getList(new Edge(v4, 1));
        v4.edges = getList();
        List<Vertex> graph = getList(v1, v2, v3, v4);*/

        // 负环情况
        /*Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = getList(new Edge(v2, 2));
        v2.edges = getList(new Edge(v3, -4));
        v3.edges = getList(new Edge(v4, 1), new Edge(v1, 1));
        v4.edges = getList();
        List<Vertex> graph = getList(v1, v2, v3, v4);*/

        bellmanFord(graph,v1);
        for (Vertex v : graph) {
            System.out.println(v);
        }
    }

    private static void bellmanFord(List<Vertex> graph, Vertex source) {
        source.dist = 0;
        int size = graph.size();
        //本来处理边为size-1，这里多循环一次用来检测是否有负环情况
        for (int i = 0; i < size; i++) {
            for (Vertex start : graph) {
                for (Edge edge : start.edges) {
                    Vertex end = edge.linked;
                    int dist = start.dist + edge.weight;
                    if(start.dist!=Integer.MAX_VALUE && dist<end.dist){
                        if(i==size-1){
                            throw new RuntimeException("出现负环");
                        }
                        end.dist = dist;
                        end.prev = start;
                    }
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
