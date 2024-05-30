import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'getMax' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING_ARRAY operations as parameter.
     */

    public static List<Integer> getMax(List<String> operations) {
        // Write your code here
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> maxStack = new Stack<>();
        List<Integer> result = new ArrayList<>();

        for (String operation : operations) {
            String[] parts = operation.split(" ");
            int opType = Integer.parseInt(parts[0]);

            if (opType == 1) {
                int value = Integer.parseInt(parts[1]);
                stack.push(value);
                if (maxStack.isEmpty() || value >= maxStack.peek()) {
                    maxStack.push(value);
                }
            } else if (opType == 2) {
                int removedValue = stack.pop();
                if (removedValue == maxStack.peek()) {
                    maxStack.pop();
                }
            } else if (opType == 3) {
                result.add(maxStack.peek());
            }
        }

        return result;
    }

}

public class maximum_element {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<String> ops = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String opsItem = bufferedReader.readLine();
            ops.add(opsItem);
        }

        List<Integer> res = Result.getMax(ops);

        for (int i = 0; i < res.size(); i++) {
            bufferedWriter.write(String.valueOf(res.get(i)));

            if (i != res.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
