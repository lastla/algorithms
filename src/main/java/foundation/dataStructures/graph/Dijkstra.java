package foundation.dataStructures.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <h3>迪克斯特拉 单源最短路径算法</h3>
 */
public class Dijkstra {
    public static void main(String[] args) {
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

        List<Vertex> graph = getList(v1, v2, v3, v4, v5, v6);

        dijkstra(graph,v1);
    }

    private static void dijkstra(List<Vertex> graph, Vertex v1) {
        ArrayList<Vertex> list = new ArrayList<>(graph);//未处理节点
        v1.dist=0;
        while (!list.isEmpty()){
            //获取列表中距离最小的节点
            Vertex curr =  chooseMinDistVertex(list);
            //修改最小节点的邻居节点的距离
                updateNeighboursDist(curr);//邻居节点必须为未处理节点才需修改
            //移除节点
            list.remove(curr);
            curr.visited=true;
        }

        for (Vertex v : graph) {
            System.out.println(v);
        }
    }

    private static void updateNeighboursDist(Vertex curr) {

        for (Edge edge : curr.edges) {
            Vertex n = edge.linked;
            if(!n.visited){
                int dist = curr.dist + edge.weight;
                if(dist<n.dist){
                    n.dist=dist;
                    n.prev = curr;
                }
            }
        }
    }

    private static Vertex chooseMinDistVertex(ArrayList<Vertex> list) {
        Vertex min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if(list.get(i).dist< min.dist){
                min = list.get(i);
            }
        }
        return min;
    }


    private static <E>List<E> getList(E... edges){
        List<E> list = new ArrayList<>();
        for (E edge : edges){
            list.add(edge);
        }
        return list;
    }
}
