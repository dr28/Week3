package week3.challenges;
/*Challenge 1 - Identify Binary Search Trees
Write a program that takes as its input an instance of TreeNode representing the root of a tree and returns a boolean
value which is true if the input is a binary search tree and false if it is not.*/

class TreeNode<E> {

    E value;
    TreeNode<E> left, right;

    public TreeNode(E value) {
        this.value = value;
        this.left = right = null;
    }

}
public class Challenges1 {

    // Time complexity is O(n)
    public boolean isValidBST(TreeNode a) {

        return isBSTUtil(a, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    boolean isBSTUtil(TreeNode node, int min, int max) {

        /* an empty tree is BST */
        if (node == null)
            return true;

        if ((Integer)node.value <= min || (Integer)node.value >= max) return false;

        return (isBSTUtil(node.left, min, (Integer)node.value) &&
                isBSTUtil(node.right, (Integer)node.value, max));
    }

    public static void main(String args[])
    {
        /* Constructed binary tree is
                  1
                 / \
                2   3
               / \  /
              4  5 6
             /
            7         */
        /*TreeNode<Integer> tree = new TreeNode<Integer>(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.right.right = new TreeNode(6);
        tree.left.left.left = new TreeNode(7);*/

        /*
         Input :
                 2
                / \
               1   3

        */

        TreeNode tree = new TreeNode(2);
        tree.left = new TreeNode(1);
        tree.right = new TreeNode(3);

        System.out.println("BST? -- "+new Challenges1().isValidBST(tree));
    }
}