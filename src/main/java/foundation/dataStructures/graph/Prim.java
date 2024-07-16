package foundation.dataStructures.graph;

import java.util.ArrayList;
import java.util.List;

public class Prim {

    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        Vertex v7 = new Vertex("v7");

        v1.edges = getList(new Edge(v2, 2), new Edge(v3, 4), new Edge(v4, 1));
        v2.edges = getList(new Edge(v1, 2), new Edge(v4, 3), new Edge(v5, 10));
        v3.edges = getList(new Edge(v1, 4), new Edge(v4, 2), new Edge(v6, 5));
        v4.edges = getList(new Edge(v1, 1), new Edge(v2, 3), new Edge(v3, 2),
                     new Edge(v5, 7), new Edge(v6, 8), new Edge(v7, 4));
        v5.edges = getList(new Edge(v2, 10), new Edge(v4, 7), new Edge(v7, 6));
        v6.edges = getList(new Edge(v3, 5), new Edge(v4, 8), new Edge(v7, 1));
        v7.edges = getList(new Edge(v4, 4), new Edge(v5, 6), new Edge(v6, 1));

        List<Vertex> graph = getList(v1, v2, v3, v4, v5, v6, v7);

        prim(graph, v1);

    }
    static void prim(List<Vertex> graph, Vertex source) {
        ArrayList<Vertex> list = new ArrayList<>(graph);//未处理节点
        source.dist=0;
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
                int dist = edge.weight;
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




    private static <E> List<E> getList(E... edges){
        List<E> list = new ArrayList<>();
        for (E edge : edges){
            list.add(edge);
        }
        return list;
    }
}
