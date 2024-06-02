import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class queue_using_two_stacks {
    public static void queueOp(Stack<Long> st1, Stack<Long> st2) {
        if (st2.isEmpty()) {
            while (!st1.isEmpty()) {
                st2.push(st1.pop());
            }
        }
    }

    public static void main(String[] args) {
        /*
         * Enter your code here. Read input from STDIN. Print output to STDOUT. Your
         * class should be named Solution.
         */
        Stack<Long> st1 = new Stack<>();
        Stack<Long> st2 = new Stack<>();

        Scanner sc = new Scanner(System.in);

        // no. of test cases
        Long t = sc.nextLong();

        for (Long i = 0L; i < t; i++) {
            // type of query
            int q = sc.nextInt();

            if (q == 1) {
                // data to be enqueued
                Long val = sc.nextLong();
                st1.push(val);
            }

            else if (q == 2) {
                queueOp(st1, st2);
                st2.pop();
            }

            else if (q == 3) {
                queueOp(st1, st2);
                System.out.println(st2.peek());
            }
        }

        // sc.close();
    }
}