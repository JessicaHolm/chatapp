public class Stack
{
    private int size;
    private int top = -1;
    private int [] stack;

    Stack(int s)
    {
        size = s;
        stack = new int[size];
    }

    public void push(int value)
    {
        if(top > size-2)
        {
            System.out.println("Stack is full. Element not added.");
            return;
        }
        top++;
        stack[top] = value;
    }

    public void pop()
    {
        top--;
    }

    public int peek()
    {
        return stack[top];
    }

    public void display()
    {
        for(int i = top; i >= 0; i--)
        {
            System.out.println(stack[i]);
        }
    }
}