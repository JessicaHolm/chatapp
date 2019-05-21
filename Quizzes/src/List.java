public class List
{
    static class Node
    {
        Node next;
        int data;

        Node(int d)
        {
            data = d;
        }
    }
    Node head;

    public boolean isEmpty()
    {
        if(head == null)
            return true;
        else
            return false;
    }

    public void insert(int value)
    {
        if(head == null)
            head = new Node(value);
        else
        {
            Node newNode = new Node(value);
            newNode.next = head;
            head = newNode;
        }
    }

    public void remove(int value)
    {
        Node curr = head;
        Node prev = head;
        while(curr != null)
        {
            if(curr.data == value)
                prev.next = curr.next;

            prev = curr;
            curr = curr.next;
        }
    }

    public void display()
    {
        Node curr = head;
        while(curr != null)
        {
            if(curr.next == null)
                System.out.print(curr.data);
            else
                System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println();
    }

    public void reverse()
    {

    }

    public void sort()
    {

    }
}