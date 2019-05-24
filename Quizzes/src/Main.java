public class Main
{
    public static void main(String[] args)
    {
/*
        //Array list = new Array(10,0);
        //List list = new List();
        Stack stack = new Stack(10);

        //System.out.println(list.isEmpty());

        list.insert(6);
        list.insert(8);
        list.insert(3);
        list.insert(5);
        list.insert(4);
        list.insert(1);

        list.display();

        //System.out.println(list.isEmpty());

        list.sort();
        //list.remove(5);

        list.display();


        stack.push(6);
        stack.push(8);
        stack.push(3);
        stack.push(5);
        stack.push(4);
        stack.push(1);


        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();

        System.out.println(stack.peek() + "\n");

        stack.display();
*/
        //Tree tree = new Tree();

        //tree.insert(6);
        //tree.insert(8);
        //tree.insert(3);
        //tree.insert(5);
        //tree.insert(4);
        //tree.insert(1);

        //tree.largestValues();
        //tree.level_order();
        //System.out.println(tree.lowest_common());
        //tree.array_to_tree();

        //tree.level_order();
        //tree.display();
        //tree.depth();

        //Encode url = new Encode();

        //String tiny = url.encode("https://leetcode.com/problems/design-tinyurl");

        //System.out.println(tiny);

        Array list = new Array(10, 0);

        list.insert(1);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        list.insert(2);
        //list.insert(2)
        //list.insert(3);
        //list.insert(1);

        //list.sort();

        System.out.println(list.findLengthOfLCIS());
    }
}