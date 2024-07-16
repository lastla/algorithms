package foundation.dataStructures.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//拓扑排序不能执行有环图
public class TopologicalSort {
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
        //1.计算入度
        for (Vertex v: graph){
            for (Edge edge : v.edges){
                edge.linked.inDegree++;
            }
        }
        /*for (Vertex vertex : graph) {
            System.out.println(vertex.name + " " + vertex.inDegree);
        }*/

        LinkedList<Vertex> queue = new LinkedList<>();
        //2.将入度为0的顶点加入到队列中
        for (Vertex v : graph) {
            if(v.inDegree==0){
                queue.offer(v);
            }
        }
        List<String> result = new ArrayList<>();
        // 3. 队列中不断移除顶点，每移除一个顶点，把它相邻顶点入度减1，若减到0则入队
        while (!queue.isEmpty()){
            Vertex poll = queue.poll();
            //System.out.println(poll.name);
            result.add(poll.name);
            for (Edge edge : poll.edges){
                edge.linked.inDegree--;
                if(edge.linked.inDegree==0){
                    queue.offer(edge.linked);
                }
            }
        }
        //判断是否有环
        if(result.size()!= graph.size()){
            System.out.println("出现环！");
        }else{
            System.out.println(result);
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
