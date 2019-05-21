public class Tree
{
    static class Node
    {
        private int data;
        private Node left;
        private Node right;

        Node(int d)
        {
            data = d;
            left = right = null;
        }
    }
    private Node root;

    Tree()
    {
        root = null;
    }

    public void insert(int value)
    {
        root = insert(root, value);
    }

    public Node insert(Node root, int value)
    {
        if(root == null)
            return new Node(value);
        else if(root.data > value)
            root.left = insert(root.left, value);
        else
            root.right = insert(root.right, value);

        return root;
    }

    public void display()
    {
        display(root);
    }

    public void display(Node root)
    {
        if(root == null)
            return;

        display(root.left);
        System.out.println(root.data);
        display(root.right);
    }

    public void depth()
    {
        int max = depth(root);

        System.out.println("Maximum depth of the tree is " + max);
    }

    public int depth(Node root)
    {
        if(root == null)
            return 0;
        int left_max = depth(root.left) + 1;
        int right_max = depth(root.right) + 1;

        if(left_max > right_max)
            return left_max;
        else
            return right_max;
    }

    public void level_order()
    {
        for(int i = 1; i < depth(root)+1; i++)
            level_order(root, i);
    }

    public void level_order(Node root, int level)
    {
        if(root == null)
            return;
        else if(level == 1)
            System.out.println(root.data);
        else if(level > 1)
        {
            level_order(root.left, level - 1);
            level_order(root.right,level - 1);
        }
    }

    public int lowest_common()
    {
        Node parent = null;
        return lowest_common(root, parent, 5, 4);
    }

    public int lowest_common(Node root, Node parent, int first, int second)
    {
        if(root.data == first)
            return parent.data;

        if(root.data > first)
            return lowest_common(root.left, root, first, second);
        else
            return lowest_common(root.right, root, first, second);

    }

    public void array_to_tree()
    {
        int len = 8;
        int [] arr = new int[len];

        for(int i = 0; i < len; i++)
        {
            arr[i] = i+1;
        }

        array_to_tree(arr, len / 2);
    }

    public void array_to_tree(int [] arr, int len)
    {
        if(len == 0)
            return;
        //if(len == len / 2)
        //{
            insert(len);
        //}

        array_to_tree(arr, len / 2);
        array_to_tree(arr, (len) + (len / 4));
    }

    public void largestValues()
    {
        int largest;
        System.out.print("[");
        for (int i = 1; i < depth(root) + 1; i++)
        {
            largest = 0;
            largest = largestValues(root, i, largest);
            System.out.print(largest + ", ");
        }
        System.out.println("]");
    }

    public int largestValues(Node root, int level, int largest)
    {
        if(root == null)
            return 0;
        else if(level == 1)
        {
            if (root.data > largest)
                largest = root.data;
            //System.out.println(root.data);
        }
        else if(level > 1)
        {
            largestValues(root.left, level - 1, largest);
            largestValues(root.right,level - 1, largest);
        }
        return largest;
    }
}