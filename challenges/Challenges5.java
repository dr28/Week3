package week3.challenges;

import java.util.*;

/*Challenge 5 - Path Sums with DFS
Write a program that takes as its input an instance of TreeNode<Integer> representing the root of a BST and a target
int value and returns an int[][] containing the node values of all paths between the root and any leaf such that the
sum of all the values of the nodes on the path equal the target value.

Hint: Use a depth-first search

Example: Given a target value of 22 and a BST with the following structure:

BST:
          5
         / \
        4   8
       /   / \
      11  13  4
     /  \    / \
    7    2  5   1
There exist two paths from root to leaf that sum to 22: 5→4→11→2 and 5→8→4→5, so the output in this case would be an array of two arrays
containing those values:

int[][] output = {
  { 5, 4, 11, 2 },
  { 5, 8, 4, 5 }
};*/
public class Challenges5 {
    //DFS - Stack
    public List<ArrayList<Integer>> pathSum1(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(root == null)
            return result;

        ArrayList<Integer> l = new ArrayList<Integer>();
        l.add((Integer)root.value);
        dfs(root, sum-(Integer)root.value, result, l);
        return result;
    }

    public void dfs(TreeNode t, int sum, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> l){
        if(t.left==null && t.right==null && sum==0){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.addAll(l);
            result.add(temp);
        }

        //search path of left node
        if(t.left != null){
            l.add((Integer)t.left.value);
            dfs(t.left, sum-(Integer)t.left.value, result, l);
            l.remove(l.size()-1);
        }

        //search path of right node
        if(t.right!=null){
            l.add((Integer)t.right.value);
            dfs(t.right, sum-(Integer)t.right.value, result, l);
            l.remove(l.size()-1);
        }
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

        tree.right.right.left = new TreeNode(5);
        tree.right.right.right = new TreeNode(1);

        System.out.println("Path Sums with DFS -- "+new Challenges5().pathSum1(tree, 22));
        Object [] result = new Challenges5().pathSumArr(tree, 22);

        for(int i=0; i<result.length; i++) {
            Object [] temp = (Object [])result[i];
            for(int j=0; j<temp.length; j++) {
                System.out.print(temp[j]+" ");
            }
            System.out.println("");

        }
    }

    private List<List<Integer>> resultArray = new ArrayList<List<Integer>>();

    public void pathSumInnerArr(TreeNode root, int sum, Stack<Integer> path) {
        path.push((Integer)root.value);
        if(root.left == null && root.right == null)
            if(sum == (Integer)root.value)
                resultArray.add(new ArrayList<Integer>(path));

        if(root.left!=null)
            pathSumInnerArr(root.left, sum-(Integer)root.value, path);

        if(root.right!=null)
            pathSumInnerArr(root.right, sum-(Integer)root.value, path);

        path.pop();
    }

    public Object[] pathSumArr(TreeNode root, int sum) {
        if(root==null) return null;
        Stack<Integer> path = new Stack<Integer>();
        pathSumInnerArr(root, sum, path);

        // At this point resultArray has all values
        // Convert to array
        Object [] result = new Object[resultArray.size()];

        for(int i=0; i<resultArray.size(); i++) {
            Object [] temp = resultArray.get(i).toArray();
            result[i] = temp;
        }

        return result;
    }
}
