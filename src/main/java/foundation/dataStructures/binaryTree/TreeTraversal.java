package foundation.dataStructures.binaryTree;

import java.util.LinkedList;

public class TreeTraversal {
    public static void main(String[] args) {
        /*
                1
               / \
              2   3
             /   / \
            4   5   6
         */

        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4), 2, null),
                1,
                new TreeNode(new TreeNode(5), 3, new TreeNode(6))
        );
        /*System.out.println("-----------------先序遍历------------------");
        preOrder(root);
        System.out.println();
        System.out.println("-----------------中序遍历------------------");
        inOrder(root);
        System.out.println();
        System.out.println("-----------------后序遍历------------------");
        postOrder(root);
        */
        //循环实现
       // preOrd(root);
       // inOrd(root);
        //postOrd(root);
        hybridOrder(root);
    }

    //递归前序遍历
    static void preOrder(TreeNode node){
        if(node==null){
            return;
        }

        System.out.print(node.val+"\t");
        preOrder(node.left);
        preOrder(node.right);

    }
    //递归中序遍历
    static void inOrder(TreeNode node){
        if(node==null){
            return;
        }

        inOrder(node.left);
        System.out.print(node.val+"\t");
        inOrder(node.right);
    }
    //递归后序遍历
    static void postOrder(TreeNode node){
        if(node==null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.val+"\t");
    }
    //循环先序
    static void preOrd(TreeNode root){
        TreeNode curr = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (curr!=null || !stack.isEmpty()){
            if(curr!=null){
                System.out.print(curr.val+"\t");
                //处理左子数
                stack.push(curr);
                curr=curr.left;
            }else{

                TreeNode poll = stack.poll();
                //处理右子树
                curr = poll.right;
            }
        }
    }
    //循环中序
    static void inOrd(TreeNode root){
        TreeNode curr = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (curr!=null || !stack.isEmpty()){
            if(curr!=null){

                //处理左子数
                stack.push(curr);
                curr=curr.left;
            }else{

                TreeNode poll = stack.poll();
                System.out.print(poll.val+"\t");
                //处理右子树
                curr = poll.right;
            }
        }
    }
    //循环后序
    //记录上一个弹栈的元素，将它与栈顶元素的右孩子比较，如果相等则说明右孩子处理完毕
    static void postOrd(TreeNode root){
        TreeNode curr = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode poll = null;//用来记录上栈的元素
        while (curr!=null || !stack.isEmpty()){
            if(curr!=null){
                stack.push(curr);
                curr = curr.left;
            }else{
                TreeNode peek = stack.peek();
                if(peek.right==null || peek.right==poll){
                    poll = stack.poll();
                    System.out.print(poll.val+"\t");
                }else{
                    curr = peek.right;
                }
            }
        }
    }
    //组合前中后（循环）

    static void hybridOrder(TreeNode root){
        TreeNode curr = root;
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode poll = null;//最近一次弹栈的元素
        while (curr!=null || !stack.isEmpty()){
            if(curr!=null){
                System.out.print("前："+curr.val+"\t");
                //处理左子数   先序遍历:  值左右
                stack.push(curr);
                curr = curr.left;
            }else{
                //右子树可以先不处理，中序遍历先获取值再处理右
                TreeNode peek = stack.peek();
                if(peek.right==null){
                    System.out.print("中："+peek.val+"\t");
                    poll = stack.poll();
                    System.out.print("后："+poll.val+"\t");
                    //右子树处理完成，对于中序遍历无需打印
                } else if (peek.right==poll) {
                    poll = stack.poll();
                    System.out.print("后："+poll.val+"\t");
                    // 右子树待处理, 对中序来说, 要在右子树处理之前打印
                } else{
                    System.out.print("中："+peek.val+"\t");
                    curr = peek.right;
                }
            }
        }
    }
}
