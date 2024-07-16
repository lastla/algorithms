package foundation.dataStructures.graph;

import java.util.*;

//哈夫曼数是一颗满二叉树,只有叶子节点才存字符

/*
        Huffman 树的构建过程

        1. 将统计了出现频率的字符，放入优先级队列

        2. 每次出队两个频次最低的元素，给它俩找个爹
        3. 把爹重新放入队列，重复 2~3
        4. 当队列只剩一个元素时，Huffman 树构建完成
     */
public class HuffmanTree {
    static class Node{
        Character ch;//字符
        int freq;// 频次
        Node left;
        Node right;
        String code;//编码

        public Node(Character ch) {
            this.ch = ch;
        }

        public Node(int freq, Node left, Node right) {
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
        int freq(){
            return  freq;
        }
        boolean isLeaf(){
            return left==null;//满二叉树，只判断一个即可
        }

        @Override
        public String toString() {
            return "Node{"+
                    "ch="+ch+
                    ", freq="+freq+
                    "}";
        }
    }
    String str;
    Node root;
    Map<Character,Node> map = new HashMap<>();
    public HuffmanTree(String str) {
        this.str = str;
        //1.统计词频
        char[] chars = str.toCharArray();
        for (char c : chars) {
            /*if(!map.containsKey(c)){
                map.put(c,new Node(c));
            }
            Node node = map.get(c);
            node.freq++;*/
            Node node = map.computeIfAbsent(c, Node::new);
            node.freq++;
        }
        /*for (Node node : map.values()) {
            System.out.println(node);
        }*/
        //2.构造树
        PriorityQueue<Node> queue = new PriorityQueue<>(
                Comparator.comparingInt(Node::freq)
        );
        queue.addAll(map.values());
        while (queue.size()>=2){
            Node x = queue.poll();
            Node y = queue.poll();
            int freq = x.freq+y.freq;
            queue.offer(new Node(freq,x,y));
        }
        root = queue.poll();
        //System.out.println(root);
        //3.计算每个字符的哈夫曼编码

        int sum = dfs(root,new StringBuilder());
        //功能4：字符串编码占用的bits
        for (Node node : map.values()) {
            System.out.println(node+" "+ node.code);
        }
        System.out.println("占用总bits："+sum);
    }

    private int dfs(Node node,StringBuilder code) {
        int sum = 0;
        if(node.isLeaf()){
            //找到编码
            //System.out.println(root+" "+code);
            node.code = code.toString();
            sum = node.freq * code.length();
        }else{
            sum += dfs(node.left,code.append(0));//向左走 + 0
            sum += dfs(node.right,code.append(1));//向右走 + 1
        }
        if(code.length()>0){
            code.deleteCharAt(code.length()-1);//向回走 - 此次添加的字符
        }
        return sum;

    }

    // 编码
    public String encoded(){
        StringBuilder sb = new StringBuilder();
        char[] chars = str.toCharArray();
        for (char c : chars) {
            Node node = map.get(c);
            sb.append(node.code);
        }
        return sb.toString();
    }

    //解码
    public String decode(String str){
        /*
            从根节点，寻找数字对应的字符
                数字是 0 向左走
                数字是 1 向右走
                如果没走到头，每走一步数字的索引 i++
            走到头就可以找到解码字符，再将 node 重置为根节点
         */
        char[] chars = str.toCharArray();
        Node node = root;
        StringBuilder sb = new StringBuilder();
        int i =0;
        while (i<chars.length){
            if(!node.isLeaf()){//非叶子节点
                if(chars[i]=='0'){
                    node = node.left;
                } else if (chars[i]=='1') {
                    node = node.right;
                }
                i++;
            }
            if(node.isLeaf()){
                sb.append(node.ch);
                node = root;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        HuffmanTree tree = new HuffmanTree("abbccccccc");
        String code = tree.encoded();
        System.out.println(code);
        System.out.println(tree.decode(code));
        Map<Integer, List<int[]>> mapMap = new HashMap<>();
        int[][] a = new int[4][2];
        int[] ints = a[1];
    }
}
