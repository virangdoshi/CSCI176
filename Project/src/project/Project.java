/*

This program is a comparison between serial and parallel version of linear search algorithm
This code includes serial serial code, followed by parallel version of the same

Time is calculated for serial and parallel code and comparion is made

To increase the number of operations, to perform tests, parallel and serial search is performed 999 times
To test
1) Generate a random array of size n
2) Generate a random array of 999 elements to search from
3) Perform 999 linear searches
4) Perform 999 parallel serches. For each search : divide n by number of threads
   
Observe time difference between the two different algorithms

Sample observation
1) Parallel algorithm runs slower when n < 25000
2) For n = 25000, the time is almost the same
3) For n > 25000, parallel algorithm runs significantly faster
4) Time difference grows faster when n increases

Reading from an array in Java is thread-safe, if the data is not modified
So, no safety mechanism is needed

Package name project
To compile
$>javac package/*.java

To run
$>java package.project

Input -> Array size and number of threads
Example
Enter array size: 
1000000
Enter thread count:
4

Output -> Time elapsed for serial and parallel algorithm


 */
package project;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Virang
 */

/*
    Project.java
    This is the main class 
    It is responsible for performing the linear search and creating threads to perform parallel search
    It takes 
*/
public class Project {

    static int arr[];
    static int searchArray[];
    public static void main(String[] args) {
        
        int n = 0;                            // count for the size of array
        int threadCount = 0;                  // number of threads to run
        double startSerial, startParallel, endSerial, endParallel; // to calculate elapsed time
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter array size:");
        n = scanner.nextInt();                 // Input size of array from user
        //n = 1000000;                             // for test
        //n = 234342;                            // for test
        System.out.println("Enter thread count");
        threadCount = scanner.nextInt();      // Input thread count from user
        //threadCount = 5;                        // for test
        //threadCount = 4;                        // for test
        arr = new int[n];
        searchArray = new int[999];
        Random random = new Random(System.currentTimeMillis()); // seed the random number generator with current time
        for(int i = 0; i < n; i++)
            arr[i] = Math.abs(random.nextInt(10000000));       // Fill the array to be searched with random numbers in the range of 0-10000000
        
        for(int i = 0; i < 999; i++)
            searchArray[i] = Math.abs(random.nextInt(10000000));
        
        int searchThis;

            
            startSerial = System.currentTimeMillis();
            for(int count = 0; count < 999; count++) {
                for(int i = 0; i < n; i++)
                {
                    if(arr[i] == searchArray[count])            // if value found
                    {
                        
                        break;
                    
                    }
                }
            }
            endSerial = System.currentTimeMillis();
            startParallel = System.currentTimeMillis();
            for(int count = 0; count < 999; count++) {
                searchThis = Math.abs(random.nextInt(100000000));
                if(count != 0) Globals.shouldDisplay = false;
                
                for(int i = 0; i < threadCount; i++)
                {
                    MyThread thread = new MyThread(n, arr, threadCount, searchArray[count]);
                    
                    thread.start();
                    if(Globals.found == true)
                        break;                                     // if search successful, then no need to create other threads
                }
                
            }
            endParallel = System.currentTimeMillis();
            
            
            
            System.out.println("Serial Time: " +(endSerial - startSerial));
            System.out.println("Parallel Time: " +(endParallel - startParallel));
        
        
        
    }
    
}
