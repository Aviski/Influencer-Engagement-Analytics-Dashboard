package analytics;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 * Custom singly linked list to store Influencer objects and support sorting
 * 
 * @author Avi Patel
 * @version Nov 18, 2025
 */
public class InfluencerLinkedList
    implements Iterable<Influencer>
{

    /**
     * Node class for the singly linked list.
     */
    private static class Node
    {
        private Influencer data;
        private Node next;

        /**
         * Creates a node holding the given influencer.
         * 
         * @param data
         *            influencer stored in this node
         */
        private Node(Influencer data)
        {
            this.data = data;
            this.next = null;
        }
    }

    // ~ Fields ................................................................

    private Node head;
    private int size;

    // ~ Constructors ..........................................................

    /**
     * Constructs an empty list
     */
    public InfluencerLinkedList()
    {
        head = null;
        size = 0;
    }
    // ~Public Methods ........................................................


    /**
     * Gets size of list
     * 
     * @return int size
     */
    public int size()
    {
        return size;
    }


    /**
     * Checks if list is empty
     * 
     * @return boolean if size = 0
     */
    public boolean isEmpty()
    {
        return size == 0;
    }


    /**
     * Resets list to empty
     */
    public void clear()
    {
        head = null;
        size = 0;
    }


    /**
     * Adds an influencer to the list
     * 
     * @param influencer
     *            being added
     * @return boolean if properly added
     */
    public boolean add(Influencer influencer)
    {
        if (influencer == null)
        {
            throw new IllegalArgumentException("Influencer cannot be null");
        }

        Node newNode = new Node(influencer);

        if (head == null)
        {
            head = newNode;
        }
        else
        {
            Node current = head;
            while (current.next != null)
            {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;
        return true;
    }


    /**
     * Traverses the list from the head, until reaching element or reach the end
     * checking for bad conditons
     */
    private void checkIndex(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException(
                "Index: " + index + ", Size: " + size);
        }
    }


    /**
     * Gets influencer data at index
     * 
     * @param index
     *            we get data from
     * @return Influencer data at index
     */
    public Influencer get(int index)
    {
        checkIndex(index);

        Node current = head;
        for (int i = 0; i < index; i++)
        {
            current = current.next;
        }

        return current.data;
    }


    /**
     * Traveres list and gets influencer by channelName
     * 
     * @param channelName
     *            we are searching for
     * @return Influencer data of channelName
     */
    public Influencer findByChannelName(String channelName)
    {
        if (channelName == null)
        {
            return null;
        }

        Node current = head;
        while (current != null)
        {
            if (current.data.getChannelName().equalsIgnoreCase(channelName))
            {
                return current.data;
            }
            current = current.next;
        }

        return null;
    }


    /**
     * Filters bad cases and calls insertionSort
     * 
     * @param comparator
     *            what we are comparing
     */
    public void sort(Comparator<Influencer> comparator)
    {
        if (comparator == null || size <= 1)
        {
            return;
        }
        insertionSort(comparator);
    }


    /**
     * Take nodes from the original list one by one and insert them into a
     * seperate list, sortedHead using insertInOrder
     * 
     * @param comparator
     *            what we are comparing
     */
    private void insertionSort(Comparator<Influencer> comparator)
    {
        Node sortedHead = null;
        Node current = head;

        while (current != null)
        {
            Node next = current.next;
            sortedHead = insertInOrder(sortedHead, current, comparator);
            current = next;
        }

        head = sortedHead;
    }


    /**
     * Insert node into the correct spot in the sorted list with head,
     * sortedHead
     */
    private Node insertInOrder(
        Node sortedHead,
        Node node,
        Comparator<Influencer> comparator)
    {

        node.next = null;

        if (sortedHead == null
            || comparator.compare(node.data, sortedHead.data) < 0)
        {
            node.next = sortedHead;
            return node;
        }

        Node current = sortedHead;
        while (current.next != null
            && comparator.compare(node.data, current.next.data) >= 0)
        {
            current = current.next;
        }

        node.next = current.next;
        current.next = node;

        return sortedHead;
    }


    /**
     * Allows for cleaner for loop
     * 
     * @return Iterator Influencer data
     */
    public Iterator<Influencer> iterator()
    {
        return new Iterator<Influencer>() {

            private Node current = head;

            @Override
            public boolean hasNext()
            {
                return current != null;
            }


            @Override
            public Influencer next()
            {
                if (!hasNext())
                {
                    throw new NoSuchElementException();
                }
                Influencer data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}
