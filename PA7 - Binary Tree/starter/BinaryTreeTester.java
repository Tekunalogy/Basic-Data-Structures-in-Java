// NAME: Kunal Singla
// ID: A15799385
// EMAIL: KUSINGLA@UCSD.EDU

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeTester
{
    private BinaryTree<Integer> emptyTree;
    private BinaryTree<Integer> oneNodeTree;
    private BinaryTree<Integer> completeTree;
    private BinaryTree<Integer> fullTree;
    private BinaryTree<Integer> testingTree;
    private List<Integer> inputList;

    private final int ONE_NODE = 1;
    private final int COMPLETE_TREE_SIZE = 6;
    private final int FULL_TREE_SIZE = 7;

    @Before
    public void setUp()
    {
        emptyTree = new BinaryTree<>();
        oneNodeTree = new BinaryTree<>(new Integer(ONE_NODE));
        completeTree = new BinaryTree<>();
        fullTree = new BinaryTree<>();
        inputList = new ArrayList<>();

        //setup input list
        for(int i = 1; i <= FULL_TREE_SIZE; ++i)
            inputList.add(new Integer(i));

        //setup a complete tree
        for(int i = 1; i <= COMPLETE_TREE_SIZE; ++i)
            completeTree.add(new Integer(i));

        //setup a full tree
        for(int i = 1; i <= FULL_TREE_SIZE; ++i)
            fullTree.add(new Integer(i));

    }

    @Test
    public void testContructor()
    {
        testingTree = new BinaryTree<>();
        assertNull("Empty contructor, root node is null", testingTree.root);

        assertEquals(1, oneNodeTree.size);
        assertEquals(new Integer(ONE_NODE), oneNodeTree.root.data);

        assertEquals(COMPLETE_TREE_SIZE, completeTree.size);
        assertEquals(new Integer(ONE_NODE), completeTree.root.data);

        assertEquals(FULL_TREE_SIZE, fullTree.size);
        assertEquals(new Integer(ONE_NODE), fullTree.root.data);

        testingTree = new BinaryTree<>(inputList);
        assertEquals(FULL_TREE_SIZE, testingTree.size);
        assertEquals(new Integer(ONE_NODE), testingTree.root.data);
    }

    @Test
    public void testAddException()
    {
        try {emptyTree.add(null);}
        catch (NullPointerException e) {/**pass*/}
    }
    
    @Test
    public void testAdd()
    {
        emptyTree.add(new Integer(1));
        assertTrue(emptyTree.containsBFS(1));
        assertTrue(emptyTree.root.data == 1);

        oneNodeTree.add(new Integer(2));
        assertTrue(oneNodeTree.containsBFS(2));
        assertTrue(oneNodeTree.root.left.data == 2);

        completeTree.add(new Integer(7));
        assertTrue(completeTree.containsBFS(7));
        assertTrue(completeTree.root.right.right.data == 7);

        fullTree.add(new Integer(8));
        assertTrue(fullTree.containsBFS(8));
        assertTrue(fullTree.root.left.left.left.data == 8);
    }

    @Test
    public void testRemoveException()
    {
        try{emptyTree.remove(null);}
        catch (NullPointerException e){/**pass*/}

        try{oneNodeTree.remove(null);}
        catch (NullPointerException e){/**pass*/}

        try{completeTree.remove(null);}
        catch (NullPointerException e){/**pass*/}

        try{fullTree.remove(null);}
        catch (NullPointerException e){/**pass*/}

        assertFalse(emptyTree.remove(1));
        assertFalse(oneNodeTree.remove(2));
        assertFalse(completeTree.remove(7));
        assertFalse(fullTree.remove(8));
    }

    @Test
    public void testRemoveFirst()
    {
        assertTrue(oneNodeTree.remove(1));
        assertNull(oneNodeTree.root);

        assertTrue(completeTree.remove(1));
        assertEquals(new Integer(6), completeTree.root.data);

        assertTrue(fullTree.remove(1));
        assertEquals(new Integer(7), fullTree.root.data);
    }

    @Test
    public void testRemoveLast()
    {
        assertTrue(oneNodeTree.remove(1));
        assertNull(oneNodeTree.root);

        assertTrue(completeTree.remove(6));
        assertEquals(new Integer(1), completeTree.root.data);
        assertEquals(new Integer(5), completeTree.root.left.right.data);

        assertTrue(fullTree.remove(7));
        assertEquals(new Integer(1), fullTree.root.data);
        assertEquals(new Integer(6), fullTree.root.right.left.data);
    }

    @Test
    public void testRemoveBetween()
    {
        assertTrue(oneNodeTree.remove(1));
        assertNull(oneNodeTree.root);

        assertTrue(completeTree.remove(2));
        assertEquals(new Integer(1), completeTree.root.data);
        assertEquals(new Integer(6), completeTree.root.left.data);

        assertTrue(fullTree.remove(3));
        assertEquals(new Integer(1), fullTree.root.data);
        assertEquals(new Integer(7), fullTree.root.right.data);
    }

    @Test
    public void testContainsBFSException()
    {
        try{emptyTree.containsBFS(null);}
        catch(NullPointerException e){/**PASS*/}

        try{oneNodeTree.containsBFS(null);}
        catch(NullPointerException e){/**PASS*/}

        try{completeTree.containsBFS(null);}
        catch(NullPointerException e){/**PASS*/}

        try{fullTree.containsBFS(null);}
        catch(NullPointerException e){/**PASS*/}

        assertFalse(emptyTree.containsBFS(0));
        assertFalse(oneNodeTree.containsBFS(0));
        assertFalse(completeTree.containsBFS(0));
        assertFalse(fullTree.containsBFS(0));
    }
    
    @Test
    public void testContainsBFS()
    {
        //First Element
        assertTrue(oneNodeTree.containsBFS(1));
        assertTrue(completeTree.containsBFS(1));
        assertTrue(fullTree.containsBFS(1));

        //Last Element
        assertTrue(oneNodeTree.containsBFS(1));
        assertTrue(completeTree.containsBFS(6));
        assertTrue(fullTree.containsBFS(7));

        //All In Between Element
        assertTrue(completeTree.containsBFS(2));
        assertTrue(completeTree.containsBFS(3));
        assertTrue(completeTree.containsBFS(4));
        assertTrue(completeTree.containsBFS(5));
        assertTrue(completeTree.containsBFS(6));

        assertTrue(fullTree.containsBFS(2));
        assertTrue(fullTree.containsBFS(3));
        assertTrue(fullTree.containsBFS(4));
        assertTrue(fullTree.containsBFS(5));
        assertTrue(fullTree.containsBFS(6));
        assertTrue(fullTree.containsBFS(7));
    }

    @Test
    public void testHeight()
    {
        assertEquals(0, emptyTree.getHeight());
        assertEquals(0, oneNodeTree.getHeight());
        assertEquals(2, completeTree.getHeight());
        assertEquals(2, fullTree.getHeight());
    }

    @Test
    public void testGetSize()
    {
        assertEquals(0, emptyTree.getSize());
        assertEquals(1, oneNodeTree.getSize());
        assertEquals(6, completeTree.getSize());
        assertEquals(7, fullTree.getSize());
    }

    @Test
    public void testGetMinValue()
    {
        assertNull(emptyTree.minValue());
        assertEquals(new Integer(1), oneNodeTree.minValue());
        assertEquals(new Integer(1), completeTree.minValue());
        completeTree.add(new Integer(-1));
        assertEquals(new Integer(-1), completeTree.minValue());
        assertEquals(new Integer(1), fullTree.minValue());
    }
}