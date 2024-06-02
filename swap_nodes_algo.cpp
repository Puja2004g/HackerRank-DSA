#include <bits/stdc++.h>

using namespace std;

string ltrim(const string &);
string rtrim(const string &);
vector<string> split(const string &);

/*
 * Complete the 'swapNodes' function below.
 *
 * The function is expected to return a 2D_INTEGER_ARRAY.
 * The function accepts following parameters:
 *  1. 2D_INTEGER_ARRAY indexes
 *  2. INTEGER_ARRAY queries
 */

class Node
{
public:
    int data;
    Node *left;
    Node *right;

    Node(int data)
    {
        this->data = data;
        this->left = NULL;
        this->right = NULL;
    }
};

Node *buildTree(const vector<vector<int>> &indexes)
{
    int n = indexes.size();
    vector<Node *> nodes(n + 1, nullptr);

    for (int i = 1; i <= n; i++)
    {
        nodes[i] = new Node(i);
    }

    for (int i = 0; i < n; i++)
    {
        int left = indexes[i][0];
        int right = indexes[i][1];
        nodes[i + 1]->left = (left == -1) ? NULL : nodes[left];
        nodes[i + 1]->right = (right == -1) ? NULL : nodes[right];
    }

    return nodes[1];
}

void swapAtDepth(Node *root, int k, int depth)
{
    if (root == NULL)
    {
        return;
    }

    if (depth % k == 0)
    {
        Node *temp = root->left;
        root->left = root->right;
        root->right = temp;
    }

    swapAtDepth(root->left, k, depth + 1);
    swapAtDepth(root->right, k, depth + 1);
}

void inorderTraversal(Node *root, vector<int> &inorder)
{
    if (root == NULL)
    {
        return;
    }

    inorderTraversal(root->left, inorder);
    inorder.push_back(root->data);
    inorderTraversal(root->right, inorder);
}

vector<vector<int>> swapNodes(vector<vector<int>> indexes, vector<int> queries)
{
    Node *root = buildTree(indexes);
    vector<vector<int>> res;

    for (int k : queries)
    {
        swapAtDepth(root, k, 1);
        vector<int> inorder;
        inorderTraversal(root, inorder);
        res.push_back(inorder);
    }

    return res;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    string n_temp;
    getline(cin, n_temp);

    int n = stoi(ltrim(rtrim(n_temp)));

    vector<vector<int>> indexes(n);

    for (int i = 0; i < n; i++)
    {
        indexes[i].resize(2);

        string indexes_row_temp_temp;
        getline(cin, indexes_row_temp_temp);

        vector<string> indexes_row_temp = split(rtrim(indexes_row_temp_temp));

        for (int j = 0; j < 2; j++)
        {
            int indexes_row_item = stoi(indexes_row_temp[j]);

            indexes[i][j] = indexes_row_item;
        }
    }

    string queries_count_temp;
    getline(cin, queries_count_temp);

    int queries_count = stoi(ltrim(rtrim(queries_count_temp)));

    vector<int> queries(queries_count);

    for (int i = 0; i < queries_count; i++)
    {
        string queries_item_temp;
        getline(cin, queries_item_temp);

        int queries_item = stoi(ltrim(rtrim(queries_item_temp)));

        queries[i] = queries_item;
    }

    vector<vector<int>> result = swapNodes(indexes, queries);

    for (size_t i = 0; i < result.size(); i++)
    {
        for (size_t j = 0; j < result[i].size(); j++)
        {
            fout << result[i][j];

            if (j != result[i].size() - 1)
            {
                fout << " ";
            }
        }

        if (i != result.size() - 1)
        {
            fout << "\n";
        }
    }

    fout << "\n";

    fout.close();

    return 0;
}

string ltrim(const string &str)
{
    string s(str);

    s.erase(
        s.begin(),
        find_if(s.begin(), s.end(), not1(ptr_fun<int, int>(isspace))));

    return s;
}

string rtrim(const string &str)
{
    string s(str);

    s.erase(
        find_if(s.rbegin(), s.rend(), not1(ptr_fun<int, int>(isspace))).base(),
        s.end());

    return s;
}

vector<string> split(const string &str)
{
    vector<string> tokens;

    string::size_type start = 0;
    string::size_type end = 0;

    while ((end = str.find(" ", start)) != string::npos)
    {
        tokens.push_back(str.substr(start, end - start));

        start = end + 1;
    }

    tokens.push_back(str.substr(start));

    return tokens;
}
