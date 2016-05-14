/*
    MyThread.java
    This class is the thread class that performs partial task
    It has a constructor that takes following parameters
    n - Size of array
    arr - the array to search from
    threadCount - the number of threads to that are going to be created
    val - the value to search within the array
*/

package project;


public class MyThread extends Thread {
    static int n;
    int[] arr;
    static int beg, end, each, my_rank, val;
    static int count = 0;
    static boolean displayOnlyOnce = true;
    static int displayCount;
    public MyThread(int n, int[] arr, int threadCount, int val) {
        my_rank = count;                           // first thread that starts is given rank0
        count++;
        if(count >= threadCount) count = 0;
        this.n = n;
        this.arr = arr;
        each = (int) Math.floor(n/threadCount);   //partition n operations among threads, each gets n/threadCount tasks to perform
        beg = my_rank * each;                     // beginning for each thread
        
            
        end = (beg + each - 1  );                         // end count for each thread
        if(my_rank == threadCount - 1)
            end = n  ;                              // Last thread will handle more work than other threads
                                                  // probability is that element will be found before this thread 
        
        this.val = val;                           // value to be searched
        if(Globals.shouldDisplay)                 // display the information for only one search operation, for n threds, so it is easier to read
            displayInfo();
    }
    void displayInfo()                            // This method displays information for each thread
    {
        System.out.println("--------------------------------------------------");
        System.out.println("n: "+ n);
        System.out.println("beg: " + beg);
        System.out.println("end: "+ end);
        System.out.println("each: " + each);
        System.out.println("val: " + val);
        System.out.println("my_rank:" + my_rank);
        System.out.println("--------------------------------------------------");
        
    }
    
    
    
    @Override
    public void  run()
    {
        

        for(int i = beg; i < end; i++)
        {
            //if(my_rank == 0)
                
            //System.out.print(arr[i] + " ");
                //if (Globals.found == true) break;    // If value already found by other thread, then no need to perform search
            if(arr[i] == val )                      // if value found in array, stop the serch operation and update the status variable
            {
                                
                System.out.println("Value found by thread: " + my_rank); // Display the thread that found the value
                Globals.found = true;                // set status to true
                       
                break;                             // stop the search operation
            }
        }
    }
    
    
} // end of MyThread class
