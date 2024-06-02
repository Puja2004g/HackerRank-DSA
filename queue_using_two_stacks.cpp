#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
#include <stack>
using namespace std;

void queueOp(stack<long> &st1, stack<long> &st2)
{
    if (st2.empty())
    {
        while (!st1.empty())
        {
            st2.push(st1.top());
            st1.pop();
        }
    }
}

int main()
{
    /* Enter your code here. Read input from STDIN. Print output to STDOUT */
    stack<long> st1;
    stack<long> st2;
    // test case
    long t;
    cin >> t;

    for (long i = 0; i < t; i++)
    {
        // queries
        long q;
        cin >> q;

        if (q == 1)
        {
            long val;
            // value to be enqueued
            cin >> val;
            st1.push(val);
        }

        else if (q == 2)
        {
            queueOp(st1, st2);
            st2.pop();
        }

        else if (q == 3)
        {
            queueOp(st1, st2);
            cout << st2.top() << endl;
        }
    }

    return 0;
}
