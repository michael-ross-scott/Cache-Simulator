import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author sctmic012
 */
public class simulation {
    
    
    
    /**
    * Allows user to input values into the program
    */
    
    public static void main(String [] args) throws FileNotFoundException, IOException
    {
        System.out.println("Welcome to Michael's Cache Simulation");
        
        System.out.println("\nAvailable files to read are as follows:\n");
        
        fileHandling files = new fileHandling();
        
        System.out.println(files.toString());
        
        System.out.println("\nEnter the desired file index (e.g. [0]) to read from:\n");
        
        Scanner in = new Scanner(System.in);
        
        String index = in.nextLine();
        
        String file = files.findFile(index);
        
        System.out.println("\nPlease specify if you would like to use l2 cache for the simulation (y/n)\n");
        
        String answer = in.nextLine();
        
        //if the user only wants to use l1 cache or l1 and l2 cache
        
        if(answer.substring(0, 1).equalsIgnoreCase("n")) 
        {
            System.out.println("\nNow enter the desired block size for l1 cache "
                    + "and its corresponding bytes per block\n");

            String line1 = in.nextLine();
            String [] level1 = line1.split(" ");

            int blockSizeL1 = Integer.parseInt(level1[0]);
            int bytesPerBlockL1 = Integer.parseInt(level1[1]);
            
            simulator s = new simulator(file,blockSizeL1,bytesPerBlockL1);
            
            System.out.println("\n----------Output----------\n");

            s.read1();

            files.printCacheOutput1(s,file);
            
            System.out.println(s.toString1());
        }
        else if(answer.substring(0, 1).equalsIgnoreCase("y"))
        {
            System.out.println("\nNow enter the desired block size for l1 cache "
                    + "and its corresponding bytes per block\n");

            String line1 = in.nextLine();
            String [] level1 = line1.split(" ");

            int blockSizeL1 = Integer.parseInt(level1[0]);
            int bytesPerBlockL1 = Integer.parseInt(level1[1]);

            System.out.println("\nNow enter the desired block size for l2 cache "
                    + "and its corresponding bytes per block\n");

            String line2 = in.nextLine();
            String [] level2 = line2.split(" ");

            int blockSizeL2 = Integer.parseInt(level2[0]);
            int bytesPerBlockL2 = Integer.parseInt(level2[1]);

            simulator s = new simulator(file,blockSizeL1,bytesPerBlockL1,blockSizeL2,bytesPerBlockL2);
            
            System.out.println("\n----------Output----------\n");

            s.read2();
            
            files.printCacheOutput2(s,file);
            
            System.out.println(s.toString2());
        } 
    }
}
