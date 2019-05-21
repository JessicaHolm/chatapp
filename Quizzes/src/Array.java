public class Array
{
    static class Node
    {
        Node next;
        int data;

        Node(int v)
        {
            data = v;
        }
    }

    private int size;
    private int len;
    private Node [] array;

    Array(int s, int l)
    {
        size = s;
        len = l;
        array = new Node[size];
    }

    public void insert(int value)
    {
        if(len == 0)
        {
            array[len] = new Node(value);
            len++;
        }
        else if(len <= size)
        {
            array[len] = new Node(value);
            array[len-1].next = array[len];
            len++;
        }
        else
            System.out.println("Array is full");

    }

    public int head()
    {
        return array[0].data;
    }

    public void remove(int index)
    {
        for(int i = index + 1; i < len; i++)
        {
            array[i - 1].data = array[i].data;
        }
        len--;
        array[len].next = null;
    }

    public void display()
    {
        for(int i = 0; i < len; i++)
        {
            if(i == len-1)
                System.out.print(array[i].data);
            else
                System.out.print(array[i].data + " -> ");
        }
        System.out.println();
    }

    public void reverse()
    {
        Node [] temp = new Node[len];
        int j = 0;
        for(int i = len; i >= 0; i--)
        {
            temp[j] = new Node(array[i].data);
            j++;
        }
        for(int i = 0; i < len; i++)
        {
            array[i].data = temp[i].data;
        }
    }

    public void sort()
    {
        int i = 1;
        Node temp;
        while(i < len)
        {
            int j = i;
            while(j > 0 && array[j-1].data > array[j].data)
            {
                temp = array[j];
                array[j] = array[j-1];
                array[j-1] = temp;
                j--;
            }
            i++;
        }
    }

    public void findDuplicates()
    {
        sort();
        for(int i = 0; i < len; i++)
        {
            if(i != 0)
            {
                if(array[i].data == array[i - 1].data)
                {
                    System.out.println(array[i].data);
                }
            }
        }
    }
}