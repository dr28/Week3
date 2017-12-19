package week3.challenges;
/*Challenge 3 - Compare Trees
Write a program that takes as its input two instances of TreeNode and returns a boolean value which is true if both
trees have an identical structure with the same node values.*/
public class Challenges3 {
    //Time compellexity is O(m) where m is the no of nodes of the tree with less nodes.
    // if TreeNode a has n nodes and TreeNode b has m nodes and m<n then, time complexity is O(m) else if n<m then O(n)
    public boolean isSameTree(TreeNode a, TreeNode b) {
        if(a == null && b == null)
            return true;
        else if(a == null || b == null)
            return false;

        if(a.value == b.value && isSameTree(a.left, b.left) && isSameTree(a.right, b.right))
            return true;

        return false;
    }

    public static void main(String[] args){
        TreeNode a = new TreeNode(1);
        a.left = new TreeNode(2);
        a.right = new TreeNode(3);

        TreeNode b = new TreeNode(1);
        b.left = new TreeNode(2);
        b.right = new TreeNode(3);

        System.out.println("Identical tree -- "+new Challenges3().isSameTree(a, b));
    }
}
