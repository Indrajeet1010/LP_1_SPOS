
//FIFO PAGE REPLACEMENT ALGO

//import java.util.HashSet;
//import java.util.LinkedList;
//import java.util.Queue;
//
//
//class Test
//{
//    // Method to find page faults using FIFO
//    static int pageFaults(int pages[], int n, int capacity)
//    {
//        // To represent set of current pages. We use
//        // an unordered_set so that we quickly check
//        // if a page is present in set or not
//        HashSet<Integer> s = new HashSet<>(capacity);
//
//        // To store the pages in FIFO manner
//        Queue<Integer> indexes = new LinkedList<>() ;
//
//        // Start from initial page
//        int page_faults = 0;
//        for (int i=0; i<n; i++)
//        {
//            // Check if the set can hold more pages
//            if (s.size() < capacity)
//            {
//                // Insert it into set if not present
//                // already which represents page fault
//                if (!s.contains(pages[i]))
//                {
//                    s.add(pages[i]);
//
//                    // increment page fault
//                    page_faults++;
//
//                    // Push the current page into the queue
//                    indexes.add(pages[i]);
//                }
//            }
//
//            // If the set is full then need to perform FIFO
//            // i.e. remove the first page of the queue from
//            // set and queue both and insert the current page
//            else
//            {
//                // Check if current page is not already
//                // present in the set
//                if (!s.contains(pages[i]))
//                {
//                    //Pop the first page from the queue
//                    int val = indexes.peek();
//
//                    indexes.poll();
//
//                    // Remove the indexes page
//                    s.remove(val);
//
//                    // insert the current page
//                    s.add(pages[i]);
//
//                    // push the current page into
//                    // the queue
//                    indexes.add(pages[i]);
//
//                    // Increment page faults
//                    page_faults++;
//                }
//            }
//        }
//
//        return page_faults;
//    }
//
//    // Driver method
//    public static void main(String args[])
//    {
//        int pages[] = {7, 0, 1, 2, 0, 3, 0, 4,
//                2, 3, 0, 3, 2};
//
//        int capacity = 4;
//        System.out.println(pageFaults(pages, pages.length, capacity));
//    }
//}


// Java implementation of above algorithm

//LRU
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

//class Test
//{
    // Method to find page faults using indexes
//    static int pageFaults(int pages[], int n, int capacity)
//    {
//        // To represent set of current pages. We use
//        // an unordered_set so that we quickly check
//        // if a page is present in set or not
//        HashSet<Integer> s = new HashSet<>(capacity);
//
//        // To store least recently used indexes
//        // of pages.
//        HashMap<Integer, Integer> indexes = new HashMap<>();
//
//        // Start from initial page
//        int page_faults = 0;
//        for (int i=0; i<n; i++)
//        {
//            // Check if the set can hold more pages
//            if (s.size() < capacity)
//            {
//                // Insert it into set if not present
//                // already which represents page fault
//                if (!s.contains(pages[i]))
//                {
//                    s.add(pages[i]);
//
//                    // increment page fault
//                    page_faults++;
//                }
//
//                // Store the recently used index of
//                // each page
//                indexes.put(pages[i], i);
//            }
//
//            // If the set is full then need to perform lru
//            // i.e. remove the least recently used page
//            // and insert the current page
//            else
//            {
//                // Check if current page is not already
//                // present in the set
//                if (!s.contains(pages[i]))
//                {
//                    // Find the least recently used pages
//                    // that is present in the set
//                    int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE;
//
//                    Iterator<Integer> itr = s.iterator();
//
//                    while (itr.hasNext()) {
//                        int temp = itr.next();
//                        if (indexes.get(temp) < lru)
//                        {
//                            lru = indexes.get(temp);
//                            val = temp;
//                        }
//                    }
//
//                    // Remove the indexes page
//                    s.remove(val);
//                    //remove lru from hashmap
//                    indexes.remove(val);
//                    // insert the current page
//                    s.add(pages[i]);
//
//                    // Increment page faults
//                    page_faults++;
//                }
//
//                // Update the current page index
//                indexes.put(pages[i], i);
//            }
//        }
//
//        return page_faults;
//    }
//
//    // Driver method
//    public static void main(String args[])
//    {
//        int pages[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};
//
//        int capacity = 4;
//
//        System.out.println(pageFaults(pages, pages.length, capacity));
//    }
//}

    //optimal

// Java program to demonstrate optimal page
// replacement algorithm.
import java.io.*;
import java.util.*;

class GFGLP {

    // Function to check whether a page exists
    // in a frame or not
    static boolean search(int key, int[] fr)
    {
        for (int i = 0; i < fr.length; i++)
            if (fr[i] == key)
                return true;
        return false;
    }

    // Function to find the frame that will not be used
    // recently in future after given index in pg[0..pn-1]
    static int predict(int pg[], int[] fr, int pn,
                       int index)
    {
        // Store the index of pages which are going
        // to be used recently in future
        int res = -1, farthest = index;
        for (int i = 0; i < fr.length; i++) {
            int j;
            for (j = index; j < pn; j++) {
                if (fr[i] == pg[j]) {
                    if (j > farthest) {
                        farthest = j;
                        res = i;
                    }
                    break;
                }
            }

            // If a page is never referenced in future,
            // return it.
            if (j == pn)
                return i;
        }

        // If all of the frames were not in future,
        // return any of them, we return 0. Otherwise
        // we return res.
        return (res == -1) ? 0 : res;
    }

    static void optimalPage(int pg[], int pn, int fn)
    {
        // Create an array for given number of
        // frames and initialize it as empty.
        int[] fr = new int[fn];

        // Traverse through page reference array
        // and check for miss and hit.
        int hit = 0;
        int index = 0;
        for (int i = 0; i < pn; i++) {

            // Page found in a frame : HIT
            if (search(pg[i], fr)) {
                hit++;
                continue;
            }

            // Page not found in a frame : MISS

            // If there is space available in frames.
            if (index < fn)
                fr[index++] = pg[i];

                // Find the page to be replaced.
            else {
                int j = predict(pg, fr, pn, i + 1);
                fr[j] = pg[i];
            }
        }
        System.out.println("No. of hits = " + hit);
        System.out.println("No. of misses = " + (pn - hit));
    }

    // driver function
    public static void main(String[] args)
    {

        int pg[]
                = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2 };
        int pn = pg.length;
        int fn = 4;
        optimalPage(pg, pn, fn);
    }
}


