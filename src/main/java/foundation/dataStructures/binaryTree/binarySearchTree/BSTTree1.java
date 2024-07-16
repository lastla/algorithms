package foundation.dataStructures.binaryTree.binarySearchTree;

import foundation.dataStructures.binaryTree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BSTTree1 { BSTNode root;


    static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
    //根据键查找值
    public Object get(int key) {

        return doGet(root,key);
    }
    //递归实现
    private Object doGet(BSTNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            return doGet(node.left, key);
        } else if (key > node.key) {
            return doGet(node.right, key);
        } else {
            return node.value;
        }

    }
    //遍历实现
    public Object getForLoop(int key){
        BSTNode node = root;
        while (node!=null){
            if(key<node.key){
                node=node.left;
            }else if(key> node.key){
                node = node.right;
            }else{
                return node.value;
            }
        }
        return null;
    }
    //找最小值
    public Object min(){

        //return doMin(root);
        return minForLoop(root);
    }
    //递归
    private Object doMin(BSTNode node){
        if(node==null){
            return null;
        }
        if(node.left==null){
            return node.value;
        }
       return doMin(node.left);

    }
    //遍历
    private Object minForLoop(BSTNode node){
        if(node==null){
            return null;
        }
        while (node.left!=null){
            node = node.left;
        }
        return node.value;
    }
    //寻找最大
    public Object max(){
       return max(root);
    }

    public Object max(BSTNode node){
        if(node==null){
            return null;
        }
        BSTNode p = node;
        while (p.right!=null){
            p = p.right;
        }
        return p.value;
    }

    //新增节点
    public void put(int key,Object value){

        BSTNode node = root;
        BSTNode parent=null;
        while (node!=null){
            parent = node;
            if(key<node.key){
                node=node.left;
            }else if(key> node.key){
                node = node.right;
            }else{
                //key 有 更新
                node.value = value;
                return;
            }
        }
        if(parent==null){
            root = new BSTNode(key, value);
            return;
        }
        //key 没有，新增
        BSTNode bstNode = new BSTNode(key, value);
        if(key< parent.key){
            parent.left = bstNode;
        }else{
            parent.right = bstNode;
        }
    }



    /**
     * 查找k的前任值
     * 1.节点有左子树，此时前驱节点就是左子树的最大值
     * 2.节点没有左子树，若离它最近的祖先自从左而来，此祖先即为前驱
     * @param key
     * @return
     */
    public Object predecessor(int key){
        BSTNode p = root;
        BSTNode ancestorFromLeft = null;//记录自左而来的祖先节点
        while (p!=null){
            if(key<p.key){
                p = p.left;
            }else if(p.key<key){
                ancestorFromLeft = p;
                p = p.right;
            }else {
                break;
            }
        }
        if(p==null){
            return null;
        }
        if(p.left!=null){
            return max(p.right);//找左子树的最大值
        }

        return ancestorFromLeft != null ? ancestorFromLeft.value : null;
    }

    /**
     * 找后任节点值
     * 1.节点有右子树，此时后继节点即为右子树的最小值
     * 2.节点没有右子树，若离它最近的祖先自从右而来，此祖先即为后继
     * @param key
     * @return
     */
    public Object successor(int key){
        BSTNode p = root;
        BSTNode ancestorFromRight = null;
        while (p!=null){
            if(key<p.key){
                ancestorFromRight = p;
                p = p.left;
            }else if(p.key<key){

                p = p.right;
            }else {
                break;
            }
        }
        if(p==null){
            return null;
        }
        if(p.left!=null){
            return minForLoop(p.left);//找左子树的最大值
        }

        return ancestorFromRight != null ? ancestorFromRight.value : null;
    }
    /**
     * 要删除某节点（称为 D），必须先找到被删除节点的父节点，这里称为 Parent
     *
     * 1. 删除节点没有左孩子，将右孩子托孤给 Parent
     * 2. 删除节点没有右孩子，将左孩子托孤给 Parent
     * 3. 删除节点左右孩子都没有，已经被涵盖在情况1、情况2 当中，把 null 托孤给 Parent
     * 4. 删除节点左右孩子都有，可以将它的后继节点（称为 S）托孤给 Parent，设 S 的父亲为 SP，又分两种情况
     *    1. SP 就是被删除节点，此时 D 与 S 紧邻，只需将 S 托孤给 Parent
     *    2. SP 不是被删除节点，此时 D 与 S 不相邻，此时需要将 S 的后代托孤给 SP，再将 S 托孤给 Parent
     */

   /* public Object delete(int key){
        BSTNode p = root;
        BSTNode parent = null;
        while (p!=null){
            if(key<p.key){
                parent = p;
                p = p.left;
            }else if(p.key<key){
                parent = p;
                p = p.right;
            }else {
                break;
            }
        }
        if(p==null){
            return null;
        }
        if(p.right==null){

            shift(parent,p,p.left);
        }else if( p.left==null){
            shift(parent,p,p.right);
        }else{
            //4.被删除节点有两个孩子

            //4.1被删除节点找后继
            BSTNode s = p.right;
            BSTNode sParent = p;
            while (s.left!=null){
                sParent = s;
                s =s.left;//后继节点即为s
            }
            if(sParent!=p){//4.2处理后继节点的后事，后继节点与根节点不相邻
                shift(sParent,s,s.right);//后继节点不可能有左孩子
                s.right = p.right;
            }

            //4.3后继节点取代被删除节点
            shift(parent,p,s);
            s.left = p.left;
        }
        return p.value;

    }
    //托孤

    private void shift(BSTNode parent,BSTNode deleted,BSTNode child){
        if(parent==null){
            root=child;
        }else if(deleted==parent.left){
            parent.left = child;
        }else{
            parent.right =child;
        }
    }

    //递归
    private BSTNode doDelete(BSTNode node, int key, List<Object> res){
        if(node==null){
            return null;
        }
        if(key<node.key){
             node.left = doDelete(node.left,key,res);
             return node;
        }
        if(node.key<key){
            node.right = doDelete(node.right,key,res);
            return node;
        }
        res.add(node.value);
        //只有一个孩子的情况
        if(node.left==null){
            return node.right;
        }
        if(node.right==null){
            return node.left;
        }
        //两个孩子都有的情况
        BSTNode s = node.right;
        while (s.left!=null){
            s = s.left;
        }

        s.right = doDelete(node.right,s.key,res);
        s.left = node.left;
        return s;
    }*/

    public Object delete(int key){
        BSTNode p = root;
        BSTNode parent = null;
        while (p!=null){
            if(key<p.key){
                parent = p;
                p = p.left;
            }else if(p.key<key){
                parent = p;
                p = p.right;
            }else{
                break;
            }
        }
        if(p==null){
            return null;
        }

        if(p.right==null){
            shift(parent,p,p.left);
        }else if(p.left==null){
            shift(parent,p,p.right);
        }else {
            BSTNode s = p.right;
            BSTNode sParent = p;
            while (s.left!=null){
                sParent = s;
                s=s.left;
            }
            if(sParent!=p){
                shift(sParent,s,s.right);
                s.right = p.right;
            }
            shift(parent,p,s);
            s.left = p.left;
        }
        return p.value;
    }

    //托孤
    private void shift(BSTNode parent,BSTNode deleted,BSTNode child){
        if(parent==null){
            root=child;
        }else if(parent.left ==deleted){
            parent.left = child;
        }else{
            parent.right = child;
        }
    }

    public BSTNode doDelete(BSTNode node,int key,List<Object> result){
        if(node==null){
            return null;
        }
        if(key<node.key){
            node.left = doDelete(node.left,key,result);
            return node;
        }
        if(node.key<key){
            node.right = doDelete(node.right,key,result);
            return node;
        }
        result.add(node.value);
    
        if(node.left==null){
            return node.right;
        }
        if(node.right==null){
            return node.left;
        }

        BSTNode s = node.right;
        while (s.left!=null){
            s = s.left;
        }

        s.right = doDelete(node.right,s.key,result);
        s.left = node.left;

        return s;
    }
}
