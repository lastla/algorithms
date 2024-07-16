package foundation.dataStructures.binaryTree.avlTree;

public class AVLTree {
    private AVLNode root;
    static class AVLNode{
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height = 1;

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    private int height(AVLNode node){
        return node==null ? 0:node.height;
    }

    private void updateHeight(AVLNode node){
        node.height = Integer.max(height(node.left),height(node.right)) +1;
    }
    //平衡因子（balance factor）= 左子树 - 右子树 -1 0 1 为平衡 大于1 左子树太高 <-1右子树太高
    private int bf(AVLNode node){
        return height(node.left) - height(node.right);
    }

    /**
     * 右旋
     * @param red 失衡节点
     * @return 返回值新的根节点
     */
    private AVLNode rightRotate(AVLNode red){
        AVLNode yellow = red.left;
        AVLNode green = yellow.right;

        yellow.right = red;  //上位
        red.left = green;   //  换爹
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }

    /**
     *左旋
     * @param red 失衡节点
     * @return 返回值新的根节点
     */
    private AVLNode leftRotate(AVLNode red){
        AVLNode yellow = red.right;
        AVLNode green = yellow.left;
        yellow.left = red;//上位
        red.right = green;//换爹
        updateHeight(red);
        updateHeight(yellow);
        return  yellow;
    }

    /**
     * LL
     *  失衡节点的 bf > 1，即左边更高
     *  失衡节点的左孩子的 bf >= 0 即左孩子这边也是左边更高或等高
     *  解决：失衡节点右旋
     */

    /**
     * LR
     *  失衡节点的 bf > 1，即左边更高
     *  失衡节点的左孩子的 bf < 0 即左孩子这边是右边更高
     *  解决：失衡节点的左孩子先左旋 ，失衡节点再右旋
     */
    private AVLNode leftRightRotate(AVLNode node){
        node.left = leftRotate(node.left);

        return rightRotate(node);
    }

    /**
     * RL
     * 失衡节点的 bf <-1，即右边更高
     * 失衡节点的右孩子的 bf > 0，即右孩子这边左边更高
     * 解决：失衡节点的的左孩子先右旋，失衡节点再左旋
     */

    private AVLNode rightLeftRotate(AVLNode node){
        node.right =  rightRotate(node.right);
        return leftRotate(node);
    }

    /**
     * RR
     *   失衡节点（图中 3）的 bf <-1，即右边更高
     *  失衡节点的右孩子（图中 6 红色）的 bf <= 0，即右孩子这边右边更高或等高
     *  解决：失衡节点左旋
     */

    //检查树是否平衡，不平衡则进行处理
    private AVLNode balance(AVLNode node){
        if(node==null){
            return null;
        }
        int bf = bf(node);
        if(bf>1 && bf(node.left) >=0){ //LL
            return rightLeftRotate(node);

        }else if(bf >1 && bf(node.left) <0){ //LR
            return leftRightRotate(node);
        }else if(bf<-1 && bf(node.left) > 0){ //RL
            return rightLeftRotate(node);
        }else if(bf <-1 && bf(node)<=0){ //RR
            return leftRotate(node);
        }

        return node;
    }

    public void put(int key,Object value){
        root = doPut(root,key,value);
    }

    private AVLNode doPut(AVLNode node,int key,Object value){
        if(node==null){
            return new AVLNode(key,value);
        }
        if(key==node.key){
            node.value = value;
            return node;
        }
        if(key<node.key){
            node.left = doPut(node.left,key,value);

        }else{
            node.right = doPut(node.right,key,value);
        }
        updateHeight(node);
        return balance(node);

    }
    
    public void remove(int key){
        root = doRemove(root,key);
    }
    
    private AVLNode doRemove(AVLNode node,int key){
        if(node==null){
            return null;
        }
        if(key<node.key){ //向左
            node = doRemove(node.left,key);
        }else if(node.key<key){ //向右
            node = doRemove(node.right,key);
        }else{ //找到节点
            if(node.left==null && node.right==null){//没有左右孩子
                return null;
            } else if (node.left==null) {//左孩子为空
                node = node.right;
            } else if (node.right==null) {//右孩子为空
                node = node.left;
            }else{
                AVLNode s = node.right;
                while (s.left!=null){
                    s=s.left;
                }
                s.right = doRemove(node.right,s.key);
                s.left = node.left;
                node =s;
            }
        }
        updateHeight(node);
       return balance(node);
    }
}
