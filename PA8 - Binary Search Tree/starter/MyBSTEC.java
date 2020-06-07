import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Iterator;

// NAME: Kunal Singla
// ID: A15799385
// EMAIL: KUSINGLA@UCSD.EDU

public class MyBSTEC<K extends Comparable<K>, V> extends MyBST<K, V> 
{
    abstract class MyBSTNodeIterator<T> implements Iterator<T>
    {
        MyBSTNode<K, V> next;
        MyBSTNode<K, V> lastVisited;

        MyBSTNodeIterator(MyBSTNode<K,V> first)
        {
            next = first;
            lastVisited = null;
        }

        public boolean hasNext()
        {
            return next != null;
        }

        public MyBSTNode<K, V> nextNode()
        {
            if(next == null)
                throw new NoSuchElementException();
            lastVisited = next;
            next = next.successor();
            return lastVisited;
        }

        public void remove()
        {
            if(lastVisited == null)
                throw new IllegalStateException();
            
            if(lastVisited.getLeft() != null && lastVisited.getRight() != null)
            {
                MyBSTNode<K, V> temp = lastVisited;
                MyBSTEC.this.remove(lastVisited.getKey());
                next = temp;
            }
            else
                MyBSTEC.this.remove(lastVisited.getKey());

        }

    }

    class MyBSTKeyIterator extends MyBSTNodeIterator<K>
    {
        MyBSTKeyIterator(MyBSTNode<K, V> first)
        {
            super(first);
        }
        public K next()
        {
            return nextNode().getKey();
        }

    }
    
    class MyBSTValueIterator extends MyBSTNodeIterator<V>
    {
        MyBSTValueIterator(MyBSTNode<K, V> first)
        {
            super(first);
        }
        public V next()
        {
            return nextNode().getValue();
        }
    }



    public MyBSTKeyIterator getKeyIterator()
    {
        ArrayList<MyBSTNode<K, V>> list = this.inorder();

        return new MyBSTKeyIterator(list.get(0));
    }

    public MyBSTValueIterator getValueIterator()
    {
        ArrayList<MyBSTNode<K, V>> list = this.inorder();

        return new MyBSTValueIterator(list.get(0));
    }

}
