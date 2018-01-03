package week3.intq;

import java.util.*;
import java.util.function.UnaryOperator;

/*Interview Question 2 - Sort tree by columns
Write an algorithm which takes as its input a binary tree of chars and outputs a singly-linked list of its values ordered by column first and
row second.

The root node is in row 0
Any node that is a child of another node will be in the row immediately following that of its parent
Any node that is a left child will be in the column immediately preceding that of its parent
Any node that is a right child will be in the column immediately following that of its parent
Any node that is a left child with a parent that is a right child will be in the same column as its grandparent
Any node that is a right child with a parent that is a left child will be in the same column as its grandparent
If two nodes share the same row and column, the node whose parent is a left child comes first
Example:

Input tree:
          5
         / \
        4   8
       /   / \
      9   6   4
     / \     / \
    7   2   5   1
In the above tree, there are 7 columns, three of which have two nodes: [4, 2], [5, 6], and [8,5]. Therefore the algorithm would return a
linked list with the following characters: 7→9→4→2→5→6→8→5→4→1.

Bonus 1 - Print the tree

Adapt the algorithm to print the tree to match the output above. If two nodes share the same row and column, print an * in place of the value:

Input tree:
          5
         / \
        4   8
       / \ / \
      9   *   4
     / \   \ / \
    7   2   *   1*/
/*
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}*/
class ListNode<E> {

    public E val;
    public ListNode next;
    ListNode(E x) { val = x; next = null; }

    @Override
    public String toString() {
        return val + (next != null ? " -> " + next : "");
    }
}
class TreeNode<E> {
    E val;
    TreeNode left;
    TreeNode right;
    TreeNode(E x) { val = x; }
}
// time complexity is O(n)
public class SortTreeByCol {

    public ListNode sortTreeByCol(TreeNode root) {

        ListNode result = new ListNode(0);

        if(root==null)
            return null;

        HashMap<Integer, ListNode> map = new HashMap<Integer, ListNode>();

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<Integer> cols = new LinkedList<Integer>();

        queue.offer(root);
        cols.offer(0);

        int col = 0;
        int min = 0;
        int max = 0;

        while(!queue.isEmpty()) {

            TreeNode curNode = queue.poll();
            col = cols.poll();

            //track min and max levels
            min = Math.min(min, col);
            max = Math.max(max, col);

            if (map.containsKey(col)) {

                ListNode temp = map.get(col);
                ListNode temp1 = temp;

                while(temp1.next != null)
                    temp1 = temp1.next;

                temp1.next = new ListNode(curNode.val);

                map.put(col, temp);
            } else {
                ListNode temp = new ListNode(curNode.val);
                map.put(col, temp);

            }

            if (curNode.left != null) {
                queue.offer(curNode.left);
                cols.offer(col - 1);
            }

            if (curNode.right != null) {
                queue.offer(curNode.right);
                cols.offer(col + 1);
            }

        }

        ListNode temp = result;
        for(int i=min; i<=max; i++) {

            if(map.containsKey(i)) {

                temp.next = map.get(i);
                while(temp.next!=null)
                    temp = temp.next;
            }
        }
        return result.next;
    }

    public ListNode sortTreeByColPrint(TreeNode root) {

        ListNode result = new ListNode(0);

        if(root==null)
            return null;

        HashMap<Integer, ListNode> map = new HashMap<Integer, ListNode>();

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        Queue<Integer> levels = new LinkedList<Integer>();

        queue.offer(root);
        levels.offer(0);

        int level = 0;
        int min = 0;
        int max = 0;

        int row = 0;
        int col = 0;
        boolean match = false;
        while(!queue.isEmpty()) {
            row = queue.size();
            while(row>0) {

                TreeNode curNode = queue.poll();
                level = levels.poll();

                if (levels.contains(level)) {
                    col = level;
                    match = true;
                }

                if (match && col == level) {
                    curNode.val = "*";
                    if (!levels.contains(level))
                        match = false;

                }
                System.out.print(" " + curNode.val);

                //track min and max levels
                min = Math.min(min, level);
                max = Math.max(max, level);

                if (map.containsKey(level)) {

                    ListNode temp = map.get(level);
                    ListNode temp1 = temp;

                    while (temp1.next != null)
                        temp1 = temp1.next;

                    temp1.next = new ListNode(curNode.val);
                    map.put(level, temp);
                } else {
                    ListNode temp = new ListNode(curNode.val);
                    map.put(level, temp);

                }

                if (curNode.left != null) {
                    queue.offer(curNode.left);
                    levels.offer(level - 1);
                }

                if (curNode.right != null) {
                    queue.offer(curNode.right);
                    levels.offer(level + 1);
                }
                row--;
            }
            System.out.println("");

        }
        // below code required only for printing as linkedlist
        ListNode temp = result;
        for(int i=min; i<=max; i++) {

            if(map.containsKey(i)) {
                temp.next = map.get(i);
                while(temp.next!=null)
                    temp = temp.next;
            }
        }

        return result.next;
    }

    public void levelOrderQueuePrint(TreeNode root){
        Queue q = new LinkedList();
        int levelNodes =0;
        if(root==null) return;
        q.add(root);
        while(!q.isEmpty()){
            levelNodes = q.size();
            while(levelNodes>0){
                TreeNode n = (TreeNode)q.remove();
                System.out.print(" " + n.val);
                if(n.left!=null) q.add(n.left);
                if(n.right!=null) q.add(n.right);
                levelNodes--;
            }
            System.out.println("");
        }
    }

    public static void main(String args[]) {
        /*
            5
          /   \
         4      8
        / \    /  \
       9   3  6    4
      / \     \   / \
     7   2    10 5  1*/

        TreeNode tree = new TreeNode(5);
        tree.left = new TreeNode(4);
        tree.left.left = new TreeNode(9);
        tree.left.right = new TreeNode(3);

        tree.left.left.right = new TreeNode(2);
        tree.left.left.left = new TreeNode(7);

        tree.right = new TreeNode(8);
        tree.right.left = new TreeNode(6);
        tree.right.right = new TreeNode(4);
        tree.right.left.right = new TreeNode(10);

        tree.right.right.right = new TreeNode(1);
        tree.right.right.left = new TreeNode(5);
        //new SortTreeByCol().levelOrderQueuePrint(tree);

        System.out.println("Vertical order linkedlist -- " + new SortTreeByCol().sortTreeByCol(tree));
        System.out.println("Print with asterix -- " + new SortTreeByCol().sortTreeByColPrint(tree));
    }
}


