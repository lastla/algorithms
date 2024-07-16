package foundation.dataStructures.graph;
//边
public class Edge {
    public Vertex linked;//边连接的点
    public int weight;//边的权重

    public Edge(Vertex linked) {
        this(linked, 1);
    }

    public Edge(Vertex linked, int weight) {
        this.linked = linked;
        this.weight = weight;
    }
}
