package week3.intq;
/*Interview Question 1 - Convert a Linked List to BST
Imagine an interview in which you are presented with the following problem:

Write an algorithm which takes as its input a linked list of integer values and outputs a balanced binary search tree in
 O(n) time.

Before beginning, consider the above statement carefully, not just in terms of which details were specified but also in
terms of which details were omitted.

There are really two problems being presented here: not just the algorithm, but also the interview question itself. In
both cases you have to show your work: not just the code for the solution, but the process of engaging with the problem
and designing the solution. Your first job as a programmer is to make sure the problem is clearly defined and
understood. In interviews (and reality) this is a verbal/visual and often interactive process.

One way to start is by restating the problem with more specifics and observations, especially with regard to any
implicit requirements arising from the explicitly stated ones. For instance, here's an example of how one might restate
the above problem:

Since the input is in the form of a linked list, we won't have the benefit of a data structure that supports random
access, such as an array. And given the time requirement of O(n), it's likely the solution will involve a
straightforward traversal of the data set--the amount of work we're doing needs to scale linearly with the size of the
input list. Additionally, we'll have to consider the depth of the leaf nodes to ensure the output BST is balanced.

But almost as much information is left unspecified. Rather than assume that any vagueness on the part of the interviewer
is an oversight or intended as latitude, consider this may be intentional in order to reveal which questions you know
to ask. Some examples:

Is the linked list single-linked or doubly-linked?
Are its values sorted? Are duplicate values ruled out?
Is there a space complexity requirement in addition to the time requirement? (i.e., is using storage allowed)?
Are there specific implementations of the data structures that should be used? (i.e., using a standard collection class
vs. implementing a model of the structure)
Is test case data available?
You should be able to think of questions such as these and others for almost any problem you are given. Even
(especially) if you have no idea how to approach the problem.

For this exercise, let's assume the following further answers:

Single-linked list of sorted, unique values
No space complexity requirements
The input / output data structures must be implemented, otherwise collections are allowed
No example/test data, you're on your own
From the above, we know a few more things:

We can use extra storage, which generally opens up more possible approaches to a solution, especially since we can use
collections classes
such as Stack or Queue for that part
The linked list type points us towards straightforward a linear algorithm, probably in one or two passes
The input won't require a lot of pre-processing, so we should focus on building the BST
We'll need to implement the input list structure and output tree structure first to frame the algorithm
At this point, you have enough information to at least sketch the solution in code or pseudo-code. You should be able
to implement a simple single-linked list and binary tree from memory to demonstrate your knowledge of the structure.
You could also stub out the solution using something as simple as a static method that declares these in its signature.

Before you begin to write the algorithm code, think about the approach to the solution: will it be recursive or
iterative? What's the space complexity of the solution structure? Any additional structures that would simplify the
approach? Is there a way to ensure the output tree is balanced while constructing it?

Hint: there is a solution with a space complexity of O(n) using no helper collections

Bonus 1 - Use a Doubly-linked List

How would you improve the algorithm given a doubly-linked list as input?

Hint: think about the difference between a DLL and a BST*/
/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     public int val;
 *     public ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
public class LinkedList2BST {

    public TreeNode sortedLinkedListToBalancedBST(ListNode node) {
        if (node == null) {
            return null;
        } else if (node.next == null) {
            return new TreeNode((Integer) node.val);
        }

        // Copy linked list to array (there are no space constraints)
        ArrayList a = new ArrayList();
        ListNode curr = node;
        while (null != curr) {
            a.add(curr.val);
            curr = curr.next;
        }
        return tree(a, 0, a.size() - 1);
    }

    private TreeNode tree(ArrayList<Integer> a, int i, int j) {
        if (i == j) {
            return new TreeNode(a.get(i));
        }

        int mid = (i + j) / 2;
        TreeNode b = new TreeNode(a.get(mid));
        if (i < mid) {
            b.left = tree(a, i, mid - 1);
        }
        if (mid < j) {
            b.right = tree(a, mid + 1, j);
        }

        return b;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode<Integer>(1);
        a.next = new ListNode<Integer>(2);
        a.next.next = new ListNode<Integer>(3);
        a.next.next.next = new ListNode<Integer>(4);
        a.next.next.next.next = new ListNode<Integer>(5);
        a.next.next.next.next.next = new ListNode<Integer>(6);
        a.next.next.next.next.next.next = new ListNode<Integer>(7);

        TreeNode result = new LinkedList2BST().sortedLinkedListToBalancedBST(a);
        //TreeNode result = new LinkedList2BST().sortedLinkedListToBalancedBST1(a);

        ArrayList a1 = new ArrayList();
        ListNode curr = a;
        while (null != curr) {
            a1.add(curr.val);
            curr = curr.next;
        }

        //TreeNode result = new LinkedList2BST().createBST(a1);

        Queue q = new LinkedList();
        q.add(result);
        while (!q.isEmpty()) {
            TreeNode node = (TreeNode) q.poll();

            System.out.println("node " + node.val);
            if (node.left != null)
                //System.out.println("result.left "+result.left.val);
                q.add(node.left);

            if (node.right != null)
                //System.out.println("result.right "+result.right.val);
                q.add(node.right);

        }
    }

//-------------

    /* This function counts the number of nodes in Linked List
           and then calls sortedListToBSTRecur() to construct BST */
    ListNode head = null;

    public TreeNode sortedLinkedListToBalancedBST1(ListNode node) {

        head = node;
        /*Count the number of nodes in Linked List */
        int n = countNodes(node);

        /* Construct BST */
        return sortedListToBSTRecur( n);
    }

    /* The main function that constructs balanced BST and
       returns root of it.
       n  --> No. of nodes in the Doubly Linked List */
    TreeNode sortedListToBSTRecur(int n) {

        /* Base Case */
        if (n <= 0)
            return null;
       // System.out.println("k " + k++);

        /* Recursively construct the left subtree */
        TreeNode left = sortedListToBSTRecur(n / 2);

        /* head_ref now refers to middle node,
           make middle node as root of BST*/
        TreeNode root = new TreeNode((Integer) head.val);

        // Set pointer to left subtree
        root.left = left;

        /* Change head pointer of Linked List for parent
           recursive calls */
        head = head.next;

        /* Recursively construct the right subtree and link it
           with root. The number of nodes in right subtree  is
           total nodes - nodes in left subtree - 1 (for root) */
        root.right = sortedListToBSTRecur(n - n / 2 - 1);

        return root;

    }

    int countNodes(ListNode head) {
        int count = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            count++;
        }
        return count;
    }

//-------------

    public static TreeNode createBST(ArrayList array) {

        return createBST(array, 0, array.size()-1);
    }

    private static TreeNode createBST(ArrayList array, int start, int end) {

        if(array == null || array.size() == 0 || start > end) {
            return null;
        }

        int mid = (start + end)/2;
        TreeNode root = new TreeNode((Integer)array.get(mid));

        root.left = createBST(array, start, mid-1);
        root.right = createBST(array, mid+1, end);

        return root;
    }

}