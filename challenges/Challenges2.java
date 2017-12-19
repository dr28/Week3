package week3.challenges;
/*Challenge 2 - Count Leaf Nodes
Write a program that takes as its input an instance of TreeNode representing the root of a tree and returns a int value
representing the number of nodes in the tree with no children.*/
public class Challenges2 {
    int countLeafNodes(TreeNode node) {
        if (node == null)
            return 0;

        if (node.left == null && node.right == null)
            return 1;
        else
            return countLeafNodes(node.left) + countLeafNodes(node.right);

    }

    public static void main(String args[])
    {
        /* Constructed binary tree is
                    1
                 /    \
                2      3
               / \    /
              4   5  6
             /
            7
        */

        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(4);
        tree.left.right = new TreeNode(5);
        tree.right.right = new TreeNode(6);
        tree.left.left.left = new TreeNode(7);

       /* TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);*/

        System.out.println("No of leaf node "+new Challenges2().countLeafNodes(tree));
    }
}
