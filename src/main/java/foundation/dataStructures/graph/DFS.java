package foundation.dataStructures.graph;
//深度优先搜索算法

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DFS {
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
        //dfs(v1);
        dfs2(v1);

    }

    //递归实现
    public static void dfs(Vertex v){
        v.visited = true;
        System.out.println(v.name);
        for(Edge edge : v.edges){
            if(!edge.linked.visited){
                dfs(edge.linked);
            }
        }
    }

    //非递归实现
    public static void dfs2(Vertex v){
        LinkedList<Vertex> stack = new LinkedList<>();
        stack.push(v);
        while (!stack.isEmpty()){
            Vertex pop = stack.pop();
            pop.visited = true;
            System.out.println(pop.name);
            for (Edge edge : pop.edges){
                if(!edge.linked.visited){
                    stack.push(edge.linked);
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
