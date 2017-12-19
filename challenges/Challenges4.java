package week3.challenges;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*Challenge 4 - Path Sum with BFS
Write a program that takes as its input an instance of TreeNode<Integer> representing the root of a BST and a target
int value and returns a boolean value which is true if the tree contains a path between the root and any leaf such that
the sum of all the values of the nodes on the path equal the target value.

Hint: Use a breadth-first search

Example: Given a target value of 22 and a BST with the following structure:

BST:
          5
         / \
        4   8
       /   / \
      11  13  4
     /  \      \
    7    2      1
The path 5→4→11→2 sums to 22, so the output in this case would be true.*/
public class Challenges4 {
    //BFS - queue
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        Queue<TreeNode> nodes = new LinkedList<TreeNode>();
        nodes.offer(root);
        Queue<Integer> sums = new LinkedList<Integer>();
        sums.offer(0);
        while (!nodes.isEmpty()) {
            TreeNode currNode = nodes.poll();
            int currSum = sums.poll();

            currSum += (Integer)currNode.value;

            if (currNode.left == null && currNode.right == null) {
                if (currSum == sum) {
                    return true;
                }
            }
            if (currNode.left != null) {
                nodes.offer(currNode.left);
                sums.offer(currSum);
            }
            if (currNode.right != null) {
                nodes.offer(currNode.right);
                sums.offer(currSum);
            }
        }

        return false;
    }

    //-------------------------------------------------------------------

    public boolean hasPathSum2(TreeNode a, int b) {
        if (a == null) return false;

        b -= (Integer)a.value;
        if (b == 0 && isLeaf(a)) {
            return true;
        }
        boolean left = hasPathSum(a.left, b);
        boolean right = hasPathSum(a.right, b);

        return left || right;
    }
    public boolean isLeaf(TreeNode root){

        return (root.left == null) && (root.right == null);
    }

    public static void main(String args[])
    {

        TreeNode tree = new TreeNode(5);
        tree.left = new TreeNode(4);
        tree.left.left = new TreeNode(11);
        tree.left.left.right = new TreeNode(2);
        tree.left.left.left = new TreeNode(7);

        tree.right = new TreeNode(8);
        tree.right.left = new TreeNode(13);
        tree.right.right = new TreeNode(4);

        tree.right.right.right = new TreeNode(1);

        System.out.println("Is path sum "+new Challenges4().hasPathSum(tree, 22));
    }

    public int hasPathSum1(TreeNode a, int b) {

        List<List<Integer>> list = new ArrayList<List<Integer>>();
        Queue<TreeNode> qNode = new LinkedList<TreeNode>();
        Queue qSum = new LinkedList();
        Queue<List<Integer>> qList = new LinkedList<List<Integer>>();

        if (a == null) return 0;
        qNode.add(a);
        qSum.add(b);
        qList.add(new LinkedList<Integer>());

        while (!qNode.isEmpty()) {
            TreeNode<Integer> curNode = qNode.remove();
            int curSum = (Integer)qSum.remove();
            System.out.println("curSum "+curSum);

            List<Integer> curList = qList.remove();

            curList.add(curNode.value);

            if (curNode.left == null && curNode.right == null && curNode.value == curSum)
                list.add(new LinkedList<Integer>(curList));
            if (curNode.left != null) {
                qNode.add(curNode.left);
                qSum.add(curSum - curNode.value);
                qList.add(new LinkedList<Integer>(curList));
            }
            if (curNode.right != null) {
                qNode.add(curNode.right);
                qSum.add(curSum - curNode.value);
                qList.add(new LinkedList<Integer>(curList));
            }
        }
        return list.size()!=0 ? 1 : 0;
    }
}
