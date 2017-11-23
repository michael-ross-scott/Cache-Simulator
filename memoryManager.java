/**
 *
 * @author sctmic012
 */

public class memoryManager 
{
    private final cache l1Cache;
    
    private final cache l2Cache;
 
    private final int l1HitPenalty = 10;
    private final int l2HitPenalty = 100;
    private final int memoryFetchPenalty = 1000;
    
    private int cycles = 0;
    
    private int l1Hits = 0;
    private int l1Misses = 0;
    
    private int l2Hits = 0;
    private int l2Misses = 0;
    
    public memoryManager(int l1BS, int l1BP, int l2BS, int l2BP)
    {
        l1Cache = new cache(l1BS, l1BP);
        l2Cache = new cache(l2BS, l2BP);
    }
    
    public memoryManager(int l1BS, int l1BP)
    {
        l1Cache = new cache(l1BS,l1BP);
        l2Cache = null;
    }
    
    /**
     * Checks if address is in l1 cache only
    */
    
    public String readIn1(String address)
    {
        boolean l1HasAddress = l1Cache.inCache(address);
        
        if(l1HasAddress)
        {
            l1Hits = l1Hits + 1;
            
            cycles = cycles + l1HitPenalty;
            
            System.out.println(address + " is in l1\n");
            return address + " is in l1\n";
        } 
        else 
        {
            l1Misses = l1Misses + 1;
            
            cycles = cycles + memoryFetchPenalty;
            
            System.out.println("fetching " + address + " from memory\n");
            return "fetching " + address + " from memory\n";
        }
    }
    
    /**
     * Checks if address is in l1 cache or in l2 cache
    */
    
    public String readIn2(String address)
    {
        boolean l1HasAddress = l1Cache.inCache(address);
        boolean l2HasAddress = l2Cache.inCache(address);
        
        if(l1HasAddress && l2HasAddress)
        {
            l1Hits = l1Hits + 1;
            
            cycles = cycles + l1HitPenalty;
            
            //System.out.println(address + " is in l1\n");
            return address + " is in l1\n";
        } 
        else if(l2HasAddress)
        {
            l2Hits = l2Hits + 1;
            l1Misses = l1Misses + 1;
            
            cycles = cycles + l2HitPenalty;
            
            //System.out.println(address + " is in L2\n");
            return address + " is in L2\n";
        }
        else
        {
            l1Misses = l1Misses + 1;
            l2Misses = l2Misses + 1;
            
            cycles = cycles + memoryFetchPenalty;
            
            //System.out.println("fetching " + address + " from memory\n");
            return "fetching " + address + " from memory\n";
        }
    }
    
    /**
    * Methods below used to get hits, misses + total cycles of L1 & L2 cache
    */
    
    public int getCycles()
    {
        return cycles;
    }
    
    public int getL1Hits()
    {
        return l1Hits;
    }
    
    public int getL1Misses()
    {
        return l1Misses;
    }
    
    public int getL2Hits()
    {
        return l2Hits;
    }
    
    public int getL2Misses()
    {
        return l2Misses;
    }
    
    /**
    * This method gets percentage of L1 hits
    */
    
    public double getPercentageOfL1Hits()
    {
        int l1Total = l1Hits + l1Misses;
        double percentage = (l1Hits * 100) / l1Total;
        return percentage;
    }
    
    /**
    * This method gets percentage of L2 hits
    */
    
    public double getPercentageOfL2Hits()
    {
        int l2Total = l2Hits + l2Misses;
        double percentage = (l2Hits * 100) / l2Total;
        return percentage;
    }
    
    /*
    * This method calculates the cycles per instruction of L2
    */
    
    public double getCPIL2()
    {
        double effCPI = 1 + (l2Hits * l2HitPenalty + l2Misses * 1000)/(l2Hits + l2Misses);
        return effCPI;
    }
    
    
    /*
    * This method calculates the cycles per instruction of L1
    */
    
    public double getCPIL1()
    {
        double effCPI = 1 + (l1Hits * l1HitPenalty + l1Misses * 1000)/(l1Hits + l1Misses);
        return effCPI;
    }
    
    /**
    * This method calculates the performance ratio between L1 & L2 cache
    */
    
    public String getPerformanceRatio()
    {
        double CPIL1 = getCPIL1();
        double CPIL2 = getCPIL2();
        
        if(CPIL1>CPIL2)
        {
            return "Performance ratio l1 v l2 cache is:" + (CPIL1 / CPIL2);
        }
        
        return "Performance ratio l2 v l1 cache is:" + (CPIL2 / CPIL1);
    }
    
    /**
    * "Quality of life" methods used to write output to files later
    */
    
    public String [] getL1Cache()
    {
        return l1Cache.ToString();
    }
    
    public String [] getL1Addresses()
    {
        return l1Cache.Addresses();
    }
    
    public int [] getL1Count()
    {
        return l1Cache.count();
    }
    
    public String [] getL2Cache()
    {
        return l2Cache.ToString();
    }
    
    public String [] getL2Addresses()
    {
        return l2Cache.Addresses();
    }
    
    public int [] getL2Count()
    {
        return l2Cache.count();
    }
}
