package foundation.dataStructures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//拓扑排序不能执行有环图
public class TopologicalSortDFS {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("网页基础");
        Vertex v2 = new Vertex("Java基础");
        Vertex v3 = new Vertex("JavaWeb");
        Vertex v4 = new Vertex("Spring框架");
        Vertex v5 = new Vertex("微服务框架");
        Vertex v6 = new Vertex("数据库");
        Vertex v7 = new Vertex("实战项目");

        v1.edges = getList(new Edge(v3));
        v2.edges = getList(new Edge(v3));
        v3.edges = getList(new Edge(v4));
        v4.edges = getList(new Edge(v5));
        v5.edges = getList(new Edge(v7));
        v6.edges = getList(new Edge(v4));
        v7.edges = getList();
        //v7.edges = getList(new Edge(v5)); //有环情况

        List<Vertex> graph = getList(v1, v2, v3, v4, v5, v6, v7);

        LinkedList<String> stack = new LinkedList<>();
        //1.计算入度
        for (Vertex v: graph){
            dfs(v,stack);
        }
        System.out.println(stack);

    }
    private static void dfs(Vertex v,LinkedList<String> stack){
        if(v.status==2){
            return;
        }
        if(v.status==1){
            throw new RuntimeException("出现环！");
        }
        v.status=1;
        for(Edge edge: v.edges){
            dfs(edge.linked,stack);
        }
        v.status=2;
        stack.push(v.name);
    }

    private static <E>List<E> getList(E... edges){
        List<E> list = new ArrayList<>();
        for (E edge : edges){
            list.add(edge);
        }
        return list;
    }
}
