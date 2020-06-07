// NAME: Kunal Singla
// ID: A15799385
// EMAIL: KUSINGLA@UCSD.EDU

import org.junit.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class MyBSTTester
{
    private MyBST<Integer, String> emptyTree;
    private MyBST<Integer, String> oneNodeTree;
    private MyBST<Integer, String> completeTree;
    private MyBST<Integer, String> fullTree;
    private MyBST<Integer, String> exampleTree;
    private MyBST<Integer, String> testingTree;
    private List<Integer> inputList;

    @Before
    public void setUp()
    {
        emptyTree = new MyBST<>();
        oneNodeTree = new MyBST<>();
        completeTree = new MyBST<>();
        fullTree = new MyBST<>();
        exampleTree = new MyBST<>();
        testingTree = new MyBST<>();
        inputList = new ArrayList<>();
        
        //elements based on tree in PA ReadMe diagram
        oneNodeTree.insert(20, "CSE");

        completeTree.insert(20, "CSE");
        completeTree.insert(12, "Haytham");
        completeTree.insert(95, "Paul");
        completeTree.insert(11, "Joe");
        completeTree.insert(15, "Gary");
        completeTree.insert(30, "Brian");

        fullTree.insert(20, "CSE");
        fullTree.insert(12, "Haytham");
        fullTree.insert(95, "Paul");
        fullTree.insert(11, "Joe");
        fullTree.insert(15, "Gary");
        fullTree.insert(30, "Brian");
        fullTree.insert(120, "Joe");

        exampleTree.insert(20, "CSE");
        exampleTree.insert(12, "Haytham");
        exampleTree.insert(95, "Paul");
        exampleTree.insert(11, "Joe");
        exampleTree.insert(15, "Gary");
        exampleTree.insert(30, "Brian");
        exampleTree.insert(120, "Joe");
        exampleTree.insert(8, "Gerald");
        exampleTree.insert(21, "Miles");
        exampleTree.insert(105, "Mia");
        exampleTree.insert(123, "Alex");
        exampleTree.insert(100, "Sander");

        //keys from 1 to 9 and letters from "a" to "i"
        testingTree.insert(5, "e");
        testingTree.insert(2, "b");
        testingTree.insert(7, "g");
        testingTree.insert(1, "a");
        testingTree.insert(3, "c");
        testingTree.insert(6, "f");
        testingTree.insert(8, "h");
        testingTree.insert(4, "d");
        testingTree.insert(9, "i");

    }

    @Test
    public void testConstructor()
    {
        assertNull(emptyTree.root);
        assertEquals(0, emptyTree.size());
    }

    @Test
    public void testSuccessor()
    {
        //empty tree
        MyBST.MyBSTNode<Integer, String> node = emptyTree.root;
        assertNull(emptyTree.successor(emptyTree.root));

        //complete tree
        node = completeTree.root.getLeft().getRight();
        assertEquals(new Integer(20), completeTree.successor(node).getKey());
        node = node.successor();
        assertEquals(new Integer(30), completeTree.successor(node).getKey());

    }

    @Test
    public void testSize()
    {
        assertEquals(0, emptyTree.size());
        assertEquals(6, completeTree.size());
        assertEquals(7, fullTree.size());
        assertEquals(12, exampleTree.size());
    }
    
    @Test
    public void testInsert()
    {
        assertNull(emptyTree.insert(16, "No Prof."));
        assertEquals(new Integer(16), emptyTree.root.getKey());
        assertEquals("Joe", completeTree.insert(11, "Niema"));
    }

    @Test
    public void testSearch()
    {
        assertNull(emptyTree.search(1));
        assertNull(completeTree.search(1));
        assertEquals("Mia", exampleTree.search(105));
    }

    @Test
    public void testRemove()
    {
        assertNull(emptyTree.remove(1));
        //remove node with 2 child node
        assertEquals("CSE", fullTree.remove(20));
        assertEquals("Brian", fullTree.root.getValue());
        assertEquals("Brian", fullTree.remove(30));
        assertEquals("Paul", fullTree.root.getValue());

        //remove node with 1 child node
        assertEquals("Paul", completeTree.remove(95));
        assertEquals("Brian", completeTree.root.getRight().getValue());

        //remove node with 0 child node
        assertEquals("Sander", exampleTree.remove(100));
        assertEquals("CSE", exampleTree.root.getValue());        
    }

    @Test
    public void testInorder()
    {
        ArrayList<Integer> keys = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        for(int i = 1; i <= 9; ++i)
        {
            keys.add(new Integer(i));
            String letter = (char)(96+i) + "";
            values.add(letter);
        }
        ArrayList<MyBST.MyBSTNode<Integer, String>> list = testingTree.inorder();
        for(int i = 0; i < 9; ++i)
        {
            assertEquals(keys.get(i), list.get(i).getKey());
            assertEquals(values.get(i), list.get(i).getValue());
        }
    }

    public void printTree(MyBST<Integer, String> input)
    {
        ArrayList<MyBST.MyBSTNode<Integer, String>> list = input.inorder();
        System.out.println();
        System.out.print("TREE: ");
        for(MyBST.MyBSTNode<Integer, String> n : list)
        {
            System.out.print(n.getKey() + " ");
        }
        System.out.println();
    }
}