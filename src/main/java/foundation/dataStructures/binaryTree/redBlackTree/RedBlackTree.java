package foundation.dataStructures.binaryTree.redBlackTree;



import static foundation.dataStructures.binaryTree.redBlackTree.RedBlackTree.Color.BLACK;
import static foundation.dataStructures.binaryTree.redBlackTree.RedBlackTree.Color.RED;

public class RedBlackTree {

     enum Color {
        RED,BLACK;
    }
    private Node root;
    private static class Node{
        int key;
        Object value;
        Node left;
        Node right;
        Node parent; //父节点
        Color color = RED;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        //是否是左孩子
        boolean isLeftChild(){
            return parent !=null && parent.left ==this;
        }
        //叔叔
        Node uncle(){
            if(parent==null || parent.parent==null){
                return null;
            }

            if (parent.isLeftChild()) {
                return parent.parent.right;
            } else {
                return parent.parent.left;
            }
        }
        //兄弟
        Node sibling() {
            if (parent == null) {
                return null;
            }
            if (this.isLeftChild()) {
                return parent.right;
            } else {
                return parent.left;
            }
        }


    }

    //是否是红色
    boolean isRed(Node node){
        return node!=null && node.color==RED;
    }
    //是否是黑色
    boolean isBlack(Node node){
        //return !isRed(node);
        return node==null || node.color==BLACK;
    }

    /**
     * 右旋 1. parent的处理 2.旋转后新根的父子关系
     * @param pink 失衡节点
     *        yellow 失衡节点的左孩子
     *        green yellow 节点的右孩子
     */
    private void rightRotate(Node pink){
        Node parent = pink.parent; //失衡节点的父亲节点 当pink为root时等于null
        Node yellow = pink.left;
        Node green = yellow.right;
        if(green!=null){
            green.parent = pink;
        }
        yellow.right = pink;
        yellow.parent = parent;
        pink.left = green;
        pink.parent = yellow;
        if(parent==null){
            root = yellow;
        }else if(parent.left==pink){
            parent.left = yellow;
        }else{
            parent.right = yellow;
        }

    }
    /**
     * 左旋 1. parent的处理 2.旋转后新根的父子关系
     * @param pink 失衡节点
     *        yellow 失衡节点的左孩子
     *        green yellow 节点的右孩子
     *             调整6个量
     */
    private void leftRotate(Node pink){
        Node parent = pink.parent;
        Node yellow = pink.right;
        Node green = yellow.left;
        if(green!=null){
            green.parent = pink;
        }
        yellow.left = pink;
        yellow.parent = parent;
        pink.right = green;
        pink.parent = yellow;
        if(parent==null){
            root = yellow;
        }else if(parent.left==pink){
            parent.left = yellow;
        }else{
            parent.right = yellow;
        }

    }

    /**
     * 新增或更新
     * 正常插入，遇到红红不平衡进行调整
     * @param key
     * @param value
     */
    public void put(int key,Object value){
        Node p = root;
        Node parent = null;
        while(p!=null){
            parent = p;
            if(key<p.key){
                p=p.left;
            }else if(p.key<key){
                p=p.right;
            }else{
                p.value=value;
                return;
            }
        }
        Node inserted = new Node(key, value);
        if(parent==null){
            root = inserted;

        }else if(key<parent.key){
            parent.left = inserted;
            inserted.parent = parent;
        }else{
            parent.right=inserted;
            inserted.parent = parent;
        }
        fixRedRed(inserted);

    }

    /**
     * 修复红红
     *
     * @param x
     */
    private void fixRedRed(Node x){
        //case1 插入节点为根节点将插入节点变黑
        if(x==root){
            x.color = BLACK;
            return;
        }
        //case2 插入节点的父亲节点为黑色，不会破坏红黑树平衡，直接返回
        if(isBlack(x.parent)){
            return;
        }
        /**
         * case3 插入节点的父亲节点为红色且叔叔节点为也为红色（通过变色即可解决平衡）
         *  将父亲节点和叔叔节点变黑，祖先节点变红，此时祖先节点也可能变遇到红红，将祖先节点递归修复即可
         */

        Node parent = x.parent;
        Node uncle = x.uncle();
        Node grandparent = parent.parent;
        if(isRed(uncle)){
            parent.color = BLACK;
            uncle.color = BLACK;
            grandparent.color = RED;
            fixRedRed(grandparent);
            return;
        }
        /**
         * case4 红红相邻且叔叔为黑色
         * 通过 LL LR RL RR 旋转调整平衡
         */
         //LL
        if(x.isLeftChild() && parent.isLeftChild()){
            parent.color = BLACK;
            grandparent.color = RED;
            rightRotate(grandparent);
        }else if(parent.isLeftChild()){//LR
            //先让parent进行一次左旋，变成LL的情况再让grandparent执行一次右旋即可
            leftRotate(parent);
            parent.color = BLACK;
            grandparent.color=RED;
            rightRotate(grandparent);
        }else if(!x.isLeftChild()){ //RR
            parent.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }else{ //RL
            rightRotate(parent);
            parent.color = BLACK;
            grandparent.color = RED;
            leftRotate(grandparent);
        }
    }

    /**
     * 删除
     * 正常删、会用到李代桃僵技巧、遇到黑黑不平衡进行调整
     * @param key
     */
    public void remove(int key){
        Node deleted = find(key);
        if(deleted==null){
            return;
        }
        doRemove(deleted);
    }
    //红色节点的特性：红色节点不能有红色的孩子，红色节点如果有孩子则必须有两个黑孩子否则不平衡
    private void fixDoubleBlack(Node x){
        if(x==root){
            return;
        }
        Node parent = x.parent;
        Node sibling = x.sibling();
        if(isRed(sibling)){//case3:被调整节点的兄弟节点为红色，此时两个侄子一定为黑 此情况可调整为===》case4 或 case5
            if(x.isLeftChild()){
                leftRotate(parent);
            }else{
                rightRotate(parent);
            }
            sibling.color = RED;
            parent.color = BLACK;
            fixDoubleBlack(x);//再次递归调用进入case4 or case5
            return;
        }
        /**
         * 双黑意为少一个黑
         * case4: 被调整节点的兄弟节点为黑色 且两侄子都为黑色
         *  1.将兄弟变红，目的是将删除节点和兄弟节点那边的树的黑色高度同时减一
         *  2.如果父亲是红，将父亲变黑，避免出现红红，此时路径黑色节点数目不变
         *  3.如果父亲为黑色，说明这条路径还少黑色，再次让父亲节点触发双黑
         */
        if(sibling!=null){
            if(isBlack(sibling.left) && isBlack(sibling.right)){
                sibling.color=RED;
                if(isRed(parent)){
                    parent.color=RED;
                }else{
                    fixDoubleBlack(parent);
                }
            }else{//case5 兄弟是黑色，侄子有红色
                //LL
                if(sibling.isLeftChild() && isRed(sibling.left)){
                    rightRotate(parent);
                    sibling.left.color=BLACK;
                    sibling.color = parent.color;
                   // parent.color = BLACK;
                }else if(sibling.isLeftChild() && isRed(sibling.right)){  //LR
                    sibling.right.color = parent.color;
                    leftRotate(sibling);
                    rightRotate(parent);
                   // parent.color=BLACK;
                }else if (!sibling.isLeftChild() && isRed(sibling.left)) {// RL
                    sibling.left.color = parent.color;
                    rightRotate(sibling);
                    leftRotate(parent);
                }
                else {// RR
                    leftRotate(parent);
                    sibling.right.color = BLACK;
                    sibling.color = parent.color;
                }

                parent.color=BLACK;
            }
        }else{
            fixDoubleBlack(parent);
        }

    }
    private void doRemove(Node deleted){
        Node replaced = findReplaced(deleted);
        Node parent = deleted.parent;
        //待删除节点没有孩子
        if(replaced==null){
            //case1 删除节点为根节点且没有孩子
            if(deleted==root){
                root=null;
            }else{// 删除节点不是根节点且无孩子
                //case2:删黑色会失衡，删红色不会失衡
                if(isBlack(deleted)){
                    //复杂调整
                    fixDoubleBlack(deleted);
                }else{
                    //红色叶子无需任何处理
                }
                if(deleted.isLeftChild()){
                    parent.left=null;
                }else{
                    parent.right=null;
                }
                deleted.parent=null; //help GC
            }
            return;
        }
        //待删除节点有一个孩子
        if(deleted.left==null || deleted.right==null){
            //case1：删除节点为根节点且有一个孩子
            if(deleted==root){
                deleted.key = replaced.key;
                deleted.value = replaced.value;
                root.left=root.right=null;
            }else{// 删除节点不是根节点且有一个孩子
                if(deleted.isLeftChild()){
                    parent.left = replaced;
                }else{
                    parent.right = replaced;
                }
                replaced.parent=parent;
                deleted.left=deleted.right=deleted.parent=null;//help GC
                //case2:删黑色会失衡，删红色不会失衡，
                if(isBlack(deleted) && isBlack(replaced)){
                    //2.删的是黑剩下的也是黑，复杂处理
                    fixDoubleBlack(replaced);
                }else{//1.删的是黑，剩下的是红，只需将红变黑
                    replaced.color = BLACK;
                }
            }
            return;
        }

        //case0：待删除节点有两个孩子,将待删除节点和后继节点交换key和value，删除后继节点====》即可转换为删一个孩子，没有孩子的情况
        int t = deleted.key;
        deleted.key = replaced.key;
        replaced.key = t;

        Object v = deleted.value;
        deleted.value = replaced.value;
        replaced.value = v;
        doRemove(replaced);
    }

    // 查找删除节点
    private Node find(int key){
        Node p = root;
        while(p!=null){
            if(key<p.key){
                p=p.left;
            }else if(p.key<key){
                p=p.right;
            }else{
                return p;
            }
        }
        return null;
    }

    // 查找剩余节点
    Node findReplaced(Node deleted){
        if(deleted.left==null && deleted.right==null){
            return null;
        }
        if(deleted.left==null){
            return deleted.right;
        }
        if(deleted.right==null){
            return deleted.left;
        }
        Node s = deleted.right;
        while(s.left!=null){
            s=s.left;
        }
        return s;
    }

}
