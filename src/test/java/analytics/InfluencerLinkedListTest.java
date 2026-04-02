package analytics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// -------------------------------------------------------------------------
/**
 * test class for InfluencerLinkedList
 * 
 * @author Avi Patel
 * @version Nov 18, 2025
 */
public class InfluencerLinkedListTest
{
    // ~ Fields ................................................................
    private InfluencerLinkedList l1;
    private InfluencerLinkedList l2;
    private Influencer coco;
    private Influencer john;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * sets up test cases
     */
    @BeforeEach
    public void setUp()
    {
        l1 = new InfluencerLinkedList();
        l2 = new InfluencerLinkedList();
        coco =
            new Influencer("coco12", "cocosworld", "United States", "Beauty");
        john = new Influencer("john123", "johnnyappleseed", "Canada", "Gaming");
        l2.add(coco);
        l2.add(john);

    }


    // ----------------------------------------------------------
    // ~Public Methods ........................................................
    /**
     * tests size()
     */

    @Test
    public void testSize()
    {
        assertEquals(0, l1.size());
        l1.add(coco);
        assertEquals(1, l1.size());

    }


// ----------------------------------------------------------
    /**
     * tests isEmpty()
     */
    @Test
    public void testIsEmpty()
    {
        assertTrue(l1.isEmpty());
        l1.add(coco);
        assertFalse(l1.isEmpty());
    }


// ----------------------------------------------------------
    /**
     * tests clear()
     */
    @Test
    public void testClear()
    {
        l1.add(coco);
        l1.clear();
        assertTrue(l1.isEmpty());
    }


// ----------------------------------------------------------
    /**
     * tests add()
     */
    @Test
    public void testAdd()
    {
        Exception exception = assertThrows(
            IllegalArgumentException.class,
            () -> l1.add(null));
        assertNotNull(exception);

        l1.add(coco);
        Influencer creator =
            new Influencer("creator01", "Creator Eats", "United States", "Food");
        l2.add(creator);

    }


// ----------------------------------------------------------
    /**
     * tests get()
     */
    @Test
    public void testGet()
    {
        l1.add(coco);
        assertEquals(coco, l1.get(0));
        assertEquals(john, l2.get(1));

        Exception exception = assertThrows(
            IndexOutOfBoundsException.class,
            () -> l2.get(-2));
        assertNotNull(exception);

        Exception exception2 = assertThrows(
            IndexOutOfBoundsException.class,
            () -> l2.get(5));
        assertNotNull(exception2);
    }


// ----------------------------------------------------------
    /**
     * tests findByChannelName()
     */
    @Test
    public void testFindByChannelName()
    {
        l1.add(coco);
        assertNull(l1.findByChannelName(null));

        InfluencerLinkedList empty = new InfluencerLinkedList();
        assertNull(empty.findByChannelName("nothing"));
        assertEquals(coco, l1.findByChannelName("cocosworld"));
        assertEquals(john, l2.findByChannelName("johnnyappleseed"));
    }


// ----------------------------------------------------------
    /**
     * tests sort(), insertionSort(), and insertInOrder()
     */
    @Test
    public void testSort()
    {
        Influencer creator =
            new Influencer("creator01", "Creator Eats", "United States", "Food");
        l2.add(creator);
        ChannelNameComparator compByChannel = new ChannelNameComparator();
        l2.sort(compByChannel);

        assertEquals(coco, l2.get(0));
        assertEquals(creator, l2.get(1));
        assertEquals(john, l2.get(2));

        InfluencerLinkedList l3 = new InfluencerLinkedList();
        l3.add(creator);
        l3.add(john);
        l3.add(coco);
        l3.sort(null);

        // should do nothing because comparator is null
        assertEquals(creator, l3.get(0));
        assertEquals(john, l3.get(1));
        assertEquals(coco, l3.get(2));

        // tests for if linked list size is <=1
        InfluencerLinkedList empty = new InfluencerLinkedList();
        empty.sort(compByChannel);
        assertEquals(0, empty.size());

        l1.add(coco);
        l1.sort(compByChannel);
        assertEquals(coco, l1.get(0));

        Influencer luna =
            new Influencer("lunamoon", "lunalove", "United States", "Beauty");
        l3.add(luna);
        l3.sort(compByChannel);
        assertEquals(coco, l3.get(0));
        assertEquals(creator, l3.get(1));
        assertEquals(john, l3.get(2));
        assertEquals(luna, l3.get(3));

    }


// ----------------------------------------------------------
    /**
     * tests iterator()
     */
    @Test
    public void testIterator()
    {
        // tests if iterates in correct order
        InfluencerLinkedList empty = new InfluencerLinkedList();
        Iterator<Influencer> iterempty = empty.iterator();
        assertFalse(iterempty.hasNext());

        l1.add(coco);
        Iterator<Influencer> iterl1 = l1.iterator();
        assertTrue(iterl1.hasNext());
        assertEquals(coco, iterl1.next());
        assertFalse(iterl1.hasNext());

        Iterator<Influencer> iterl2 = l2.iterator();
        assertTrue(iterl2.hasNext());
        assertEquals(coco, iterl2.next());
        assertTrue(iterl2.hasNext());
        assertEquals(john, iterl2.next());
        assertFalse(iterl2.hasNext());

        // tests if throws NoSuchElementException

        Exception exception = assertThrows(
            NoSuchElementException.class,
            iterl1::next);
        assertNotNull(exception);

    }

}
