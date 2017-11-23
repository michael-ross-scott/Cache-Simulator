import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author sctmic012
 */

public class simulator 
{
    
    private final String fileName;
    private final memoryManager m;
    
    /**
    * Constructor for l1 & l2 cache
    */
    
    public simulator(String fileName, int l1BP, int l1BS, int L2BP, int L2BS)
    {
        this.fileName = fileName;
        m = new memoryManager(l1BP,l1BS,L2BP,L2BS);
    }
    
    /**
    * Constructor for just l1 cache
    */
    
    public simulator(String fileName, int l1BP, int l1BS)
    {
        this.fileName = fileName;
        m = new memoryManager(l1BP,l1BS);
    }
    
    /**
    * This method reads a destination file with addresses separated by new lines,
    * it then sends them into the memoryManager which checks if they are in cache,
    * this check includes l1 and l2 cache
    */
    
    public void read1() throws FileNotFoundException
    {
        Scanner sc = new Scanner(new File(fileName));
        
        while(sc.hasNextLine())
        {
            String hexAddress = sc.nextLine();
            
            System.out.println(m.readIn1(hexAddress));
        }
    }
    
    /**
    * This method reads a destination file with addresses separated by new lines,
    * it then sends them into the memoryManager which checks if they are in cache,
    * this check includes only l1 cache
    */
    
    public void read2() throws FileNotFoundException
    {
        Scanner sc = new Scanner(new File(fileName));
        
        while(sc.hasNextLine())
        {
            String hexAddress = sc.nextLine();
            
            System.out.println(m.readIn2(hexAddress));
        }
    }
    
    /**
    * returns l1 hits and misses
    */
    
    public String base()
    {
        return "L1 hits: "+ m.getL1Hits() + "\n" +
               "L1 misses: "+ m.getL1Misses() + "\n" +
               "L1 hit percentage: "+ m.getPercentageOfL1Hits() + " %\n";
    }
    
    /**
    * returns base & CPU cycles
    */
    
    public String toString1()
    {
        return base() +
               "\n" + 
               "Cycles: " + m.getCycles() + "\n" +
               "CPI: " + m.getCPIL1();
    }
    
    /**
    * returns base, l2 hits and misses & CPU cycles
    */
    
    public String toString2()
    {
        return base() +
               "\n" + 
               "L2 hits: " + m.getL2Hits() + "\n" +
               "L2 misses: "+m.getL2Misses() + "\n" +
               "L2 hit percenatge: " + m.getPercentageOfL2Hits() + " %\n" +
               "\n" +
               "CPI L1: " + m.getCPIL1() + "\n" +
               "CPI L2: " + m.getCPIL2() + "\n" +
               "Cycles: " + m.getCycles() + "\n" +
                m.getPerformanceRatio();
    }
    
    /**
    * This method gets CPI for L1 
    */
    
    public String getL1CPI()
    {
        return "CPI: "+m.getCPIL1();
    }
    
    /**
    * This method gets CPI for L2
    */
    
    public String getL2CPI()
    {
        return "CPI: "+m.getCPIL2();
    }
    
    /**
    * Methods below get Strings to be written to the file later
    */
    
    public String [] getL1Performance()
    {
        String [] temp = new String[] {"L1 hits: "+ m.getL1Hits(),
            "L1 misses: "+ m.getL1Misses(),
            "L1 hit percentage: "+ m.getPercentageOfL1Hits()};
        return temp;
    }
    
    public String [] getL2Performance()
    {
        String [] temp = new String[] {"L2 hits: "+ m.getL2Hits(),
            "L2 misses: "+ m.getL2Misses(),
            "L2 hit percentage: "+ m.getPercentageOfL2Hits()};
        return temp;
    }
    
    public String [] getl1Cache()
    {
        return m.getL1Cache();
    }
    
    public String [] getL1Addresses()
    {
        return m.getL1Addresses();
    }
    
    public int [] getL1Count()
    {
        return m.getL1Count();
    }
    
    public String [] getl2Cache()
    {
        return m.getL2Cache();
    }
    
    public String [] getL2Addresses()
    {
        return m.getL2Addresses();
    }
    
    public int [] getL2Count()
    {
        return m.getL2Count();
    }
    
    public String getCycles()
    {
        return "Cycles: " + m.getCycles();
    }
    
    public String getPerformanceRatio()
    {
        return m.getPerformanceRatio();
    }
}
