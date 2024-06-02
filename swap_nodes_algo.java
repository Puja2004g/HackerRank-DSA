import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /*
     * Complete the 'swapNodes' function below.
     *
     * The function is expected to return a 2D_INTEGER_ARRAY.
     * The function accepts following parameters:
     * 1. 2D_INTEGER_ARRAY indexes
     * 2. INTEGER_ARRAY queries
     */
    private static Node buildTree(List<List<Integer>> indexes) {
        int n = indexes.size();
        Node[] nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < n; i++) {
            int left = indexes.get(i).get(0);
            int right = indexes.get(i).get(1);
            nodes[i + 1].left = (left == -1) ? null : nodes[left];
            nodes[i + 1].right = (right == -1) ? null : nodes[right];
        }

        return nodes[1];
    }

    private static void swapAtDepth(Node root, int k, int depth) {
        if (root == null) {
            return;
        }

        if (depth % k == 0) {
            Node temp = root.left;
            root.left = root.right;
            root.right = temp;
        }

        swapAtDepth(root.left, k, depth + 1);
        swapAtDepth(root.right, k, depth + 1);
    }

    private static void inorderTraversal(Node root, List<Integer> inorder) {
        if (root == null) {
            return;
        }

        inorderTraversal(root.left, inorder);
        inorder.add(root.data);
        inorderTraversal(root.right, inorder);
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        // Write your code here
        Node root = buildTree(indexes);

        List<List<Integer>> res = new ArrayList<>();

        for (int k : queries) {
            swapAtDepth(root, k, 1);
            List<Integer> inorder = new ArrayList<>();
            inorderTraversal(root, inorder);
            res.add(inorder);
        }

        return res;
    }

}

public class swap_nodes_algo {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> indexes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] indexesRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> indexesRowItems = new ArrayList<>();

            for (int j = 0; j < 2; j++) {
                int indexesItem = Integer.parseInt(indexesRowTempItems[j]);
                indexesRowItems.add(indexesItem);
            }

            indexes.add(indexesRowItems);
        }

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = new ArrayList<>();

        for (int i = 0; i < queriesCount; i++) {
            int queriesItem = Integer.parseInt(bufferedReader.readLine().trim());
            queries.add(queriesItem);
        }

        List<List<Integer>> result = Result.swapNodes(indexes, queries);

        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                bufferedWriter.write(String.valueOf(result.get(i).get(j)));

                if (j != result.get(i).size() - 1) {
                    bufferedWriter.write(" ");
                }
            }

            if (i != result.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
